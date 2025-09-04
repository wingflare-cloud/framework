package com.wingflare.engine.task.server.job.support.stop;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskStatusEnum;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.JobExecutorResultDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.JobTaskStopHandler;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.pekko.actor.ActorRef;
import org.springframework.beans.factory.InitializingBean;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-03 09:48:10
 * @since 2.4.0
 */
public abstract class AbstractJobTaskStopHandler implements JobTaskStopHandler, InitializingBean {

    @Resource
    private JobTaskMapper jobTaskMapper;
    @Resource
    private JobTaskBatchMapper jobTaskBatchMapper;

    protected abstract void doStop(TaskStopJobContext context);

    @Override
    public void stop(TaskStopJobContext context) {

        LambdaQueryWrapper<JobTask> queryWrapper = new LambdaQueryWrapper<JobTask>()
                .eq(JobTask::getTaskBatchId, context.getTaskBatchId());

        if (!context.isForceStop()) {
            queryWrapper.in(JobTask::getTaskStatus, JobTaskStatusEnum.NOT_COMPLETE);
        }

        List<JobTask> jobTasks = jobTaskMapper.selectList(queryWrapper);

        if (CollUtil.isEmpty(jobTasks)) {
            // 若没有任务项，直接变更状态为已停止
            JobTaskBatch jobTaskBatch = new JobTaskBatch();
            jobTaskBatch.setId(context.getTaskBatchId());
            jobTaskBatch.setTaskBatchStatus(JobTaskBatchStatusEnum.STOP.getStatus());
            jobTaskBatch.setOperationReason(context.getJobOperationReason());
            jobTaskBatchMapper.updateById(jobTaskBatch);
            return;
        }

        context.setJobTasks(jobTasks);

        doStop(context);

        if (context.isNeedUpdateTaskStatus()) {
            for (final JobTask jobTask : jobTasks) {
                if (jobTask.getTaskStatus() == JobTaskStatusEnum.SUCCESS.getStatus()){
                    continue;
                }
                JobExecutorResultDTO jobExecutorResultDTO = JobTaskConverter.INSTANCE.toJobExecutorResultDTO(jobTask);
                jobExecutorResultDTO.setTaskStatus(JobTaskStatusEnum.STOP.getStatus());
                jobExecutorResultDTO.setMessage("Task stopped successfully");
                jobExecutorResultDTO.setJobOperationReason(context.getJobOperationReason());
                jobExecutorResultDTO.setTaskType(getTaskType().getType());
                jobExecutorResultDTO.setWorkflowNodeId(context.getWorkflowNodeId());
                jobExecutorResultDTO.setWorkflowTaskBatchId(context.getWorkflowTaskBatchId());
                ActorRef actorRef = ActorGenerator.jobTaskExecutorResultActor();
                actorRef.tell(jobExecutorResultDTO, actorRef);
            }

        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JobTaskStopFactory.registerTaskStop(getTaskType(), this);
    }
}
