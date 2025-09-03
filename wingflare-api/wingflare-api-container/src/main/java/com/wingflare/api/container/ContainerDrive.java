package com.wingflare.api.container;

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
     * 注入bean对象
     * @param beanName
     * @param bean
     */
    void set(String beanName, Object bean);

    /**
     * 注入bean对象
     * @param bean
     */
    void set(Object bean);

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
     * @param <T>
     */
    <T> boolean has(String beanName);

}
