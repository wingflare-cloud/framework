package com.wingflare.engine.task.server.job.support.timer;

import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.RealJobExecutorDTO;
import io.netty.util.Timeout;
import org.apache.pekko.actor.ActorRef;

import java.text.MessageFormat;
import java.time.LocalDateTime;


public class RetryJobTimerTask implements TimerTask<String> {
    public static final String IDEMPOTENT_KEY_PREFIX = "retry_job_{0}";
    private RealJobExecutorDTO jobExecutorDTO;

    public RetryJobTimerTask(RealJobExecutorDTO jobExecutorDTO) {
        this.jobExecutorDTO = jobExecutorDTO;
    }

    @Override
    public void run(final Timeout timeout) throws Exception {
        // 执行任务调度
        SnailJobLog.LOCAL.debug("Start retry task scheduling. Current time:[{}] Task ID:[{}]", LocalDateTime.now(), jobExecutorDTO.getTaskBatchId());
        JobTimerWheel.clearCache(idempotentKey());
        try {
            ActorRef actorRef = ActorGenerator.jobRealTaskExecutorActor();
            actorRef.tell(jobExecutorDTO, actorRef);
        } catch (Exception e) {
            SnailJobLog.LOCAL.error("Retry task scheduling execution failed", e);
        }
    }

    @Override
    public String idempotentKey() {
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, jobExecutorDTO.getTaskId());
    }
}
