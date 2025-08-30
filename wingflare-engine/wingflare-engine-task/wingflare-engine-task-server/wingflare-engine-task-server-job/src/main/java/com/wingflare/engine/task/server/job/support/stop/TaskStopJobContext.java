package com.wingflare.engine.task.server.job.support.stop;

import com.wingflare.task.datasource.template.persistence.po.JobTask;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-02 22:53:49
 * @since 2.4.0
 */
public class TaskStopJobContext {

    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 任务id
     */
    private Long jobId;

    /**
     * 任务id
     */
    private Long taskBatchId;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 是否需要变更任务状态
     */
    private boolean needUpdateTaskStatus;

    private List<JobTask> jobTasks;

    private Integer jobOperationReason;

    private boolean forceStop;

    /**
     * 工作流任务批次id
     */
    private Long workflowTaskBatchId;

    private Long workflowNodeId;

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

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public boolean isNeedUpdateTaskStatus() {
        return needUpdateTaskStatus;
    }

    public void setNeedUpdateTaskStatus(boolean needUpdateTaskStatus) {
        this.needUpdateTaskStatus = needUpdateTaskStatus;
    }

    public Integer getJobOperationReason() {
        return jobOperationReason;
    }

    public void setJobOperationReason(Integer jobOperationReason) {
        this.jobOperationReason = jobOperationReason;
    }

    public boolean isForceStop() {
        return forceStop;
    }

    public void setForceStop(boolean forceStop) {
        this.forceStop = forceStop;
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

    protected List<JobTask> getJobTasks() {
        return jobTasks;
    }

    protected void setJobTasks(List<JobTask> jobTasks) {
        this.jobTasks = jobTasks;
    }
}
