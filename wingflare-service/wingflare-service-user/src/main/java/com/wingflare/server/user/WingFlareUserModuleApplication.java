package com.wingflare.server.user;


import com.wingflare.adapter.spring.common.annotation.BaseConfig;
import com.wingflare.api.core.annotation.MicroserviceMode;
import com.wingflare.api.security.annotation.EnableDataSecret;
import com.wingflare.api.security.annotation.EnableDataSensitive;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@BaseConfig
@EnableDataSensitive
@EnableDataSecret
@MicroserviceMode
@SpringBootApplication
@MapperScan("com.wingflare.business.user.mapper")
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
