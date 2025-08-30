package com.wingflare.engine.task.server.retry.support.handler;

import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.retry.dto.RequestRetryExecutorDTO;
import com.wingflare.engine.task.server.retry.dto.RetryExecutorResultDTO;
import com.wingflare.engine.task.server.retry.dto.TaskStopJobDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-10
 */
@Component
public class RetryTaskStopHandler {
    /**
     * 执行停止任务
     *
     * @param stopJobDTO
     */
    public void stop(TaskStopJobDTO stopJobDTO) {

        RequestRetryExecutorDTO executorDTO = RetryTaskConverter.INSTANCE.toRealRetryExecutorDTO(stopJobDTO);
        ActorRef actorRef = ActorGenerator.stopRetryTaskActor();
        actorRef.tell(executorDTO, actorRef);

        // 更新结果为失败
        doHandleResult(stopJobDTO);
    }

    private static void doHandleResult(TaskStopJobDTO stopJobDTO) {
        if (!stopJobDTO.isNeedUpdateTaskStatus()) {
            return;
        }
        RetryExecutorResultDTO executorResultDTO = RetryTaskConverter.INSTANCE.toRetryExecutorResultDTO(stopJobDTO);
        executorResultDTO.setExceptionMsg(stopJobDTO.getMessage());
        executorResultDTO.setTaskStatus(RetryTaskStatusEnum.FAIL.getStatus());
        executorResultDTO.setIncrementRetryCount(true);
        executorResultDTO.setOperationReason(stopJobDTO.getOperationReason());
        ActorRef actorRef = ActorGenerator.retryTaskExecutorResultActor();
        actorRef.tell(executorResultDTO, actorRef);
    }


}
