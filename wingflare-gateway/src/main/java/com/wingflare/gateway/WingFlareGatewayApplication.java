package com.wingflare.gateway;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAspectJAutoProxy(exposeProxy = true)
@EnableAsync
@SpringBootApplication
public class WingFlareGatewayApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WingFlareGatewayApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
