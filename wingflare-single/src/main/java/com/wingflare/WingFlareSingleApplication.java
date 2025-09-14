package com.wingflare;


import com.wingflare.adapter.spring.jwt.ServletAuthFilter;
import com.wingflare.adapter.spring.servlet.web.filter.HeaderConvertContextFilter;
import com.wingflare.adapter.spring.servlet.web.handler.ApiResponseAdviceHandler;
import com.wingflare.adapter.spring.servlet.web.handler.WebApiExceptionHandler;
import com.wingflare.api.security.annotation.EnableDataSecret;
import com.wingflare.api.security.annotation.EnableDataSensitive;
import com.wingflare.lib.spring.ResponseConverter;
import com.wingflare.lib.spring.annotation.BaseConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;

import java.util.TimeZone;


@BaseConfig
@EnableDataSensitive
@EnableDataSecret
@EnableRedisIndexedHttpSession
@SpringBootApplication
@Import(
        {
                HeaderConvertContextFilter.class,
                ServletAuthFilter.class,
                ResponseConverter.Default.class,
                ApiResponseAdviceHandler.class,
                WebApiExceptionHandler.class,
        }
)
public class WingFlareSingleApplication {

    public static void main(String[] args) {
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
            SpringApplication application = new SpringApplication(WingFlareSingleApplication.class);
            application.setBannerMode(Banner.Mode.OFF);
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}