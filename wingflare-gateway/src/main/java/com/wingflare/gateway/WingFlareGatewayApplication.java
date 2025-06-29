package com.wingflare.gateway;


import com.wingflare.lib.security.properties.AuthProperties;
import com.wingflare.lib.spring.configure.properties.CorsProperties;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAspectJAutoProxy(exposeProxy = true)
@EnableAsync
@Import({
        CorsProperties.class,
        AuthProperties.class,
})
@SpringBootApplication
public class WingFlareGatewayApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WingFlareGatewayApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
