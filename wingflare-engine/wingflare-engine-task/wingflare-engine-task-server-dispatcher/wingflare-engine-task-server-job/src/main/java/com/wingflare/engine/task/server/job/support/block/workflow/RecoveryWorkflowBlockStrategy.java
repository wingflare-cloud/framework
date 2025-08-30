package com.wingflare.engine.task.server.job.support.block.workflow;

import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
import com.wingflare.engine.task.server.job.support.handler.WorkflowBatchHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: opensnail
 * @date : 2024-06-26
 * @since : sj_1.1.0
 */
@Component
public class RecoveryWorkflowBlockStrategy extends AbstractWorkflowBlockStrategy {
    private final WorkflowBatchHandler workflowBatchHandler;

    public RecoveryWorkflowBlockStrategy(WorkflowBatchHandler workflowBatchHandler) {
        this.workflowBatchHandler = workflowBatchHandler;
    }

    @Override
    protected void doBlock(final WorkflowBlockStrategyContext workflowBlockStrategyContext) {

        try {
            workflowBatchHandler.recoveryWorkflowExecutor(workflowBlockStrategyContext.getWorkflowTaskBatchId(), null);
        } catch (IOException e) {
            throw new SnailJobServerException("Workflow validation failed", e);
        }
    }

    @Override
    protected JobBlockStrategyEnum blockStrategyEnum() {
        return JobBlockStrategyEnum.RECOVERY;
    }
}
