package com.wingflare.lib.jwt.filter;

import com.alibaba.fastjson.JSONObject;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.jwt.AuthTool;
import com.wingflare.lib.jwt.ErrorCode;
import com.wingflare.lib.security.properties.AuthProperties;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.R;
import jakarta.annotation.Resource;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * 认证过滤器
 */
public class ReactiveAuthFilter implements WebFilter, Ordered {

    @Resource
    private AuthProperties authProperties;

    @Resource
    private AuthTool authTool;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
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

                    ContextHolder.set(Ctx.CONTEXT_KEY_AUTH_USER, authResponseDTO.getUserAuth());

                    return chain.filter(exchange);
                });
    }

    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(Ctx.AUTHENTICATION);

        if (StringUtil.isNotEmpty(token) && token.startsWith(authProperties.getAuthenticationPrefix())) {
            token = token.replaceFirst(authProperties.getAuthenticationPrefix(), "");
        }
        return token;
    }

    private String getBusinessSystem(ServerHttpRequest request) {
        return request.getHeaders().getFirst(Ctx.HEADER_KEY_BUSINESS_SYSTEM);
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg) {
        return writeJSON(
                exchange.getResponse(), HttpStatus.OK, R.fail(HttpStatus.UNAUTHORIZED.value(), msg));
    }

    private Mono<Void> loginLostResponse(ServerWebExchange exchange, String msg) {
        return writeJSON(
                exchange.getResponse(), HttpStatus.OK, R.fail(HttpStatus.PAYMENT_REQUIRED.value(), msg));
    }

    private static <T> Mono<Void> writeJSON(ServerHttpResponse response, HttpStatusCode httpStatus, T value) {
        response.setStatusCode(httpStatus);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.create(monoSink -> {
            try {
                DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONBytes(value));
                monoSink.success(dataBuffer);
            } catch (Throwable e) {
                monoSink.error(e);
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }

}