package com.wingflare.lib.core.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description map工具
 */
public class MapUtil {

    /**
     * Map中根据key批量删除键值对
     *
     * @param map
     * @param excludeKeys
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> removeEntries(Map<K, V> map, K[] excludeKeys) {
        Iterator<K> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            K key = iterator.next();
            // 如果key 刚好在要排除的key的范围中
            if (ArrayUtils.contains(excludeKeys, key)) {
                iterator.remove();
                map.remove(key);
            }
        }
        return map;
    }

    /**
     * 通过元素获取首个key
     *
     * @param map
     * @param val
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> K findFirstKeyByValue(Map<K, V> map, V val) {
        return map.entrySet()
                .stream()
                .filter(e -> val.equals(e.getValue()))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * 通过元素获取key
     *
     * @param map
     * @param val
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> List<K> findKeyByValue(Map<K, V> map, V val) {
        return map.entrySet()
                .stream()
                .filter(e -> val.equals(e.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
