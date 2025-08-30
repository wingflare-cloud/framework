package com.wingflare.engine.task.server.retry.dto;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-26
 */
public class RetryTaskPrepareDTO {

    private String namespaceId;

    private String groupName;

    private String sceneName;

    private Long retryId;

    private Long retryTaskId;

    private Integer taskStatus;

    private Integer taskType;

    private Long nextTriggerAt;

    private Integer blockStrategy;

    private boolean onlyTimeoutCheck;

    private Integer executorTimeout;

    private Integer retryTaskExecutorScene;

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

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Long getRetryId() {
        return retryId;
    }

    public void setRetryId(Long retryId) {
        this.retryId = retryId;
    }

    public Long getRetryTaskId() {
        return retryTaskId;
    }

    public void setRetryTaskId(Long retryTaskId) {
        this.retryTaskId = retryTaskId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Long getNextTriggerAt() {
        return nextTriggerAt;
    }

    public void setNextTriggerAt(Long nextTriggerAt) {
        this.nextTriggerAt = nextTriggerAt;
    }

    public Integer getBlockStrategy() {
        return blockStrategy;
    }

    public void setBlockStrategy(Integer blockStrategy) {
        this.blockStrategy = blockStrategy;
    }

    public boolean isOnlyTimeoutCheck() {
        return onlyTimeoutCheck;
    }

    public void setOnlyTimeoutCheck(boolean onlyTimeoutCheck) {
        this.onlyTimeoutCheck = onlyTimeoutCheck;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Integer getRetryTaskExecutorScene() {
        return retryTaskExecutorScene;
    }

    public void setRetryTaskExecutorScene(Integer retryTaskExecutorScene) {
        this.retryTaskExecutorScene = retryTaskExecutorScene;
    }
}
