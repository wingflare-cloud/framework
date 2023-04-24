package com.wingflare.lib.redis.configure;

import org.redisson.config.Config;

/**
 * @author naizui_ycx
 * @date {2022/1/12}
 * @description
 */
@FunctionalInterface
public interface RedissonAutoConfigurationCustomizer {

    /**
     * Customize the RedissonClient configuration.
     * @param configuration the {@link Config} to customize
     */
    void customize(final Config configuration);

}
