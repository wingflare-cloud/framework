package com.wingflare.gateway.configure;


import com.wingflare.gateway.filter.CorsGatewayFilterFactory;
import com.wingflare.gateway.filter.SessionGatewayFilterFactory;
import com.wingflare.lib.core.constants.HttpHeader;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import jakarta.annotation.Resource;

/**
 * @author naizui_ycx
 * @date {2022/2/8}
 * @description 全局过滤器配置
 */
@Configuration
public class GlobalsConfiguration {

    @Resource
    private Tracer tracer;

    @Bean
    public WebFilter globalFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpResponse response = ctx.getResponse();
            HttpHeaders responseHeaders = response.getHeaders();
            Span span = tracer.currentSpan();

            if (span != null) {
                responseHeaders.add(HttpHeader.X_RESPONSE_ID, span.context().traceId());
            }

            return chain.filter(ctx);
        };
    }

    @Bean
    public CorsGatewayFilterFactory corsGatewayFilterFactory() {
        return new CorsGatewayFilterFactory();
    }


    @Bean
    @ConditionalOnProperty(
            name = "session.enable",
            havingValue = "true"
    )
    public SessionGatewayFilterFactory sessionInitializationFilter() {
        return new SessionGatewayFilterFactory();
    }

}
