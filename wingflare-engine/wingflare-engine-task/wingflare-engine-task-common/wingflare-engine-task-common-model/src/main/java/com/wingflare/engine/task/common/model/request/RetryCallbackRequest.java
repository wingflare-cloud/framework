package com.wingflare.engine.task.common.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 服务端调度重试入参
 *
 * @auther opensnail
 * @date 2022/03/25 10:06
 */
public class RetryCallbackRequest {
    @NotBlank(message = "namespaceId cannot be null")
    private String namespaceId;
    @NotBlank(message = "groupName cannot be null")
    private String groupName;
    @NotBlank(message = "sceneName cannot be null")
    private String sceneName;
    @NotBlank(message = "parameters cannot be null")
    private String argsStr;
    @NotBlank(message = "executorName cannot be null")
    private String executorName;
    @NotNull(message = "retryStatus cannot be null")
    private Integer retryStatus;
    @NotNull(message = "retryTaskId cannot be null")
    private Long retryTaskId;
    @NotNull(message = "retryId cannot be null")
    private Long retryId;
    @NotNull(message = "executorTimeout cannot be null")
    private Integer executorTimeout;
    @NotBlank(message = "serializerName cannot be null")
    private String serializerName;

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

    public String getArgsStr() {
        return argsStr;
    }

    public void setArgsStr(String argsStr) {
        this.argsStr = argsStr;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public Integer getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Integer retryStatus) {
        this.retryStatus = retryStatus;
    }

    public Long getRetryTaskId() {
        return retryTaskId;
    }

    public void setRetryTaskId(Long retryTaskId) {
        this.retryTaskId = retryTaskId;
    }

    public Long getRetryId() {
        return retryId;
    }

    public void setRetryId(Long retryId) {
        this.retryId = retryId;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public String getSerializerName() {
        return serializerName;
    }

    public void setSerializerName(String serializerName) {
        this.serializerName = serializerName;
    }
}
