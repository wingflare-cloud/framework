package com.wingflare.engine.task.client.core.executor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import com.wingflare.api.alarm.AlarmContext;
import com.wingflare.engine.task.client.common.cache.GroupVersionCache;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.client.common.log.support.TaskLogManager;
import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.core.cache.ThreadPoolCache;
import com.wingflare.engine.task.client.core.client.JobNettyClient;
import com.wingflare.engine.task.client.core.log.JobLogMeta;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.JobNotifySceneEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskStatusEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.JobContext;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.EnvironmentUtils;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.core.util.NetUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.common.model.request.ConfigRequest.Notify.Recipient;
import com.wingflare.engine.task.common.model.request.DispatchJobResultRequest;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.wingflare.lib.alarm.AlarmUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CancellationException;

/**
 * @author: opensnail
 * @date : 2023-10-08 16:44
 * @since : 2.4.0
 */
public class JobExecutorFutureCallback implements FutureCallback<ExecuteResult> {

    private static final String TEXT_MESSAGE_FORMATTER = """
            <font face="Microsoft YaHei" color=#ff0000 size=4>{}环境 定时任务上报异常</font> \s
             > IP:{}   \s
             > 空间ID:{}  \s
             > 名称:{}   \s
             > 时间:{}   \s
             > 异常:{} \s
            \s""";

    private static final JobNettyClient CLIENT = RequestBuilder.<JobNettyClient, TaskRpcResult>newBuilder()
            .client(JobNettyClient.class)
            .callback(nettyResult -> {
                if (nettyResult.getStatus() == StatusEnum.NO.getStatus()) {
                    sendMessage(nettyResult.getMessage());
                }
                TaskEngineLog.LOCAL.debug("Job execute result report successfully requestId:[{}]",
                        nettyResult.getReqId());
            }).build();

    private final JobContext jobContext;

    public JobExecutorFutureCallback(final JobContext jobContext) {
        this.jobContext = jobContext;
    }

    @Override
    public void onSuccess(ExecuteResult result) {

        try {
            // 初始化调度信息（日志上报LogUtil）
            initLogContext();

            // 上报执行成功
            TaskEngineLog.REMOTE.info("Task executed successfully taskBatchId:[{}] [{}]", jobContext.getTaskBatchId(), JsonUtil.toJsonString(result));

            if (Objects.isNull(result)) {
                result = ExecuteResult.success();
            }

            int taskStatus;
            if (result.getStatus() == StatusEnum.NO.getStatus()) {
                taskStatus = JobTaskStatusEnum.FAIL.getStatus();
            } else {
                taskStatus = JobTaskStatusEnum.SUCCESS.getStatus();
            }

            CLIENT.dispatchResult(buildDispatchJobResultRequest(result, taskStatus));
        } catch (Exception e) {
            TaskEngineLog.REMOTE.error("Execution result reporting exception.[{}]", jobContext.getTaskId(), e);
            sendMessage(e.getMessage());
        } finally {
            TaskLogManager.removeLogMeta();
            stopThreadPool();
        }
    }

    @Override
    public void onFailure(final Throwable t) {
        if (t instanceof CancellationException) {
            TaskEngineLog.LOCAL.debug("The task has been canceled, no status feedback will be made");
            return;
        }
        ExecuteResult failure = ExecuteResult.failure();
        try {
            // 初始化调度信息（日志上报LogUtil）
            initLogContext();

            // 上报执行失败
            TaskEngineLog.REMOTE.error("Task execution failed taskBatchId:[{}]", jobContext.getTaskBatchId(), t);
            failure.setMessage(t.getMessage());

            CLIENT.dispatchResult(
                    buildDispatchJobResultRequest(failure, JobTaskStatusEnum.FAIL.getStatus())
            );
        } catch (Exception e) {
            TaskEngineLog.REMOTE.error("Execution result reporting exception.[{}]", jobContext.getTaskId(), e);
            sendMessage(e.getMessage());
        } finally {
            TaskLogManager.removeLogMeta();
            stopThreadPool();
        }
    }

    private void initLogContext() {
        JobLogMeta logMeta = new JobLogMeta();
        logMeta.setNamespaceId(jobContext.getNamespaceId());
        logMeta.setTaskId(jobContext.getTaskId());
        logMeta.setGroupName(jobContext.getGroupName());
        logMeta.setJobId(jobContext.getJobId());
        logMeta.setTaskBatchId(jobContext.getTaskBatchId());
        TaskLogManager.initLogInfo(logMeta, LogTypeEnum.JOB);
    }

    private void stopThreadPool() {
        if (jobContext.getTaskType() == JobTaskTypeEnum.CLUSTER.getType()) {
            ThreadPoolCache.stopThreadPool(jobContext.getTaskBatchId());
        }
    }

    private DispatchJobResultRequest buildDispatchJobResultRequest(ExecuteResult executeResult, int status) {
        DispatchJobResultRequest dispatchJobRequest = new DispatchJobResultRequest();
        dispatchJobRequest.setTaskBatchId(jobContext.getTaskBatchId());
        dispatchJobRequest.setGroupName(jobContext.getGroupName());
        dispatchJobRequest.setJobId(jobContext.getJobId());
        dispatchJobRequest.setTaskId(jobContext.getTaskId());
        dispatchJobRequest.setWorkflowTaskBatchId(jobContext.getWorkflowTaskBatchId());
        dispatchJobRequest.setWorkflowNodeId(jobContext.getWorkflowNodeId());
        dispatchJobRequest.setTaskBatchId(jobContext.getTaskBatchId());
        dispatchJobRequest.setTaskId(jobContext.getTaskId());
        dispatchJobRequest.setTaskType(jobContext.getTaskType());
        dispatchJobRequest.setExecuteResult(executeResult);
        dispatchJobRequest.setTaskStatus(status);
        dispatchJobRequest.setRetryStatus(jobContext.getRetryStatus());
        dispatchJobRequest.setRetryScene(jobContext.getRetryScene());
        // 传递变更后的上下文
        if (CollUtil.isNotEmpty(jobContext.getChangeWfContext())) {
            dispatchJobRequest.setWfContext(JsonUtil.toJsonString(jobContext.getChangeWfContext()));
        }

        return dispatchJobRequest;
    }

    private static void sendMessage(String message) {

        try {
            TaskProperties taskProperties = SnailSpringContext.getBean(TaskProperties.class);
            if (Objects.isNull(taskProperties)) {
                return;
            }
            ConfigRequest.Notify notify = GroupVersionCache.getJobNotifyAttribute(
                    JobNotifySceneEnum.JOB_CLIENT_ERROR.getNotifyScene());
            if (Objects.nonNull(notify)) {
                List<Recipient> recipients = Optional.ofNullable(notify.getRecipients()).orElse(Lists.newArrayList());
                for (final Recipient recipient : recipients) {
                    AlarmContext context = AlarmContext.build()
                            .text(TEXT_MESSAGE_FORMATTER,
                                    EnvironmentUtils.getActiveProfile(),
                                    NetUtil.getLocalIpStr(),
                                    taskProperties.getNamespace(),
                                    taskProperties.getGroup(),
                                    LocalDateTime.now().format(DatePattern.NORM_DATETIME_FORMATTER),
                                    message)
                            .title("Scheduled task execution result reporting exception:[{}]", taskProperties.getGroup())
                            .notifyAttribute(recipient.getNotifyAttribute());

                    Optional.ofNullable(AlarmUtil.getAlarmType(recipient.getNotifyType()))
                            .ifPresent(alarm -> AlarmUtil.asyncSendMessage(recipient.getNotifyType(), context));
                }
            }
        } catch (Exception e1) {
            TaskEngineLog.LOCAL.error("Client failed to send component exception alert.", e1);
        }
    }
}
