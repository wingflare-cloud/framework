package com.wingflare.lib.core.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Iterator;
import java.util.Map;

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

}
