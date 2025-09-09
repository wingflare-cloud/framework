package com.wingflare.server.auth;


import com.wingflare.adapter.spring.common.annotation.BaseConfig;
import com.wingflare.api.core.annotation.MicroserviceMode;
import com.wingflare.api.security.annotation.EnableDataSecret;
import com.wingflare.api.security.annotation.EnableDataSensitive;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;


@BaseConfig
@EnableDataSensitive
@EnableDataSecret
@MicroserviceMode
@SpringBootApplication
public class WingFlareAuthModuleApplication {

    public static void main(String[] args) {
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
            SpringApplication application = new SpringApplication(WingFlareAuthModuleApplication.class);
            application.setBannerMode(Banner.Mode.OFF);
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
