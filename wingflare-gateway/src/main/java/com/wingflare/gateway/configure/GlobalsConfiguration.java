package com.wingflare.gateway.configure;


import com.wingflare.gateway.filter.SessionInitializationFilter;
import com.wingflare.lib.core.constants.HttpHeader;
import com.wingflare.lib.spring.configure.properties.CorsProperties;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import jakarta.annotation.Resource;

/**
 * @author naizui_ycx
 * @date {2022/2/8}
 * @description 全局过滤器配置
 */
@Configuration
public class GlobalsConfiguration {

    @Resource
    private CorsProperties corsProperties;

    @Resource
    private Tracer tracer;

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            ServerHttpResponse response = ctx.getResponse();
            HttpHeaders responseHeaders = response.getHeaders();
            Span span = tracer.currentSpan();

            if (span != null) {
                responseHeaders.add(HttpHeader.X_RESPONSE_ID, span.context().traceId());
            }

            if (CorsUtils.isCorsRequest(request)) {
                String origin = request.getHeaders().getOrigin();

                for (String domain : corsProperties.getDomains()) {
                    if (domain.equalsIgnoreCase(origin)) {
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_ALLOW_ORIGIN, origin);
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_ALLOW_HEADERS, corsProperties.getAllowHeaders());
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_EXPOSE_HEADERS, corsProperties.getExposeHeaders());
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_ALLOW_METHODS, corsProperties.getAllowMethods());
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_ALLOW_CREDENTIALS, corsProperties.getAllowCredentials());
                        break;
                    }
                }

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }

            return chain.filter(ctx);
        };
    }


    @Bean
    @ConditionalOnProperty(
            name = "session.enable",
            havingValue = "true"
    )
    public SessionInitializationFilter sessionInitializationFilter() {
        return new SessionInitializationFilter();
    }

}
