package com.wingflare.adapter.spring.common.container;

import com.wingflare.adapter.spring.common.utils.SpringUtil;
import com.wingflare.api.container.ContainerDrive;

import java.util.Collection;
import java.util.Map;

/**
 * spring静态工具容器类
 */
public final class SpringStaticContainer implements ContainerDrive {

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
    public <T> boolean has(Class<T> clz) {
        return SpringUtil.containsBean(clz.getName());
    }

    @Override
    public boolean has(String beanName) {
        return SpringUtil.containsBean(beanName);
    }

}
