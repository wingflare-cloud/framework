package com.wingflare.engine.task.server.job.dto;


/**
 * @author: opensnail
 * @date : 2023-09-26 15:39
 */
public class TaskExecuteDTO {

    private Long jobId;
    private Long taskBatchId;
    /**
     * 工作流任务批次id
     */
    private Long workflowTaskBatchId;

    private Long workflowNodeId;
    /**
     * 执行策略 1、auto 2、manual 3、workflow
     */
    private Integer taskExecutorScene;

    /**
     * 临时任务参数
     */
    private String tmpArgsStr;

    public TaskExecuteDTO() {
    }

    public TaskExecuteDTO(Long jobId, Long taskBatchId, Long workflowTaskBatchId, Long workflowNodeId, Integer taskExecutorScene) {
        this.jobId = jobId;
        this.taskBatchId = taskBatchId;
        this.workflowTaskBatchId = workflowTaskBatchId;
        this.workflowNodeId = workflowNodeId;
        this.taskExecutorScene = taskExecutorScene;
    }

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

    public Integer getTaskExecutorScene() {
        return taskExecutorScene;
    }

    public void setTaskExecutorScene(Integer taskExecutorScene) {
        this.taskExecutorScene = taskExecutorScene;
    }

    public String getTmpArgsStr() {
        return tmpArgsStr;
    }

    public void setTmpArgsStr(String tmpArgsStr) {
        this.tmpArgsStr = tmpArgsStr;
    }
}
