package com.wingflare.tool.generator.strategy;

import cn.hutool.core.util.BooleanUtil;

import java.util.HashMap;
import java.util.Map;

public class Strategy {

    private String fileType;

    private Map<String, Object> properties = new HashMap<>();

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public boolean containsKey(String key) {
        return properties.containsKey(key);
    }

    public String getStringProperty(String key) {
        return (String) properties.get(key);
    }

    public boolean getBooleanProperty(String key) {
        if (containsKey(key) && BooleanUtil.isBoolean(getProperty(key).getClass())) {
            return (boolean) getProperty(key);
        }

        return false;
    }

}
