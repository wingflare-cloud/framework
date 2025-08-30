package com.wingflare.engine.task.server.common.dto;


/**
 * @author opensnail
 * @date 2024-05-05
 * @since sj_1.0.0
 */
public class WorkflowAlarmInfo extends AlarmInfo {

    private Long id;

    /**
     * 名称
     */
    private String workflowName;

    /**
     * 任务信息id
     */
    private Long workflowId;

    /**
     * 操作原因
     */
    private Integer operationReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }
}
