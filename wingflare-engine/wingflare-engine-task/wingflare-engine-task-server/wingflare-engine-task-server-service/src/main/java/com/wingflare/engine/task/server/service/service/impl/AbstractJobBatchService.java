package com.wingflare.engine.task.server.service.service.impl;

import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.request.CallbackConfig;
import com.wingflare.engine.task.common.model.request.DecisionConfigRequest;
import com.wingflare.engine.task.common.model.response.base.JobBatchResponse;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.service.convert.JobBatchResponseConverter;
import com.wingflare.engine.task.server.service.service.JobBatchService;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.WorkflowNodeMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTaskBatch;
import com.wingflare.engine.task.datasource.template.persistence.po.WorkflowNode;
import jakarta.annotation.Resource;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-06
 */
public abstract class AbstractJobBatchService implements JobBatchService {
    @Resource
    protected JobTaskBatchMapper jobTaskBatchMapper;
    @Resource
    protected JobMapper jobMapper;
    @Resource
    protected WorkflowNodeMapper workflowNodeMapper;

    @Override
    public <T extends JobBatchResponse> T getJobBatchById(Long jobBatchId, Class<T> clazz) {
        JobTaskBatch jobTaskBatch = jobTaskBatchMapper.selectById(jobBatchId);
        if (Objects.isNull(jobTaskBatch)) {
            return null;
        }

        Job job = jobMapper.selectById(jobTaskBatch.getJobId());

        T jobBatchResponse;
        try {
            jobBatchResponse = clazz.getDeclaredConstructor().newInstance();
            JobBatchResponseConverter.INSTANCE.fillCommonFields(jobTaskBatch, job, jobBatchResponse);
        } catch (Exception e) {
            throw new TaskServerException("getJobBatchById is error", e);
        }

        if (jobTaskBatch.getSystemTaskType().equals(SyetemTaskTypeEnum.WORKFLOW.getType())) {
            WorkflowNode workflowNode = workflowNodeMapper.selectById(jobTaskBatch.getWorkflowNodeId());
            jobBatchResponse.setNodeName(workflowNode.getNodeName());

            // 回调节点
            if (SystemConstants.CALLBACK_JOB_ID.equals(jobTaskBatch.getJobId())) {
                jobBatchResponse.setCallback(JsonUtil.parseObject(workflowNode.getNodeInfo(), CallbackConfig.class));
                jobBatchResponse.setExecutionAt(jobTaskBatch.getCreateDt());
                return jobBatchResponse;
            }

            // 条件节点
            if (SystemConstants.DECISION_JOB_ID.equals(jobTaskBatch.getJobId())) {
                jobBatchResponse.setDecision(JsonUtil.parseObject(workflowNode.getNodeInfo(), DecisionConfigRequest.class));
                jobBatchResponse.setExecutionAt(jobTaskBatch.getCreateDt());
                return jobBatchResponse;
            }
        }

        return jobBatchResponse;
    }
}
