package com.wingflare.engine.task.server.job.support.dispatch;

import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.pekko.ActorGenerator;
import com.wingflare.engine.task.server.job.dto.CompleteJobBatchDTO;
import com.wingflare.engine.task.server.job.dto.JobExecutorResultDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.handler.JobTaskBatchHandler;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.task.datasource.template.persistence.po.JobTask;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.pekko.actor.AbstractActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author opensnail
 * @date 2023-10-05 17:16:35
 * @since 2.4.0
 */
@Component(ActorGenerator.JOB_EXECUTOR_RESULT_ACTOR)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobExecutorResultActor extends AbstractActor {
    private final JobTaskMapper jobTaskMapper;
    private final JobTaskBatchHandler jobTaskBatchHandler;

    public JobExecutorResultActor(JobTaskMapper jobTaskMapper, JobTaskBatchHandler jobTaskBatchHandler) {
        this.jobTaskMapper = jobTaskMapper;
        this.jobTaskBatchHandler = jobTaskBatchHandler;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(JobExecutorResultDTO.class, result -> {
            SnailJobLog.LOCAL.debug("Update task status. Parameters:[{}]", JsonUtil.toJsonString(result));
            try {
                Assert.notNull(result.getTaskId(), ()-> new TaskServerException("taskId can not be null"));
                Assert.notNull(result.getJobId(), ()-> new TaskServerException("jobId can not be null"));
                Assert.notNull(result.getTaskBatchId(), ()-> new TaskServerException("taskBatchId can not be null"));
                Assert.notNull(result.getTaskType(), ()-> new TaskServerException("taskType can not be null"));

                JobTask jobTask = new JobTask();
                jobTask.setTaskStatus(result.getTaskStatus());
                jobTask.setWfContext(result.getWfContext());
                if (Objects.nonNull(result.getResult())) {
                    if (result.getResult() instanceof String) {
                        jobTask.setResultMessage((String) result.getResult());
                    } else {
                        jobTask.setResultMessage(JsonUtil.toJsonString(result.getResult()));
                    }
                }

                Assert.isTrue(1 == jobTaskMapper.update(jobTask,
                                new LambdaUpdateWrapper<JobTask>().eq(JobTask::getId, result.getTaskId())),
                        () -> new TaskServerException("Updating task instance failed"));

                // 除MAP和MAP_REDUCE 任务之外，其他任务都是叶子节点
                if (Objects.nonNull(result.getIsLeaf()) && StatusEnum.NO.getStatus().equals(result.getIsLeaf())) {
                    return;
                }

                tryCompleteAndStop(result);
            } catch (Exception e) {
                SnailJobLog.LOCAL.error(" job executor result exception. [{}]", result, e);
            } finally {
                getContext().stop(getSelf());
            }

        }).build();

    }

    private void tryCompleteAndStop(JobExecutorResultDTO jobExecutorResultDTO) {
        CompleteJobBatchDTO completeJobBatchDTO = JobTaskConverter.INSTANCE.toCompleteJobBatchDTO(jobExecutorResultDTO);
        jobTaskBatchHandler.handleResult(completeJobBatchDTO);
    }
}
