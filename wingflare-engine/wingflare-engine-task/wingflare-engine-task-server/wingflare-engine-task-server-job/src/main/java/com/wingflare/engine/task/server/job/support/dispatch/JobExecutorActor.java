package com.wingflare.engine.task.server.job.support.dispatch;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.*;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.dto.InstanceLiveInfo;
import com.wingflare.engine.task.server.common.enums.JobTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.handler.InstanceManager;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.JobTaskFailAlarmEventDTO;
import com.wingflare.engine.task.server.job.dto.TaskExecuteDTO;
import com.wingflare.engine.task.server.job.dto.WorkflowNodeTaskExecuteDTO;
import com.wingflare.engine.task.server.job.support.JobExecutor;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.alarm.event.JobTaskFailAlarmEvent;
import com.wingflare.engine.task.server.job.support.executor.job.JobExecutorContext;
import com.wingflare.engine.task.server.job.support.executor.job.JobExecutorFactory;
import com.wingflare.engine.task.server.job.support.generator.task.JobTaskGenerateContext;
import com.wingflare.engine.task.server.job.support.generator.task.JobTaskGenerator;
import com.wingflare.engine.task.server.job.support.generator.task.JobTaskGeneratorFactory;
import com.wingflare.engine.task.server.job.support.handler.JobTaskBatchHandler;
import com.wingflare.engine.task.server.job.support.handler.WorkflowBatchHandler;
import com.wingflare.engine.task.server.job.support.timer.JobTimeoutCheckTask;
import com.wingflare.engine.task.server.job.support.timer.JobTimerTask;
import com.wingflare.engine.task.server.job.support.timer.JobTimerWheel;
import com.wingflare.lib.core.Builder;
import com.wingflare.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.mapper.WorkflowTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.po.Job;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import com.wingflare.task.datasource.template.persistence.po.JobTaskBatch;
import com.wingflare.task.datasource.template.persistence.po.WorkflowTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import org.apache.pekko.actor.AbstractActor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum.MAP;
import static com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum.MAP_REDUCE;

/**
 * @author: opensnail
 * @date : 2023-09-25 17:41
 * @since : 2.4.0
 */
@Component(ActorGenerator.JOB_EXECUTOR_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobExecutorActor extends AbstractActor {
    private static final Logger log = LoggerFactory.getLogger(JobExecutorActor.class);
    private final JobMapper jobMapper;
    private final JobTaskBatchMapper jobTaskBatchMapper;
    private final TransactionTemplate transactionTemplate;
    private final WorkflowBatchHandler workflowBatchHandler;
    private final JobTaskBatchHandler jobTaskBatchHandler;

    public JobExecutorActor(JobMapper jobMapper, JobTaskBatchMapper jobTaskBatchMapper, TransactionTemplate transactionTemplate, WorkflowBatchHandler workflowBatchHandler, JobTaskBatchHandler jobTaskBatchHandler, WorkflowTaskBatchMapper workflowTaskBatchMapper, InstanceManager instanceManager) {
        this.jobMapper = jobMapper;
        this.jobTaskBatchMapper = jobTaskBatchMapper;
        this.transactionTemplate = transactionTemplate;
        this.workflowBatchHandler = workflowBatchHandler;
        this.jobTaskBatchHandler = jobTaskBatchHandler;
        this.workflowTaskBatchMapper = workflowTaskBatchMapper;
        this.instanceManager = instanceManager;
    }

    private final WorkflowTaskBatchMapper workflowTaskBatchMapper;
    private final InstanceManager instanceManager;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(TaskExecuteDTO.class, taskExecute -> {
            try {
                log.debug("Preparing to execute task. [{}] [{}]", LocalDateTime.now(), JsonUtil.toJsonString(taskExecute));
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(final TransactionStatus status) {
                        doExecute(taskExecute);
                    }
                });

            } catch (Exception e) {
                TaskEngineLog.LOCAL.error("job executor exception. [{}]", taskExecute, e);
                handleTaskBatch(taskExecute, JobTaskBatchStatusEnum.FAIL.getStatus(), JobOperationReasonEnum.TASK_EXECUTION_ERROR.getReason());
            } finally {
                getContext().stop(getSelf());
            }
        }).build();
    }

    private void doExecute(final TaskExecuteDTO taskExecute) {

        LambdaQueryWrapper<Job> queryWrapper = new LambdaQueryWrapper<>();
        // 自动地校验任务必须是开启状态，手动触发无需校验
        if (JobTaskExecutorSceneEnum.AUTO_JOB.getType().equals(taskExecute.getTaskExecutorScene())) {
            queryWrapper.eq(Job::getJobStatus, StatusEnum.YES.getStatus());
        }

        Job job = jobMapper.selectOne(queryWrapper.eq(Job::getId, taskExecute.getJobId()));
        int taskStatus = JobTaskBatchStatusEnum.RUNNING.getStatus();
        try {
            int operationReason = JobOperationReasonEnum.NONE.getReason();
            if (Objects.isNull(job)) {
                taskStatus = JobTaskBatchStatusEnum.CANCEL.getStatus();
                operationReason = JobOperationReasonEnum.JOB_CLOSED.getReason();
            } else {
                Set<InstanceLiveInfo> liveInfoSet = instanceManager.getInstanceALiveInfoSet(
                        job.getNamespaceId(), job.getGroupName(), job.getLabels());

                // 空节点处理
                if (CollUtil.isEmpty(liveInfoSet)) {

                    taskStatus = JobTaskBatchStatusEnum.CANCEL.getStatus();
                    operationReason = JobOperationReasonEnum.NOT_CLIENT.getReason();

                    WorkflowNodeTaskExecuteDTO taskExecuteDTO = new WorkflowNodeTaskExecuteDTO();
                    taskExecuteDTO.setWorkflowTaskBatchId(taskExecute.getWorkflowTaskBatchId());
                    taskExecuteDTO.setTaskExecutorScene(taskExecute.getTaskExecutorScene());
                    taskExecuteDTO.setParentId(taskExecute.getWorkflowNodeId());
                    taskExecuteDTO.setTaskBatchId(taskExecute.getTaskBatchId());
                    workflowBatchHandler.openNextNode(taskExecuteDTO);

                    // 无客户端节点-告警通知
                    SnailSpringContext.getContext().publishEvent(
                            new JobTaskFailAlarmEvent(Builder.of(JobTaskFailAlarmEventDTO::new)
                                    .with(JobTaskFailAlarmEventDTO::setJobTaskBatchId, taskExecute.getTaskBatchId())
                                    .with(JobTaskFailAlarmEventDTO::setReason, JobNotifySceneEnum.JOB_NO_CLIENT_NODES_ERROR.getDesc())
                                    .with(JobTaskFailAlarmEventDTO::setNotifyScene, JobNotifySceneEnum.JOB_NO_CLIENT_NODES_ERROR.getNotifyScene())
                                    .build()));
                }
            }

            // 更新状态
            handleTaskBatch(taskExecute, taskStatus, operationReason);

            // 不是运行中的，不需要生产任务
            if (taskStatus != JobTaskBatchStatusEnum.RUNNING.getStatus()) {
                return;
            }

            // 生成任务
            JobTaskGenerator taskInstance = JobTaskGeneratorFactory.getTaskInstance(job.getTaskType());
            JobTaskGenerateContext instanceGenerateContext = JobTaskConverter.INSTANCE.toJobTaskInstanceGenerateContext(job);
            instanceGenerateContext.setTaskBatchId(taskExecute.getTaskBatchId());
            if (Objects.nonNull(taskExecute.getTmpArgsStr())) {
                instanceGenerateContext.setArgsStr(taskExecute.getTmpArgsStr());
            }
            if (Lists.newArrayList(MAP_REDUCE.getType(), MAP.getType()).contains(job.getTaskType())) {
                instanceGenerateContext.setTaskName(SystemConstants.ROOT_MAP);
                instanceGenerateContext.setMapSubTask(Lists.newArrayList(StrUtil.EMPTY));
                instanceGenerateContext.setMrStage(MapReduceStageEnum.MAP.getStage());
            }
            List<JobTask> taskList = taskInstance.generate(instanceGenerateContext);
            if (CollUtil.isEmpty(taskList)) {
                TaskEngineLog.LOCAL.warn("Generate job task is empty, taskBatchId:[{}]", taskExecute.getTaskBatchId());
                return;
            }

            // 事务提交以后再执行任务
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    // 获取工作流的上下文
                    WorkflowTaskBatch workflowTaskBatch = null;
                    Long workflowTaskBatchId = taskExecute.getWorkflowTaskBatchId();
                    if (Objects.nonNull(workflowTaskBatchId)) {
                        workflowTaskBatch = workflowTaskBatchMapper.selectOne(
                                new LambdaQueryWrapper<WorkflowTaskBatch>()
                                        .select(WorkflowTaskBatch::getWfContext)
                                        .eq(WorkflowTaskBatch::getId, taskExecute.getWorkflowTaskBatchId())
                        );
                    }

                    // 执行任务
                    JobExecutor jobExecutor = JobExecutorFactory.getJobExecutor(job.getTaskType());
                    jobExecutor.execute(buildJobExecutorContext(taskExecute, job, taskList, workflowTaskBatch));
                }
            });

        } finally {
            log.debug("Task preparation complete.[{}]", JsonUtil.toJsonString(taskExecute));
            final int finalTaskStatus = taskStatus;
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCompletion(int status) {
                    // 清除时间轮的缓存
                    JobTimerWheel.clearCache(MessageFormat.format(JobTimerTask.IDEMPOTENT_KEY_PREFIX, taskExecute.getTaskBatchId()));

                    if (JobTaskBatchStatusEnum.RUNNING.getStatus() == finalTaskStatus) {

                        // 运行中的任务，需要进行超时检查
                        JobTimerWheel.registerWithJob(() -> new JobTimeoutCheckTask(taskExecute.getTaskBatchId(), job.getId()),
                                // 加500ms是为了让尽量保证客户端自己先超时中断，防止客户端上报成功但是服务端已触发超时中断
                                Duration.ofMillis(DateUtils.toEpochMilli(job.getExecutorTimeout()) + 500));
                    }

                    // 开启下一个常驻任务
                    jobTaskBatchHandler.openResidentTask(job, taskExecute);
                }
            });
        }

    }

    @NotNull
    private static JobExecutorContext buildJobExecutorContext(TaskExecuteDTO taskExecute, Job job, List<JobTask> taskList,
                                                              final WorkflowTaskBatch workflowTaskBatch) {
        JobExecutorContext context = JobTaskConverter.INSTANCE.toJobExecutorContext(job);
        context.setTaskList(taskList);
        context.setTaskBatchId(taskExecute.getTaskBatchId());
        context.setJobId(job.getId());
        context.setWorkflowTaskBatchId(taskExecute.getWorkflowTaskBatchId());
        context.setWorkflowNodeId(taskExecute.getWorkflowNodeId());
        if (Objects.nonNull(workflowTaskBatch)) {
            context.setWfContext(workflowTaskBatch.getWfContext());
        }
        return context;
    }

    private void handleTaskBatch(TaskExecuteDTO taskExecute, int taskStatus, int operationReason) {

        JobTaskBatch jobTaskBatch = new JobTaskBatch();
        jobTaskBatch.setId(taskExecute.getTaskBatchId());
        jobTaskBatch.setExecutionAt(DateUtils.toNowMilli());
        jobTaskBatch.setTaskBatchStatus(taskStatus);
        jobTaskBatch.setOperationReason(operationReason);
        Assert.isTrue(1 == jobTaskBatchMapper.updateById(jobTaskBatch),
                () -> new TaskServerException("Updating task failed"));

        if (JobTaskBatchStatusEnum.NOT_SUCCESS.contains(taskStatus)) {
            SnailSpringContext.getContext().publishEvent(
                    new JobTaskFailAlarmEvent(Builder.of(JobTaskFailAlarmEventDTO::new)
                            .with(JobTaskFailAlarmEventDTO::setJobTaskBatchId, taskExecute.getTaskBatchId())
                            .with(JobTaskFailAlarmEventDTO::setReason, JobOperationReasonEnum.TASK_EXECUTION_ERROR.getDesc())
                            .with(JobTaskFailAlarmEventDTO::setNotifyScene, JobNotifySceneEnum.JOB_TASK_ERROR.getNotifyScene())
                            .build()));
        }
    }

}
