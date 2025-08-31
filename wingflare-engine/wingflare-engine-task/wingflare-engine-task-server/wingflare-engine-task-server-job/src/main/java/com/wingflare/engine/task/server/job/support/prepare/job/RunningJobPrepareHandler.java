package com.wingflare.engine.task.server.job.support.prepare.job;

import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.CompleteJobBatchDTO;
import com.wingflare.engine.task.server.job.dto.JobTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.job.dto.JobTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.BlockStrategy;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.JobTaskStopHandler;
import com.wingflare.engine.task.server.job.support.block.job.BlockStrategyContext;
import com.wingflare.engine.task.server.job.support.block.job.JobBlockStrategyFactory;
import com.wingflare.engine.task.server.job.support.handler.JobTaskBatchHandler;
import com.wingflare.engine.task.server.job.support.stop.JobTaskStopFactory;
import com.wingflare.engine.task.server.job.support.stop.TaskStopJobContext;
import com.wingflare.lib.core.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 处理处于{@link JobTaskBatchStatusEnum::RUNNING}状态的任务
 *
 * @author opensnail
 * @date 2023-10-05 18:29:22
 * @since 2.4.0
 */
@Component
public class RunningJobPrepareHandler extends AbstractJobPrepareHandler {

    private static final Logger log = LoggerFactory.getLogger(RunningJobPrepareHandler.class);
    @Resource
    private JobTaskBatchHandler jobTaskBatchHandler;

    @Override
    public boolean matches(Integer status) {
        return JobTaskBatchStatusEnum.RUNNING.getStatus() == status;
    }

    @Override
    protected void doHandle(JobTaskPrepareDTO prepare) {
        log.debug("Running tasks exist. Prepare:[{}]", JsonUtil.toJsonString(prepare));

        // 若存在所有的任务都是完成，但是批次上的状态为运行中，则是并发导致的未把批次状态变成为终态，此处做一次兜底处理
        int blockStrategy = prepare.getBlockStrategy();
        JobOperationReasonEnum jobOperationReasonEnum = JobOperationReasonEnum.NONE;
        CompleteJobBatchDTO completeJobBatchDTO = JobTaskConverter.INSTANCE.completeJobBatchDTO(prepare);
        completeJobBatchDTO.setJobOperationReason(jobOperationReasonEnum.getReason());
        completeJobBatchDTO.setRetryStatus(Boolean.FALSE);
        if (jobTaskBatchHandler.handleResult(completeJobBatchDTO)) {
            blockStrategy = JobBlockStrategyEnum.CONCURRENCY.getBlockStrategy();
        } else {
            // 计算超时时间
            long delay = DateUtils.toNowMilli() - prepare.getExecutionAt();

            // 计算超时时间，到达超时时间中断任务
            if (delay > DateUtils.toEpochMilli(prepare.getExecutorTimeout())) {
                log.info("Task execution timeout. Task batch ID:[{}] Delay:[{}] Executor timeout:[{}]", prepare.getTaskBatchId(), delay, DateUtils.toEpochMilli(prepare.getExecutorTimeout()));
                // 超时停止任务
                JobTaskStopHandler instanceInterrupt = JobTaskStopFactory.getJobTaskStop(prepare.getTaskType());
                TaskStopJobContext stopJobContext = JobTaskConverter.INSTANCE.toStopJobContext(prepare);
                stopJobContext.setJobOperationReason(JobOperationReasonEnum.TASK_EXECUTION_TIMEOUT.getReason());
                stopJobContext.setNeedUpdateTaskStatus(Boolean.TRUE);
                instanceInterrupt.stop(stopJobContext);

                SnailSpringContext.getContext().publishEvent(
                        Builder.of(JobTaskFailAlarmEventDTO::new)
                                .with(JobTaskFailAlarmEventDTO::setJobTaskBatchId, prepare.getTaskBatchId())
                                .build());
            }
        }

        // 仅是超时检测的，不执行阻塞策略
        if (prepare.isOnlyTimeoutCheck()) {
            return;
        }

        BlockStrategyContext blockStrategyContext = JobTaskConverter.INSTANCE.toBlockStrategyContext(prepare);
        blockStrategyContext.setOperationReason(jobOperationReasonEnum.getReason());
        BlockStrategy blockStrategyInterface = JobBlockStrategyFactory.getBlockStrategy(blockStrategy);
        blockStrategyInterface.block(blockStrategyContext);

    }

}
