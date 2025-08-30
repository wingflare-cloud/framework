package com.wingflare.engine.task.server.retry.support.prepare;

import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;
import com.wingflare.engine.task.server.retry.support.RetryPrePareHandler;
import com.wingflare.engine.task.server.retry.support.RetryTaskConverter;
import com.wingflare.engine.task.server.retry.support.timer.RetryTimerContext;
import com.wingflare.engine.task.server.retry.support.timer.RetryTimerTask;
import com.wingflare.engine.task.server.retry.support.timer.RetryTimerWheel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
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
public class WaitRetryPrepareHandler implements RetryPrePareHandler  {

    private static final Logger log = LoggerFactory.getLogger(WaitRetryPrepareHandler.class);

    @Override
    public boolean matches(Integer status) {
        return Objects.equals(RetryTaskStatusEnum.WAITING.getStatus(), status);
    }

    @Override
    public void handle(RetryTaskPrepareDTO prepare) {
        // 若时间轮中数据不存在则重新加入
        if (!RetryTimerWheel.isExisted(MessageFormat.format(RetryTimerTask.IDEMPOTENT_KEY_PREFIX, prepare.getRetryTaskId()))) {
            log.info("There are pending tasks and no retryTaskId:[{}] in the time wheel", prepare.getRetryTaskId());

            // 进入时间轮
            long delay = prepare.getNextTriggerAt() - DateUtils.toNowMilli();
            RetryTimerContext timerContext = RetryTaskConverter.INSTANCE.toRetryTimerContext(prepare);
            RetryTimerWheel.registerWithRetry(() -> new RetryTimerTask(timerContext), Duration.ofMillis(delay));
        }
    }
}
