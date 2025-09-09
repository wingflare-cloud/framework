package com.wingflare.lib.core.utils;


import com.wingflare.api.core.KeyValueEnum;
import com.wingflare.api.core.ValueEnum;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 枚举类工具
 */
public class EnumUtil {

    /**
     * 判断枚key举值是否存在指定枚举类中
     *
     * @param enumClass 枚举类
     * @param key     枚举值
     * @param <E>       枚举类型
     * @param <V>       值类型
     * @return 是否存在
     **/
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends KeyValueEnum<V>>, V> boolean isExist(Class<E> enumClass, String key) {
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(item -> ((KeyValueEnum<V>) item).getKey().equals(key));
    }

    /**
     * 判断枚举value值是否存在指定枚举类中
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param <E>       枚举类型
     * @param <V>       值类型
     * @return 是否存在
     **/
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends ValueEnum<V>>, V> boolean isExist(Class<E> enumClass, V value) {
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(item -> ((ValueEnum<V>) item).getValue().equals(value));
    }

    /**
     * 根据枚举value值获取对应的枚举对象
     *
     * @param enums 枚举列表
     * @param value 枚举值
     * @return 枚举对象
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends ValueEnum<V>>, V> E getEnumByValue(E[] enums, V value) {

        if (Objects.isNull(value)) {
            return null;
        }

        return Arrays.stream(enums)
                .filter(item -> ((ValueEnum<V>) item).getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据枚举key值获取对应的枚举对象
     *
     * @param enums 枚举列表
     * @param key 枚举值
     * @return 枚举对象
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends KeyValueEnum<V>>, V> E getEnumByKey(E[] enums, String key) {

        if (StringUtil.isEmpty(key)) {
            return null;
        }

        return Arrays.stream(enums)
                .filter(item -> ((KeyValueEnum<V>) item).getKey().equals(key))
                .findFirst()
                .orElse(null);
    }

}
