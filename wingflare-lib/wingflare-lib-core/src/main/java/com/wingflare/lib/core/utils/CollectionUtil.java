package com.wingflare.lib.core.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 集合工具
 */
public class CollectionUtil {


    /**
     * Return {@code true} if the supplied Collection is {@code null} or empty.
     * Otherwise, return {@code false}.
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * Return {@code true} if the supplied Map is {@code null} or empty.
     * Otherwise, return {@code false}.
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * Return {@code true} if the supplied Array is {@code null} or empty.
     * Otherwise, return {@code false}.
     * @param arr the Array to check
     * @return whether the given Map is empty
     */
    public static <T> boolean isEmpty(T[] arr) {
        return (arr == null || arr.length == 0);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }

    /**
     * @param source 源文件集合
     * @param key        泛型的属性key
     * @param value      泛型的属性值
     * @return 将List中所有的对象的指定值,存放到一个Map中
     * List<user>  ->  Map<String,String> userMap
     */
    public static <K, V, T> Map<K, V> collection2Map(Collection<T> source, Function<T, K> key, Function<T, V> value) {

        if (isEmpty(source)) {
            return Collections.emptyMap();
        }
        return source.stream().collect(Collectors.toMap(key, value, (k, k2) -> k));
    }

}
