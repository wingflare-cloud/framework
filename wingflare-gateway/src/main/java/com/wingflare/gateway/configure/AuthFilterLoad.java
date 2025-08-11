package com.wingflare.gateway.configure;


import com.wingflare.abstraction.secuirty.starter.SecurityStarterInterface;
import com.wingflare.gateway.filter.AuthGatewayFilterFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(SecurityStarterInterface.class)
public class AuthFilterLoad {

    @Bean
    public AuthGatewayFilterFactory authFilter() {
        return new AuthGatewayFilterFactory();
    }

}
