package com.wingflare.gateway.configure;


import com.wingflare.abstraction.security.SecurityCheckUser;
import com.wingflare.gateway.filter.AuthGatewayFilterFactory;
import com.wingflare.lib.jwt.AuthTool;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(SecurityCheckUser.class)
public class AuthFilterLoad {

    @Bean
    public AuthGatewayFilterFactory authFilter(AuthTool authTool) {
        return new AuthGatewayFilterFactory(authTool);
    }

}
