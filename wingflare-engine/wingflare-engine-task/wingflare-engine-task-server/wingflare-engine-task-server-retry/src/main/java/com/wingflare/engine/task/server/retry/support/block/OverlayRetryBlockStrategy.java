package com.wingflare.engine.task.server.retry.support.block;

import com.wingflare.engine.task.common.core.enums.*;
import com.wingflare.engine.task.server.retry.dto.RetryTaskGeneratorDTO;
import com.wingflare.engine.task.server.retry.dto.TaskStopJobDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.generator.task.RetryTaskGeneratorHandler;
import com.wingflare.engine.task.server.retry.support.handler.RetryTaskStopHandler;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: opensnail
 * @date : 2025-02-10
 * @since : sj_1.4.0
 */
@Component
public class OverlayRetryBlockStrategy extends AbstracJobBlockStrategy {
    private final RetryTaskGeneratorHandler retryTaskGeneratorHandler;
    private final RetryTaskStopHandler retryTaskStopHandler;

    public OverlayRetryBlockStrategy(RetryTaskGeneratorHandler retryTaskGeneratorHandler, RetryTaskStopHandler retryTaskStopHandler) {
        this.retryTaskGeneratorHandler = retryTaskGeneratorHandler;
        this.retryTaskStopHandler = retryTaskStopHandler;
    }

    @Override
    public void doBlock(final BlockStrategyContext context) {

        // 重新生成任务
        RetryTaskGeneratorDTO generatorDTO = RetryTaskConverter.INSTANCE.toRetryTaskGeneratorDTO(context);
        generatorDTO.setTaskStatus(RetryTaskStatusEnum.CANCEL.getStatus());
        generatorDTO.setOperationReason(RetryOperationReasonEnum.RETRY_TASK_DISCARD.getReason());
        retryTaskGeneratorHandler.generateRetryTask(generatorDTO);

        TaskStopJobDTO stopJobDTO = RetryTaskConverter.INSTANCE.toTaskStopJobDTO(context);
        if (Objects.isNull(context.getOperationReason()) || context.getOperationReason() == JobOperationReasonEnum.NONE.getReason()) {
            stopJobDTO.setOperationReason(RetryOperationReasonEnum.RETRY_TASK_OVERLAY.getReason());
        }

        stopJobDTO.setNeedUpdateTaskStatus(true);
        retryTaskStopHandler.stop(stopJobDTO);

    }

    @Override
    protected RetryBlockStrategyEnum blockStrategyEnum() {
        return RetryBlockStrategyEnum.OVERLAY;
    }
}
