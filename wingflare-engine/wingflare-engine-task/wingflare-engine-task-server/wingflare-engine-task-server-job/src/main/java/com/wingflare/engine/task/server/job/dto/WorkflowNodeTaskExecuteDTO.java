package com.wingflare.engine.task.server.job.dto;


/**
 * @author: xiaowoniu
 * @date : 2023-12-22
 * @since : 2.6.0
 */
public class WorkflowNodeTaskExecuteDTO {

    /**
     * 工作流任务批次id
     */
    private Long workflowTaskBatchId;
    /**
     * 执行策略 1、auto 2、manual 3、workflow
     */
    private Integer taskExecutorScene;

    private Long parentId;

    /**
     * 调度任务id
     */
    private Long taskBatchId;

    public Long getWorkflowTaskBatchId() {
        return workflowTaskBatchId;
    }

    public void setWorkflowTaskBatchId(Long workflowTaskBatchId) {
        this.workflowTaskBatchId = workflowTaskBatchId;
    }

    public Integer getTaskExecutorScene() {
        return taskExecutorScene;
    }

    public void setTaskExecutorScene(Integer taskExecutorScene) {
        this.taskExecutorScene = taskExecutorScene;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
    }
}
