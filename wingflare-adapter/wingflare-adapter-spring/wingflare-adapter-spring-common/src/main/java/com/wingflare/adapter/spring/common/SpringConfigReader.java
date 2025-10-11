package com.wingflare.adapter.spring.common;


import com.wingflare.adapter.spring.common.utils.SpringUtil;
import com.wingflare.api.config.ConfigReader;
import org.springframework.core.env.Environment;

import java.util.Collection;
import java.util.List;

public final class SpringConfigReader implements ConfigReader {

    @Override
    public String getProperty(String key) {
        return SpringUtil.getBean(Environment.class).getProperty(key);
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return SpringUtil.getBean(Environment.class).getProperty(key, defaultValue);
    }

    @Override
    public Long getLongProperty(String key) {
        return SpringUtil.getBean(Environment.class).getProperty(key, Long.class);
    }

    @Override
    public Long getLongProperty(String key, Long defaultValue) {
        return SpringUtil.getBean(Environment.class).getProperty(key, Long.class, defaultValue);
    }

    @Override
    public Boolean getBooleanProperty(String key) {
        return SpringUtil.getBean(Environment.class).getProperty(key, Boolean.class);
    }

    @Override
    public Boolean getBooleanProperty(String key, Boolean defaultValue) {
        return SpringUtil.getBean(Environment.class).getProperty(key, Boolean.class, defaultValue);
    }

    @Override
    public Collection<String> getProfiles() {
        return List.of(SpringUtil.getBean(Environment.class).getActiveProfiles());
    }

    @Override
    public int order() {
        return -1;
    }
}
