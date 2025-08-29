package com.wingflare.engine.task.client.core.executor;

import com.wingflare.engine.task.client.core.MapHandler;
import com.wingflare.engine.task.client.core.cache.JobExecutorInfoCache;
import com.wingflare.engine.task.client.core.dto.JobExecutorInfo;
import com.wingflare.engine.task.client.core.dto.MapArgs;
import com.wingflare.engine.task.client.core.dto.MergeReduceArgs;
import com.wingflare.engine.task.client.core.dto.ReduceArgs;
import com.wingflare.engine.task.common.core.exception.SnailJobMapReduceException;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 基于注解的MapReduce执行器
 *
 * @author opensnail
 * @date 2024-06-26 22:20:36
 * @since sj_1.1.0
 */
@Component
public class AnnotationMapReduceJobExecutor extends AbstractMapReduceExecutor {

    @Override
    protected ExecuteResult doReduceExecute(final ReduceArgs reduceArgs) {
        JobExecutorInfo jobExecutorInfo = JobExecutorInfoCache.get(reduceArgs.getExecutorInfo());
        if (Objects.isNull(jobExecutorInfo)) {
            throw new SnailJobMapReduceException("[{}] not found", reduceArgs.getExecutorInfo());
        }

        if (Objects.isNull(jobExecutorInfo.getReduceExecutor())) {
            throw new SnailJobMapReduceException(
                "[{}] MapTask execution method not found. Please configure the @ReduceExecutor annotation",
                reduceArgs.getExecutorInfo());
        }

        Class<?>[] paramTypes = jobExecutorInfo.getReduceExecutor().getParameterTypes();
        if (paramTypes.length > 0) {
            return (ExecuteResult) ReflectionUtils.invokeMethod(jobExecutorInfo.getReduceExecutor(),
                jobExecutorInfo.getExecutor(), reduceArgs);
        }

        throw new SnailJobMapReduceException("[{}] ReduceTask execution method not found", reduceArgs.getExecutorInfo());
    }

    @Override
    protected ExecuteResult doMergeReduceExecute(final MergeReduceArgs mergeReduceArgs) {
        JobExecutorInfo jobExecutorInfo = JobExecutorInfoCache.get(mergeReduceArgs.getExecutorInfo());

        if (Objects.isNull(jobExecutorInfo)) {
            throw new SnailJobMapReduceException("[{}] not found", mergeReduceArgs.getExecutorInfo());
        }

        Method mergeReduceExecutor = jobExecutorInfo.getMergeReduceExecutor();
        if (Objects.isNull(mergeReduceExecutor)) {
            throw new SnailJobMapReduceException(
                "[{}] MapTask execution method not found. Please configure the @MergeReduceExecutor annotation",
                mergeReduceArgs.getExecutorInfo());
        }

        Class<?>[] paramTypes = mergeReduceExecutor.getParameterTypes();
        if (paramTypes.length > 0) {
            return (ExecuteResult) ReflectionUtils.invokeMethod(mergeReduceExecutor,
                jobExecutorInfo.getExecutor(), mergeReduceArgs);
        }

        throw new SnailJobMapReduceException("[{}] MergeReduceTask execution method not found [{}]",
            mergeReduceArgs.getExecutorInfo());

    }

    @Override
    public ExecuteResult doJobMapExecute(final MapArgs mapArgs, final MapHandler mapHandler) {
        return invokeMapExecute(mapArgs, mapHandler);
    }
}
