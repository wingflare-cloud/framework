package com.wingflare.lib.mybatis.plus.processor;


import com.wingflare.lib.mybatis.plus.generator.CustomIdGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 覆盖已存在的Bean
 *
 * @author shaoyuyao
 * @date 2022/8/16 11:56
 */
@Component
public class OverrideExistsBeanProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 从容器中删除mybatis-plus自带的主键生成器bean
        String beanName = "identifierGenerator";
        if (registry.containsBeanDefinition(beanName)) {
            registry.removeBeanDefinition(beanName);
            // 向容器中注册自定义的bean
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(CustomIdGenerator.class);
            registry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
