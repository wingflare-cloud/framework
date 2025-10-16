package com.wingflare.adapter.spring.common.configure;


import com.wingflare.adapter.spring.common.configure.properties.OkHttpProperties;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author naizui_ycx
 * @date {2022/3/9}
 * @description
 */
@Configuration
public class OkHttpConfig {
    
    private final OkHttpProperties okHttpProperties;

    public OkHttpConfig(OkHttpProperties okHttpProperties) {
        this.okHttpProperties = okHttpProperties;
    }

    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder()
                .readTimeout(okHttpProperties.getReadTimeout(), TimeUnit.SECONDS)
                .connectTimeout(okHttpProperties.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(okHttpProperties.getReadTimeout(), TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(okHttpProperties.getMaxIdleConnections(), okHttpProperties.getKeepAliveDuration(),
                        okHttpProperties.getKeepAliveTimeUnit()))
                .build();
    }

}
