package com.wingflare.engine.task.server.job.support.timer;

import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.JobNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.job.support.alarm.event.WorkflowTaskFailAlarmEvent;
import com.wingflare.engine.task.server.job.support.handler.WorkflowBatchHandler;
import com.wingflare.lib.core.Builder;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.po.WorkflowTaskBatch;
import io.netty.util.Timeout;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * @author opensnail
 * @date 2024-05-20 22:25:12
 * @since sj_1.0.0
 */
public class WorkflowTimeoutCheckTask implements TimerTask<String> {
    private static final String IDEMPOTENT_KEY_PREFIX = "workflow_timeout_check_{0}";

    private final Long workflowTaskBatchId;

    public WorkflowTimeoutCheckTask(Long workflowTaskBatchId) {
        this.workflowTaskBatchId = workflowTaskBatchId;
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        JobTimerWheel.clearCache(idempotentKey());
        WorkflowTaskBatchMapper workflowTaskBatchMapper = SnailSpringContext.getBean(WorkflowTaskBatchMapper.class);
        WorkflowTaskBatch workflowTaskBatch = workflowTaskBatchMapper.selectById(workflowTaskBatchId);
        // 幂等检查
        if (Objects.isNull(workflowTaskBatch) || JobTaskBatchStatusEnum.COMPLETED.contains(workflowTaskBatch.getTaskBatchStatus())) {
            return;
        }

        WorkflowBatchHandler workflowBatchHandler = SnailSpringContext.getBean(WorkflowBatchHandler.class);

        // 超时停止任务
        workflowBatchHandler.stop(workflowTaskBatchId, JobOperationReasonEnum.TASK_EXECUTION_TIMEOUT.getReason());

        String reason = String.format("Timeout interruption. Workflow task batch ID:[%s]", workflowTaskBatchId);
        SnailSpringContext.getContext().publishEvent(new WorkflowTaskFailAlarmEvent(Builder.of(WorkflowTaskFailAlarmEventDTO::new)
                .with(WorkflowTaskFailAlarmEventDTO::setWorkflowTaskBatchId, workflowTaskBatchId)
                .with(WorkflowTaskFailAlarmEventDTO::setNotifyScene, JobNotifySceneEnum.WORKFLOW_TASK_ERROR.getNotifyScene())
                .with(WorkflowTaskFailAlarmEventDTO::setReason, reason)
                .build()));

        TaskEngineLog.LOCAL.info(reason);
    }

    @Override
    public String idempotentKey() {
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, workflowTaskBatchId);
    }
}
