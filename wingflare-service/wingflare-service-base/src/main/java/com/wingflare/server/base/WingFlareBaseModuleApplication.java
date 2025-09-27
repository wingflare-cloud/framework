package com.wingflare.server.base;


import com.wingflare.adapter.spring.common.annotation.BaseConfig;
import com.wingflare.api.core.annotation.MicroserviceMode;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@BaseConfig
@MicroserviceMode
@SpringBootApplication
@MapperScan("com.wingflare.business.base.mapper")
public class WingFlareBaseModuleApplication {

    public static void main(String[] args) {
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
            SpringApplication application = new SpringApplication(WingFlareBaseModuleApplication.class);
            application.setBannerMode(Banner.Mode.OFF);
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
