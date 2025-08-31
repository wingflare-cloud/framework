package com.wingflare.engine.task.server.job.support.executor.workflow;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.*;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.CallbackConfig;
import com.wingflare.engine.task.common.model.request.CallbackParamsRequest;
import com.wingflare.engine.task.server.common.dto.JobLogMetaDTO;
import com.wingflare.engine.task.server.common.rpc.okhttp.RequestInterceptor;
import com.wingflare.engine.task.server.job.dto.WorkflowTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.job.support.alarm.event.WorkflowTaskFailAlarmEvent;
import com.wingflare.lib.core.Builder;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import com.wingflare.task.datasource.template.persistence.po.JobTaskBatch;
import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.wingflare.engine.task.common.core.enums.JobOperationReasonEnum.WORKFLOW_SUCCESSOR_SKIP_EXECUTION;

/**
 * @author xiaowoniu
 * @date 2023-12-24 08:18:06
 * @since 2.6.0
 */
@Component
@Deprecated(since = "1.7.0")
public class CallbackWorkflowExecutor extends AbstractWorkflowExecutor {

    private static final String CALLBACK_TIMEOUT = "10";
    private final RestTemplate restTemplate;

    public CallbackWorkflowExecutor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WorkflowNodeTypeEnum getWorkflowNodeType() {
        return WorkflowNodeTypeEnum.CALLBACK;
    }

    @Override
    protected void beforeExecute(WorkflowExecutorContext context) {

    }

    @Override
    protected void doExecute(WorkflowExecutorContext context) {

        // 初始化上下状态
        context.setTaskBatchStatus(JobTaskBatchStatusEnum.SUCCESS.getStatus());
        context.setOperationReason(JobOperationReasonEnum.NONE.getReason());
        context.setJobTaskStatus(JobTaskStatusEnum.SUCCESS.getStatus());

        if (WORKFLOW_SUCCESSOR_SKIP_EXECUTION.contains(context.getParentOperationReason())) {
            // 针对无需处理的批次直接新增一个记录
            context.setTaskBatchStatus(JobTaskBatchStatusEnum.CANCEL.getStatus());
            context.setOperationReason(JobOperationReasonEnum.WORKFLOW_NODE_NO_REQUIRED.getReason());
            context.setJobTaskStatus(JobTaskStatusEnum.CANCEL.getStatus());
        } else if (Objects.equals(context.getWorkflowNodeStatus(), StatusEnum.NO.getStatus())) {
            context.setTaskBatchStatus(JobTaskBatchStatusEnum.CANCEL.getStatus());
            context.setOperationReason(JobOperationReasonEnum.WORKFLOW_NODE_CLOSED_SKIP_EXECUTION.getReason());
            context.setJobTaskStatus(JobTaskStatusEnum.CANCEL.getStatus());
        } else {
            invokeCallback(context);
        }

    }

    private void invokeCallback(WorkflowExecutorContext context) {

        CallbackConfig decisionConfig = JsonUtil.parseObject(context.getNodeInfo(), CallbackConfig.class);
        String message = StrUtil.EMPTY;
        String result = null;

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(SystemConstants.SECRET, decisionConfig.getSecret());
        requestHeaders.setContentType(ContentTypeEnum.valueOf(decisionConfig.getContentType()).getMediaType());
        // 设置回调超时时间
        requestHeaders.set(RequestInterceptor.TIMEOUT_TIME, CALLBACK_TIMEOUT);

        CallbackParamsRequest callbackParamsRequest = new CallbackParamsRequest();
        callbackParamsRequest.setWfContext(context.getWfContext());

        try {
            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put(SystemConstants.SECRET, decisionConfig.getSecret());

            ResponseEntity<String> response = buildRetryer(decisionConfig).call(
                    () -> restTemplate.exchange(decisionConfig.getWebhook(), HttpMethod.POST,
                            new HttpEntity<>(callbackParamsRequest, requestHeaders), String.class, uriVariables));

            result = response.getBody();
            TaskEngineLog.LOCAL.info("Callback result. WebHook:[{}], Result: [{}]", decisionConfig.getWebhook(), result);
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Callback exception. WebHook:[{}], Parameter: [{}]", decisionConfig.getWebhook(),
                    context.getWfContext(), e);

            context.setTaskBatchStatus(JobTaskBatchStatusEnum.FAIL.getStatus());
            context.setOperationReason(JobOperationReasonEnum.WORKFLOW_CALLBACK_NODE_EXECUTION_ERROR.getReason());
            context.setJobTaskStatus(JobTaskStatusEnum.FAIL.getStatus());

            Throwable throwable = e;
            if (e.getClass().isAssignableFrom(RetryException.class)) {
                RetryException re = (RetryException) e;
                throwable = re.getLastFailedAttempt().getExceptionCause();
            }

            message = throwable.getMessage();
            SnailSpringContext.getContext().publishEvent(new WorkflowTaskFailAlarmEvent(Builder.of(WorkflowTaskFailAlarmEventDTO::new)
                    .with(WorkflowTaskFailAlarmEventDTO::setWorkflowTaskBatchId, context.getWorkflowTaskBatchId())
                    .with(WorkflowTaskFailAlarmEventDTO::setNotifyScene, JobNotifySceneEnum.WORKFLOW_TASK_ERROR.getNotifyScene())
                    .with(WorkflowTaskFailAlarmEventDTO::setReason, message)
                    .build()));
        }

        context.setEvaluationResult(result);
        context.setLogMessage(message);

    }

    private static Retryer<ResponseEntity<String>> buildRetryer(CallbackConfig decisionConfig) {
        Retryer<ResponseEntity<String>> retryer = RetryerBuilder.<ResponseEntity<String>>newBuilder()
                .retryIfException(throwable -> true)
                .withWaitStrategy(WaitStrategies.fixedWait(150, TimeUnit.MILLISECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(10))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(final Attempt<V> attempt) {
                        if (attempt.hasException()) {
                            TaskEngineLog.LOCAL.error("Callback interface attempt [{}]. Callback configuration information: [{}]",
                                    attempt.getAttemptNumber(), JsonUtil.toJsonString(decisionConfig));
                        }
                    }
                }).build();
        return retryer;
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
        jobLogMetaDTO.setJobId(SystemConstants.CALLBACK_JOB_ID);
        jobLogMetaDTO.setTaskId(jobTask.getId());
        if (jobTaskBatch.getTaskBatchStatus() == JobTaskStatusEnum.SUCCESS.getStatus()) {
            TaskEngineLog.REMOTE.info("Node [{}] callback success.\nCallback params: {} \nCallback result: [{}] <|>{}<|>",
                    context.getWorkflowNodeId(), context.getWfContext(), context.getEvaluationResult(), jobLogMetaDTO);
        } else if (jobTaskBatch.getTaskBatchStatus() == JobTaskStatusEnum.CANCEL.getStatus()) {
            if (WORKFLOW_SUCCESSOR_SKIP_EXECUTION.contains(context.getParentOperationReason())) {
                TaskEngineLog.REMOTE.warn("Node [{}] cancels callback. Cancellation reason: Current task does not require processing <|>{}<|>",
                        context.getWorkflowNodeId(), jobLogMetaDTO);
            } else {
                TaskEngineLog.REMOTE.warn("Node [{}] cancels callback. Cancellation reason: Task status is closed <|>{}<|>",
                        context.getWorkflowNodeId(), jobLogMetaDTO);
            }

        } else {
            TaskEngineLog.REMOTE.error("Node [{}] fail to callback.\nReason: {} <|>{}<|>",
                    context.getWorkflowNodeId(),
                    context.getLogMessage(), jobLogMetaDTO);
        }
    }
}
