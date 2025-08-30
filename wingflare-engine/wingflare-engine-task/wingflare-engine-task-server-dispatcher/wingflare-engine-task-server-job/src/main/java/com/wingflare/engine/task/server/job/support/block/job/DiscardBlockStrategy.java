package com.wingflare.engine.task.server.job.support.block.job;

import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.generator.batch.JobTaskBatchGenerator;
import com.wingflare.engine.task.server.job.support.generator.batch.JobTaskBatchGeneratorContext;
import org.springframework.stereotype.Component;

/**
 * @author: xiaowoniu
 * @date : 2024-01-18
 * @since : 2.6.0
 */
@Component
public class DiscardBlockStrategy extends AbstracJobBlockStrategy {
    private final JobTaskBatchGenerator jobTaskBatchGenerator;

    public DiscardBlockStrategy(JobTaskBatchGenerator jobTaskBatchGenerator) {
        this.jobTaskBatchGenerator = jobTaskBatchGenerator;
    }

    @Override
    public void doBlock(final BlockStrategyContext context) {
        // 重新生成任务
        JobTaskBatchGeneratorContext jobTaskBatchGeneratorContext = JobTaskConverter.INSTANCE.toJobTaskGeneratorContext(context);
        jobTaskBatchGeneratorContext.setTaskBatchStatus(JobTaskBatchStatusEnum.CANCEL.getStatus());
        jobTaskBatchGeneratorContext.setOperationReason(JobOperationReasonEnum.JOB_DISCARD.getReason());
        jobTaskBatchGenerator.generateJobTaskBatch(jobTaskBatchGeneratorContext);
    }

    @Override
    protected JobBlockStrategyEnum blockStrategyEnum() {
        return JobBlockStrategyEnum.DISCARD;
    }
}
