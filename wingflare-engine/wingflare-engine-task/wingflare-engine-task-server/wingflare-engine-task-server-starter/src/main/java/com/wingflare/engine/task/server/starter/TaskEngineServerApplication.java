package com.wingflare.engine.task.server.starter;


import com.wingflare.adapter.spring.common.ResponseConverter;
import com.wingflare.adapter.spring.common.annotation.BaseConfig;
import com.wingflare.adapter.spring.common.configure.properties.ApiProperties;
import com.wingflare.adapter.spring.jwt.ServletAuthFilter;
import com.wingflare.adapter.spring.security.properties.AuthProperties;
import com.wingflare.adapter.spring.servlet.web.filter.HeaderConvertContextFilter;
import com.wingflare.adapter.spring.servlet.web.handler.ApiResponseAdviceHandler;
import com.wingflare.adapter.spring.servlet.web.handler.WebApiExceptionHandler;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.TimeZone;


@BaseConfig
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@Import(
        {
                AuthProperties.class,
                ApiProperties.class,
                HeaderConvertContextFilter.class,
                ServletAuthFilter.class,
                ResponseConverter.Default.class,
                ApiResponseAdviceHandler.class,
                WebApiExceptionHandler.class,
        }
)
public class TaskEngineServerApplication {

    public static void main(String[] args) {
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
            SpringApplication application = new SpringApplication(TaskEngineServerApplication.class);
            application.setBannerMode(Banner.Mode.OFF);
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

}
