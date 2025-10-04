package com.wingflare.engine.task.client.core.executor;


import com.wingflare.engine.task.client.core.MapHandler;
import com.wingflare.engine.task.client.core.dto.MapArgs;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;

/**
 * 基于注解的Map任务执行器
 *
 * @author opensnail
 * @date 2024-06-26 22:20:36
 * @since sj_1.1.0
 */
public class AnnotationMapJobExecutor extends AbstractMapExecutor {

    @Override
    public ExecuteResult doJobMapExecute(final MapArgs mapArgs, final MapHandler mapHandler) {
        return invokeMapExecute(mapArgs, mapHandler);
    }
}
