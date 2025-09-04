package com.wingflare.engine.task.server.job.support.handler;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.enums.*;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.JobArgsHolder;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.server.common.WaitStrategy;
import com.wingflare.engine.task.server.common.dto.DistributeInstance;
import com.wingflare.engine.task.server.common.enums.JobTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.strategy.WaitStrategies;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.*;
import com.wingflare.engine.task.server.job.support.JobExecutorResultHandler;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.cache.ResidentTaskCache;
import com.wingflare.engine.task.server.job.support.result.job.JobExecutorResultContext;
import com.wingflare.engine.task.server.job.support.timer.JobTimerWheel;
import com.wingflare.engine.task.server.job.support.timer.ResidentJobTimerTask;
import com.wingflare.engine.task.datasource.template.persistence.mapper.GroupConfigMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum.COMPLETED;

/**
 * @author: opensnail
 * @date : 2023-10-10 16:50
 */
@Component
public class JobTaskBatchHandler {

    private static final Logger log = LoggerFactory.getLogger(JobTaskBatchHandler.class);
    private final JobTaskBatchMapper jobTaskBatchMapper;
    private final JobTaskMapper jobTaskMapper;
    private final GroupConfigMapper groupConfigMapper;
    private final List<JobExecutorResultHandler> resultHandlerList;

    public JobTaskBatchHandler(JobTaskBatchMapper jobTaskBatchMapper, JobTaskMapper jobTaskMapper, GroupConfigMapper groupConfigMapper, List<JobExecutorResultHandler> resultHandlerList) {
        this.jobTaskBatchMapper = jobTaskBatchMapper;
        this.jobTaskMapper = jobTaskMapper;
        this.groupConfigMapper = groupConfigMapper;
        this.resultHandlerList = resultHandlerList;
    }

    public boolean handleResult(CompleteJobBatchDTO completeJobBatchDTO) {
        Assert.notNull(completeJobBatchDTO.getTaskType(), ()-> new TaskServerException("taskType can not be null"));
        Assert.notNull(completeJobBatchDTO.getRetryStatus(), ()-> new TaskServerException("retryStatus can not be null"));

        // 非重试流量幂等处理
        if(Boolean.FALSE.equals(completeJobBatchDTO.getRetryStatus())) {
            // 幂等处理
            Long countJobTaskBatch = jobTaskBatchMapper.selectCount(new LambdaQueryWrapper<JobTaskBatch>()
                .eq(JobTaskBatch::getId, completeJobBatchDTO.getTaskBatchId())
                .in(JobTaskBatch::getTaskBatchStatus, COMPLETED)
            );
            if (countJobTaskBatch > 0) {
                // 批次已经完成了，不需要重复更新
                return true;
            }
        }

        JobExecutorResultContext context = JobTaskConverter.INSTANCE.toJobExecutorResultContext(completeJobBatchDTO);
        for (final JobExecutorResultHandler jobExecutorResultHandler : resultHandlerList) {
            if (completeJobBatchDTO.getTaskType().equals(jobExecutorResultHandler.getTaskInstanceType().getType())) {
                jobExecutorResultHandler.handleResult(context);
                break;
            }
        }

        // 处理的结果 若已经更新成功 或者 需要开启reduce任务都算是已经处理了
        return context.isTaskBatchComplete() || context.isCreateReduceTask();
    }

    /**
     * 开启常驻任务
     *
     * @param job            定时任务配置信息
     * @param taskExecuteDTO 任务执行新
     */
    public void openResidentTask(Job job, TaskExecuteDTO taskExecuteDTO) {
        if (Objects.isNull(job)
                || Objects.equals(StatusEnum.NO.getStatus(), job.getJobStatus())
                || JobTaskExecutorSceneEnum.MANUAL_JOB.getType().equals(taskExecuteDTO.getTaskExecutorScene())
                || JobTaskExecutorSceneEnum.AUTO_WORKFLOW.getType().equals(taskExecuteDTO.getTaskExecutorScene())
                || JobTaskExecutorSceneEnum.MANUAL_WORKFLOW.getType().equals(taskExecuteDTO.getTaskExecutorScene())
                // 是否是常驻任务
                || Objects.equals(StatusEnum.NO.getStatus(), job.getResident())
                // 防止任务已经分配到其他节点导致的任务重复执行
                || !DistributeInstance.INSTANCE.getConsumerBucket().contains(job.getBucketIndex())
        ) {
            return;
        }

        long count = groupConfigMapper.selectCount(new LambdaQueryWrapper<GroupConfig>()
                .eq(GroupConfig::getNamespaceId, job.getNamespaceId())
                .eq(GroupConfig::getGroupName, job.getGroupName())
                .eq(GroupConfig::getGroupStatus, StatusEnum.YES.getStatus()));
        if (count == 0) {
            return;
        }

        JobTimerTaskDTO jobTimerTaskDTO = new JobTimerTaskDTO();
        jobTimerTaskDTO.setJobId(taskExecuteDTO.getJobId());
        jobTimerTaskDTO.setTaskBatchId(taskExecuteDTO.getTaskBatchId());
        jobTimerTaskDTO.setTaskExecutorScene(JobTaskExecutorSceneEnum.AUTO_JOB.getType());
        WaitStrategy waitStrategy = WaitStrategies.WaitStrategyEnum.getWaitStrategy(job.getTriggerType());

        Long preTriggerAt = ResidentTaskCache.get(job.getId());
        if (Objects.isNull(preTriggerAt) || preTriggerAt < job.getNextTriggerAt()) {
            preTriggerAt = job.getNextTriggerAt();
        }

        WaitStrategies.WaitStrategyContext waitStrategyContext = new WaitStrategies.WaitStrategyContext();
        waitStrategyContext.setTriggerInterval(job.getTriggerInterval());
        waitStrategyContext.setNextTriggerAt(preTriggerAt);
        Long nextTriggerAt = waitStrategy.computeTriggerTime(waitStrategyContext);

        // 获取时间差的毫秒数
        long milliseconds = nextTriggerAt - preTriggerAt;

        Duration duration = Duration.ofMillis(milliseconds - DateUtils.toNowMilli() % 1000);

        log.debug("Resident task monitoring. [{}] Task time difference:[{}] Modulus:[{}]", duration, milliseconds,
                DateUtils.toNowMilli() % 1000);
        job.setNextTriggerAt(nextTriggerAt);
        JobTimerWheel.registerWithJob(() -> new ResidentJobTimerTask(jobTimerTaskDTO, job), duration);
        ResidentTaskCache.refresh(job.getId(), nextTriggerAt);
    }

    /**
     * 这里为了兼容MAP或者MAP_REDUCE场景下手动执行任务的时候参数丢失问题，
     * 需要从JobTask中获取任务类型为MAP的且是taskName是ROOT_MAP的任务的参数作为执行参数下发给客户端
     *
     * @param taskBatchId 任务批次
     * @param job 任务
     * @return 需要给客户端下发的参数
     */
    public String getArgStr(Long taskBatchId, Job job) {
        JobTask rootMapTask = jobTaskMapper.selectList(new PageDTO<>(1, 1),
                new LambdaQueryWrapper<JobTask>()
                        .select(JobTask::getId, JobTask::getArgsStr)
                        .eq(JobTask::getTaskBatchId, taskBatchId)
                        .eq(JobTask::getMrStage,  MapReduceStageEnum.MAP.getStage())
                        .eq(JobTask::getTaskName, SystemConstants.ROOT_MAP)
                        .orderByAsc(JobTask::getId)
        ).stream().findFirst().orElse(null);

        // {"jobParams":"Test parameter passing","maps":""}
        String argsStr = job.getArgsStr();
        if (Objects.nonNull(rootMapTask) && StrUtil.isNotBlank(rootMapTask.getArgsStr())) {
            JobArgsHolder jobArgsHolder = JsonUtil.parseObject(rootMapTask.getArgsStr(), JobArgsHolder.class);
            // MAP_REDUCE的参数: {"shardNum":2,"argsStr":"Test parameter passing"} 这里得解析出来覆盖argsStr
            if (JobTaskTypeEnum.MAP_REDUCE.getType() == job.getTaskType()) {
                MapReduceArgsStrDTO mapReduceArgsStrDTO = JsonUtil.parseObject(argsStr, MapReduceArgsStrDTO.class);
                mapReduceArgsStrDTO.setArgsStr((String) jobArgsHolder.getJobParams());
                argsStr = JsonUtil.toJsonString(mapReduceArgsStrDTO);
            } else {
                // MAP的参数: 测试参数传递 直接覆盖即可
                argsStr = (String) jobArgsHolder.getJobParams();
            }
        }

        return argsStr;
    }

}
