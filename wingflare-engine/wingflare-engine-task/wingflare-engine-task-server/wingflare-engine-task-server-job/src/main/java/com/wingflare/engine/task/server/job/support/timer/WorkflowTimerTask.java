package com.wingflare.engine.task.server.job.support.timer;

import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.WorkflowNodeTaskExecuteDTO;
import com.wingflare.engine.task.server.job.dto.WorkflowTimerTaskDTO;
import io.netty.util.Timeout;
import org.apache.pekko.actor.ActorRef;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @author: xiaowoniu
 * @date : 2023-09-25
 * @since 2.6.0
 */
public class WorkflowTimerTask implements TimerTask<String> {
    public static final String IDEMPOTENT_KEY_PREFIX = "workflow_{0}";

    private WorkflowTimerTaskDTO workflowTimerTaskDTO;

    public WorkflowTimerTask(WorkflowTimerTaskDTO workflowTimerTaskDTO) {
        this.workflowTimerTaskDTO = workflowTimerTaskDTO;
    }

    @Override
    public void run(final Timeout timeout) throws Exception {
        // 执行任务调度
        SnailJobLog.LOCAL.debug("Start task scheduling. Current time:[{}] Task ID:[{}]", LocalDateTime.now(), workflowTimerTaskDTO.getWorkflowTaskBatchId());

        try {

            WorkflowNodeTaskExecuteDTO taskExecuteDTO = new WorkflowNodeTaskExecuteDTO();
            taskExecuteDTO.setWorkflowTaskBatchId(workflowTimerTaskDTO.getWorkflowTaskBatchId());
            taskExecuteDTO.setTaskExecutorScene(workflowTimerTaskDTO.getTaskExecutorScene());
            taskExecuteDTO.setParentId(SystemConstants.ROOT);
            ActorRef actorRef = ActorGenerator.workflowTaskExecutorActor();
            actorRef.tell(taskExecuteDTO, actorRef);

        } catch (Exception e) {
            SnailJobLog.LOCAL.error("Task scheduling execution failed", e);
        }
    }

    @Override
    public String idempotentKey() {
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, workflowTimerTaskDTO.getWorkflowTaskBatchId());
    }
}
