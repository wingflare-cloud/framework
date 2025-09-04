package com.wingflare.engine.task.server.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.job.support.handler.WorkflowBatchHandler;
import com.wingflare.engine.task.server.web.service.WorkflowNodeService;
import com.wingflare.engine.task.server.web.service.handler.JobHandler;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiaowoniu
 * @date 2024-02-03 21:25:00
 * @since 2.6.0
 */
@Service
public class WorkflowNodeServiceImpl implements WorkflowNodeService {

    private final JobTaskBatchMapper jobTaskBatchMapper;
    private final JobMapper jobMapper;
    private final WorkflowBatchHandler workflowBatchHandler;
    private final JobHandler jobHandler;

    public WorkflowNodeServiceImpl(JobTaskBatchMapper jobTaskBatchMapper, JobMapper jobMapper, WorkflowBatchHandler workflowBatchHandler, JobHandler jobHandler) {
        this.jobTaskBatchMapper = jobTaskBatchMapper;
        this.jobMapper = jobMapper;
        this.workflowBatchHandler = workflowBatchHandler;
        this.jobHandler = jobHandler;
    }

    @Override
    @Transactional
    public Boolean stop(Long nodeId, Long workflowTaskBatchId) {
        // 调用JOB的停止接口
        List<JobTaskBatch> jobTaskBatches = jobTaskBatchMapper.selectList(
                new LambdaQueryWrapper<JobTaskBatch>()
                        .eq(JobTaskBatch::getWorkflowNodeId, nodeId)
                        .eq(JobTaskBatch::getWorkflowTaskBatchId, workflowTaskBatchId)
                        .in(JobTaskBatch::getTaskBatchStatus, JobTaskBatchStatusEnum.NOT_COMPLETE)
        );

        if (CollUtil.isEmpty(jobTaskBatches)) {
            return Boolean.TRUE;
        }

        for (JobTaskBatch jobTaskBatch : jobTaskBatches) {
            jobHandler.stop(jobTaskBatch.getId());
        }

        return Boolean.TRUE;
    }

    @Override
    @Transactional
    public Boolean retry(Long nodeId, Long workflowTaskBatchId) {

        // 调用JOB的停止接口
        List<JobTaskBatch> jobTaskBatches = jobTaskBatchMapper.selectList(
                new LambdaQueryWrapper<JobTaskBatch>()
                        .select(JobTaskBatch::getId)
                        .eq(JobTaskBatch::getWorkflowNodeId, nodeId)
                        .eq(JobTaskBatch::getWorkflowTaskBatchId, workflowTaskBatchId)
                        .in(JobTaskBatch::getTaskBatchStatus, JobTaskBatchStatusEnum.NOT_SUCCESS)
        );

        Assert.notEmpty(jobTaskBatches, () -> new TaskServerException("job task batch is empty."));

        for (JobTaskBatch jobTaskBatch : jobTaskBatches) {
            jobHandler.retry(jobTaskBatch.getId(), nodeId, workflowTaskBatchId);
        }

        return Boolean.TRUE;
    }
}
