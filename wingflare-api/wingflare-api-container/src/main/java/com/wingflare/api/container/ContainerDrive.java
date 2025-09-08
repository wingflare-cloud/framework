package com.wingflare.api.container;


import java.util.Collection;
import java.util.Map;

/**
 * IOC容器抽象
 */
public interface ContainerDrive {

    /**
     * 获取bean对象
     * @param clz
     * @return
     * @param <T>
     */
    <T> T get(Class<T> clz);

    /**
     * 获取bean对象
     * @param name
     * @param clz
     * @return
     * @param <T>
     */
    <T> T get(String name, Class<T> clz);

    /**
     * 获取全部同类型bean对象
     * @param type
     * @return
     * @param <T>
     */
    <T> Map<String, T> getAllMap(Class<T> type);

    /**
     * 获取全部同类型bean对象
     * @param type
     * @return
     * @param <T>
     */
    <T> Collection<T> getAll(Class<T> type);

    /**
     * 是否存在bean对象
     * @param clz
     * @return
     * @param <T>
     */
    <T> boolean has(Class<T> clz);

    /**
     * 是否存在bean对象
     * @param beanName
     * @return
     */
    boolean has(String beanName);

}
