package com.wingflare.gateway.filter;

import com.wingflare.gateway.utils.WebFluxUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.configure.properties.SystemContextProperties;
import com.wingflare.lib.standard.Ctx;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import jakarta.annotation.Resource;

import java.util.Set;

/**
 * 全局安全信息头处理过滤器（优化非阻塞版）
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Component
public class SecHeaderFilter implements GlobalFilter, Ordered {

    @Resource
    private SystemContextProperties systemContextProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String secTokenKey = StringUtil.isNotEmpty(systemContextProperties.getSecTokenCtxKey()) ?
                systemContextProperties.getSecTokenCtxKey() : Ctx.HEADER_KEY_SEC_TOKEN;
        String secToken = request.getHeaders().getFirst(secTokenKey);
        Set<String> allowedHeaders = systemContextProperties.getClientTransferCtx();

        // 使用响应式处理器决定是否执行header移除
        return Mono.just(secToken == null ? "" : secToken)
                .filter(this::shouldProcessHeaders)
                .flatMap(token -> Mono.fromRunnable(() -> removeUntrustedHeaders(mutate, allowedHeaders)))
                .then(chain.filter(exchange));
    }

    private boolean shouldProcessHeaders(String secToken) {
        // 如果secToken匹配或为空，则跳过header处理
        if (StringUtil.isEmpty(secToken) || StringUtil.isEmpty(systemContextProperties.getSecToken())) {
            return false;
        }
        return !systemContextProperties.getSecToken().equals(secToken);
    }

    private void removeUntrustedHeaders(ServerHttpRequest.Builder mutate, Set<String> allowedHeaders) {
        // 在弹性线程中执行header移除操作
        systemContextProperties.getGlobalCtx().forEach((key, value) -> {
            if (!allowedHeaders.contains(key)) {
                WebFluxUtil.removeHeader(mutate, key);
            }
        });
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 5;
    }

}