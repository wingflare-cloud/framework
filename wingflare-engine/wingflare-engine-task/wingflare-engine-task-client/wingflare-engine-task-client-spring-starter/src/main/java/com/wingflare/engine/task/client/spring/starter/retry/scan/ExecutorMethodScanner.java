package com.wingflare.engine.task.client.spring.starter.retry.scan;


import com.wingflare.api.retry.IdempotentIdGenerate;
import com.wingflare.engine.task.client.retry.Scanner;
import com.wingflare.api.retry.annotation.ExecutorMethodRegister;
import com.wingflare.api.retry.RetryCompleteCallback;
import com.wingflare.api.retry.enums.RetryType;
import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;
import com.wingflare.api.retry.ExecutorMethod;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 扫描手动注入重试方法
 *
 * @author: opensnail
 * @date : 2023-05-10 11:10
 */
@Component
public class ExecutorMethodScanner implements Scanner, ApplicationContextAware {

    public ApplicationContext applicationContext;

    @Override
    public List<RetryerInfo> doScan() {
        List<RetryerInfo> retryerInfoList = new ArrayList<>();
        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Object.class, false, true);

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            ExecutorMethodRegister annotation = bean.getClass().getAnnotation(ExecutorMethodRegister.class);
            if (Objects.nonNull(annotation)) {
                RetryerInfo retryerInfo = resolvingRetryable(annotation, bean);
                Optional.ofNullable(retryerInfo).ifPresent(retryerInfoList::add);
            }
        }

        return retryerInfoList;
    }

    private RetryerInfo resolvingRetryable(ExecutorMethodRegister retryable, Object executor) {

        try {
            Class executorNotProxy = AopUtils.getTargetClass(executor);
            String executorClassName = executorNotProxy.getName();
            Class<? extends IdempotentIdGenerate> idempotentIdGenerate = retryable.idempotentId();
            Method executorMethodName = executorNotProxy.getMethod("doExecute", Object.class);
            Class<? extends RetryCompleteCallback> retryCompleteCallback = retryable.retryCompleteCallback();
            boolean async = retryable.async();
            long timeout = retryable.timeout();
            TimeUnit unit = retryable.unit();
            boolean forceReport = retryable.forceReport();

            return new RetryerInfo(retryable.scene(),
                    executorClassName,
                    new HashSet<>(Collections.emptyList()),
                    new HashSet<>(Collections.emptyList()),
                    executor,
                    executorMethodName,
                    RetryType.ONLY_REMOTE,
                    1,
                    1,
                    idempotentIdGenerate,
                    retryable.bizNo(),
                    (Class<? extends ExecutorMethod>) executor.getClass(),
                    Boolean.TRUE,
                    retryCompleteCallback,
                    async,
                    forceReport,
                    timeout,
                    unit,
                    null
            );
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Error loading retry information for {}: {}", executor.getClass().getName(), e);
        }

        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
