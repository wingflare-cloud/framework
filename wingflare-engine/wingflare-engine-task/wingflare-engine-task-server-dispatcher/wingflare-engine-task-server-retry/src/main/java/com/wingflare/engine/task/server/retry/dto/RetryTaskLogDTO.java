package com.wingflare.engine.task.server.retry.dto;


import java.time.LocalDateTime;

/**
 * 日志上下文模型
 *
 * @author: opensnail
 * @date : 2023-06-16 14:14
 * @since 2.0.0
 */
public class RetryTaskLogDTO {

    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 重试任务id
     */
    private Long retryTaskId;

    /**
     * 重试信息Id
     */
    private Long retryId;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 重试状态
     */
    private Integer retryStatus;

    /**
     * 触发时间
     */
    private LocalDateTime triggerTime;

    /**
     * 客户端信息
     */
    private String clientInfo;

    /**
     * 真实上报时间
     */
    private Long realTime;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Integer retryStatus) {
        this.retryStatus = retryStatus;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(LocalDateTime triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Long getRealTime() {
        return realTime;
    }

    public void setRealTime(Long realTime) {
        this.realTime = realTime;
    }
}
