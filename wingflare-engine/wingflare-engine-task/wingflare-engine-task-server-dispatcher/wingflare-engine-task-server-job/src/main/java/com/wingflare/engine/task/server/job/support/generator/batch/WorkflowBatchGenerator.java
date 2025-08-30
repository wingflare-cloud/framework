package com.wingflare.engine.task.server.job.support.generator.batch;

import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.server.common.exception.SnailJobServerException;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.WorkflowTimerTaskDTO;
import com.wingflare.engine.task.server.job.support.WorkflowTaskConverter;
import com.wingflare.engine.task.server.job.support.timer.JobTimerWheel;
import com.wingflare.engine.task.server.job.support.timer.WorkflowTimerTask;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.po.WorkflowTaskBatch;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

/**
 * @author: xiaowoniu
 * @date : 2023-12-22 09:04
 * @since : 2.6.0
 */
@Component
public class WorkflowBatchGenerator {
    private final WorkflowTaskBatchMapper workflowTaskBatchMapper;

    public WorkflowBatchGenerator(WorkflowTaskBatchMapper workflowTaskBatchMapper) {
        this.workflowTaskBatchMapper = workflowTaskBatchMapper;
    }

    public void generateJobTaskBatch(WorkflowTaskBatchGeneratorContext context) {

        // 生成任务批次
        WorkflowTaskBatch workflowTaskBatch = WorkflowTaskConverter.INSTANCE.toWorkflowTaskBatch(context);
        workflowTaskBatch.setTaskBatchStatus(Optional.ofNullable(context.getTaskBatchStatus()).orElse(JobTaskBatchStatusEnum.WAITING.getStatus()));
        workflowTaskBatch.setOperationReason(context.getOperationReason());
        workflowTaskBatch.setWfContext(context.getWfContext());

        Assert.isTrue(1 == workflowTaskBatchMapper.insert(workflowTaskBatch), () -> new SnailJobServerException("Adding new scheduling task failed. [{}]", context.getWorkflowId()));

        // 非待处理状态无需进入时间轮中
        if (JobTaskBatchStatusEnum.WAITING.getStatus() != workflowTaskBatch.getTaskBatchStatus()) {
            return;
        }

        // 开始执行工作流
        // 进入时间轮
        long delay = context.getNextTriggerAt() - DateUtils.toNowMilli();
        WorkflowTimerTaskDTO workflowTimerTaskDTO = new WorkflowTimerTaskDTO();
        workflowTimerTaskDTO.setWorkflowTaskBatchId(workflowTaskBatch.getId());
        workflowTimerTaskDTO.setWorkflowId(context.getWorkflowId());
        workflowTimerTaskDTO.setTaskExecutorScene(context.getTaskExecutorScene());

        JobTimerWheel.registerWithWorkflow(() -> new WorkflowTimerTask(workflowTimerTaskDTO), Duration.ofMillis(delay));
    }
}
