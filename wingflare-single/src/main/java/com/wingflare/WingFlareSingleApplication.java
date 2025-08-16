package com.wingflare;


import com.wingflare.lib.security.annotation.EnableDataSecret;
import com.wingflare.lib.security.annotation.EnableDataSensitive;
import com.wingflare.lib.spring.annotation.BaseConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@BaseConfig
@EnableDataSensitive
@EnableDataSecret
@SpringBootApplication
public class WingFlareSingleApplication {

    public static void main(String[] args) {
        try {
            SpringApplication application = new SpringApplication(WingFlareSingleApplication.class);
            application.setBannerMode(Banner.Mode.OFF);
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}