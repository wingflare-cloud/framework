package com.wingflare.adapter.spring.webflux.session;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;


import java.util.Collections;
import java.util.List;


public class CustomSessionIdResolver implements WebSessionIdResolver {

    @Value("session.headerName:'X-SID'")
    private String sessionHeaderName;

    private final CookieWebSessionIdResolver cookieResolver = new CookieWebSessionIdResolver();

    @Override
    public List<String> resolveSessionIds(ServerWebExchange exchange) {
        // 1. 尝试从Cookie获取
        List<String> cookieSessionIds = cookieResolver.resolveSessionIds(exchange);
        if (!cookieSessionIds.isEmpty()) {
            return cookieSessionIds;
        }

        // 2. 尝试从自定义Header获取
        String headerSessionId = exchange.getRequest()
                .getHeaders()
                .getFirst(sessionHeaderName);
        if (headerSessionId != null && !headerSessionId.isBlank()) {
            return Collections.singletonList(headerSessionId.trim());
        }

        // 3. 都未找到返回空列表
        return Collections.emptyList();
    }

    @Override
    public void setSessionId(ServerWebExchange exchange, String sessionId) {
        // 写入时仍使用Cookie（可选：也可同时写入Header）
        cookieResolver.setSessionId(exchange, sessionId);
    }

    @Override
    public void expireSession(ServerWebExchange exchange) {
        // 过期处理
        cookieResolver.expireSession(exchange);
    }

}