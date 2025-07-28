package com.wingflare.gateway.filter;


import com.wingflare.gateway.R;
import com.wingflare.gateway.utils.WebFluxUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.jwt.AuthTool;
import com.wingflare.lib.jwt.ErrorCode;
import com.wingflare.lib.security.properties.AuthProperties;
import com.wingflare.lib.standard.AuthResponseDTO;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import jakarta.annotation.Resource;


/**
 * @author naizui_ycx
 * @date {2021/01/02}
 * @description 身份认证过滤器
 */
public class AuthFilter implements GlobalFilter, Ordered {

    @Resource
    private AuthProperties authProperties;

    @Resource
    private AuthTool authTool;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) throws RuntimeException {
        ServerHttpRequest request = exchange.getRequest();

        if (!StringUtil.urlMatches(request.getURI().getPath(), authProperties.getPathPrefix())) {
            ServerHttpRequest.Builder mutate = request.mutate();

            AuthResponseDTO authResponseDTO = authTool.checkLogin(getToken(request), getBusinessSystem(request));

            if (StringUtil.isNotBlank(authResponseDTO.getError())) {
                if (!StringUtil.urlMatches(request.getURI().getPath(), authProperties.getWhites())) {
                    if (StringUtil.equals(authResponseDTO.getError(), ErrorCode.TOKEN_EXPIRATION)) {
                        if (StringUtil.isNotEmpty(authProperties.getRefreshTokenUrl()) && !StringUtil.isMatch(authProperties.getRefreshTokenUrl(), request.getURI().getPath())) {
                            return unauthorizedResponse(exchange, authResponseDTO.getError());
                        }
                    } else {
                        return loginLostResponse(exchange, authResponseDTO.getError());
                    }
                }

                return chain.filter(exchange.mutate().request(mutate.build()).build());
            } else {
                WebFluxUtil.addHeader(mutate, authProperties.getRemoteContextName(),
                        SecurityUtil.typeValueEncode(authResponseDTO.getUserAuth()));
            }
        }

        return chain.filter(exchange);
    }


    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        return WebFluxUtil.writeJSON(
                exchange.getResponse(), HttpStatus.OK, R.fail(HttpStatus.UNAUTHORIZED.value(), msg));
    }

    private Mono<Void> loginLostResponse(ServerWebExchange exchange, String msg) {
        return WebFluxUtil.writeJSON(
                exchange.getResponse(), HttpStatus.OK, R.fail(HttpStatus.PAYMENT_REQUIRED.value(), msg));
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(Ctx.AUTHENTICATION);

        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtil.isNotEmpty(token) && token.startsWith(authProperties.getAuthenticationPrefix())) {
            token = token.replaceFirst(authProperties.getAuthenticationPrefix(), StringUtil.EMPTY);
        }

        return token;
    }

    /**
     * 获取请求token
     */
    private String getBusinessSystem(ServerHttpRequest request) {
        return request.getHeaders().getFirst(Ctx.HEADER_KEY_BUSINESS_SYSTEM);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }

}
