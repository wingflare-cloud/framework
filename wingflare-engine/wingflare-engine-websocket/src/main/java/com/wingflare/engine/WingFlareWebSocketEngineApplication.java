package com.wingflare.engine;

import com.wingflare.lib.spring.annotation.BaseConfig;
import com.wingflare.lib.spring.annotation.MicroserviceMode;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@BaseConfig
@MicroserviceMode
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class WingFlareWebSocketEngineApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WingFlareWebSocketEngineApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
