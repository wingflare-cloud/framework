package com.wingflare.engine.task.server.web.timer;

import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.common.enums.WebSocketSceneEnum;
import com.wingflare.engine.task.server.common.vo.JobLogQueryVO;
import com.wingflare.engine.task.server.web.service.JobLogService;
import io.netty.util.Timeout;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.server.common.timer
 * @Project：wingflare-task
 * @Date：2025/3/24 14:08
 * @Filename：JobTaskLogTimerTask
 * @since 1.5.0
 */
public class JobTaskLogTimerTask implements TimerTask<String> {
    private static final String IDEMPOTENT_KEY_PREFIX = "jobTaskLog_{0}_{1}_{2}";
    private JobLogQueryVO logQueryVO;
    private String sid;

    public JobTaskLogTimerTask(JobLogQueryVO logQueryVO, String sid) {
        this.logQueryVO = logQueryVO;
        this.sid = sid;
    }

    @Override
    public void run(final Timeout timeout) throws Exception {
        TaskEngineLog.LOCAL.debug("Start querying scheduled task logs. Current time:[{}] jobTaskId:[{}]", LocalDateTime.now(), logQueryVO.getTaskBatchId());

        try {
            LogTimerWheel.clearCache(idempotentKey());
            JobLogService logService = SnailSpringContext.getBean(JobLogService.class);
            logService.getJobLogPage(logQueryVO);
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Scheduled task log query execution failed", e);
        }
    }

    @Override
    public String idempotentKey() {

        Long jobTaskId = logQueryVO.getTaskBatchId();
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, sid, WebSocketSceneEnum.JOB_LOG_SCENE, jobTaskId);
    }
}
