package com.wingflare.engine.task.client.starter;

import com.wingflare.engine.task.client.retry.core.annotation.Retryable;
import com.wingflare.engine.task.client.retry.core.intercepter.SnailRetryInterceptor;
import com.wingflare.engine.task.client.retry.core.intercepter.SnailRetryPointcutAdvisor;
import com.wingflare.engine.task.client.retry.core.strategy.RetryStrategy;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.core.env.StandardEnvironment;

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConditionalOnClass(Retryable.class)
@ComponentScan({"com.wingflare.engine.task.client.retry.core", "com.wingflare.engine.task.client.common"})
@ConditionalOnProperty(prefix = "snail-job", name = "enabled", havingValue = "true")
public class TaskEngineClientRetryCoreAutoConfiguration {

    @Bean("snailRetryInterceptor")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public MethodInterceptor snailJobInterceptor(StandardEnvironment standardEnvironment,
                                                 @Lazy RetryStrategy localRetryStrategies) {
        Integer order = standardEnvironment
                .getProperty(TaskEngineClientsRegistrar.AOP_ORDER_CONFIG, Integer.class, Ordered.HIGHEST_PRECEDENCE);

        return new SnailRetryInterceptor(order, localRetryStrategies);
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor snailJobPointcutAdvisor(@Qualifier("snailRetryInterceptor") MethodInterceptor snailJobInterceptor) {
        return new SnailRetryPointcutAdvisor(snailJobInterceptor);
    }

}
