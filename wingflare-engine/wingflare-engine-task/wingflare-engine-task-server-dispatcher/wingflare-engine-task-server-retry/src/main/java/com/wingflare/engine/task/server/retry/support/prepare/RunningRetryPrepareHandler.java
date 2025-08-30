package com.wingflare.engine.task.server.retry.support.prepare;

import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.RetryOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;
import com.wingflare.engine.task.server.retry.dto.TaskStopJobDTO;
import com.wingflare.engine.task.server.retry.support.BlockStrategy;
import com.wingflare.engine.task.server.retry.support.RetryPrePareHandler;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.block.BlockStrategyContext;
import com.wingflare.engine.task.server.retry.support.block.RetryBlockStrategyFactory;
import com.wingflare.engine.task.server.retry.support.handler.RetryTaskStopHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-26
 */
@Component
public class RunningRetryPrepareHandler implements RetryPrePareHandler {
    private static final Logger log = LoggerFactory.getLogger(RunningRetryPrepareHandler.class);
    private final RetryTaskStopHandler retryTaskStopHandler;

    public RunningRetryPrepareHandler(RetryTaskStopHandler retryTaskStopHandler) {
        this.retryTaskStopHandler = retryTaskStopHandler;
    }

    @Override
    public boolean matches(Integer status) {
        return Objects.equals(RetryTaskStatusEnum.RUNNING.getStatus(), status);
    }

    @Override
    public void handle(RetryTaskPrepareDTO prepare) {
        // 若存在所有的任务都是完成，但是批次上的状态为运行中，则是并发导致的未把批次状态变成为终态，此处做一次兜底处理
        int blockStrategy = prepare.getBlockStrategy();
        JobOperationReasonEnum jobOperationReasonEnum = JobOperationReasonEnum.NONE;

        // 计算超时时间
        long delay = DateUtils.toNowMilli() - prepare.getNextTriggerAt();

        // 计算超时时间，到达超时时间中断任务
        if (delay > DateUtils.toEpochMilli(prepare.getExecutorTimeout())) {
            log.info("Task execution timeout. RetryTaskId:[{}] Delay:[{}] ExecutorTimeout:[{}]", prepare.getRetryTaskId(), delay, DateUtils.toEpochMilli(prepare.getExecutorTimeout()));
            // 超时停止任务
            TaskStopJobDTO stopJobDTO = RetryTaskConverter.INSTANCE.toTaskStopJobDTO(prepare);
            stopJobDTO.setOperationReason(RetryOperationReasonEnum.TASK_EXECUTION_TIMEOUT.getReason());
            stopJobDTO.setNeedUpdateTaskStatus(true);
            retryTaskStopHandler.stop(stopJobDTO);
        }

        // 仅是超时检测的，不执行阻塞策略
        if (prepare.isOnlyTimeoutCheck()) {
            return;
        }

        BlockStrategyContext blockStrategyContext = RetryTaskConverter.INSTANCE.toBlockStrategyContext(prepare);
        blockStrategyContext.setOperationReason(jobOperationReasonEnum.getReason());
        BlockStrategy blockStrategyInterface = RetryBlockStrategyFactory.getBlockStrategy(blockStrategy);
        blockStrategyInterface.block(blockStrategyContext);

    }
}
