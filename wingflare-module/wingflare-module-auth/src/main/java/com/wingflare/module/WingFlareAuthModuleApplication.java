package com.wingflare.module;

import com.wingflare.lib.security.annotation.EnableDataSecret;
import com.wingflare.lib.security.annotation.EnableDataSensitive;
import com.wingflare.lib.spring.annotation.BaseConfig;
import com.wingflare.lib.spring.annotation.MicroserviceMode;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@BaseConfig
@EnableDataSensitive
@EnableDataSecret
@MicroserviceMode
@SpringBootApplication
public class WingFlareAuthModuleApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WingFlareAuthModuleApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
