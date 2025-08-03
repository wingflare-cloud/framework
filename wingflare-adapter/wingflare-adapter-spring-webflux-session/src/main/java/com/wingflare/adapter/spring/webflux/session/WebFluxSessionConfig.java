package com.wingflare.adapter.spring.webflux.session;


import org.springframework.context.annotation.Bean;

import org.springframework.web.server.session.WebSessionIdResolver;


public class WebFluxSessionConfig {

    @Bean
    public WebSessionIdResolver compositeResolver() {
        return new CustomSessionIdResolver();
    }

}