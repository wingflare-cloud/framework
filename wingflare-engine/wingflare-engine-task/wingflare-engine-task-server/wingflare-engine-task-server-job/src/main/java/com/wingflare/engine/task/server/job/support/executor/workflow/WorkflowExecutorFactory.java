package com.wingflare.engine.task.server.job.support.executor.workflow;

import com.wingflare.engine.task.common.core.enums.WorkflowNodeTypeEnum;
import com.wingflare.engine.task.server.job.support.WorkflowExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaowoniu
 * @date 2023-12-24 13:04:09
 * @since 2.6.0
 */
public class WorkflowExecutorFactory {

    private static final ConcurrentHashMap<WorkflowNodeTypeEnum, WorkflowExecutor> CACHE = new ConcurrentHashMap<>();

    protected static void registerJobExecutor(WorkflowNodeTypeEnum workflowNodeTypeEnum, WorkflowExecutor executor) {
        CACHE.put(workflowNodeTypeEnum, executor);
    }

    public static WorkflowExecutor getWorkflowExecutor(Integer type) {
        return CACHE.get(WorkflowNodeTypeEnum.valueOf(type));
    }
}
