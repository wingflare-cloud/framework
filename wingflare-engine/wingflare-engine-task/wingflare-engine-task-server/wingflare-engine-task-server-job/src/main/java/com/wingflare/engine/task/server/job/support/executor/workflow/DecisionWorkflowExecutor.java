package com.wingflare.engine.task.server.job.support.executor.workflow;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.wingflare.api.event.EventPublisher;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.*;
import com.wingflare.engine.task.common.core.expression.ExpressionEngine;
import com.wingflare.engine.task.common.core.expression.ExpressionFactory;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.DecisionConfigRequest;
import com.wingflare.engine.task.server.common.dto.JobLogMetaDTO;
import com.wingflare.engine.task.server.common.enums.ExpressionTypeEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.job.support.alarm.event.WorkflowTaskFailAlarmEvent;
import com.wingflare.engine.task.server.job.support.expression.ExpressionInvocationHandler;
import com.wingflare.lib.container.Container;
import com.wingflare.lib.core.Builder;
import com.wingflare.engine.task.datasource.template.persistence.mapper.WorkflowTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTaskBatch;
import com.wingflare.engine.task.datasource.template.persistence.po.WorkflowTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum.WORKFLOW_SUCCESSOR_SKIP_EXECUTION;

/**
 * @author xiaowoniu
 * @date 2023-12-24 08:17:11
 * @since 2.6.0
 */
@Component
public class DecisionWorkflowExecutor extends AbstractWorkflowExecutor {
    private static final Logger log = LoggerFactory.getLogger(DecisionWorkflowExecutor.class);
    private final WorkflowTaskBatchMapper workflowTaskBatchMapper;

    public DecisionWorkflowExecutor(WorkflowTaskBatchMapper workflowTaskBatchMapper) {
        this.workflowTaskBatchMapper = workflowTaskBatchMapper;
    }

    @Override
    public WorkflowNodeTypeEnum getWorkflowNodeType() {
        return WorkflowNodeTypeEnum.DECISION;
    }

    @Override
    protected void beforeExecute(WorkflowExecutorContext context) {

    }

    @Override
    public void doExecute(WorkflowExecutorContext context) {
        int taskBatchStatus = JobTaskBatchStatusEnum.SUCCESS.getStatus();
        int operationReason = JobOperationReasonEnum.NONE.getReason();
        int jobTaskStatus = JobTaskStatusEnum.SUCCESS.getStatus();
        String message = StrUtil.EMPTY;
        String wfContext = "";

        Boolean result = (Boolean) Optional.ofNullable(context.getEvaluationResult()).orElse(Boolean.FALSE);

        if (result || (WORKFLOW_SUCCESSOR_SKIP_EXECUTION.contains(context.getParentOperationReason()))) {
            // 多个条件节点直接是或的关系，只要一个成功其他节点就取消且是无需处理状态
            taskBatchStatus = JobTaskBatchStatusEnum.CANCEL.getStatus();
            jobTaskStatus = JobTaskStatusEnum.CANCEL.getStatus();
            operationReason = JobOperationReasonEnum.WORKFLOW_NODE_NO_REQUIRED.getReason();
        } else {
            DecisionConfigRequest decisionConfigRequest = JsonUtil.parseObject(context.getNodeInfo(), DecisionConfigRequest.class);
            if (StatusEnum.NO.getStatus().equals(decisionConfigRequest.getDefaultDecision())) {

                try {
                    // 这里重新加载一次最新的上下文
                    WorkflowTaskBatch workflowTaskBatch = workflowTaskBatchMapper.selectOne(new LambdaQueryWrapper<WorkflowTaskBatch>()
                            .select(WorkflowTaskBatch::getWfContext)
                            .eq(WorkflowTaskBatch::getId, context.getWorkflowTaskBatchId())
                    );

                    if (Objects.isNull(workflowTaskBatch)) {
                        operationReason = JobOperationReasonEnum.WORKFLOW_DECISION_FAILED.getReason();
                    } else {
                        wfContext = workflowTaskBatch.getWfContext();
                        ExpressionEngine realExpressionEngine = ExpressionTypeEnum.valueOf(decisionConfigRequest.getExpressionType());
                        Assert.notNull(realExpressionEngine, () -> new TaskServerException("Expression engine does not exist"));
                        ExpressionInvocationHandler invocationHandler = new ExpressionInvocationHandler(realExpressionEngine);
                        ExpressionEngine expressionEngine = ExpressionFactory.getExpressionEngine(invocationHandler);
                        result = (Boolean) Optional.ofNullable(expressionEngine.eval(decisionConfigRequest.getNodeExpression(), wfContext)).orElse(Boolean.FALSE);
                        if (!result) {
                            operationReason = JobOperationReasonEnum.WORKFLOW_DECISION_FAILED.getReason();
                        }
                    }

                } catch (Exception e) {
                    log.error("Condition expression execution parsing exception. Expression:[{}], Parameter: [{}]", decisionConfigRequest.getNodeExpression(), wfContext, e);
                    taskBatchStatus = JobTaskBatchStatusEnum.FAIL.getStatus();
                    operationReason = JobOperationReasonEnum.WORKFLOW_CONDITION_NODE_EXECUTION_ERROR.getReason();
                    jobTaskStatus = JobTaskStatusEnum.FAIL.getStatus();
                    message = e.getMessage();

                    Container.get(EventPublisher.class).publishEvent(new WorkflowTaskFailAlarmEvent(Builder.of(WorkflowTaskFailAlarmEventDTO::new)
                            .with(WorkflowTaskFailAlarmEventDTO::setWorkflowTaskBatchId, context.getWorkflowTaskBatchId())
                            .with(WorkflowTaskFailAlarmEventDTO::setNotifyScene, JobNotifySceneEnum.WORKFLOW_TASK_ERROR.getNotifyScene())
                            .with(WorkflowTaskFailAlarmEventDTO::setReason, message)
                            .build()));
                }
            } else {
                result = Boolean.TRUE;
            }
        }

        // 回传执行结果
        context.setEvaluationResult(result);
        context.setTaskBatchStatus(taskBatchStatus);
        context.setOperationReason(operationReason);
        context.setJobTaskStatus(jobTaskStatus);
        context.setLogMessage(message);
        context.setWfContext(wfContext);

    }

    @Override
    protected boolean doPreValidate(WorkflowExecutorContext context) {
        return true;
    }

    @Override
    protected void afterExecute(WorkflowExecutorContext context) {

        JobTaskBatch jobTaskBatch = generateJobTaskBatch(context);

        JobTask jobTask = generateJobTask(context, jobTaskBatch);

        JobLogMetaDTO jobLogMetaDTO = new JobLogMetaDTO();
        jobLogMetaDTO.setNamespaceId(context.getNamespaceId());
        jobLogMetaDTO.setGroupName(context.getGroupName());
        jobLogMetaDTO.setTaskBatchId(jobTaskBatch.getId());
        jobLogMetaDTO.setJobId(SystemConstants.DECISION_JOB_ID);
        jobLogMetaDTO.setTaskId(jobTask.getId());
        if (jobTaskBatch.getTaskBatchStatus() == JobTaskStatusEnum.SUCCESS.getStatus()
                || JobOperationReasonEnum.WORKFLOW_NODE_NO_REQUIRED.getReason() == context.getOperationReason()) {
            TaskEngineLog.REMOTE.info("Node ID:[{}] Decision completed. Context:[{}] Decision result:[{}] <|>{}<|>",
                    context.getWorkflowNodeId(), context.getWfContext(), context.getEvaluationResult(), jobLogMetaDTO);
        } else {
            TaskEngineLog.REMOTE.error("Node ID:[{}] Decision failed. Context:[{}] Failure reason:[{}] <|>{}<|>",
                    context.getWorkflowNodeId(), context.getWfContext(), context.getLogMessage(), jobLogMetaDTO);

        }
    }
}
