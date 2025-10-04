package com.wingflare.engine.task.client.retry.strategy;


import com.wingflare.api.retry.ExecutorMethod;
import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.lib.core.utils.ReflectionUtil;

/**
 * @author: opensnail
 * @date : 2022-03-04 17:18
 */
public class ExecutorAnnotationMethod implements ExecutorMethod {

    private RetryerInfo retryerInfo;

    public ExecutorAnnotationMethod(RetryerInfo retryerInfo) {
        this.retryerInfo = retryerInfo;
    }

    @Override
    public Object doExecute(Object params) {
        Class<?>[] paramTypes = retryerInfo.getMethod().getParameterTypes();
        TaskEngineLog.LOCAL.info("Executing original retry method: [{}], parameters: [{}]", retryerInfo.getExecutorClassName(), JsonUtil.toJsonString(params));

        if (paramTypes.length > 0) {
            return ReflectionUtil.invokeMethod(retryerInfo.getMethod(), retryerInfo.getExecutor(), (Object[]) params);
        } else {
            return ReflectionUtil.invokeMethod(retryerInfo.getMethod(), retryerInfo.getExecutor());
        }
    }
}
