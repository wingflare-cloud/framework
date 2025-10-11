package com.wingflare.api.config;

import java.util.Collection;

/**
 * 配置获取接口
 */
public interface ConfigReader {

    /**
     * 获取指定key的配置值
     * @param key 配置键
     * @return 配置值，如果不存在则返回null
     */
    String getProperty(String key);

    /**
     * 获取指定key的配置值，如果不存在则返回默认值
     * @param key 配置键
     * @param defaultValue 默认值
     * @return 配置值或默认值
     */
    String getProperty(String key, String defaultValue);

    /**
     * 获取整数类型的配置值
     */
    Long getLongProperty(String key);

    /**
     * 获取整数类型的配置值，带默认值
     */
    Long getLongProperty(String key, Long defaultValue);

    /**
     * 获取布尔类型的配置值
     */
    Boolean getBooleanProperty(String key);

    /**
     * 获取布尔类型的配置值，带默认值
     */
    Boolean getBooleanProperty(String key, Boolean defaultValue);

    /**
     * 获取当前环境
     * @return
     */
    Collection<String> getProfiles();

    /**
     * 配置项获取的权重
     *
     * @return
     */
    default int order() {
       return 0;
    }

}
