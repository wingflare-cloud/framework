package com.wingflare.engine.task.client.spring.starter.retry.scan;


import com.wingflare.api.retry.IdempotentIdGenerate;
import com.wingflare.api.retry.RetryCondition;
import com.wingflare.engine.task.client.retry.Scanner;
import com.wingflare.api.retry.annotation.Retryable;
import com.wingflare.api.retry.RetryCompleteCallback;
import com.wingflare.api.retry.enums.RetryType;
import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;
import com.wingflare.api.retry.ExecutorMethod;
import com.wingflare.engine.task.client.retry.strategy.ExecutorAnnotationMethod;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: opensnail
 * @date : 2022-03-03 16:55
 */
@Component
public class RetryableScanner implements Scanner, ApplicationContextAware {

    public ApplicationContext applicationContext;

    @Override
    public List<RetryerInfo> doScan() {
        return scanRetryAbleMethod();
    }

    private List<RetryerInfo> scanRetryAbleMethod() {

        List<RetryerInfo> retryerInfoList = new ArrayList<>();
        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Object.class, false, true);
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);

            Map<Method, Retryable> annotatedMethods = null;
            try {
                annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                        (MethodIntrospector.MetadataLookup<Retryable>) method -> AnnotatedElementUtils
                                .findMergedAnnotation(method, Retryable.class));
            } catch (Throwable ex) {
                TaskEngineLog.LOCAL.error("Error loading retry information for {}: {}", beanDefinitionName, ex);
            }
            if (annotatedMethods == null || annotatedMethods.isEmpty()) {
                continue;
            }

            for (Map.Entry<Method, Retryable> methodEntry : annotatedMethods.entrySet()) {
                Method executeMethod = methodEntry.getKey();
                Retryable retryable = methodEntry.getValue();
                RetryerInfo retryableRegistrarContext = resolvingRetryable(retryable, bean, executeMethod);
                retryerInfoList.add(retryableRegistrarContext);
            }
        }

        return retryerInfoList;
    }

    private RetryerInfo resolvingRetryable(Retryable retryable, Object executor, Method executorMethodName) {

        //异常校验处理
        Class<? extends Throwable>[] include = retryable.include();
        Class<? extends Throwable>[] exclude = retryable.exclude();

        Class executorNotProxy = AopProxyUtils.ultimateTargetClass(executor);
        String executorClassName = executorNotProxy.getName();
        Class<? extends IdempotentIdGenerate> idempotentIdGenerate = retryable.idempotentId();
        String bizNo = retryable.bizNo();
        RetryType retryType = retryable.retryStrategy();
        int localTimes = retryable.localTimes();
        int localInterval = retryable.localInterval();
        Class<? extends ExecutorMethod> retryMethod = retryable.retryMethod();

        if (retryMethod == null) {
            retryMethod = ExecutorAnnotationMethod.class;
        }

        boolean throwException = retryable.isThrowException();
        Class<? extends RetryCompleteCallback> retryCompleteCallback = retryable.retryCompleteCallback();
        boolean async = retryable.async();
        long timeout = retryable.timeout();
        TimeUnit unit = retryable.unit();
        Class<? extends RetryCondition> retryIf = retryable.retryIfResult();
        return new RetryerInfo(retryable.scene(),
                executorClassName,
                new HashSet<>(Arrays.asList(include)),
                new HashSet<>(Arrays.asList(exclude)),
                executor,
                executorMethodName,
                retryType,
                localTimes,
                localInterval,
                idempotentIdGenerate,
                bizNo,
                retryMethod,
                throwException,
                retryCompleteCallback,
                async,
                Boolean.FALSE, // 基于注解的上报不允许强制上报
                timeout,
                unit,
                retryIf
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
