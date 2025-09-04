package com.wingflare.engine.task.server.service.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.common.model.request.base.JobRequest;
import com.wingflare.engine.task.common.model.request.base.JobTriggerRequest;
import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;
import com.wingflare.engine.task.common.model.response.base.JobResponse;
import com.wingflare.engine.task.datasource.template.persistence.po.GroupConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import com.wingflare.engine.task.datasource.template.persistence.po.JobSummary;
import com.wingflare.engine.task.server.common.config.SystemProperties;
import com.wingflare.engine.task.server.common.enums.JobTaskExecutorSceneEnum;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.common.util.TriggerIntervalUtils;
import com.wingflare.engine.task.server.job.dto.JobTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.prepare.job.TerminalJobPrepareHandler;
import com.wingflare.engine.task.server.service.convert.JobConverter;
import com.wingflare.engine.task.server.service.dto.*;
import com.wingflare.engine.task.server.service.kit.JobKit;
import com.wingflare.engine.task.server.service.kit.TriggerIntervalKit;
import com.wingflare.engine.task.server.service.service.JobService;
import com.wingflare.lib.core.Builder;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobSummaryMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.WorkflowNodeMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
public abstract class AbstractJobService implements JobService {
    @Resource
    protected SystemProperties systemProperties;
    @Resource
    protected JobMapper jobMapper;
    @Resource
    protected JobSummaryMapper jobSummaryMapper;
    @Resource
    protected AccessTemplate accessTemplate;
    @Resource
    protected TerminalJobPrepareHandler terminalJobPrepareHandler;
    @Resource
    protected WorkflowNodeMapper workflowNodeMapper;

    @Override
    public Boolean trigger(JobTriggerRequest jobTrigger) {
        Job job = jobMapper.selectById(jobTrigger.getJobId());
        Assert.notNull(job, () -> new TaskServerException("job can not be null."));

        long count = accessTemplate.getGroupConfigAccess().count(new LambdaQueryWrapper<GroupConfig>()
                .eq(GroupConfig::getGroupName, job.getGroupName())
                .eq(GroupConfig::getNamespaceId, job.getNamespaceId())
                .eq(GroupConfig::getGroupStatus, StatusEnum.YES.getStatus())
        );

        Assert.isTrue(count > 0,
                () -> new TaskServerException("Group [{}] is closed, manual execution is not supported.", job.getGroupName()));
        JobTaskPrepareDTO jobTaskPrepare = JobTaskConverter.INSTANCE.toJobTaskPrepare(job);
        // 设置now表示立即执行
        jobTaskPrepare.setNextTriggerAt(DateUtils.toNowMilli());
        jobTaskPrepare.setTaskExecutorScene(JobTaskExecutorSceneEnum.MANUAL_JOB.getType());
        if (StrUtil.isNotBlank(jobTrigger.getTmpArgsStr())) {
            jobTaskPrepare.setTmpArgsStr(jobTrigger.getTmpArgsStr());
        }
        // 创建批次
        terminalJobPrepareHandler.handle(jobTaskPrepare);
        return true;
    }


    /**
     * 删除任务
     *
     * @param ids jobId列表
     * @return
     */
    @Override
    public boolean deleteJobByIds(Set<Long> ids) {
        String namespaceId = getNamespaceId();

        Assert.isTrue(ids.size() == jobMapper.selectList(
                new LambdaQueryWrapper<Job>().select(Job::getId)
                        .eq(Job::getNamespaceId, namespaceId)
                        .eq(Job::getJobStatus, StatusEnum.NO.getStatus())
                        .in(Job::getId, ids)
        ).size(), () -> new TaskServerException("Failed to delete scheduled task, please check if the task status is closed"));

        Assert.isTrue(workflowNodeMapper.selectJobUsedInNonLatestWorkflow(ids).isEmpty(),
                () -> new TaskServerException("Failed to delete scheduled task, please check if the task is used in the workflow"));

        List<JobSummary> jobSummaries = jobSummaryMapper.selectList(new LambdaQueryWrapper<JobSummary>()
                .select(JobSummary::getId)
                .in(JobSummary::getBusinessId, ids)
                .eq(JobSummary::getNamespaceId, namespaceId)
                .eq(JobSummary::getSystemTaskType, SyetemTaskTypeEnum.JOB.getType())
        );
        if (CollUtil.isNotEmpty(jobSummaries)) {
            jobSummaryMapper.deleteByIds(StreamUtils.toSet(jobSummaries, JobSummary::getId));
        }

        Assert.isTrue(ids.size() == jobMapper.delete(
                new LambdaQueryWrapper<Job>()
                        .eq(Job::getNamespaceId, namespaceId)
                        .eq(Job::getJobStatus, StatusEnum.NO.getStatus())
                        .in(Job::getId, ids)
        ), () -> new TaskServerException("Failed to delete scheduled task"));

        return true;
    }

    @Override
    public boolean updateJob(JobRequest jobRequest) {
        Assert.notNull(jobRequest.getId(), () -> new TaskServerException("ID cannot be empty"));
        if (Objects.nonNull(jobRequest.getJobStatus())){
            Assert.notNull(StatusEnum.of(jobRequest.getJobStatus()), () -> new TaskServerException("Invalid status parameter"));
        }
        Job job = jobMapper.selectById(jobRequest.getId());
        Assert.notNull(job, () -> new TaskServerException("Job is null, update failed"));

        // 前置校验
        updateJobPreValidator(jobRequest);

        // 判断常驻任务
        Job updateJob = JobConverter.INSTANCE.convert(jobRequest);
        Integer triggerType = Optional.ofNullable(jobRequest.getTriggerType()).orElse(job.getTriggerType());
        String triggerInterval = jobRequest.getTriggerInterval();
        if (StrUtil.isBlank(triggerInterval)) {
            triggerInterval = job.getTriggerInterval();
        }
        // 封装 pointInTime
        String pointInTimeStr = TriggerIntervalUtils.getPointInTimeStr(triggerInterval, triggerType);
        updateJob.setTriggerInterval(pointInTimeStr);
        updateJob.setResident(JobKit.isResident(triggerType, triggerInterval));

        // check triggerInterval
        checkTriggerInterval(jobRequest);

        CalculateNextTriggerAtDTO nextTriggerAtDTO = Builder.of(CalculateNextTriggerAtDTO::new)
                .with(CalculateNextTriggerAtDTO::setTriggerInterval, updateJob.getTriggerInterval())
                .with(CalculateNextTriggerAtDTO::setTriggerType, triggerType)
                .with(CalculateNextTriggerAtDTO::setNewResident, updateJob.getResident())
                .with(CalculateNextTriggerAtDTO::setOldResident, job.getResident())
                .with(CalculateNextTriggerAtDTO::setId, job.getId())
                .build();

        updateJob.setNextTriggerAt(JobKit.calculateNextTriggerAt(nextTriggerAtDTO));
        // 禁止更新组
        updateJob.setGroupName(null);
        updateJob.setNamespaceId(null);
        updateJob.setOwnerId(Optional.ofNullable(jobRequest.getOwnerId()).orElse(0L));

        LambdaUpdateWrapper<Job> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Job::getId, jobRequest.getId());

        return 1 == jobMapper.update(updateJob, updateWrapper);
    }

    @Override
    public Long addJob(JobRequest request) {

        // 前置校验
        addJobPreValidator(request);

        // 判断常驻任务
        Job job = JobConverter.INSTANCE.convert(request);
        job.setResident(JobKit.isResident(request.getTriggerType(), request.getTriggerInterval()));

        // check triggerInterval
        checkTriggerInterval(request);

        job.setBucketIndex(HashUtil.bkdrHash(request.getGroupName() + request.getJobName())
                % systemProperties.getBucketTotal());
        job.setNextTriggerAt(JobKit.calculateNextTriggerAt(job.getTriggerType(), job.getTriggerInterval(), DateUtils.toNowMilli()));
        job.setId(null);
        job.setNamespaceId(getNamespaceId());
        // 子类填充属性
        addJobPopulate(job, request);
        Assert.isTrue(1 == jobMapper.insert(job), () -> new TaskServerException("Adding new task failed"));
        return job.getId();
    }

    @Override
    public Boolean updateJobStatus(StatusUpdateRequest requestDTO) {
        Assert.notNull(StatusEnum.of(requestDTO.getStatus()), () -> new TaskServerException("Status cannot be empty"));
        Job job = jobMapper.selectById(requestDTO.getId());
        Assert.notNull(job, () -> new TaskServerException("update job status failed"));
        // 直接幂等
        if (Objects.equals(requestDTO.getStatus(), job.getJobStatus())) {
            return true;
        }
        Job update = new Job();
        if (StatusEnum.YES.getStatus().equals(job.getJobStatus())) {
            // 开启时重新计算调度时间
            update.setNextTriggerAt(JobKit.calculateNextTriggerAt(job.getTriggerType(), job.getTriggerInterval(), DateUtils.toNowMilli()));
        }

        update.setJobStatus(requestDTO.getStatus());
        update.setId(requestDTO.getId());

        return 1 == jobMapper.updateById(update);
    }

    @Override
    public <T extends JobResponse> T getJobById(Long id, Class<T> clazz) {
        Job job = jobMapper.selectById(id);
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            JobConverter.INSTANCE.fillCommonFields(job, instance);
            getJobByIdAfter(instance, job);
            return instance;
        } catch (Exception e) {
            throw new TaskServerException("Failed to get job by id [{}]", id, e);
        }
    }

    protected abstract void getJobByIdAfter(JobResponse responseBaseDTO, Job job);

    protected abstract void updateJobPreValidator(JobRequest jobRequest);

    protected abstract String getNamespaceId();

    protected abstract void addJobPopulate(Job job, JobRequest request);

    protected void checkTriggerInterval(JobRequest jobRequestVO) {
        TriggerIntervalKit.checkTriggerInterval(jobRequestVO.getTriggerInterval(), jobRequestVO.getTriggerType());
    }

    protected abstract void addJobPreValidator(JobRequest request);
}
