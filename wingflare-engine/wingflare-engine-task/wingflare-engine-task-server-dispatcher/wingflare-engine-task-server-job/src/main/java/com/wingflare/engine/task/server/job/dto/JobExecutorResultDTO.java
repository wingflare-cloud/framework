package com.wingflare.engine.task.server.job.dto;


/**
 * @author opensnail
 * @date 2023-10-05 17:18:38
 * @since 2.4.0
 */
public class JobExecutorResultDTO {

    private Long jobId;

    private Long taskBatchId;

    /**
     * 工作流任务批次id
     */
    private Long workflowTaskBatchId;

    private Long workflowNodeId;

    private Long taskId;

    /**
     * 命名空间
     */
    private String namespaceId;

    private String groupName;

    private Integer taskStatus;

    private String message;

    private Integer taskType;

    private Object result;

    private Integer jobOperationReason;

    private Integer isLeaf;

    private String wfContext;

    private Boolean retryStatus = Boolean.FALSE;

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getJobOperationReason() {
        return jobOperationReason;
    }

    public void setJobOperationReason(Integer jobOperationReason) {
        this.jobOperationReason = jobOperationReason;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getWfContext() {
        return wfContext;
    }

    public void setWfContext(String wfContext) {
        this.wfContext = wfContext;
    }

    public Boolean getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Boolean retryStatus) {
        this.retryStatus = retryStatus;
    }
}
