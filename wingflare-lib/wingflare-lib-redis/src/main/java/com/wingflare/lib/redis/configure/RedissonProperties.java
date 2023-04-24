package com.wingflare.lib.redis.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author naizui_ycx
 * @date {2022/1/12}
 * @description
 */
@ConditionalOnBean(RedissonAutoConfiguration.class)
@ConfigurationProperties(prefix = "spring.redis.redisson")
public class RedissonProperties {

    private String config;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

}
