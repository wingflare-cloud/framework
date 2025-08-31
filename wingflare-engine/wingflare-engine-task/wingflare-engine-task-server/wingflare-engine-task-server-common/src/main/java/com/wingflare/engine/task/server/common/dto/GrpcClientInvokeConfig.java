package com.wingflare.engine.task.server.common.dto;

import com.wingflare.engine.task.server.common.rpc.client.TaskEngineRetryListener;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-05-24
 */
public class GrpcClientInvokeConfig {

    private String groupName;
    private InstanceLiveInfo instanceLiveInfo;
    private boolean failRetry;
    private int retryTimes;
    private int retryInterval;
    private TaskEngineRetryListener retryListener;
    private Integer routeKey;
    private String allocKey;
    private boolean failover;
    private Integer executorTimeout;
    private String namespaceId;
    private boolean async; // 默认 false，可忽略设置
    private String targetLabels;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public InstanceLiveInfo getInstanceLiveInfo() {
        return instanceLiveInfo;
    }

    public void setInstanceLiveInfo(InstanceLiveInfo instanceLiveInfo) {
        this.instanceLiveInfo = instanceLiveInfo;
    }

    public boolean isFailRetry() {
        return failRetry;
    }

    public void setFailRetry(boolean failRetry) {
        this.failRetry = failRetry;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public TaskEngineRetryListener getRetryListener() {
        return retryListener;
    }

    public void setRetryListener(TaskEngineRetryListener retryListener) {
        this.retryListener = retryListener;
    }

    public Integer getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(Integer routeKey) {
        this.routeKey = routeKey;
    }

    public String getAllocKey() {
        return allocKey;
    }

    public void setAllocKey(String allocKey) {
        this.allocKey = allocKey;
    }

    public boolean isFailover() {
        return failover;
    }

    public void setFailover(boolean failover) {
        this.failover = failover;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getTargetLabels() {
        return targetLabels;
    }

    public void setTargetLabels(String targetLabels) {
        this.targetLabels = targetLabels;
    }
}
