package com.wingflare.engine.task.server.web.timer;

import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.common.enums.WebSocketSceneEnum;
import com.wingflare.engine.task.server.web.model.request.RetryTaskLogMessageQueryVO;
import com.wingflare.engine.task.server.web.service.RetryTaskService;
import io.netty.util.Timeout;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @Author：xiaochaihu
 * @Package：com.wingflare.engine.task.server.common.timer
 * @Project：snail-job
 * @Date：2025/4/15 22:15
 * @Filename：RetryTaskLogTimerTask
 * @since 1.5.0
 */
public class RetryTaskLogTimerTask implements TimerTask<String> {
    private static final String IDEMPOTENT_KEY_PREFIX = "retryTaskLog_{0}_{1}_{2}";
    private RetryTaskLogMessageQueryVO logQueryVO;
    private String sid;

    public RetryTaskLogTimerTask(RetryTaskLogMessageQueryVO logQueryVO, String sid) {
        this.logQueryVO = logQueryVO;
        this.sid = sid;
    }

    @Override
    public void run(final Timeout timeout) throws Exception {
        SnailJobLog.LOCAL.debug("Start querying scheduled task logs. Current time:[{}] retryTaskId:[{}]", LocalDateTime.now(), logQueryVO.getRetryTaskId());

        try {
            LogTimerWheel.clearCache(idempotentKey());
            RetryTaskService logService = SnailSpringContext.getBean(RetryTaskService.class);
            logService.getRetryTaskLogMessagePage(logQueryVO);
        } catch (Exception e) {
            SnailJobLog.LOCAL.error("Scheduled task log query execution failed", e);
        }
    }

    @Override
    public String idempotentKey() {
        Long taskId = logQueryVO.getRetryTaskId();
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, sid, WebSocketSceneEnum.JOB_LOG_SCENE, taskId);
    }
}
