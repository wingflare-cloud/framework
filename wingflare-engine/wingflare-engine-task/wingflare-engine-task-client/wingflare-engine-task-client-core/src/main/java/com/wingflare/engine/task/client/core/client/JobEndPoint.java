package com.wingflare.engine.task.client.core.client;

import cn.hutool.core.util.StrUtil;
import com.wingflare.api.validation.annotation.Validated;
import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.annotation.TaskEndPoint;
import com.wingflare.engine.task.client.common.log.support.TaskLogManager;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.client.core.IJobExecutor;
import com.wingflare.engine.task.client.core.cache.JobExecutorInfoCache;
import com.wingflare.engine.task.client.core.cache.ThreadPoolCache;
import com.wingflare.engine.task.client.core.dto.JobExecutorInfo;
import com.wingflare.engine.task.client.core.executor.*;
import com.wingflare.engine.task.client.core.log.JobLogMeta;
import com.wingflare.engine.task.common.core.enums.ExecutorTypeEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.model.JobArgsHolder;
import com.wingflare.engine.task.common.core.model.JobContext;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.common.model.request.DispatchJobRequest;
import com.wingflare.engine.task.common.model.request.StopJobRequest;
import com.google.common.collect.Maps;
import com.wingflare.lib.container.Container;
import jakarta.validation.Valid;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.JOB_DISPATCH;
import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.JOB_STOP;

/**
 * @author: opensnail
 * @date : 2023-09-27 16:33
 */
@TaskEndPoint
@Validated
public class JobEndPoint {

    @Mapping(path = JOB_DISPATCH, method = RequestMethod.POST)
    public Result<Boolean> dispatchJob(@Valid DispatchJobRequest dispatchJob) {

        try {
            JobContext jobContext = buildJobContext(dispatchJob);

            // 初始化调度信息（日志上报LogUtil）
            initLogContext(jobContext);

            if (Objects.nonNull(dispatchJob.getRetryCount()) && dispatchJob.getRetryCount() > 0) {
                TaskEngineLog.REMOTE.info("Task execution/scheduling failed, executing retry. Retry count:[{}]",
                        dispatchJob.getRetryCount());
            }

            if (ExecutorTypeEnum.JAVA.getType() != dispatchJob.getExecutorType()) {
                TaskEngineLog.REMOTE.error("Non-Java type executors are not supported. executorType:[{}]", dispatchJob.getExecutorType());
                return new Result<>("Non-Java type executors are not supported", Boolean.FALSE);
            }

            JobExecutorInfo jobExecutorInfo = JobExecutorInfoCache.get(jobContext.getExecutorInfo());
            if (Objects.isNull(jobExecutorInfo)) {
                TaskEngineLog.REMOTE.error("Executor configuration is incorrect. executorInfo:[{}]", dispatchJob.getExecutorInfo());
                return new Result<>("Executor configuration is incorrect", Boolean.FALSE);
            }

            // 选择执行器
            Object executor = jobExecutorInfo.getExecutor();
            IJobExecutor jobExecutor;
            if (IJobExecutor.class.isAssignableFrom(executor.getClass())) {
                if (JobTaskTypeEnum.MAP.getType() == jobContext.getTaskType()) {
                    jobExecutor = (AbstractMapExecutor) executor;
                } else if (JobTaskTypeEnum.MAP_REDUCE.getType() == jobContext.getTaskType()) {
                    jobExecutor = (AbstractMapReduceExecutor) executor;
                } else {
                    jobExecutor = (AbstractJobExecutor) executor;
                }
            } else {
                if (JobTaskTypeEnum.MAP.getType() == jobContext.getTaskType()) {
                    jobExecutor = Container.get(AnnotationMapJobExecutor.class);
                } else if (JobTaskTypeEnum.MAP_REDUCE.getType() == jobContext.getTaskType()) {
                    jobExecutor = Container.get(AnnotationMapReduceJobExecutor.class);
                } else {
                    jobExecutor = Container.get(AnnotationJobExecutor.class);
                }
            }

            TaskEngineLog.REMOTE.info(" Task scheduler:[{}] Task ID:[{}] Task batch:[{}] Workflow batch:[{}] Task scheduled successfully.",
                    Objects.isNull(dispatchJob.getWorkflowTaskBatchId()) ? "job" : "workflow",
                    dispatchJob.getJobId(),
                    dispatchJob.getTaskBatchId(),
                    dispatchJob.getWorkflowTaskBatchId());

            jobExecutor.jobExecute(jobContext);

        } catch (Exception e) {
            TaskEngineLog.REMOTE.error("Client encountered an unexpected exception. taskBatchId:[{}]", dispatchJob.getTaskBatchId());
            throw e;
        } finally {
            TaskLogManager.removeLogMeta();
        }

        return new Result<>(Boolean.TRUE);
    }

    private void initLogContext(JobContext jobContext) {
        JobLogMeta logMeta = new JobLogMeta();
        logMeta.setNamespaceId(jobContext.getNamespaceId());
        logMeta.setTaskId(jobContext.getTaskId());
        logMeta.setGroupName(jobContext.getGroupName());
        logMeta.setJobId(jobContext.getJobId());
        logMeta.setTaskBatchId(jobContext.getTaskBatchId());
        TaskLogManager.initLogInfo(logMeta, LogTypeEnum.JOB);
    }


    private static JobContext buildJobContext(DispatchJobRequest dispatchJob) {
        JobContext jobContext = new JobContext();
        jobContext.setJobId(dispatchJob.getJobId());
        jobContext.setShardingTotal(dispatchJob.getShardingTotal());
        jobContext.setShardingIndex(dispatchJob.getShardingIndex());
        jobContext.setNamespaceId(dispatchJob.getNamespaceId());
        jobContext.setTaskId(dispatchJob.getTaskId());
        jobContext.setTaskBatchId(dispatchJob.getTaskBatchId());
        jobContext.setGroupName(dispatchJob.getGroupName());
        jobContext.setExecutorInfo(dispatchJob.getExecutorInfo());
        jobContext.setParallelNum(dispatchJob.getParallelNum());
        jobContext.setTaskType(dispatchJob.getTaskType());
        jobContext.setExecutorTimeout(dispatchJob.getExecutorTimeout());
        jobContext.setWorkflowNodeId(dispatchJob.getWorkflowNodeId());
        jobContext.setWorkflowTaskBatchId(dispatchJob.getWorkflowTaskBatchId());
        jobContext.setRetryStatus(dispatchJob.getRetryStatus());
        jobContext.setRetryScene(dispatchJob.getRetryScene());
        jobContext.setTaskName(dispatchJob.getTaskName());
        jobContext.setMrStage(dispatchJob.getMrStage());

        if (StrUtil.isNotBlank(dispatchJob.getArgsStr())) {
            try {
                jobContext.setJobArgsHolder(JsonUtil.parseObject(dispatchJob.getArgsStr(), JobArgsHolder.class));
            } catch (Exception e) {
                TaskEngineLog.REMOTE.warn("argsStr parse error", e);
                JobArgsHolder jobArgsHolder = new JobArgsHolder();
                jobArgsHolder.setJobParams(dispatchJob.getArgsStr());
                jobContext.setJobArgsHolder(jobArgsHolder);
            }
        } else {
            // 没有数据给个空对象，方便后面取参数
            jobContext.setJobArgsHolder(new JobArgsHolder());
        }

        String wfContext = dispatchJob.getWfContext();
        if (StrUtil.isNotBlank(wfContext)) {
            try {
                jobContext.setWfContext(JsonUtil.parseConcurrentHashMap(wfContext));
            } catch (Exception e) {
                TaskEngineLog.REMOTE.warn("workflow context parse error", e);
            }
        } else {
            jobContext.setWfContext(Maps.newConcurrentMap());
        }

        return jobContext;
    }

    @Mapping(path = JOB_STOP, method = RequestMethod.POST)
    public Result<Boolean> stopJob(@Valid StopJobRequest interruptJob) {

        ThreadPoolExecutor threadPool = ThreadPoolCache.getThreadPool(interruptJob.getTaskBatchId());
        if (Objects.isNull(threadPool) || threadPool.isShutdown() || threadPool.isTerminated()) {
            return new Result<>(Boolean.TRUE);
        }

        ThreadPoolCache.stopThreadPool(interruptJob.getTaskBatchId());
        return new Result<>(threadPool.isShutdown() || threadPool.isTerminated());
    }
}
