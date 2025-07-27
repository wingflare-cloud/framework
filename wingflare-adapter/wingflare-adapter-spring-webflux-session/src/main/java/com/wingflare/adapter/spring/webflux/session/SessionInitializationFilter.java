package com.wingflare.adapter.spring.webflux.session;


import com.wingflare.lib.core.exceptions.NoPermissionException;
import com.wingflare.lib.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Arrays;

/**
 * session过滤器
 */
public class SessionInitializationFilter implements WebFilter, Ordered {

    @Value("${web.trustedProxies:}")
    private String trustedProxies;

    @Value("${web.session.strictModel:}")
    private String strictModel;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return exchange.getSession()
                .flatMap(webSession -> {
                    ServerHttpRequest request = exchange.getRequest();
                    if (webSession.getAttributes().isEmpty() ||
                            !webSession.getAttributes().containsKey("userAgent")) {
                        // 初始化新会话
                        webSession.getAttributes().put("ip", getClientIp(exchange));
                        webSession.getAttributes().put("userAgent", request.getHeaders().getFirst("User-Agent"));
                        return chain.filter(exchange);
                    } else {
                        // 现有会话校验
                        if (StringUtil.isNoneBlank(strictModel)) {
                            String sessionUserAgent = (String) webSession.getAttribute("userAgent");
                            String currentUserAgent = request.getHeaders().getFirst("User-Agent");

                            if ("simple".equals(strictModel) || "strict".equals(strictModel)) {
                                if (!ObjectUtils.nullSafeEquals(currentUserAgent, sessionUserAgent)) {
                                    return Mono.error(new NoPermissionException("Illegal UserAgent"));
                                }
                            }

                            if ("strict".equals(strictModel)) {
                                String sessionIp = (String) webSession.getAttribute("ip");
                                String currentIp = getClientIp(exchange);
                                if (!ObjectUtils.nullSafeEquals(currentIp, sessionIp)) {
                                    return Mono.error(new NoPermissionException("Illegal IP"));
                                }
                            }
                        }
                        return chain.filter(exchange);
                    }
                });
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
            // X-Forwarded-For：Squid 服务代理
            String ipAddresses = headers.getFirst("X-Forwarded-For");

            if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
                // Proxy-Client-IP：apache 服务代理
                ipAddresses = headers.getFirst("Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
                // WL-Proxy-Client-IP：weblogic 服务代理
                ipAddresses = headers.getFirst("WL-Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
                // HTTP_CLIENT_IP：有些代理服务器
                ipAddresses = headers.getFirst("HTTP_CLIENT_IP");
            }

            if (ipAddresses == null || ipAddresses.isEmpty() || "unknown".equalsIgnoreCase(ipAddresses)) {
                // X-Real-IP：nginx服务代理
                ipAddresses = headers.getFirst("X-Real-IP");
            }

            return StringUtil.hasText(ipAddresses) &&
                    !"unknown".equalsIgnoreCase(ipAddresses)
                    ? ipAddresses.split(",")[0].trim()
                    : ip;
        }

        return ip;
    }

    private boolean isTrustedProxy(String clientIp) {
        // 实际项目中应结合IP白名单/网段判断
        return trustedProxies.equals("all") ||
                Arrays.asList(trustedProxies.split(",")).contains(clientIp);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
