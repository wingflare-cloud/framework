package com.wingflare.engine.task.client.spring.starter;


import com.wingflare.engine.task.client.retry.register.RetryableRegistrar;
import com.wingflare.engine.task.client.spring.starter.log.report.RetryLogReport;
import com.wingflare.engine.task.client.spring.starter.retry.TaskRetryEndPoint;
import com.wingflare.engine.task.client.spring.starter.retry.intercepter.TaskRetryHeaderAspect;
import com.wingflare.engine.task.client.spring.starter.retry.intercepter.TaskRetryInterceptor;
import com.wingflare.engine.task.client.spring.starter.retry.intercepter.TaskRetryPointcutAdvisor;
import com.wingflare.engine.task.client.retry.strategy.RetryStrategy;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.core.env.StandardEnvironment;


@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConditionalOnClass(RetryableRegistrar.class)
@Import({TaskRetryEndPoint.class, TaskRetryHeaderAspect.class, RetryLogReport.class})
@ConditionalOnProperty(prefix = "task", name = "enabled", havingValue = "true")
public class TaskEngineClientRetryCoreAutoConfiguration {

    @Bean("taskEngineRetryInterceptor")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public MethodInterceptor taskEngineRetryInterceptor(StandardEnvironment standardEnvironment,
                                                 @Lazy RetryStrategy localRetryStrategies) {
        Integer order = standardEnvironment
                .getProperty(TaskEngineClientsRegistrar.AOP_ORDER_CONFIG, Integer.class, Ordered.HIGHEST_PRECEDENCE);

        return new TaskRetryInterceptor(order, localRetryStrategies);
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor jobPointcutAdvisor(
            @Qualifier("taskEngineRetryInterceptor") MethodInterceptor taskEngineRetryInterceptor) {
        return new TaskRetryPointcutAdvisor(taskEngineRetryInterceptor);
    }

}
