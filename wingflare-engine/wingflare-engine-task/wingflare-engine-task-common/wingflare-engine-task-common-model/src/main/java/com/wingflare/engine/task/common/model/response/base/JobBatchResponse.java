package com.wingflare.engine.task.common.model.response.base;


import com.wingflare.engine.task.common.model.request.CallbackConfig;
import com.wingflare.engine.task.common.model.request.DecisionConfigRequest;

import java.time.LocalDateTime;

/**
 * @author: opensnail
 * @date : 2023-10-12 10:18
 * @since : 2.4.0
 */
public class JobBatchResponse {

    private Long id;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 名称
     */
    private String jobName;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 工作流节点名称
     */
    private String nodeName;

    /**
     * 任务信息id
     */
    private Long jobId;

    /**
     * 任务状态
     */
    private Integer taskBatchStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createDt;

    /**
     * 更新时间
     */
    private LocalDateTime updateDt;

    /**
     * 任务执行时间
     */
    private LocalDateTime executionAt;
    /**
     * 操作原因
     */
    private Integer operationReason;

    /**
     * 执行器类型 1、Java
     */
    private Integer executorType;

    /**
     * 执行器名称
     */
    private String executorInfo;

    /**
     * 工作流的回调节点信息
     */
    private CallbackConfig callback;

    /**
     * 工作流的决策节点信息
     */
    private DecisionConfigRequest decision;

    /**
     * 工作流批次id
     */
    private Long workflowTaskBatchId;

    /**
     * 工作流节点id
     */
    private Long workflowNodeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getTaskBatchStatus() {
        return taskBatchStatus;
    }

    public void setTaskBatchStatus(Integer taskBatchStatus) {
        this.taskBatchStatus = taskBatchStatus;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public LocalDateTime getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(LocalDateTime updateDt) {
        this.updateDt = updateDt;
    }

    public LocalDateTime getExecutionAt() {
        return executionAt;
    }

    public void setExecutionAt(LocalDateTime executionAt) {
        this.executionAt = executionAt;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public Integer getExecutorType() {
        return executorType;
    }

    public void setExecutorType(Integer executorType) {
        this.executorType = executorType;
    }

    public String getExecutorInfo() {
        return executorInfo;
    }

    public void setExecutorInfo(String executorInfo) {
        this.executorInfo = executorInfo;
    }

    public CallbackConfig getCallback() {
        return callback;
    }

    public void setCallback(CallbackConfig callback) {
        this.callback = callback;
    }

    public DecisionConfigRequest getDecision() {
        return decision;
    }

    public void setDecision(DecisionConfigRequest decision) {
        this.decision = decision;
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
}
