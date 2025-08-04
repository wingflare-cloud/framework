package com.wingflare.gateway.filter;

import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.IPAddressUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.configure.properties.SessionProperties;
import com.wingflare.lib.spring.configure.properties.WebProperties;
import com.wingflare.lib.standard.Ctx;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;


public class SessionInitializationFilter implements GlobalFilter, Ordered {

    @Resource
    private SessionProperties sessionProperties;

    @Resource
    private WebProperties webProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return exchange.getSession().flatMap(webSession -> {
            ServerHttpRequest request = exchange.getRequest();

            if (webSession.getAttributes().isEmpty() ||
                    !webSession.getAttributes().containsKey("ip") ||
                    !webSession.getAttributes().containsKey("sid")) {
                // 初始化新会话
                webSession.getAttributes().put("sid", webSession.getId());
                webSession.getAttributes().put("ip", getClientIp(exchange));
                webSession.getAttributes().put("userAgent", request.getHeaders().getFirst("User-Agent"));

                ContextHolder.set(Ctx.CONTEXT_KEY_CLIENT_ID, webSession.getId());

                // 创建带有Session ID的请求头
                ServerHttpRequest mutatedRequest = request.mutate()
                        .header(webProperties.getClientIdCtxName(), webSession.getId())
                        .build();

                return chain.filter(
                            exchange.mutate().request(mutatedRequest).build()
                        );
            } else {
                // 现有会话校验
                return handleExistingSession(exchange, webSession)
                        .flatMap(shouldContinue -> {
                            String sid = webSession.getAttributes().containsKey("sid") ? webSession.getAttribute("sid") : "";

                            ContextHolder.set(Ctx.CONTEXT_KEY_CLIENT_ID, sid);

                            if (shouldContinue) {
                                // 创建带有Session ID的请求头
                                ServerHttpRequest mutatedRequest = request.mutate()
                                        .header(webProperties.getClientIdCtxName(), sid)
                                        .build();

                                return chain.filter(
                                        exchange.mutate().request(mutatedRequest).build()
                                );
                            } else {
                                return Mono.empty();
                            }
                        });
            }
        });
    }

    private Mono<Boolean> handleExistingSession(ServerWebExchange exchange, WebSession webSession) {
        ServerHttpRequest request = exchange.getRequest();

        if (StringUtil.isNoneBlank(sessionProperties.getStrictModel())) {
            String sessionUserAgent = webSession.getAttribute("userAgent");
            String currentUserAgent = request.getHeaders().getFirst("User-Agent");

            if ("simple".equals(sessionProperties.getStrictModel()) || "strict".equals(sessionProperties.getStrictModel())) {
                if (!ObjectUtils.nullSafeEquals(currentUserAgent, sessionUserAgent)) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return Mono.just(false);
                }
            }

            if ("strict".equals(sessionProperties.getStrictModel())) {
                String sessionIp = webSession.getAttribute("ip");
                String currentIp = getClientIp(exchange);
                if (!ObjectUtils.nullSafeEquals(currentIp, sessionIp)) {
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return Mono.just(false);
                }
            }
        }

        return Mono.just(true);
    }

    private String getClientIp(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        InetSocketAddress remoteAddress = request.getRemoteAddress();

        if (remoteAddress == null || remoteAddress.getAddress() == null) {
            return "0.0.0.0";
        }

        String ip = remoteAddress.getAddress().getHostAddress();
        if (isTrustedProxy(ip)) {
            String[] headersToCheck = {
                    "X-Forwarded-For", "Proxy-Client-IP",
                    "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "X-Real-IP"
            };

            for (String header : headersToCheck) {
                String ipAddresses = headers.getFirst(header);
                if (ipAddresses != null && !"unknown".equalsIgnoreCase(ipAddresses)) {
                    return ipAddresses.split(",")[0].trim();
                }
            }
        }
        return ip;
    }

    private boolean isTrustedProxy(String clientIp) {
        return IPAddressUtil.isIpInRange(clientIp, webProperties.getTrustedProxies());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}