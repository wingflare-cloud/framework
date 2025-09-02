package com.wingflare.lib.config;

import com.wingflare.api.config.ConfigInterface;

/**
 * 环境变量配置获取
 */
public class EnvironmentConfig implements ConfigInterface {

    private String caseKey(String key) {
        return key.toUpperCase().replace('.', '_');
    }

    @Override
    public String getProperty(String key) {
        return System.getenv(caseKey(key));
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        String val = System.getenv(caseKey(key));

        if (val == null) {
            return defaultValue;
        }

        return val;
    }

    @Override
    public Integer getIntProperty(String key) {
        return Integer.valueOf(System.getenv(caseKey(key)));
    }

    @Override
    public Integer getIntProperty(String key, Integer defaultValue) {
        String val = System.getenv(caseKey(key));

        if (val == null) {
            return defaultValue;
        }

        return Integer.valueOf(val);
    }

    @Override
    public Boolean getBooleanProperty(String key) {
        return Boolean.valueOf(System.getenv(caseKey(key)));
    }

    @Override
    public Boolean getBooleanProperty(String key, Boolean defaultValue) {
        String val = System.getenv(caseKey(key));

        if (val == null) {
            return defaultValue;
        }

        return Boolean.valueOf(val);
    }

    @Override
    public int order() {
        return 1;
    }

}
