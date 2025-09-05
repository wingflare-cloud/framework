package com.wingflare.lib.config;

import com.wingflare.api.config.ConfigReader;

/**
 * 系统属性配置获取方法
 */
public class SystemPropertyConfig implements ConfigReader {

    @Override
    public String getProperty(String key) {
        return System.getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }

    @Override
    public Integer getIntProperty(String key) {
        return Integer.valueOf(System.getProperty(key));
    }

    @Override
    public Integer getIntProperty(String key, Integer defaultValue) {
        String val = System.getProperty(key);

        if (val == null) {
            return defaultValue;
        }

        return Integer.valueOf(val);
    }

    @Override
    public Boolean getBooleanProperty(String key) {
        return Boolean.valueOf(System.getProperty(key));
    }

    @Override
    public Boolean getBooleanProperty(String key, Boolean defaultValue) {
        String val = System.getProperty(key);

        if (val == null) {
            return defaultValue;
        }

        return Boolean.valueOf(val);
    }

}
