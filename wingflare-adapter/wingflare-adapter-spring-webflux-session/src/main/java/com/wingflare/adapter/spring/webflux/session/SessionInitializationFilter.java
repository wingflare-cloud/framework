package com.wingflare.adapter.spring.webflux.session;


import com.wingflare.lib.core.utils.IPAddressUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.configure.properties.SessionProperties;
import com.wingflare.lib.spring.configure.properties.WebProperties;
import com.wingflare.lib.standard.Ctx;
import jakarta.annotation.Resource;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * session过滤器
 */
public class SessionInitializationFilter implements WebFilter, Ordered {

    @Resource
    private SessionProperties sessionProperties;

    @Resource
    private WebProperties webProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return exchange.getSession().flatMap(webSession -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!StringUtil.urlMatches(request.getURI().getPath(), sessionProperties.getResetSessionIdUrls())) {
                // 初始化新会话或校验现有会话
                if (webSession.getAttributes().isEmpty() ||
                        !webSession.getAttributes().containsKey("ip") ||
                        !webSession.getAttributes().containsKey("sid")) {
                    // 初始化新会话
                    webSession.getAttributes().put("sid", webSession.getId());
                    webSession.getAttributes().put("ip", getClientIp(exchange));
                    webSession.getAttributes().put("userAgent", request.getHeaders().getFirst("User-Agent"));

                    return processChain(exchange, chain, webSession.getId());
                } else {
                    return handleExistingSession(exchange, webSession)
                            .flatMap(shouldContinue -> shouldContinue ?
                                    processChain(exchange, chain, webSession.getAttribute("sid")) :
                                    Mono.empty());
                }
            }

            Map<String, Object> oldAttributes = webSession.getAttributes();

            return webSession.invalidate().doOnSuccess(v -> exchange.getSession().flatMap(newSession -> {
                newSession.getAttributes().putAll(oldAttributes);
                return newSession.save();
            }));
        });
    }

    // 处理过滤器链调用
    private Mono<Void> processChain(ServerWebExchange exchange, WebFilterChain chain, String sessionId) {
        exchange.getAttributes().put(Ctx.CONTEXT_KEY_CLIENT_ID, sessionId);
        return chain.filter(exchange);
    }

    // 现有会话校验
    private Mono<Boolean> handleExistingSession(ServerWebExchange exchange, WebSession webSession) {
        ServerHttpRequest request = exchange.getRequest();

        if (StringUtil.isNoneBlank(sessionProperties.getStrictModel())) {
            String sessionUserAgent = webSession.getAttribute("userAgent");
            String currentUserAgent = request.getHeaders().getFirst("User-Agent");

            if (("simple".equals(sessionProperties.getStrictModel()) || "strict".equals(sessionProperties.getStrictModel())) &&
                    !ObjectUtils.nullSafeEquals(currentUserAgent, sessionUserAgent)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return Mono.just(false);
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
