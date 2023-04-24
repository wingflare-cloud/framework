package com.wingflare.lib.core.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.wingflare.lib.core.utils.ConvertUtil;
import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.core.utils.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 线程上下文工具
 */
public class ContextHolder {

    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value);
    }

    public static boolean has(String key) {
        return getLocalMap().containsKey(key);
    }

    public static String get(String key) {
        Map<String, Object> map = getLocalMap();
        return ConvertUtil.toStr(map.getOrDefault(key, StringUtil.EMPTY));
    }

    public static <T> T get(String key, T defaultValue, Class<T> clazz) {
        Map<String, Object> map = getLocalMap();
        return ObjectUtil.cast(map.getOrDefault(key, defaultValue));
    }

    public static String get(String key, String defaultValue) {
        return get(key, defaultValue, String.class);
    }

    public static <T> T get(String key, Class<T> clazz) {
        Map<String, Object> map = getLocalMap();
        return ObjectUtil.cast(map.getOrDefault(key, null));
    }

    public static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<String, Object>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}
