package com.wingflare.gateway.filter;

import com.wingflare.adapter.spring.security.properties.AuthProperties;
import com.wingflare.api.core.Ctx;
import com.wingflare.api.core.Std;
import com.wingflare.gateway.R;
import com.wingflare.gateway.utils.WebFluxUtil;
import com.wingflare.lib.core.exceptions.RiskException;
import com.wingflare.lib.core.utils.SerializationUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.jwt.AuthTool;
import com.wingflare.lib.jwt.ErrorCode;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import jakarta.annotation.Resource;

import java.util.HashMap;

/**
 * @author naizui_ycx
 * @date {2021/01/02}
 * @description 身份认证过滤器（异步非阻塞优化版）
 */
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

    @Resource
    private AuthTool authTool;

    public AuthGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((ctx, chain) -> {
            ServerHttpRequest request = ctx.getRequest();

            String token = getToken(request, config);
            String businessSystem = getBusinessSystem(request);

            // 响应式封装同步认证调用
            return Mono.fromCallable(() -> authTool.checkLogin(token, businessSystem))
                    .subscribeOn(Schedulers.boundedElastic())
                    .flatMap(authResponseDTO -> {
                        if (StringUtil.urlMatches(request.getURI().getPath(), config.getPathPrefix())) {
                            if (StringUtil.isNotBlank(authResponseDTO.getError())) {
                                // 白名单路径直接放行
                                if (StringUtil.urlMatches(request.getURI().getPath(), config.getWhites())) {
                                    return chain.filter(ctx);
                                }

                                // 处理Token过期特殊场景
                                if (StringUtil.equals(authResponseDTO.getError(), ErrorCode.TOKEN_EXPIRATION)) {
                                    if (StringUtil.isNotEmpty(config.getRefreshTokenUrl()) &&
                                            !StringUtil.isMatch(config.getRefreshTokenUrl(), request.getURI().getPath())) {
                                        return unauthorizedResponse(ctx, authResponseDTO.getError());
                                    }
                                } else {
                                    return loginLostResponse(ctx, authResponseDTO.getError());
                                }
                            }
                        }

                        String clientId = ctx.getAttribute(Ctx.CONTEXT_KEY_CLIENT_ID);

                        if (authResponseDTO.getUserAuth() != null) {
                            if (StringUtil.isNotBlank(authResponseDTO.getUserAuth().getClientId())
                                    || StringUtil.isNotBlank(clientId)) {
                                if (!StringUtil.equals(authResponseDTO.getUserAuth().getClientId(), clientId)) {
                                    return Mono.error(new RiskException(Std.USER_CLIENT_ID_ERROR, new HashMap<String, Object>() {{
                                        put("user", authResponseDTO.getUserAuth());
                                        put("clientId", clientId);
                                    }}));
                                }
                            }

                            // 认证成功：添加用户认证头信息
                            ServerHttpRequest mutatedRequest = request.mutate()
                                    .header(Ctx.HEADER_KEY_AUTH_USER, SerializationUtil.typeValueEncode(authResponseDTO.getUserAuth()))
                                    .build();
                            ctx.getAttributes().put(Ctx.CONTEXT_KEY_AUTH_USER, authResponseDTO.getUserAuth());

                            return chain.filter(
                                    ctx.mutate().request(mutatedRequest).build()
                            );
                        }

                        return chain.filter(ctx);
                    });
        });
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        return WebFluxUtil.writeJSON(
                exchange.getResponse(), HttpStatus.OK, R.fail(HttpStatus.UNAUTHORIZED.value(), msg));
    }

    private Mono<Void> loginLostResponse(ServerWebExchange exchange, String msg) {
        return WebFluxUtil.writeJSON(
                exchange.getResponse(), HttpStatus.OK, R.fail(HttpStatus.PAYMENT_REQUIRED.value(), msg));
    }

    private String getToken(ServerHttpRequest request, Config config) {
        String token = request.getHeaders().getFirst(Ctx.AUTHENTICATION);
        if (StringUtil.isNotEmpty(token) && token.startsWith(config.getAuthenticationPrefix())) {
            return token.replaceFirst(config.getAuthenticationPrefix(), StringUtil.EMPTY);
        }
        return token;
    }

    private String getBusinessSystem(ServerHttpRequest request) {
        return request.getHeaders().getFirst(Ctx.HEADER_KEY_BUSINESS_SYSTEM);
    }

    public static class Config extends AuthProperties {

    }

}