package com.wingflare;


import com.wingflare.adapter.spring.server.web.filter.HeaderConvertContextFilter;
import com.wingflare.adapter.spring.server.web.handler.ApiResponseAdviceHandler;
import com.wingflare.lib.jwt.filter.ServletAuthFilter;
import com.wingflare.lib.security.annotation.EnableDataSecret;
import com.wingflare.lib.security.annotation.EnableDataSensitive;
import com.wingflare.lib.spring.ResponseConverter;
import com.wingflare.lib.spring.annotation.BaseConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@BaseConfig
@EnableDataSensitive
@EnableDataSecret
@SpringBootApplication
@Import(
        {
                HeaderConvertContextFilter.class,
                ServletAuthFilter.class,
                ResponseConverter.Default.class,
                ApiResponseAdviceHandler.class,
        }
)
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