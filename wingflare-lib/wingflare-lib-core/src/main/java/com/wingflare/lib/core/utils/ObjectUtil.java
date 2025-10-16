package com.wingflare.lib.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 对象工具
 */
public class ObjectUtil {

    private static final String STREAM_CHARSET_NAME = "ISO-8859-1";

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj, Class<T> clt) {
        return (T) obj;
    }

    /**
     * 序列化对象
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public static String serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        String string = byteArrayOutputStream.toString(STREAM_CHARSET_NAME);
        objectOutputStream.close();
        byteArrayOutputStream.close();

        return string;
    }

    /**
     * 反序列化对象
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object serializeToObject(String str) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(STREAM_CHARSET_NAME));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();

        return object;
    }

    public static Map<String, Object> objectToMap(Object obj) {
        Map<String,Object> map = new HashMap<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for(Field field:fields){
            field.setAccessible(true);

            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalArgumentException | IllegalAccessException e) {

            }
        }

        return map;
    }

    /**
     * list深拷贝
     *
     * @param <T>
     * @param src
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deepCopy(T src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        T dest = (T) in.readObject();
        return dest;
    }

    /**
     * 判断类是否存在
     *
     * @param className
     *
     * @return
     */
    public static boolean classIsPresent(String className) {
        try {
            Thread.currentThread().getContextClassLoader().loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 判断对象是否为基本数据对象
     * @param obj
     * @return
     */
    public static boolean isPrimitive(Object obj) {
        try {
            return ((Class<?>)obj.getClass().getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断对象是否为null
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 判断对象不为null
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 如果给定对象为{@code null}返回默认值
     *
     * @param object
     * @param defaultValue
     * @return
     * @param <T>
     */
    public static <T> T defaultIfNull(final T object, final T defaultValue) {
        return isNull(object) ? defaultValue : object;
    }


    public static <T> T defaultIfNull(T source, Supplier<? extends T> defaultValueSupplier) {
        if (isNull(source)) {
            return defaultValueSupplier.get();
        }
        return source;
    }

    public static <T> T defaultIfNull(T source, Function<T, ? extends T> defaultValueSupplier) {
        if (isNull(source)) {
            return defaultValueSupplier.apply(null);
        }
        return source;
    }


}
