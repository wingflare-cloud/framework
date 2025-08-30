package com.wingflare.engine.task.server.retry.dto;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-26
 */
public class RetryTaskGeneratorDTO extends BaseDTO {

    private Integer taskStatus;

    private Integer operationReason;

    private Integer taskType;

    private long nextTriggerAt;

    private Integer retryTaskExecutorScene;

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    @Override
    public Integer getTaskType() {
        return taskType;
    }

    @Override
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public long getNextTriggerAt() {
        return nextTriggerAt;
    }

    public void setNextTriggerAt(long nextTriggerAt) {
        this.nextTriggerAt = nextTriggerAt;
    }

    public Integer getRetryTaskExecutorScene() {
        return retryTaskExecutorScene;
    }

    public void setRetryTaskExecutorScene(Integer retryTaskExecutorScene) {
        this.retryTaskExecutorScene = retryTaskExecutorScene;
    }
}
