package com.wingflare.engine.task.server.job.dto;


/**
 * @author zhengweilin
 * @version 1.0.0
 * @date 2024/12/23
 */
public class WorkflowTaskFailAlarmEventDTO {

    /**
     * 工作流任务批次id
     */
    private Long workflowTaskBatchId;

    /**
     * 通知场景
     */
    private Integer notifyScene;

    /**
     * 失败原因
     */
    private String reason;

    public Long getWorkflowTaskBatchId() {
        return workflowTaskBatchId;
    }

    public void setWorkflowTaskBatchId(Long workflowTaskBatchId) {
        this.workflowTaskBatchId = workflowTaskBatchId;
    }

    public Integer getNotifyScene() {
        return notifyScene;
    }

    public void setNotifyScene(Integer notifyScene) {
        this.notifyScene = notifyScene;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
