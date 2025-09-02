package com.wingflare.lib.container;

import com.wingflare.api.container.ContainerDrive;

import java.util.ServiceLoader;

/**
 * 容器工具类
 */
public class DiUtil {

    private static volatile ContainerDrive container;

    private DiUtil() {
    }

    private static ContainerDrive getContainer() {
        if (container == null) {
            synchronized (DiUtil.class) {
                if (container == null) {
                    ServiceLoader<ContainerDrive> drives = ServiceLoader.load(ContainerDrive.class);
                    for (ContainerDrive nowDrive : drives) {
                        container = nowDrive;
                        break;
                    }
                }
            }
        }
        return container;
    }

    /**
     * 获取bean对象
     * @param clz
     * @return
     * @param <T>
     */
    public static <T> T get(Class<T> clz) {
        return getContainer().get(clz);
    }

    /**
     * 获取bean对象
     * @param name
     * @param clz
     * @return
     * @param <T>
     */
    public static <T> T get(String name, Class<T> clz) {
        return getContainer().get(name, clz);
    }

    /**
     * 注入bean对象
     * @param beanName
     * @param bean
     */
    public static void set(String beanName, Object bean) {
        getContainer().set(beanName, bean);
    }

    /**
     * 注入bean对象
     * @param bean
     */
    public static void set(Object bean) {
        getContainer().set(bean);
    }

}
