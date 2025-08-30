package com.wingflare.engine.task.server.retry.support.generator.task;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.retry.dto.RetryTaskGeneratorDTO;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.timer.RetryTimerContext;
import com.wingflare.engine.task.server.retry.support.timer.RetryTimerTask;
import com.wingflare.engine.task.server.retry.support.timer.RetryTimerWheel;
import com.wingflare.task.datasource.template.persistence.mapper.RetryTaskMapper;
import com.wingflare.task.datasource.template.persistence.po.RetryTask;
import org.springframework.stereotype.Component;

import java.time.Duration;
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
public class RetryTaskGeneratorHandler {
    private final RetryTaskMapper retryTaskMapper;

    public RetryTaskGeneratorHandler(RetryTaskMapper retryTaskMapper) {
        this.retryTaskMapper = retryTaskMapper;
    }

    /**
     * 生成重试任务
     *
     * @param generator RetryTaskGeneratorContext
     */
    public void generateRetryTask(RetryTaskGeneratorDTO generator) {

        RetryTask retryTask = RetryTaskConverter.INSTANCE.toRetryTask(generator);
        Integer taskStatus = generator.getTaskStatus();
        if (Objects.isNull(taskStatus)) {
            taskStatus = RetryTaskStatusEnum.WAITING.getStatus();
        }
        retryTask.setTaskStatus(taskStatus);
        retryTask.setOperationReason(generator.getOperationReason());

        retryTask.setExtAttrs(StrUtil.EMPTY);
        Assert.isTrue(1 == retryTaskMapper.insert(retryTask), () -> new SnailJobServerException("Inserting retry task failed"));

        if (!RetryTaskStatusEnum.WAITING.getStatus().equals(taskStatus)) {
            return;
        }

        // 放到到时间轮
        long delay = generator.getNextTriggerAt() - DateUtils.toNowMilli();
        RetryTimerContext timerContext = RetryTaskConverter.INSTANCE.toRetryTimerContext(generator);
        timerContext.setRetryTaskId(retryTask.getId());
        RetryTimerWheel.registerWithRetry(() -> new RetryTimerTask(timerContext), Duration.ofMillis(delay));
    }
}
