package com.wingflare.tool.generator;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WingFlareGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(WingFlareGeneratorApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

}
