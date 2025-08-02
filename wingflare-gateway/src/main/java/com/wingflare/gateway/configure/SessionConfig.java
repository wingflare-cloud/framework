package com.wingflare.gateway.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.DefaultWebSessionManager;
import org.springframework.web.server.session.WebSessionIdResolver;

@Configuration
public class SessionConfig {

    @Bean
    public WebSessionIdResolver sessionIdResolver() {
        return new CustomSessionIdResolver();
    }

    @Bean
    public DefaultWebSessionManager webSessionManager(WebSessionIdResolver sessionIdResolver) {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        manager.setSessionIdResolver(sessionIdResolver);
        return manager;
    }
}
