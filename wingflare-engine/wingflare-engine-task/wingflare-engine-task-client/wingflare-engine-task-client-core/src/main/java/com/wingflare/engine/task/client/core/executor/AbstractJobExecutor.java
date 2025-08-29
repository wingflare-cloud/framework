package com.wingflare.engine.task.client.core.executor;

import com.wingflare.engine.task.client.common.log.support.SnailJobLogManager;
import com.wingflare.engine.task.client.core.IJobExecutor;
import com.wingflare.engine.task.client.core.cache.FutureCache;
import com.wingflare.engine.task.client.core.cache.ThreadPoolCache;
import com.wingflare.engine.task.client.core.dto.*;
import com.wingflare.engine.task.client.core.log.JobLogMeta;
import com.wingflare.engine.task.client.core.timer.StopTaskTimerTask;
import com.wingflare.engine.task.client.core.timer.TimerManager;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.enums.MapReduceStageEnum;
import com.wingflare.engine.task.common.core.model.JobArgsHolder;
import com.wingflare.engine.task.common.core.model.JobContext;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.common.model.dto.ExecuteResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 广播模式
 *
 * @author: opensnail
 * @date : 2023-09-27 09:48
 * @since 2.4.0
 */
public abstract class AbstractJobExecutor implements IJobExecutor {

    @Override
    public void jobExecute(JobContext jobContext) {

        // 创建可执行的任务
        Integer parallelNum = Optional.ofNullable(jobContext.getParallelNum()).orElse(1);
        ThreadPoolExecutor threadPool = ThreadPoolCache.createThreadPool(jobContext.getTaskBatchId(), Math.max(1, parallelNum));
        ListeningExecutorService decorator = MoreExecutors.listeningDecorator(threadPool);

        // 将任务添加到时间轮中，到期停止任务
        TimerManager.add(new StopTaskTimerTask(jobContext.getTaskBatchId()), jobContext.getExecutorTimeout(), TimeUnit.SECONDS);

        jobContext.setChangeWfContext(Maps.newConcurrentMap());
        // 执行任务
        ListenableFuture<ExecuteResult> submit = decorator.submit(() -> {
            JobArgs jobArgs;
            if (jobContext.getTaskType() == JobTaskTypeEnum.SHARDING.getType()) {
                jobArgs = buildShardingJobArgs(jobContext);
            } else if (Lists.newArrayList(JobTaskTypeEnum.MAP_REDUCE.getType(), JobTaskTypeEnum.MAP.getType())
                    .contains(jobContext.getTaskType())) {
                if (MapReduceStageEnum.MAP.getStage() == jobContext.getMrStage()) {
                    jobArgs = buildMapJobArgs(jobContext);
                } else if (MapReduceStageEnum.REDUCE.getStage() == jobContext.getMrStage()) {
                    jobArgs = buildReduceJobArgs(jobContext);
                } else {
                    jobArgs = buildMergeReduceJobArgs(jobContext);
                }

            } else {
                jobArgs = buildJobArgs(jobContext);
            }

            jobArgs.setWfContext(jobContext.getWfContext());
            jobArgs.setChangeWfContext(jobContext.getChangeWfContext());
            jobArgs.setJobId(jobContext.getJobId());

            try {
                // 初始化调度信息（日志上报LogUtil）
                initLogContext(jobContext);
                return doJobExecute(jobArgs);
            } finally {
                SnailJobLogManager.removeLogMeta();
                JobContextManager.removeJobContext();
            }

        });

        FutureCache.addFuture(jobContext.getTaskBatchId(), submit);
        Futures.addCallback(submit, new JobExecutorFutureCallback(jobContext), decorator);
    }

    private void initLogContext(JobContext jobContext) {
        JobLogMeta logMeta = new JobLogMeta();
        logMeta.setNamespaceId(jobContext.getNamespaceId());
        logMeta.setTaskId(jobContext.getTaskId());
        logMeta.setGroupName(jobContext.getGroupName());
        logMeta.setJobId(jobContext.getJobId());
        logMeta.setTaskBatchId(jobContext.getTaskBatchId());
        SnailJobLogManager.initLogInfo(logMeta, LogTypeEnum.JOB);
        JobContextManager.setJobContext(jobContext);
    }

    private static JobArgs buildJobArgs(JobContext jobContext) {
        JobArgs jobArgs = new JobArgs();
        jobArgs.setJobParams(jobContext.getJobArgsHolder().getJobParams());
        jobArgs.setExecutorInfo(jobContext.getExecutorInfo());
        jobArgs.setTaskBatchId(jobContext.getTaskBatchId());
        jobArgs.setWorkflowNodeId(jobContext.getWorkflowNodeId());
        jobArgs.setWorkflowTaskBatchId(jobContext.getWorkflowTaskBatchId());
        return jobArgs;
    }

    private static JobArgs buildShardingJobArgs(JobContext jobContext) {
        ShardingJobArgs jobArgs = new ShardingJobArgs();
        jobArgs.setJobParams(jobContext.getJobArgsHolder().getJobParams());
        jobArgs.setTaskBatchId(jobContext.getTaskBatchId());
        jobArgs.setExecutorInfo(jobContext.getExecutorInfo());
        jobArgs.setShardingIndex(jobContext.getShardingIndex());
        jobArgs.setShardingTotal(jobContext.getShardingTotal());
        jobArgs.setWorkflowNodeId(jobContext.getWorkflowNodeId());
        jobArgs.setWorkflowTaskBatchId(jobContext.getWorkflowTaskBatchId());
        return jobArgs;
    }

    private static JobArgs buildMapJobArgs(JobContext jobContext) {
        MapArgs jobArgs = new MapArgs();
        JobArgsHolder jobArgsHolder = jobContext.getJobArgsHolder();
        jobArgs.setJobParams(jobArgsHolder.getJobParams());
        jobArgs.setMapResult(jobArgsHolder.getMaps());
        jobArgs.setExecutorInfo(jobContext.getExecutorInfo());
        jobArgs.setTaskName(jobContext.getTaskName());
        jobArgs.setTaskBatchId(jobContext.getTaskBatchId());
        jobArgs.setWorkflowNodeId(jobContext.getWorkflowNodeId());
        jobArgs.setWorkflowTaskBatchId(jobContext.getWorkflowTaskBatchId());
        return jobArgs;
    }

    private static JobArgs buildReduceJobArgs(JobContext jobContext) {
        ReduceArgs jobArgs = new ReduceArgs();
        JobArgsHolder jobArgsHolder = jobContext.getJobArgsHolder();
        jobArgs.setJobParams(jobArgsHolder.getJobParams());
        Object maps = jobArgsHolder.getMaps();
        if (Objects.nonNull(maps)) {
            if (maps instanceof String) {
                jobArgs.setMapResult(JsonUtil.parseList((String) maps, Object.class));
            } else {
                jobArgs.setMapResult((List<?>) maps);
            }
        }
        jobArgs.setExecutorInfo(jobContext.getExecutorInfo());
        jobArgs.setTaskBatchId(jobContext.getTaskBatchId());
        jobArgs.setWfContext(jobContext.getWfContext());
        jobArgs.setWorkflowNodeId(jobContext.getWorkflowNodeId());
        jobArgs.setWorkflowTaskBatchId(jobContext.getWorkflowTaskBatchId());
        return jobArgs;
    }

    private static JobArgs buildMergeReduceJobArgs(JobContext jobContext) {
        MergeReduceArgs jobArgs = new MergeReduceArgs();
        JobArgsHolder jobArgsHolder = jobContext.getJobArgsHolder();
        jobArgs.setJobParams(jobArgsHolder.getJobParams());
        Object reduces = jobArgsHolder.getReduces();
        if (Objects.nonNull(reduces)) {
            if (reduces instanceof String) {
                jobArgs.setReduces(JsonUtil.parseList((String) reduces, Object.class));
            } else {
                jobArgs.setReduces((List<?>) reduces);
            }
        }

        jobArgs.setExecutorInfo(jobContext.getExecutorInfo());
        jobArgs.setTaskBatchId(jobContext.getTaskBatchId());
        jobArgs.setWfContext(jobContext.getWfContext());
        jobArgs.setWorkflowNodeId(jobContext.getWorkflowNodeId());
        jobArgs.setWorkflowTaskBatchId(jobContext.getWorkflowTaskBatchId());
        return jobArgs;
    }

    protected abstract ExecuteResult doJobExecute(JobArgs jobArgs);
}
