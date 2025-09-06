package com.wingflare.engine.task.server.job.support.timer;


import com.wingflare.api.event.EventPublisher;
import com.wingflare.engine.task.common.core.enums.JobNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.TimerTask;
import com.wingflare.engine.task.server.job.dto.JobTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.JobTaskStopHandler;
import com.wingflare.engine.task.server.job.support.alarm.event.JobTaskFailAlarmEvent;
import com.wingflare.engine.task.server.job.support.stop.JobTaskStopFactory;
import com.wingflare.engine.task.server.job.support.stop.TaskStopJobContext;
import com.wingflare.lib.container.Container;
import com.wingflare.lib.core.Builder;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTaskBatch;
import io.netty.util.Timeout;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * 任务超时检查
 *
 * @author opensnail
 * @date 2024-05-20 21:16:09
 * @since sj_1.0.0
 */
public class JobTimeoutCheckTask implements TimerTask<String> {
    private static final String IDEMPOTENT_KEY_PREFIX = "job_timeout_check_{0}";

    private final Long taskBatchId;
    private final Long jobId;

    public JobTimeoutCheckTask(Long taskBatchId, Long jobId) {
        this.taskBatchId = taskBatchId;
        this.jobId = jobId;
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        JobTimerWheel.clearCache(idempotentKey());
        JobTaskBatchMapper jobTaskBatchMapper = Container.get(JobTaskBatchMapper.class);
        JobTaskBatch jobTaskBatch = jobTaskBatchMapper.selectById(taskBatchId);
        if (Objects.isNull(jobTaskBatch)) {
            TaskEngineLog.LOCAL.error("jobTaskBatch:[{}] does not exist", taskBatchId);
            return;
        }

        // 已经完成了，无需重复停止任务
        if (JobTaskBatchStatusEnum.COMPLETED.contains(jobTaskBatch.getTaskBatchStatus())) {
            return;
        }

        JobMapper jobMapper = Container.get(JobMapper.class);
        Job job = jobMapper.selectById(jobId);
        if (Objects.isNull(job)) {
            TaskEngineLog.LOCAL.error("job:[{}] does not exist", jobId);
            return;
        }

        // 超时停止任务
        JobTaskStopHandler instanceInterrupt = JobTaskStopFactory.getJobTaskStop(job.getTaskType());
        TaskStopJobContext stopJobContext = JobTaskConverter.INSTANCE.toStopJobContext(job);
        stopJobContext.setJobOperationReason(JobOperationReasonEnum.TASK_EXECUTION_TIMEOUT.getReason());
        stopJobContext.setNeedUpdateTaskStatus(Boolean.TRUE);
        stopJobContext.setForceStop(Boolean.TRUE);
        stopJobContext.setTaskBatchId(taskBatchId);
        stopJobContext.setWorkflowNodeId(jobTaskBatch.getWorkflowNodeId());
        stopJobContext.setWorkflowTaskBatchId(jobTaskBatch.getWorkflowTaskBatchId());
        instanceInterrupt.stop(stopJobContext);

        String reason = "Timeout interruption. Task batch ID:[" + taskBatchId + "]";

        JobTaskFailAlarmEventDTO jobTaskFailAlarmEventDTO = Builder.of(JobTaskFailAlarmEventDTO::new)
                .with(JobTaskFailAlarmEventDTO::setJobTaskBatchId, taskBatchId)
                .with(JobTaskFailAlarmEventDTO::setReason, reason)
                .with(JobTaskFailAlarmEventDTO::setNotifyScene, JobNotifySceneEnum.JOB_TASK_ERROR.getNotifyScene())
                .build();

        Container.get(EventPublisher.class).publishEvent(
                new JobTaskFailAlarmEvent(jobTaskFailAlarmEventDTO));
        TaskEngineLog.LOCAL.info(reason);
    }

    @Override
    public String idempotentKey() {
        return MessageFormat.format(IDEMPOTENT_KEY_PREFIX, taskBatchId);
    }
}
