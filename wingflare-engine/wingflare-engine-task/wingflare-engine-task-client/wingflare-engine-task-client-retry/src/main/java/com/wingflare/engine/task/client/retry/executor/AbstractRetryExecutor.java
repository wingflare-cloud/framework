package com.wingflare.engine.task.client.retry.executor;


import com.wingflare.engine.task.client.retry.RetryExecutor;
import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;
import com.wingflare.engine.task.client.retry.strategy.ExecutorAnnotationMethod;
import com.wingflare.api.retry.ExecutorMethod;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.lib.container.Container;

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
            ExecutorMethod executorMethod = Container.get(retryMethodClass);
            return executorMethod.doExecute(params);
        }
    }

    @Override
    public RetryerInfo getRetryerInfo() {
        return retryerInfo;
    }

}
