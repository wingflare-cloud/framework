package com.wingflare.lib.config;


import com.wingflare.api.config.ConfigReader;

import java.util.Collection;
import java.util.List;

/**
 * 环境变量配置获取
 */
public final class EnvironmentConfig implements ConfigReader {

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
    public Long getLongProperty(String key) {
        String val = System.getenv(caseKey(key));

        if (val == null) {
            return null;
        }

        return Long.valueOf(val);
    }

    @Override
    public Long getLongProperty(String key, Long defaultValue) {
        String val = System.getenv(caseKey(key));

        if (val == null) {
            return defaultValue;
        }

        return Long.valueOf(val);
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
    public Collection<String> getProfiles() {
        String profiles = System.getenv("PROFILES_ACTIVE");

        if (profiles == null || profiles.trim().isEmpty()) {
            return null;
        }

        return List.of(profiles.split(","));
    }

    @Override
    public int order() {
        return 1;
    }

}
