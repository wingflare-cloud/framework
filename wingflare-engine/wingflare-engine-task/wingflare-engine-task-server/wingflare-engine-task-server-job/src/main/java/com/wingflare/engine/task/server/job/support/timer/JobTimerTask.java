package com.wingflare.engine.task.server.job.support.timer;

import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.JobTimerTaskDTO;
import com.wingflare.engine.task.server.job.dto.TaskExecuteDTO;
import io.netty.util.Timeout;
import org.apache.pekko.actor.ActorRef;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @author: opensnail
 * @date : 2023-09-25 17:28
 * @since 2.4.0
 */
public class JobTimerTask implements TimerTask<String> {
    public static final String IDEMPOTENT_KEY_PREFIX = "job_{0}";
    private JobTimerTaskDTO jobTimerTaskDTO;

    public JobTimerTask(JobTimerTaskDTO jobTimerTaskDTO) {
        this.jobTimerTaskDTO = jobTimerTaskDTO;
    }

    @Override
    public void run(final Timeout timeout) throws Exception {
        // 执行任务调度
        SnailJobLog.LOCAL.debug("Start task scheduling. Current time:[{}] Task ID:[{}]", LocalDateTime.now(), jobTimerTaskDTO.getTaskBatchId());

        try {
            TaskExecuteDTO taskExecuteDTO = new TaskExecuteDTO();
            taskExecuteDTO.setTaskBatchId(jobTimerTaskDTO.getTaskBatchId());
            taskExecuteDTO.setJobId(jobTimerTaskDTO.getJobId());
            taskExecuteDTO.setTaskExecutorScene(jobTimerTaskDTO.getTaskExecutorScene());
            taskExecuteDTO.setWorkflowTaskBatchId(jobTimerTaskDTO.getWorkflowTaskBatchId());
            taskExecuteDTO.setWorkflowNodeId(jobTimerTaskDTO.getWorkflowNodeId());
            taskExecuteDTO.setTmpArgsStr(jobTimerTaskDTO.getTmpArgsStr());
            ActorRef actorRef = ActorGenerator.jobTaskExecutorActor();
            actorRef.tell(taskExecuteDTO, actorRef);

        } catch (Exception e) {
            SnailJobLog.LOCAL.error("Task scheduling execution failed", e);
        }
    }

    @Override
    public String idempotentKey() {
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, jobTimerTaskDTO.getTaskBatchId());
    }
}
