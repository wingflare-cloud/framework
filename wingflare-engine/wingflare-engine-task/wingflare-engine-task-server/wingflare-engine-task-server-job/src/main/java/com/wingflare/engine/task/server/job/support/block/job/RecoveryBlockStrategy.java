package com.wingflare.engine.task.server.job.support.block.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.core.enums.JobBlockStrategyEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskStatusEnum;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.server.common.enums.JobTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.TaskExecuteDTO;
import com.wingflare.engine.task.server.job.support.JobExecutor;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.executor.job.JobExecutorContext;
import com.wingflare.engine.task.server.job.support.executor.job.JobExecutorFactory;
import com.wingflare.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.task.datasource.template.persistence.po.Job;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.pekko.actor.ActorRef;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 重新触发执行失败的任务
 *
 * @author opensnail
 * @date 2024-06-16 11:30:59
 * @since sj_1.1.0
 */
@Component
public class RecoveryBlockStrategy extends AbstracJobBlockStrategy {

    private final JobTaskMapper jobTaskMapper;
    private final JobMapper jobMapper;

    public RecoveryBlockStrategy(JobTaskMapper jobTaskMapper, JobMapper jobMapper) {
        this.jobTaskMapper = jobTaskMapper;
        this.jobMapper = jobMapper;
    }

    @Override
    protected void doBlock(BlockStrategyContext context) {
        Assert.notNull(context.getJobId(), () -> new TaskServerException("job id can not be null"));
        Assert.notNull(context.getTaskBatchId(), () -> new TaskServerException("task batch id can not be null"));
        Assert.notNull(context.getTaskType(), () -> new TaskServerException("task type can not be null"));

        List<JobTask> jobTasks = jobTaskMapper.selectList(
            new LambdaQueryWrapper<JobTask>()
                .eq(JobTask::getTaskBatchId, context.getTaskBatchId())
        );

        //  若任务项为空则生成任务项
        if (CollUtil.isEmpty(jobTasks)) {
            TaskExecuteDTO taskExecuteDTO = new TaskExecuteDTO();
            taskExecuteDTO.setTaskBatchId(context.getTaskBatchId());
            taskExecuteDTO.setJobId(context.getJobId());
            taskExecuteDTO.setTaskExecutorScene(JobTaskExecutorSceneEnum.MANUAL_JOB.getType());
            taskExecuteDTO.setWorkflowTaskBatchId(context.getWorkflowTaskBatchId());
            taskExecuteDTO.setWorkflowNodeId(context.getWorkflowNodeId());
            ActorRef actorRef = ActorGenerator.jobTaskExecutorActor();
            actorRef.tell(taskExecuteDTO, actorRef);
            return;
        }

        Job job = jobMapper.selectById(context.getJobId());
        // 执行任务 Stop or Fail 任务
        JobExecutor jobExecutor = JobExecutorFactory.getJobExecutor(context.getTaskType());
        jobExecutor.execute(buildJobExecutorContext(context, job,
            StreamUtils.filter(jobTasks,
                (jobTask) -> JobTaskStatusEnum.NOT_SUCCESS.contains(jobTask.getTaskStatus())
                             )));
    }

    @Override
    protected JobBlockStrategyEnum blockStrategyEnum() {
        return JobBlockStrategyEnum.RECOVERY;
    }

    private static JobExecutorContext buildJobExecutorContext(BlockStrategyContext strategyContext, Job job,
        List<JobTask> taskList) {
        JobExecutorContext context = JobTaskConverter.INSTANCE.toJobExecutorContext(job);
        context.setTaskList(taskList);
        context.setTaskBatchId(strategyContext.getTaskBatchId());
        context.setWorkflowTaskBatchId(strategyContext.getWorkflowTaskBatchId());
        context.setWorkflowNodeId(strategyContext.getWorkflowNodeId());
        return context;
    }

}
