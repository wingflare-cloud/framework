package com.wingflare.adapter.spring.container;


import com.wingflare.api.container.ContainerDrive;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Collection;
import java.util.Map;


/**
 * spring容器类
 */
public class SpringContainer implements ContainerDrive, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> T get(Class<T> clz) {
        try {
            return applicationContext.getBean(clz);
        } catch (BeansException e) {
            return null;
        }
    }

    @Override
    public <T> T get(String name, Class<T> clz) {
        try {
            return applicationContext.getBean(name, clz);
        } catch (BeansException e) {
            return null;
        }
    }

    @Override
    public <T> Map<String, T> getAllMap(Class<T> type) {
        try {
            return applicationContext.getBeansOfType(type);
        } catch (BeansException e) {
            return null;
        }
    }

    @Override
    public <T> Collection<T> getAll(Class<T> type) {
        Map<String, T> beansOfType = getAllMap(type);
        return beansOfType != null ? beansOfType.values() : null;
    }

    @Override
    public <T> boolean has(Class<T> clz) {
        String[] beanNamesForType = applicationContext.getBeanNamesForType(clz);
        return beanNamesForType != null && beanNamesForType.length > 0;
    }

    @Override
    public boolean has(String beanName) {
        return applicationContext.containsBean(beanName);
    }

}
