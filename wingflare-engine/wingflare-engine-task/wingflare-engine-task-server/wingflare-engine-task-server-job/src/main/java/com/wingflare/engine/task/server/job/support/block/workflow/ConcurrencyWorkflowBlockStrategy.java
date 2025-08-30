package com.wingflare.engine.task.server.job.support.block.workflow;

import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.server.job.support.WorkflowTaskConverter;
import com.wingflare.engine.task.server.job.support.generator.batch.WorkflowBatchGenerator;
import com.wingflare.engine.task.server.job.support.generator.batch.WorkflowTaskBatchGeneratorContext;
import com.wingflare.engine.task.server.job.support.handler.WorkflowBatchHandler;
import org.springframework.stereotype.Component;

/**
 * @author: shuguang.zhang
 * @date : 2023-12-26
 * @since : 2.6.0
 */
@Component
public class ConcurrencyWorkflowBlockStrategy extends AbstractWorkflowBlockStrategy {
    private final WorkflowBatchGenerator workflowBatchGenerator;
    private final WorkflowBatchHandler workflowBatchHandler;

    public ConcurrencyWorkflowBlockStrategy(WorkflowBatchGenerator workflowBatchGenerator, WorkflowBatchHandler workflowBatchHandler) {
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

        WorkflowTaskBatchGeneratorContext workflowTaskBatchGeneratorContext = WorkflowTaskConverter.INSTANCE.toWorkflowTaskBatchGeneratorContext(workflowBlockStrategyContext);
        workflowBatchGenerator.generateJobTaskBatch(workflowTaskBatchGeneratorContext);
    }

    @Override
    protected JobBlockStrategyEnum blockStrategyEnum() {
        return JobBlockStrategyEnum.CONCURRENCY;
    }
}
