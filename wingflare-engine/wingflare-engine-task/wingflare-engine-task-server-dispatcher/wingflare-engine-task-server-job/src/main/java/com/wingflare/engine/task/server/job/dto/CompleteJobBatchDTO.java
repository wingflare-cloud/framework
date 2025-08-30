package com.wingflare.engine.task.server.job.dto;


/**
 * @author xiaowoniu
 * @date 2023-12-24 23:00:24
 * @since 2.6.0
 */
public class CompleteJobBatchDTO extends BaseDTO {

    private Long jobId;
    private Long workflowNodeId;
    private Long workflowTaskBatchId;
    private Long taskBatchId;
    private Integer jobOperationReason;
    private Object result;
    private String message;
    private Integer taskType;
    private Boolean retryStatus;

    @Override
    public Long getJobId() {
        return jobId;
    }

    @Override
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getWorkflowNodeId() {
        return workflowNodeId;
    }

    public void setWorkflowNodeId(Long workflowNodeId) {
        this.workflowNodeId = workflowNodeId;
    }

    public Long getWorkflowTaskBatchId() {
        return workflowTaskBatchId;
    }

    public void setWorkflowTaskBatchId(Long workflowTaskBatchId) {
        this.workflowTaskBatchId = workflowTaskBatchId;
    }

    @Override
    public Long getTaskBatchId() {
        return taskBatchId;
    }

    @Override
    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
    }

    public Integer getJobOperationReason() {
        return jobOperationReason;
    }

    public void setJobOperationReason(Integer jobOperationReason) {
        this.jobOperationReason = jobOperationReason;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Integer getTaskType() {
        return taskType;
    }

    @Override
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Boolean getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Boolean retryStatus) {
        this.retryStatus = retryStatus;
    }
}
