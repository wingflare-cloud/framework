package com.wingflare.lib.spring.configure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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

}
