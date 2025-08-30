package com.wingflare.task.datasource.template.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 调度任务
 *
 * @author opensnail
 * @since 2023-09-24
 */
@TableName("sj_job_task_batch")
public class JobTaskBatch extends CreateUpdateDt {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 任务信息id
     */
    private Long jobId;

    /**
     * 工作流批次id
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

    /**
     * 任务批次状态
     */
    private Integer taskBatchStatus;

    /**
     * 任务执行时间
     */
    private Long executionAt;

    /**
     * 任务类型 3、JOB任务 4、WORKFLOW任务
     */
    private Integer systemTaskType;


    /**
     * 操作原因
     */
    private Integer operationReason;

    /**
     * 逻辑删除 1、删除
     */
    private Integer deleted;

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

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
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

    public Integer getTaskBatchStatus() {
        return taskBatchStatus;
    }

    public void setTaskBatchStatus(Integer taskBatchStatus) {
        this.taskBatchStatus = taskBatchStatus;
    }

    public Long getExecutionAt() {
        return executionAt;
    }

    public void setExecutionAt(Long executionAt) {
        this.executionAt = executionAt;
    }

    public Integer getSystemTaskType() {
        return systemTaskType;
    }

    public void setSystemTaskType(Integer systemTaskType) {
        this.systemTaskType = systemTaskType;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
