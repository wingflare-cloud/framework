package com.wingflare.engine.task.client.core.executor;


import com.wingflare.engine.task.client.core.cache.JobExecutorInfoCache;
import com.wingflare.api.task.JobArgs;
import com.wingflare.engine.task.client.core.dto.JobExecutorInfo;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;
import com.wingflare.lib.core.utils.ReflectionUtil;


/**
 * 基于注解的执行器
 *
 * @author opensnail
 * @date 2023-09-27 22:20:36
 * @since 2.4.0
 */
public class AnnotationJobExecutor extends AbstractJobExecutor {

    @Override
    protected ExecuteResult doJobExecute(final JobArgs jobArgs) {
        JobExecutorInfo jobExecutorInfo = JobExecutorInfoCache.get(jobArgs.getExecutorInfo());
        Class<?>[] paramTypes = jobExecutorInfo.getMethod().getParameterTypes();

        if (paramTypes.length > 0) {
            return (ExecuteResult) ReflectionUtil.invokeMethod(jobExecutorInfo.getMethod(), jobExecutorInfo.getExecutor(), jobArgs);
        } else {
            return (ExecuteResult) ReflectionUtil.invokeMethod(jobExecutorInfo.getMethod(), jobExecutorInfo.getExecutor());
        }
    }
}
