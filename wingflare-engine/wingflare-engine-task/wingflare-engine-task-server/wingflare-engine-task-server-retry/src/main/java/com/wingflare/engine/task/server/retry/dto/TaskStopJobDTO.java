package com.wingflare.engine.task.server.retry.dto;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-11
 */
public class TaskStopJobDTO extends BaseDTO {

    /**
     * 操作原因
     */
    private Integer operationReason;

    /**
     * 若是失败补充失败信息
     */
    private String message;

    /**
     * 是否需要变更任务状态
     */
    private boolean needUpdateTaskStatus;

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNeedUpdateTaskStatus() {
        return needUpdateTaskStatus;
    }

    public void setNeedUpdateTaskStatus(boolean needUpdateTaskStatus) {
        this.needUpdateTaskStatus = needUpdateTaskStatus;
    }
}
