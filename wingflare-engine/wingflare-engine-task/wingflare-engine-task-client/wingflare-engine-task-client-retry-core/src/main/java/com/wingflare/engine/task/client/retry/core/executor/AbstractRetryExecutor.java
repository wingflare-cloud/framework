package com.wingflare.engine.task.client.retry.core.executor;


import com.wingflare.engine.task.client.retry.core.RetryExecutor;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerInfo;
import com.wingflare.engine.task.client.retry.core.strategy.ExecutorAnnotationMethod;
import com.wingflare.engine.task.client.retry.core.strategy.ExecutorMethod;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;

/**
 * @author: opensnail
 * @date : 2022-03-03 18:08
 */
public abstract class AbstractRetryExecutor<BR, SR> implements RetryExecutor<BR, SR> {

    protected RetryerInfo retryerInfo;

    @Override
    public Object execute(Object... params) {
        return doExecute(params);
    }

    public Object doExecute(Object... params) {

        Class<? extends ExecutorMethod> retryMethodClass = retryerInfo.getExecutorMethod();
        if (retryMethodClass.isAssignableFrom(ExecutorAnnotationMethod.class)) {
            TaskEngineLog.LOCAL.debug("Executing annotated retry method: {}, parameters: {}", retryMethodClass.getName(), JsonUtil.toJsonString(params));
            ExecutorAnnotationMethod retryAnnotationMethod = new ExecutorAnnotationMethod(retryerInfo);
            return retryAnnotationMethod.doExecute(params);
        } else {
            TaskEngineLog.LOCAL.debug("Executing custom retry method: {}, parameters: {}", retryMethodClass.getName(), JsonUtil.toJsonString(params));
            ExecutorMethod executorMethod = SnailSpringContext.getBeanByType(retryMethodClass);
            return executorMethod.doExecute(params);
        }
    }

    @Override
    public RetryerInfo getRetryerInfo() {
        return retryerInfo;
    }

}
