package com.wingflare.adapter.spring.common.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author naizui_ycx
 * @date {2022/3/10}
 * @description okhttp 配置
 */
@Configuration
@ConfigurationProperties(prefix = "okhttp")
public class OkHttpProperties {

    private Integer readTimeout = 30;

    private Integer connectTimeout = 30;

    private Integer writeTimeout = 30;

    private Integer maxIdleConnections = 10;

    private Long keepAliveDuration = 10L;

    private TimeUnit keepAliveTimeUnit = TimeUnit.SECONDS;

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(Integer writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public Integer getMaxIdleConnections() {
        return maxIdleConnections;
    }

    public void setMaxIdleConnections(Integer maxIdleConnections) {
        this.maxIdleConnections = maxIdleConnections;
    }

    public Long getKeepAliveDuration() {
        return keepAliveDuration;
    }

    public void setKeepAliveDuration(Long keepAliveDuration) {
        this.keepAliveDuration = keepAliveDuration;
    }

    public TimeUnit getKeepAliveTimeUnit() {
        return keepAliveTimeUnit;
    }

    public void setKeepAliveTimeUnit(TimeUnit keepAliveTimeUnit) {
        this.keepAliveTimeUnit = keepAliveTimeUnit;
    }

}
