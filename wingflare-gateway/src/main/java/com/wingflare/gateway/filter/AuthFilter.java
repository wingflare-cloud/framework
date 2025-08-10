package com.wingflare.gateway.filter;

import com.wingflare.gateway.R;
import com.wingflare.gateway.utils.WebFluxUtil;
import com.wingflare.lib.core.exceptions.RiskException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.jwt.AuthTool;
import com.wingflare.lib.jwt.ErrorCode;
import com.wingflare.lib.security.properties.AuthProperties;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.Std;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
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
public class AuthFilter implements GlobalFilter, Ordered {

    @Resource
    private AuthProperties authProperties;

    @Resource
    private AuthTool authTool;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 跳过无需认证的路径前缀
        if (!StringUtil.urlMatches(request.getURI().getPath(), authProperties.getPathPrefix())) {
            return chain.filter(exchange);
        }

        String token = getToken(request);
        String businessSystem = getBusinessSystem(request);

        // 响应式封装同步认证调用
        return Mono.fromCallable(() -> authTool.checkLogin(token, businessSystem))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(authResponseDTO -> {
                    if (StringUtil.isNotBlank(authResponseDTO.getError())) {
                        // 白名单路径直接放行
                        if (StringUtil.urlMatches(request.getURI().getPath(), authProperties.getWhites())) {
                            return chain.filter(exchange);
                        }

                        // 处理Token过期特殊场景
                        if (StringUtil.equals(authResponseDTO.getError(), ErrorCode.TOKEN_EXPIRATION)) {
                            if (StringUtil.isNotEmpty(authProperties.getRefreshTokenUrl()) &&
                                    !StringUtil.isMatch(authProperties.getRefreshTokenUrl(), request.getURI().getPath())) {
                                return unauthorizedResponse(exchange, authResponseDTO.getError());
                            }
                        } else {
                            return loginLostResponse(exchange, authResponseDTO.getError());
                        }
                    }

                    String clientId = exchange.getAttribute(Ctx.CONTEXT_KEY_CLIENT_ID);

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
                            .header(Ctx.HEADER_KEY_AUTH_USER, SecurityUtil.typeValueEncode(authResponseDTO.getUserAuth()))
                            .build();
                    exchange.getAttributes().put(Ctx.CONTEXT_KEY_AUTH_USER, authResponseDTO.getUserAuth());

                    return chain.filter(
                            exchange.mutate().request(mutatedRequest).build()
                    );
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

    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(Ctx.AUTHENTICATION);
        if (StringUtil.isNotEmpty(token) && token.startsWith(authProperties.getAuthenticationPrefix())) {
            return token.replaceFirst(authProperties.getAuthenticationPrefix(), StringUtil.EMPTY);
        }
        return token;
    }

    private String getBusinessSystem(ServerHttpRequest request) {
        return request.getHeaders().getFirst(Ctx.HEADER_KEY_BUSINESS_SYSTEM);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 4;
    }
}