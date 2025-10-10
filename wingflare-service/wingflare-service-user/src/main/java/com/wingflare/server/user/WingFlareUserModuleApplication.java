package com.wingflare.server.user;


import com.wingflare.lib.security.annotation.EnableDataSecret;
import com.wingflare.lib.security.annotation.EnableDataSensitive;
import com.wingflare.lib.spring.annotation.BaseConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@BaseConfig
@EnableDataSensitive
@EnableDataSecret
@MicroserviceMode
@SpringBootApplication
public class WingFlareUserModuleApplication {

    public static void main(String[] args) {
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
            SpringApplication application = new SpringApplication(WingFlareUserModuleApplication.class);
            application.setBannerMode(Banner.Mode.OFF);
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
