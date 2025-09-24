package com.wingflare.lib.standard;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author naizui_ycx
 * @date {2023/01/09}
 * @description 系统设置获取标准接口
 */
public interface SettingUtil {

    /**
     * 获取json格式的设置值
     *
     * @param settingKey
     *
     * @return
     */
    JSONObject getJSON(String settingKey);

    /**
     * 获取设置string值
     *
     * @param settingKey
     *
     * @return
     */
    String get(String settingKey);

    /**
     * 获取设置string值，带默认值
     *
     * @param settingKey
     * @param defaultValue
     *
     * @return
     */
    String get(String settingKey, String defaultValue);

    /**
     * 获取设置值，泛型
     *
     * @param settingKey
     * @param defaultValue
     * @param tClass
     * @param <T>
     *
     * @return
     */
    <T> T get(String settingKey, T defaultValue, Class<T> tClass);


    /**
     * 获取json格式的设置值，带默认值
     *
     * @param settingKey
     * @param defaultValue
     *
     * @return
     */
    JSONObject getJSON(String settingKey, JSONObject defaultValue);

    /**
     * 获取json array类型的设置值
     *
     * @param settingKey
     *
     * @return
     */
    JSONArray getJSONArray(String settingKey);

    /**
     * 获取json array类型的设置值，带默认值
     *
     * @param settingKey
     * @param defaultValue
     *
     * @return
     */
    JSONArray getJSONArray(String settingKey, JSONArray defaultValue);
}
