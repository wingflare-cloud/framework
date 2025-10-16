package com.wingflare.adapter.spring.common;


import com.wingflare.api.core.annotation.PrototypeBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;


/**
 * 自定义Bean处理器
 */
public class CustomizeBeanProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String[] beanNames = registry.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            String className = beanDefinition.getBeanClassName();

            if (className == null) {
                continue;
            }

            try {
                Class<?> beanClass = Class.forName(className);

                if (beanClass.isAnnotationPresent(PrototypeBean.class)) {
                    beanDefinition.setScope("prototype");
                }

            } catch (ClassNotFoundException ignored) {
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}