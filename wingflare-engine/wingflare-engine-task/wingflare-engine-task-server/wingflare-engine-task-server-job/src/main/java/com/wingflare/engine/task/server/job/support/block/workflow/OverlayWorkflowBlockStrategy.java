package com.wingflare.engine.task.server.job.support.block.workflow;

import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.server.job.support.WorkflowTaskConverter;
import com.wingflare.engine.task.server.job.support.generator.batch.WorkflowBatchGenerator;
import com.wingflare.engine.task.server.job.support.generator.batch.WorkflowTaskBatchGeneratorContext;
import com.wingflare.engine.task.server.job.support.handler.WorkflowBatchHandler;
import org.springframework.stereotype.Component;

/**
 * @author: xiaowoniu
 * @date : 2023-12-26
 * @since : 2.6.0
 */
@Component
public class OverlayWorkflowBlockStrategy extends AbstractWorkflowBlockStrategy {

    private final WorkflowBatchHandler workflowBatchHandler;
    private final WorkflowBatchGenerator workflowBatchGenerator;

    public OverlayWorkflowBlockStrategy(WorkflowBatchHandler workflowBatchHandler, WorkflowBatchGenerator workflowBatchGenerator) {
        this.workflowBatchHandler = workflowBatchHandler;
        this.workflowBatchGenerator = workflowBatchGenerator;
    }

    @Override
    protected void doBlock(final WorkflowBlockStrategyContext workflowBlockStrategyContext) {

        // 停止当前批次
        workflowBatchHandler.stop(workflowBlockStrategyContext.getWorkflowTaskBatchId(), workflowBlockStrategyContext.getOperationReason());

        // 重新生成一个批次
        WorkflowTaskBatchGeneratorContext workflowTaskBatchGeneratorContext = WorkflowTaskConverter.INSTANCE.toWorkflowTaskBatchGeneratorContext(
                workflowBlockStrategyContext);
        workflowBatchGenerator.generateJobTaskBatch(workflowTaskBatchGeneratorContext);

    }

    @Override
    protected JobBlockStrategyEnum blockStrategyEnum() {
        return JobBlockStrategyEnum.OVERLAY;
    }
}
