package com.wingflare.engine.task.server.job.support;

import com.wingflare.engine.task.common.core.enums.WorkflowNodeTypeEnum;
import com.wingflare.engine.task.server.job.support.executor.workflow.WorkflowExecutorContext;

/**
 * @author opensnail
 * @date 2023-09-24 11:40:21
 * @since 2.4.0
 */
public interface WorkflowExecutor {

    WorkflowNodeTypeEnum getWorkflowNodeType();

    void execute(WorkflowExecutorContext context);
}
