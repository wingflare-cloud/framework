package com.wingflare.lib.core.utils;

import java.util.*;
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

    /**
     * 将给定的Collection按指定大小分切为多个小的Collection。
     *
     * @param <T>       泛型类型
     * @param collection 输入的集合
     * @param size      每个小集合的最大大小
     * @return 分割后的集合列表
     */
    public static <T> List<Collection<T>> splitCollection(Collection<T> collection, int size) {
        List<Collection<T>> result = new ArrayList<>();
        if (collection == null || collection.isEmpty() || size <= 0) {
            return result;
        }

        int totalSize = collection.size();
        int fullChunks = totalSize / size;
        int remainder = totalSize % size;

        int start = 0;

        for (int i = 0; i < fullChunks; i++) {
            result.add(new ArrayList<>(collection).subList(start, start + size));
            start += size;
        }

        if (remainder > 0) {
            result.add(new ArrayList<>(collection).subList(start, start + remainder));
        }

        return result;
    }

}
