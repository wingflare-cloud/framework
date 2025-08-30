package com.wingflare.engine.task.server.job.support.prepare.workflow;

import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.engine.task.server.job.dto.WorkflowTimerTaskDTO;
import com.wingflare.engine.task.server.job.support.timer.JobTimerWheel;
import com.wingflare.engine.task.server.job.support.timer.WorkflowTimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Objects;

/**
 * 处理处于{@link JobTaskBatchStatusEnum::WAIT}状态的任务
 *
 * @author xiaowoniu
 * @date 2023-10-05 18:29:22
 * @since 2.6.0
 */
@Component
public class WaitWorkflowPrepareHandler extends AbstractWorkflowPrePareHandler {

    private static final Logger log = LoggerFactory.getLogger(WaitWorkflowPrepareHandler.class);

    @Override
    public boolean matches(Integer status) {
        return Objects.nonNull(status) && JobTaskBatchStatusEnum.WAITING.getStatus() == status;
    }

    @Override
    protected void doHandler(WorkflowTaskPrepareDTO workflowTaskPrepareDTO) {
        log.debug("Pending tasks exist. Workflow task batch ID:[{}]", workflowTaskPrepareDTO.getWorkflowTaskBatchId());

        // 若时间轮中数据不存在则重新加入
        if (!JobTimerWheel.isExisted(MessageFormat.format(WorkflowTimerTask.IDEMPOTENT_KEY_PREFIX, workflowTaskPrepareDTO.getWorkflowTaskBatchId()))) {
            log.info("Pending tasks exist and workflowTaskBatchId:[{}] does not exist in the time wheel", workflowTaskPrepareDTO.getWorkflowTaskBatchId());

            // 进入时间轮
            long delay = workflowTaskPrepareDTO.getNextTriggerAt() - DateUtils.toNowMilli();
            WorkflowTimerTaskDTO workflowTimerTaskDTO = new WorkflowTimerTaskDTO();
            workflowTimerTaskDTO.setWorkflowTaskBatchId(workflowTaskPrepareDTO.getWorkflowTaskBatchId());
            workflowTimerTaskDTO.setWorkflowId(workflowTaskPrepareDTO.getWorkflowId());
            workflowTimerTaskDTO.setTaskExecutorScene(workflowTaskPrepareDTO.getTaskExecutorScene());

            JobTimerWheel.registerWithWorkflow(() -> new WorkflowTimerTask(workflowTimerTaskDTO), Duration.ofMillis(delay));
        }
    }
}
