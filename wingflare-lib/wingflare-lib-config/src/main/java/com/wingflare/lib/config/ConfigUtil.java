package com.wingflare.lib.config;


import com.wingflare.api.config.ConfigReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

/**
 * 配置获取类
 */
public class ConfigUtil {

    private static volatile List<ConfigReader> configReaderList;

    private ConfigUtil() {
    }

    private static List<ConfigReader> getConfigs() {
        if (configReaderList == null) {
            synchronized (ConfigUtil.class) {
                if (configReaderList == null) {
                    configReaderList = new ArrayList<>();
                    ServiceLoader<ConfigReader> cs = ServiceLoader.load(ConfigReader.class);
                    for (ConfigReader configReader : cs) {
                        configReaderList.add(configReader);
                        break;
                    }
                    configReaderList.add(new SystemPropertyConfig());
                    configReaderList.add(new EnvironmentConfig());
                    configReaderList.sort(
                            (o1, o2) ->
                                    Integer.compare(o2.order(), o1.order()));
                }
            }
        }

        return configReaderList;
    }


    /**
     * 获取指定key的配置值
     * @param key 配置键
     * @return 配置值，如果不存在则返回null
     */
    public static String getProperty(String key) {
        for (ConfigReader loader : getConfigs()) {
            String config = loader.getProperty(key);
            if (config != null) return config;
        }

        return null;
    }

    /**
     * 获取指定key的配置值，如果不存在则返回默认值
     * @param key 配置键
     * @param defaultValue 默认值
     * @return 配置值或默认值
     */
    public static String getProperty(String key, String defaultValue) {
        for (ConfigReader loader : getConfigs()) {
            String config = loader.getProperty(key);
            if (config != null) return config;
        }

        return defaultValue;
    }

    /**
     * 获取整数类型的配置值
     */
    public static Integer getIntProperty(String key) {
        for (ConfigReader loader : getConfigs()) {
            Integer config = loader.getIntProperty(key);
            if (config != null) return config;
        }

        return null;
    }

    /**
     * 获取整数类型的配置值，带默认值
     */
    public static Integer getIntProperty(String key, Integer defaultValue) {
        for (ConfigReader loader : getConfigs()) {
            Integer config = loader.getIntProperty(key);
            if (config != null) return config;
        }

        return defaultValue;
    }

    /**
     * 获取布尔类型的配置值
     */
    public static Boolean getBooleanProperty(String key) {
        for (ConfigReader loader : getConfigs()) {
            Boolean config = loader.getBooleanProperty(key);
            if (config != null) return config;
        }

        return null;
    }

    /**
     * 获取布尔类型的配置值，带默认值
     */
    public static Boolean getBooleanProperty(String key, Boolean defaultValue) {
        for (ConfigReader loader : getConfigs()) {
            Boolean config = loader.getBooleanProperty(key);
            if (config != null) return config;
        }

        return defaultValue;
    }

    /**
     * 获取当前环境
     * @return
     */
    public static Collection<String> getProfiles() {
        for (ConfigReader loader : getConfigs()) {
            Collection<String> config = loader.getProfiles();
            if (config != null) return config;
        }

        return List.of();
    }

}
