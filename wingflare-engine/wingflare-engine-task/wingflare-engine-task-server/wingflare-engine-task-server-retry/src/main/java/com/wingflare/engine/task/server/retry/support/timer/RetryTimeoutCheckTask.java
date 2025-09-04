package com.wingflare.engine.task.server.retry.support.timer;

import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.retry.dto.TaskStopJobDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.handler.RetryTaskStopHandler;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.wingflare.engine.task.datasource.template.persistence.po.RetryTask;
import io.netty.util.Timeout;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * 任务超时检查
 *
 * @author opensnail
 * @date 2024-05-20 21:16:09
 * @since sj_1.0.0
 */
public class RetryTimeoutCheckTask implements TimerTask<String> {
    private static final String IDEMPOTENT_KEY_PREFIX = "retry_timeout_check_{0}";

    private final Long retryTaskId;
    private final Long retryId;
    private final RetryTaskStopHandler retryTaskStopHandler;
    private final RetryMapper retryMapper;
    private final RetryTaskMapper retryTaskMapper;

    public RetryTimeoutCheckTask(Long retryTaskId, Long retryId, RetryTaskStopHandler retryTaskStopHandler, RetryMapper retryMapper, RetryTaskMapper retryTaskMapper) {
        this.retryTaskId = retryTaskId;
        this.retryId = retryId;
        this.retryTaskStopHandler = retryTaskStopHandler;
        this.retryMapper = retryMapper;
        this.retryTaskMapper = retryTaskMapper;
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        RetryTimerWheel.clearCache(idempotentKey());
        RetryTask retryTask = retryTaskMapper.selectById(retryTaskId);
        if (Objects.isNull(retryTask)) {
            TaskEngineLog.LOCAL.error("RetryTaskId:[{}] does not exist", retryTaskId);
            return;
        }

        // 已经完成了，无需重复停止任务
        if (RetryTaskStatusEnum.TERMINAL_STATUS_SET.contains(retryTask.getTaskStatus())) {
            return;
        }

        Retry retry = retryMapper.selectById(retryId);
        if (Objects.isNull(retry)) {
            TaskEngineLog.LOCAL.error("RetryId:[{}] does not exist", retryId);
            return;
        }

        // 超时停止任务
        String reason = "Timeout interrupt. RetryTaskId:[" + retryTaskId + "]";

        TaskStopJobDTO stopJobDTO = RetryTaskConverter.INSTANCE.toTaskStopJobDTO(retry);
        stopJobDTO.setRetryTaskId(retryTaskId);
        stopJobDTO.setRetryId(retryId);
        stopJobDTO.setOperationReason(JobOperationReasonEnum.TASK_EXECUTION_TIMEOUT.getReason());
        stopJobDTO.setNeedUpdateTaskStatus(true);
        retryTaskStopHandler.stop(stopJobDTO);

        TaskEngineLog.LOCAL.info(reason);
    }

    @Override
    public String idempotentKey() {
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, retryId);
    }
}
