package com.wingflare.adapter.spring.servlet.session;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * 启用Session兼容过滤器
 */
public class WingFlareHttpSession {

    @Value("session.headerName:'X-SID'")
    private String sessionHeaderName;

    @Bean
    public HttpSessionIdResolver compositeResolver() {
        return new CompositeSessionIdResolver(
                new CookieHttpSessionIdResolver(),
                new HeaderHttpSessionIdResolver(sessionHeaderName)
        );
    }

}
