package com.wingflare.engine.task.client.retry.context;


import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-12
 */
public final class CallbackContext {

    private String namespaceId;
    private String groupName;
    private String sceneName;
    private Long retryTaskId;
    private Long retryId;
    private Integer retryStatus;
    private Object[] deSerialize;
    private RetryerInfo retryerInfo;

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

    public Integer getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Integer retryStatus) {
        this.retryStatus = retryStatus;
    }

    public Object[] getDeSerialize() {
        return deSerialize;
    }

    public void setDeSerialize(Object[] deSerialize) {
        this.deSerialize = deSerialize;
    }

    public RetryerInfo getRetryerInfo() {
        return retryerInfo;
    }

    public void setRetryerInfo(RetryerInfo retryerInfo) {
        this.retryerInfo = retryerInfo;
    }
}
