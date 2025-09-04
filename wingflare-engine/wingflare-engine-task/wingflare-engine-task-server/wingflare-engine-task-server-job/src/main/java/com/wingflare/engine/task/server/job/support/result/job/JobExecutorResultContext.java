package com.wingflare.engine.task.server.job.support.result.job;

import com.wingflare.engine.task.server.job.dto.BaseDTO;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2024-09-04
 * @since :1.2.0
 */
public class JobExecutorResultContext extends BaseDTO {

    private Long workflowNodeId;
    private Long workflowTaskBatchId;
    private Integer jobOperationReason;
    private boolean isRetry;
    private List<JobTask> jobTaskList;

    /**
     * 是否开启创建Reduce任务
     */
    private boolean createReduceTask;

    /**
     * 是否更新批次完成
     */
    private boolean taskBatchComplete;

    /**
     * 原因
     */
    private String message;

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

    public Integer getJobOperationReason() {
        return jobOperationReason;
    }

    public void setJobOperationReason(Integer jobOperationReason) {
        this.jobOperationReason = jobOperationReason;
    }

    public boolean isRetry() {
        return isRetry;
    }

    public void setRetry(boolean retry) {
        isRetry = retry;
    }

    public List<JobTask> getJobTaskList() {
        return jobTaskList;
    }

    public void setJobTaskList(List<JobTask> jobTaskList) {
        this.jobTaskList = jobTaskList;
    }

    public boolean isCreateReduceTask() {
        return createReduceTask;
    }

    public void setCreateReduceTask(boolean createReduceTask) {
        this.createReduceTask = createReduceTask;
    }

    public boolean isTaskBatchComplete() {
        return taskBatchComplete;
    }

    public void setTaskBatchComplete(boolean taskBatchComplete) {
        this.taskBatchComplete = taskBatchComplete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
