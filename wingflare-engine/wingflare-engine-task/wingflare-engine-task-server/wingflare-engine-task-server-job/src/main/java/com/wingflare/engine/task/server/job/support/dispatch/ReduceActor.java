package com.wingflare.engine.task.server.job.support.dispatch;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.ReduceTaskDTO;
import com.wingflare.engine.task.server.job.support.JobExecutor;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.executor.job.JobExecutorContext;
import com.wingflare.engine.task.server.job.support.executor.job.JobExecutorFactory;
import com.wingflare.engine.task.server.job.support.generator.task.JobTaskGenerateContext;
import com.wingflare.engine.task.server.job.support.generator.task.JobTaskGenerator;
import com.wingflare.engine.task.server.job.support.generator.task.JobTaskGeneratorFactory;
import com.wingflare.engine.task.server.job.support.handler.DistributedLockHandler;
import com.wingflare.engine.task.server.job.support.handler.JobTaskBatchHandler;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.WorkflowTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import com.wingflare.engine.task.datasource.template.persistence.po.WorkflowTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.pekko.actor.AbstractActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * 负责生成reduce任务并执行
 *
 * @author: opensnail
 * @date : 2024-06-12
 * @since : sj_1.1.0
 */
@Component(ActorGenerator.JOB_REDUCE_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReduceActor extends AbstractActor {

    private static final String KEY = "job_generate_reduce_{0}_{1}";
    private final DistributedLockHandler distributedLockHandler;
    private final JobMapper jobMapper;
    private final JobTaskMapper jobTaskMapper;
    private final WorkflowTaskBatchMapper workflowTaskBatchMapper;
    private final JobTaskBatchHandler jobTaskBatchHandler;

    public ReduceActor(DistributedLockHandler distributedLockHandler, JobMapper jobMapper, JobTaskMapper jobTaskMapper, WorkflowTaskBatchMapper workflowTaskBatchMapper, JobTaskBatchHandler jobTaskBatchHandler) {
        this.distributedLockHandler = distributedLockHandler;
        this.jobMapper = jobMapper;
        this.jobTaskMapper = jobTaskMapper;
        this.workflowTaskBatchMapper = workflowTaskBatchMapper;
        this.jobTaskBatchHandler = jobTaskBatchHandler;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(ReduceTaskDTO.class, reduceTask -> {
            TaskEngineLog.LOCAL.info("Executing Reduce, [{}]", JsonUtil.toJsonString(reduceTask));
            try {

                Assert.notNull(reduceTask.getMrStage(), () -> new TaskServerException("mrStage can not be null"));
                Assert.notNull(reduceTask.getJobId(), () -> new TaskServerException("jobId can not be null"));
                Assert.notNull(reduceTask.getTaskBatchId(),
                        () -> new TaskServerException("taskBatchId can not be null"));
                String key = MessageFormat.format(KEY, reduceTask.getTaskBatchId(), reduceTask.getJobId());
                distributedLockHandler.lockWithDisposableAndRetry(() -> {
                    doReduce(reduceTask);
                }, key, Duration.ofSeconds(1), Duration.ofSeconds(2), 6);
            } catch (Exception e) {
                TaskEngineLog.LOCAL.error("Reduce processing exception. [{}]", reduceTask, e);
            } finally {
                getContext().stop(getSelf());
            }

        }).build();
    }

    private void doReduce(final ReduceTaskDTO reduceTask) {

        List<JobTask> jobTasks = jobTaskMapper.selectList(new PageDTO<>(1, 1),
                new LambdaQueryWrapper<JobTask>()
                        .select(JobTask::getId)
                        .eq(JobTask::getTaskBatchId, reduceTask.getTaskBatchId())
                        .eq(JobTask::getMrStage, reduceTask.getMrStage())
                        .orderByAsc(JobTask::getId)
        );

        if (CollUtil.isNotEmpty(jobTasks)) {
            // 说明已经创建了reduce 或者 merge reduce 任务了
            return;
        }

        Job job = jobMapper.selectById(reduceTask.getJobId());
        // 非MAP_REDUCE任务不处理
        if (JobTaskTypeEnum.MAP_REDUCE.getType() != job.getTaskType()) {
            return;
        }

        String argStr = jobTaskBatchHandler.getArgStr(reduceTask.getTaskBatchId(), job);

        // 创建reduce任务
        JobTaskGenerator taskInstance = JobTaskGeneratorFactory.getTaskInstance(JobTaskTypeEnum.MAP_REDUCE.getType());
        JobTaskGenerateContext context = JobTaskConverter.INSTANCE.toJobTaskInstanceGenerateContext(job);
        context.setTaskBatchId(reduceTask.getTaskBatchId());
        context.setMrStage(reduceTask.getMrStage());
        context.setWfContext(reduceTask.getWfContext());
        context.setArgsStr(argStr);
        List<JobTask> taskList = taskInstance.generate(context);
        if (CollUtil.isEmpty(taskList)) {
            TaskEngineLog.LOCAL.warn("Job task is empty, taskBatchId:[{}]", reduceTask.getTaskBatchId());
            return;
        }

        String wfContext = null;
        if (Objects.nonNull(reduceTask.getWorkflowTaskBatchId())) {
            WorkflowTaskBatch workflowTaskBatch = workflowTaskBatchMapper.selectOne(
                    new LambdaQueryWrapper<WorkflowTaskBatch>()
                            .select(WorkflowTaskBatch::getWfContext, WorkflowTaskBatch::getId)
                            .eq(WorkflowTaskBatch::getId, reduceTask.getWorkflowTaskBatchId())
            );
            wfContext = workflowTaskBatch.getWfContext();
        }

        // 执行任务
        JobExecutor jobExecutor = JobExecutorFactory.getJobExecutor(JobTaskTypeEnum.MAP_REDUCE.getType());
        jobExecutor.execute(buildJobExecutorContext(reduceTask, job, taskList, wfContext));

    }

    private static JobExecutorContext buildJobExecutorContext(
            ReduceTaskDTO reduceTask,
            Job job,
            List<JobTask> taskList,
            String wfContext) {
        JobExecutorContext context = JobTaskConverter.INSTANCE.toJobExecutorContext(job);
        context.setTaskList(taskList);
        context.setTaskBatchId(reduceTask.getTaskBatchId());
        context.setWorkflowTaskBatchId(reduceTask.getWorkflowTaskBatchId());
        context.setWorkflowNodeId(reduceTask.getWorkflowNodeId());
        context.setWfContext(wfContext);
        return context;
    }
}
