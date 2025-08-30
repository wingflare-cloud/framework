package com.wingflare.engine.task.server.retry.dto;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-02
 */
public class RetryExecutorResultDTO extends BaseDTO  {

    private Integer operationReason;
    private boolean incrementRetryCount;
    private String resultJson;
    private String exceptionMsg;
    private Integer taskStatus;

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public boolean isIncrementRetryCount() {
        return incrementRetryCount;
    }

    public void setIncrementRetryCount(boolean incrementRetryCount) {
        this.incrementRetryCount = incrementRetryCount;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
