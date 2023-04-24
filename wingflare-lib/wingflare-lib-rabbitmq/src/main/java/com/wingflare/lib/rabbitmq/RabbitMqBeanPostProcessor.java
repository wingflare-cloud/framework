package com.wingflare.lib.rabbitmq;


import com.wingflare.lib.rabbitmq.interceptor.RabbitMqReceiveInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.amqp.rabbit.config.AbstractRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author naizui_ycx
 * @date {2022/1/15}
 * @description
 */
public class RabbitMqBeanPostProcessor implements BeanPostProcessor {

    private final RabbitMqReceiveInterceptor rabbitMqReceiveInterceptor;

    public RabbitMqBeanPostProcessor(RabbitMqReceiveInterceptor rabbitMqReceiveInterceptor) {
        this.rabbitMqReceiveInterceptor = rabbitMqReceiveInterceptor;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof SimpleRabbitListenerContainerFactory) {
            SimpleRabbitListenerContainerFactory factory = (SimpleRabbitListenerContainerFactory) bean;
            registerTracingInterceptor(factory);
        } else if (bean instanceof SimpleMessageListenerContainer) {
            SimpleMessageListenerContainer container = (SimpleMessageListenerContainer) bean;
            registerTracingInterceptor(container);
        } else if (bean instanceof DirectRabbitListenerContainerFactory) {
            DirectRabbitListenerContainerFactory factory = (DirectRabbitListenerContainerFactory) bean;
            registerTracingInterceptor(factory);
        } else if (bean instanceof DirectMessageListenerContainer) {
            DirectMessageListenerContainer container = (DirectMessageListenerContainer) bean;
            registerTracingInterceptor(container);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private void registerTracingInterceptor(AbstractRabbitListenerContainerFactory factory) {
        Advice[] chain = factory.getAdviceChain();
        Advice[] adviceChainWithTracing = getAdviceChainOrAddInterceptorToChain(chain);
        factory.setAdviceChain(adviceChainWithTracing);
    }

    private void registerTracingInterceptor(AbstractMessageListenerContainer container) {
        Field adviceChainField =
                ReflectionUtils.findField(AbstractMessageListenerContainer.class, "adviceChain");
        ReflectionUtils.makeAccessible(adviceChainField);
        Advice[] chain = (Advice[]) ReflectionUtils.getField(adviceChainField, container);
        Advice[] adviceChainWithTracing = getAdviceChainOrAddInterceptorToChain(chain);
        container.setAdviceChain(adviceChainWithTracing);
    }

    private Advice[] getAdviceChainOrAddInterceptorToChain(Advice... existingAdviceChain) {
        if (existingAdviceChain == null) {
            return new Advice[]{rabbitMqReceiveInterceptor};
        }

        for (Advice advice : existingAdviceChain) {
            if (advice instanceof RabbitMqReceiveInterceptor) {
                return existingAdviceChain;
            }
        }

        Advice[] newChain = new Advice[existingAdviceChain.length + 1];
        System.arraycopy(existingAdviceChain, 0, newChain, 0, existingAdviceChain.length);
        newChain[existingAdviceChain.length] = rabbitMqReceiveInterceptor;

        return newChain;
    }
}
