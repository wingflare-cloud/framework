package com.wingflare.engine.task.server.job.support.block.job;

import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.JobTaskStopHandler;
import com.wingflare.engine.task.server.job.support.generator.batch.JobTaskBatchGenerator;
import com.wingflare.engine.task.server.job.support.generator.batch.JobTaskBatchGeneratorContext;
import com.wingflare.engine.task.server.job.support.stop.JobTaskStopFactory;
import com.wingflare.engine.task.server.job.support.stop.TaskStopJobContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: xiaowoniu
 * @date : 2024-01-18
 * @since : 2.6.0
 */
@Component
public class OverlayBlockStrategy extends AbstracJobBlockStrategy {
    private final JobTaskBatchGenerator jobTaskBatchGenerator;

    public OverlayBlockStrategy(JobTaskBatchGenerator jobTaskBatchGenerator) {
        this.jobTaskBatchGenerator = jobTaskBatchGenerator;
    }

    @Override
    public void doBlock(final BlockStrategyContext context) {

        // 重新生成任务
        JobTaskBatchGeneratorContext jobTaskBatchGeneratorContext = JobTaskConverter.INSTANCE.toJobTaskGeneratorContext(context);
        jobTaskBatchGenerator.generateJobTaskBatch(jobTaskBatchGeneratorContext);

        // 停止任务
        JobTaskStopHandler instanceInterrupt = JobTaskStopFactory.getJobTaskStop(context.getTaskType());
        TaskStopJobContext stopJobContext = JobTaskConverter.INSTANCE.toStopJobContext(context);

        Integer operationReason = context.getOperationReason();
        if (Objects.isNull(context.getOperationReason()) || context.getOperationReason() == JobOperationReasonEnum.NONE.getReason()) {
            operationReason = JobOperationReasonEnum.JOB_OVERLAY.getReason();
        }

        stopJobContext.setJobOperationReason(operationReason);
        stopJobContext.setNeedUpdateTaskStatus(Boolean.TRUE);
        instanceInterrupt.stop(stopJobContext);
    }

    @Override
    protected JobBlockStrategyEnum blockStrategyEnum() {
        return JobBlockStrategyEnum.OVERLAY;
    }
}
