package com.wingflare.engine.task.server.job.support.block.workflow;

import com.wingflare.engine.task.server.job.support.block.job.BlockStrategyContext;

/**
 * @author: xiaowoniu
 * @date : 2023-12-26
 * @since : 2.6.0
 */
public class WorkflowBlockStrategyContext extends BlockStrategyContext {

    /**
     * 工作流id
     */
    private Long workflowId;

    /**
     * 工作流任务批次id
     */
    private Long workflowTaskBatchId;

    /**
     * 流程信息
     */
    private String flowInfo;

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    @Override
    public Long getWorkflowTaskBatchId() {
        return workflowTaskBatchId;
    }

    @Override
    public void setWorkflowTaskBatchId(Long workflowTaskBatchId) {
        this.workflowTaskBatchId = workflowTaskBatchId;
    }

    public String getFlowInfo() {
        return flowInfo;
    }

    public void setFlowInfo(String flowInfo) {
        this.flowInfo = flowInfo;
    }
}
