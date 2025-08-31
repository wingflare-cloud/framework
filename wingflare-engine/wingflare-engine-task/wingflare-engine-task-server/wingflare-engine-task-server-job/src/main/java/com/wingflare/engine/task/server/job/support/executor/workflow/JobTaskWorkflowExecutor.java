package com.wingflare.engine.task.server.job.support.executor.workflow;

import com.wingflare.engine.task.common.core.enums.*;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.dto.JobLogMetaDTO;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.JobTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import com.wingflare.task.datasource.template.persistence.po.JobTaskBatch;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum.WORKFLOW_NODE_CLOSED_SKIP_EXECUTION;
import static com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum.WORKFLOW_SUCCESSOR_SKIP_EXECUTION;

/**
 * @author xiaowoniu
 * @date 2023-12-24 08:09:14
 * @since 2.6.0
 */
@Component
public class JobTaskWorkflowExecutor extends AbstractWorkflowExecutor {


    @Override
    public WorkflowNodeTypeEnum getWorkflowNodeType() {
        return WorkflowNodeTypeEnum.JOB_TASK;
    }

    @Override
    protected boolean doPreValidate(WorkflowExecutorContext context) {
        return true;
    }

    @Override
    protected void afterExecute(WorkflowExecutorContext context) {

    }

    @Override
    protected void beforeExecute(WorkflowExecutorContext context) {

    }

    @Override
    protected void doExecute(WorkflowExecutorContext context) {

        if (WORKFLOW_SUCCESSOR_SKIP_EXECUTION.contains(context.getParentOperationReason())) {
            // 针对无需处理的批次直接新增一个记录
            context.setTaskBatchStatus(JobTaskBatchStatusEnum.CANCEL.getStatus());
            context.setOperationReason(JobOperationReasonEnum.WORKFLOW_NODE_NO_REQUIRED.getReason());
            context.setJobTaskStatus(JobTaskStatusEnum.CANCEL.getStatus());

            // 创建批次和任务节点4
            invokeCancelJobTask(context, "Current node does not require processing");
        } else if (Objects.equals(context.getWorkflowNodeStatus(), StatusEnum.NO.getStatus())) {
            // 针对无需处理的批次直接新增一个记录
            context.setTaskBatchStatus(JobTaskBatchStatusEnum.CANCEL.getStatus());
            context.setOperationReason(WORKFLOW_NODE_CLOSED_SKIP_EXECUTION.getReason());
            context.setJobTaskStatus(JobTaskStatusEnum.CANCEL.getStatus());

            // 创建批次和任务节点
            invokeCancelJobTask(context, "Task is closed");
        } else {
            invokeJobTask(context);
        }

    }

    private static void invokeJobTask(final WorkflowExecutorContext context) {
        // 生成任务批次
        JobTaskPrepareDTO jobTaskPrepare = JobTaskConverter.INSTANCE.toJobTaskPrepare(context.getJob(), context);
        jobTaskPrepare.setNextTriggerAt(DateUtils.toNowMilli() + DateUtils.toNowMilli() % 1000);
        // 执行预处理阶段
        ActorRef actorRef = ActorGenerator.jobTaskPrepareActor();
        actorRef.tell(jobTaskPrepare, actorRef);
    }

    private void invokeCancelJobTask(final WorkflowExecutorContext context, String cancelReason) {

        JobTaskBatch jobTaskBatch = generateJobTaskBatch(context);
        JobTask jobTask = generateJobTask(context, jobTaskBatch);

        JobLogMetaDTO jobLogMetaDTO = new JobLogMetaDTO();
        jobLogMetaDTO.setNamespaceId(context.getNamespaceId());
        jobLogMetaDTO.setGroupName(context.getGroupName());
        jobLogMetaDTO.setTaskBatchId(jobTaskBatch.getId());
        jobLogMetaDTO.setJobId(context.getJobId());
        jobLogMetaDTO.setTaskId(jobTask.getId());

        TaskEngineLog.REMOTE.warn("Node [{}] has canceled task execution. Cancellation reason: {}. <|>{}<|>",
            context.getWorkflowNodeId(), cancelReason, jobLogMetaDTO);
    }
}
