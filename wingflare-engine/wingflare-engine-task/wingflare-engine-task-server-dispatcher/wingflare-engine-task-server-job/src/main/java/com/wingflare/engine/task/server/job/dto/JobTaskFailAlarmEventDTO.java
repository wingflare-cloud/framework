package com.wingflare.engine.task.server.job.dto;


/**
 * @author zhengweilin
 * @version 1.0.0
 * @date 2024/12/12
 */
public class JobTaskFailAlarmEventDTO {

    /**
     * 任务批次id
     */
    private Long jobTaskBatchId;

    /**
     * 通知场景
     */
    private Integer notifyScene;

    /**
     * 原因
     */
    private String reason;

    public Long getJobTaskBatchId() {
        return jobTaskBatchId;
    }

    public void setJobTaskBatchId(Long jobTaskBatchId) {
        this.jobTaskBatchId = jobTaskBatchId;
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
