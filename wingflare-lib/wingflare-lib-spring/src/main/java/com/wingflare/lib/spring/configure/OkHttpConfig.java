package com.wingflare.lib.spring.configure;


import com.wingflare.lib.spring.configure.properties.OkHttpProperties;
import com.wingflare.lib.spring.feign.FeignAutoConfiguration;
import feign.Feign;
import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author naizui_ycx
 * @date {2022/3/9}
 * @description
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class OkHttpConfig {

    @Resource
    private OkHttpProperties okHttpProperties;

    @Bean
    public okhttp3.OkHttpClient okHttpClient(){
        return new okhttp3.OkHttpClient.Builder()
                .readTimeout(okHttpProperties.getReadTimeout(), TimeUnit.SECONDS)
                .connectTimeout(okHttpProperties.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(okHttpProperties.getReadTimeout(), TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool())
                .build();
    }

}
