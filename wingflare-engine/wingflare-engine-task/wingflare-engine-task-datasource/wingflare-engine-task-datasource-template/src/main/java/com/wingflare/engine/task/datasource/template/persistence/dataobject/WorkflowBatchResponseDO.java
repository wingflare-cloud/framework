package com.wingflare.engine.task.datasource.template.persistence.dataobject;


import java.time.LocalDateTime;

/**
 * @author xiaowoniu
 * @date 2023-12-23 18:01:52
 * @since 2.6.0
 */
public class WorkflowBatchResponseDO {

    private Long id;

    /**
     * 命名空间id
     */
    private String namespaceId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 工作流任务id
     */
    private Long workflowId;

    /**
     * 工作流任务名称
     */
    private String workflowName;

    /**
     * 任务批次状态 0、失败 1、成功
     */
    private Integer taskBatchStatus;

    /**
     * 操作原因
     */
    private Integer operationReason;

    /**
     * 任务执行时间
     */
    private Long executionAt;

    /**
     * 创建时间
     */
    private LocalDateTime createDt;

    /**
     * 通知配置
     */
    private String notifyIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public Integer getTaskBatchStatus() {
        return taskBatchStatus;
    }

    public void setTaskBatchStatus(Integer taskBatchStatus) {
        this.taskBatchStatus = taskBatchStatus;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public Long getExecutionAt() {
        return executionAt;
    }

    public void setExecutionAt(Long executionAt) {
        this.executionAt = executionAt;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public String getNotifyIds() {
        return notifyIds;
    }

    public void setNotifyIds(String notifyIds) {
        this.notifyIds = notifyIds;
    }
}
