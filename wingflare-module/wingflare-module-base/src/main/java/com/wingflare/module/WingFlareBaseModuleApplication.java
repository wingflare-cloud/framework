package com.wingflare.module;

import com.wingflare.lib.spring.annotation.BaseConfig;
import com.wingflare.lib.spring.annotation.MicroserviceMode;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@BaseConfig
@MicroserviceMode
@SpringBootApplication
public class WingFlareBaseModuleApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WingFlareBaseModuleApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
