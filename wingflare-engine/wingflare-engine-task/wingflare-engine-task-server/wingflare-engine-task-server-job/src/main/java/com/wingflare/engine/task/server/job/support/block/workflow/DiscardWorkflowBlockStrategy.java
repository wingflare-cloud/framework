package com.wingflare.engine.task.server.job.support.block.workflow;

import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
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
public class DiscardWorkflowBlockStrategy extends AbstractWorkflowBlockStrategy {
    private final WorkflowBatchGenerator workflowBatchGenerator;
    private final WorkflowBatchHandler workflowBatchHandler;

    public DiscardWorkflowBlockStrategy(WorkflowBatchGenerator workflowBatchGenerator, WorkflowBatchHandler workflowBatchHandler) {
        this.workflowBatchGenerator = workflowBatchGenerator;
        this.workflowBatchHandler = workflowBatchHandler;
    }

    @Override
    protected void doBlock(final WorkflowBlockStrategyContext workflowBlockStrategyContext) {

//        try {
//            workflowBatchHandler.recoveryWorkflowExecutor(workflowBlockStrategyContext.getWorkflowTaskBatchId(), null);
//        } catch (IOException e) {
//            throw new TaskServerException("Workflow validation failed", e);
//        }
        // 生成状态为取消的工作流批次
        WorkflowTaskBatchGeneratorContext workflowTaskBatchGeneratorContext = WorkflowTaskConverter.INSTANCE.toWorkflowTaskBatchGeneratorContext(workflowBlockStrategyContext);
        workflowTaskBatchGeneratorContext.setTaskBatchStatus(JobTaskBatchStatusEnum.CANCEL.getStatus());
        workflowTaskBatchGeneratorContext.setOperationReason(JobOperationReasonEnum.JOB_DISCARD.getReason());
        workflowBatchGenerator.generateJobTaskBatch(workflowTaskBatchGeneratorContext);
    }

    @Override
    protected JobBlockStrategyEnum blockStrategyEnum() {
        return JobBlockStrategyEnum.DISCARD;
    }
}
