package com.wingflare.lib.redis.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wingflare.lib.standard.SettingUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * @author naizui_ycx
 * @date {2022/1/21}
 * @description 系统设置获取类
 */
@Component
@ConditionalOnBean(RedisService.class)
public class SystemSettingUtil implements SettingUtil {

    @Resource
    private RedisService redisService;

    @Value("${system.cacheSettingKey:wfSystemSetting}")
    private String cacheSettingKey;

    @Override
    public JSONObject getJSON(String settingKey) {
        return getJSON(settingKey, null);
    }

    @Override
    public String get(String settingKey) {
        return get(settingKey, null);
    }

    @Override
    public <T> T get(String settingKey, T defaultValue, Class<T> tClass) {
        String val = get(settingKey);

        if (StringUtil.isEmpty(val)) {
            return defaultValue;
        }

        return JSONObject.parseObject(val, tClass);
    }

    @Override
    public JSONObject getJSON(String settingKey, JSONObject defaultValue) {
        String val = get(settingKey);

        if (StringUtil.isEmpty(val)) {
            return defaultValue;
        }

        return JSONObject.parseObject(val);
    }

    @Override
    public JSONArray getJSONArray(String settingKey) {
        return getJSONArray(settingKey, null);
    }

    @Override
    public JSONArray getJSONArray(String settingKey, JSONArray defaultValue) {
        String val = get(settingKey);

        if (StringUtil.isEmpty(val)) {
            return defaultValue;
        }

        return JSONArray.parseArray(val);
    }

    @Override
    public String get(String settingKey, String defaultValue) {
        String v = redisService.getCacheMapValue(cacheSettingKey, settingKey);

        if (v == null) {
            return defaultValue;
        }

        return v;
    }

}
