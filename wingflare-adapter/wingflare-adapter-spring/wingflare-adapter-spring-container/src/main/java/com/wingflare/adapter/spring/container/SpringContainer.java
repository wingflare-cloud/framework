package com.wingflare.adapter.spring.container;

import com.wingflare.api.container.ContainerDrive;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * spring容器类
 */
public final class SpringContainer implements ContainerDrive {

    @Override
    public <T> T get(Class<T> clz) {
        return SpringUtil.getBean(clz);
    }

    @Override
    public <T> T get(String name, Class<T> clz) {
        return SpringUtil.getBean(name);
    }

    @Override
    public <T> Map<String, T> getAllMap(Class<T> type) {
        return SpringUtil.getApplicationContext().getBeansOfType(type);
    }

    @Override
    public <T> Collection<T> getAll(Class<T> type) {
        return SpringUtil.getApplicationContext().getBeansOfType(type).values();
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
    public boolean has(String beanName) {
        return SpringUtil.containsBean(beanName);
    }

}
