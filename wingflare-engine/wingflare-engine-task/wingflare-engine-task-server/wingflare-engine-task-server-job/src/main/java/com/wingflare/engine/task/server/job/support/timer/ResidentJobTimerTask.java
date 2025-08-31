package com.wingflare.engine.task.server.job.support.timer;

import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.common.enums.JobTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.JobTaskPrepareDTO;
import com.wingflare.engine.task.server.job.dto.JobTimerTaskDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.task.datasource.template.persistence.po.Job;
import io.netty.util.Timeout;
import org.apache.pekko.actor.ActorRef;

import java.text.MessageFormat;

/**
 * @author opensnail
 * @date 2023-10-20 23:09:13
 * @since 2.4.0
 */
public class ResidentJobTimerTask implements TimerTask<String> {
    private static final String IDEMPOTENT_KEY_PREFIX = " resident_job_{0}";

    private JobTimerTaskDTO jobTimerTaskDTO;
    private Job job;

    public ResidentJobTimerTask(JobTimerTaskDTO jobTimerTaskDTO, Job job) {
        this.jobTimerTaskDTO = jobTimerTaskDTO;
        this.job = job;
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        try {
            // 清除时间轮的缓存
            JobTimerWheel.clearCache(idempotentKey());
            JobTaskPrepareDTO jobTaskPrepare = JobTaskConverter.INSTANCE.toJobTaskPrepare(job);
            jobTaskPrepare.setTaskExecutorScene(JobTaskExecutorSceneEnum.AUTO_JOB.getType());
            // 执行预处理阶段
            ActorRef actorRef = ActorGenerator.jobTaskPrepareActor();
            actorRef.tell(jobTaskPrepare, actorRef);
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Task scheduling execution failed", e);
        }
    }

    @Override
    public String idempotentKey() {
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, jobTimerTaskDTO.getTaskBatchId());
    }
}
