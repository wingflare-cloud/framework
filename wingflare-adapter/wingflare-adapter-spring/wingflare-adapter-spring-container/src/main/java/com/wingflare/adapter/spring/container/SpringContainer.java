package com.wingflare.adapter.spring.container;

import com.wingflare.api.container.ContainerDrive;

/**
 * spring容器类
 */
public class SpringContainer implements ContainerDrive {

    @Override
    public <T> T get(Class<T> clz) {
        return SpringUtil.getBean(clz);
    }

    @Override
    public <T> T get(String name, Class<T> clz) {
        return SpringUtil.getBean(name);
    }

    @Override
    public void set(String beanName, Object bean) {
        SpringUtil.registerBean(beanName, bean);
    }

    @Override
    public void set(Object bean) {
        SpringUtil.registerBean(bean.getClass().getName(), bean);
    }

    @Override
    public <T> boolean has(Class<T> clz) {
        return SpringUtil.containsBean(clz.getName());
    }

    @Override
    public <T> boolean has(String beanName) {
        return SpringUtil.containsBean(beanName);
    }

}
