package com.wingflare.engine.task.server.job.support.block.job;


/**
 * @author: xiaowoniu
 * @date : 2024-01-18
 * @since : 2.6.0
 */
public class BlockStrategyContext {

    private Long jobId;

    private Long taskBatchId;

    private String namespaceId;

    private String groupName;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 下次触发时间
     */
    private Long nextTriggerAt;

    private Integer operationReason;

    /**
     * 执行策略 1、auto 2、manual 3、workflow
     */
    private Integer taskExecutorScene;

    /**
     * 工作流任务批次id
     */
    private Long workflowTaskBatchId;

    /**
     * 工作流节点id
     */
    private Long workflowNodeId;

    /**
     * 工作流父节点id
     */
    private Long parentWorkflowNodeId;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Long getNextTriggerAt() {
        return nextTriggerAt;
    }

    public void setNextTriggerAt(Long nextTriggerAt) {
        this.nextTriggerAt = nextTriggerAt;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public Integer getTaskExecutorScene() {
        return taskExecutorScene;
    }

    public void setTaskExecutorScene(Integer taskExecutorScene) {
        this.taskExecutorScene = taskExecutorScene;
    }

    public Long getWorkflowTaskBatchId() {
        return workflowTaskBatchId;
    }

    public void setWorkflowTaskBatchId(Long workflowTaskBatchId) {
        this.workflowTaskBatchId = workflowTaskBatchId;
    }

    public Long getWorkflowNodeId() {
        return workflowNodeId;
    }

    public void setWorkflowNodeId(Long workflowNodeId) {
        this.workflowNodeId = workflowNodeId;
    }

    public Long getParentWorkflowNodeId() {
        return parentWorkflowNodeId;
    }

    public void setParentWorkflowNodeId(Long parentWorkflowNodeId) {
        this.parentWorkflowNodeId = parentWorkflowNodeId;
    }
}
