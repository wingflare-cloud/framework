package com.wingflare.engine.task.server.job.dto;


/**
 * @author: shuguang.zhang
 * @date : 2024-06-12
 */
public class ReduceTaskDTO {

    private Long workflowNodeId;
    private Long workflowTaskBatchId;
    private Long taskBatchId;
    private Long jobId;
    private Integer mrStage;
    private String wfContext;

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

    public Long getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getMrStage() {
        return mrStage;
    }

    public void setMrStage(Integer mrStage) {
        this.mrStage = mrStage;
    }

    public String getWfContext() {
        return wfContext;
    }

    public void setWfContext(String wfContext) {
        this.wfContext = wfContext;
    }
}
