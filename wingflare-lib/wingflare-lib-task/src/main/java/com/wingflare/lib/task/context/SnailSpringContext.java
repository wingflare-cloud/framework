package com.wingflare.lib.task.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author: opensnail
 * @date : 2022-02-16 18:03
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SnailSpringContext implements BeanFactoryPostProcessor, ApplicationContextAware {

    private final static Logger log = LoggerFactory.getLogger(SnailSpringContext.class);

    private static ConfigurableListableBeanFactory FACTORY;

    private static ApplicationContext CONTEXT;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        SnailSpringContext.FACTORY = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SnailSpringContext.CONTEXT = applicationContext;
    }

    public static ListableBeanFactory getBeanFactory() {
        final ListableBeanFactory factory = null == FACTORY ? CONTEXT : FACTORY;
        if (null == factory) {
            throw new RuntimeException("No ConfigurableListableBeanFactory or ApplicationContext injected, maybe not in the Spring environment?");
        }
        return factory;
    }

    public static ApplicationContext getContext() {
        return CONTEXT;
    }

    public static <T> T getBeanByType(Class<T> clazz) {
        return getBeanFactory().getBean(clazz);
    }


    public static synchronized <T> T getBean(String name) {
        try {
            return (T) getBeanFactory().getBean(name);
        } catch (BeansException | NullPointerException exception) {
            log.error(" BeanName:{} not exist，Exception => {}", name, exception.getMessage());
            return null;
        }
    }

    public static synchronized <T> T getBean(Class<T> requiredType) {
        try {
            return getBeanFactory().getBean(requiredType);
        } catch (BeansException | NullPointerException exception) {
            log.error(" BeanName:{} not exist，Exception => {}", requiredType.getName(), exception.getMessage());
            return null;
        }
    }

    public static synchronized <T> T getBean(String name, Class<T> requiredType) {
        try {
            return getBeanFactory().getBean(name, requiredType);
        } catch (BeansException | NullPointerException exception) {
            log.error(" BeanName:{} not exist，Exception => {}", name, exception.getMessage());
            return null;
        }
    }

}
