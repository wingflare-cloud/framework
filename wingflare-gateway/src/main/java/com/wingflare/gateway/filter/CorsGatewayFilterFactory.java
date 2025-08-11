package com.wingflare.gateway.filter;

import com.wingflare.lib.core.constants.HttpHeader;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 跨域设置
 */
public class CorsGatewayFilterFactory extends AbstractGatewayFilterFactory<CorsGatewayFilterFactory.Config> implements Ordered {

    public CorsGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((ctx, chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            ServerHttpResponse response = ctx.getResponse();
            HttpHeaders responseHeaders = response.getHeaders();

            if (CorsUtils.isCorsRequest(request)) {
                String origin = request.getHeaders().getOrigin();

                for (String domain : config.getDomains()) {
                    if (domain.equalsIgnoreCase(origin)) {
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_ALLOW_ORIGIN, origin);
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_ALLOW_HEADERS, config.getAllowHeaders());
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_EXPOSE_HEADERS, config.getExposeHeaders());
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_ALLOW_METHODS, config.getAllowMethods());
                        responseHeaders.add(HttpHeader.RESPONSE_ACCESS_CONTROL_ALLOW_CREDENTIALS, config.getAllowCredentials());
                        break;
                    }
                }

                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }

            return chain.filter(ctx);
        });
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }


    public static class Config {

        private List<String> domains = new ArrayList<>();

        private String allowHeaders = "Origin,Content-Type,Cookie,X-CSRF-TOKEN,Accept,Authorization,X-XSRF-TOKEN,Token,X-Request-Id,Locale,Business-System";

        private String exposeHeaders = "Authorization,authenticated,X-Response-Id";

        private String allowMethods = "GET,POST,PATCH,PUT,OPTIONS,DELETE";

        private String allowCredentials = "true";


        public List<String> getDomains() {
            return domains;
        }

        public void setDomains(List<String> domains) {
            this.domains = domains;
        }

        public String getAllowHeaders() {
            return allowHeaders;
        }

        public void setAllowHeaders(String allowHeaders) {
            this.allowHeaders = allowHeaders;
        }

        public String getExposeHeaders() {
            return exposeHeaders;
        }

        public void setExposeHeaders(String exposeHeaders) {
            this.exposeHeaders = exposeHeaders;
        }

        public String getAllowMethods() {
            return allowMethods;
        }

        public void setAllowMethods(String allowMethods) {
            this.allowMethods = allowMethods;
        }

        public String getAllowCredentials() {
            return allowCredentials;
        }

        public void setAllowCredentials(String allowCredentials) {
            this.allowCredentials = allowCredentials;
        }
    }

}
