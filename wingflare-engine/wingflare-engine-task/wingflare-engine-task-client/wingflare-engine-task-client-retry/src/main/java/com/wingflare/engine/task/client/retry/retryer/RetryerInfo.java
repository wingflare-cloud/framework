package com.wingflare.engine.task.client.retry.retryer;


import com.wingflare.api.retry.IdempotentIdGenerate;
import com.wingflare.api.retry.enums.RetryType;
import com.wingflare.api.retry.RetryCondition;
import com.wingflare.api.retry.RetryCompleteCallback;
import com.wingflare.api.retry.ExecutorMethod;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 定义重试场景的信息
 *
 * @author: opensnail
 * @date : 2022-03-03 15:06
 * @since 1.0.0
 */
public class RetryerInfo {

    private final String scene;
    private final String executorClassName;
    private final Set<Class<? extends Throwable>> include;
    private final Set<Class<? extends Throwable>> exclude;
    private final Object executor;
    private final Method method;
    private final RetryType retryType;
    private final Integer localTimes;
    private final Integer localInterval;
    private final Class<? extends IdempotentIdGenerate> idempotentIdGenerate;
    private final String bizNo;
    private final Class<? extends ExecutorMethod> executorMethod;
    private final boolean isThrowException;
    private final Class<? extends RetryCompleteCallback> retryCompleteCallback;
    private final boolean async;
    private final boolean forceReport;
    private final long timeout;
    private final TimeUnit unit;
    private final Class<? extends RetryCondition> retryCondition;

    public RetryerInfo(String scene, String executorClassName, Set<Class<? extends Throwable>> include, Set<Class<? extends Throwable>> exclude, Object executor, Method method, RetryType retryType, Integer localTimes, Integer localInterval, Class<? extends IdempotentIdGenerate> idempotentIdGenerate, String bizNo, Class<? extends ExecutorMethod> executorMethod, boolean isThrowException, Class<? extends RetryCompleteCallback> retryCompleteCallback, boolean async, boolean forceReport, long timeout, TimeUnit unit, Class<? extends RetryCondition> retryCondition) {
        this.scene = scene;
        this.executorClassName = executorClassName;
        this.include = include;
        this.exclude = exclude;
        this.executor = executor;
        this.method = method;
        this.retryType = retryType;
        this.localTimes = localTimes;
        this.localInterval = localInterval;
        this.idempotentIdGenerate = idempotentIdGenerate;
        this.bizNo = bizNo;
        this.executorMethod = executorMethod;
        this.isThrowException = isThrowException;
        this.retryCompleteCallback = retryCompleteCallback;
        this.async = async;
        this.forceReport = forceReport;
        this.timeout = timeout;
        this.unit = unit;
        this.retryCondition = retryCondition;
    }

    public String getScene() {
        return scene;
    }

    public String getExecutorClassName() {
        return executorClassName;
    }

    public Set<Class<? extends Throwable>> getInclude() {
        return include;
    }

    public Set<Class<? extends Throwable>> getExclude() {
        return exclude;
    }

    public Object getExecutor() {
        return executor;
    }

    public Method getMethod() {
        return method;
    }

    public RetryType getRetryType() {
        return retryType;
    }

    public Integer getLocalTimes() {
        return localTimes;
    }

    public Integer getLocalInterval() {
        return localInterval;
    }

    public Class<? extends IdempotentIdGenerate> getIdempotentIdGenerate() {
        return idempotentIdGenerate;
    }

    public String getBizNo() {
        return bizNo;
    }

    public Class<? extends ExecutorMethod> getExecutorMethod() {
        return executorMethod;
    }

    public boolean isThrowException() {
        return isThrowException;
    }

    public Class<? extends RetryCompleteCallback> getRetryCompleteCallback() {
        return retryCompleteCallback;
    }

    public boolean isAsync() {
        return async;
    }

    public boolean isForceReport() {
        return forceReport;
    }

    public long getTimeout() {
        return timeout;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public Class<? extends RetryCondition> getRetryCondition() {
        return retryCondition;
    }
}
