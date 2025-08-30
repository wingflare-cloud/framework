package com.wingflare.engine.task.server.web.model.response;


import java.time.LocalDateTime;

/**
 * @author: opensnail
 * @date : 2022-02-28 09:09
 */
public class RetryTaskResponseVO {

    private Long id;

    private String groupName;

    private String sceneName;

    private Integer taskStatus;

    private Long retryId;

    private Integer taskType;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    private Integer operationReason;

    /**
     * 客户端ID
     */
    private String clientInfo;

    private RetryResponseWebVO responseVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getRetryId() {
        return retryId;
    }

    public void setRetryId(Long retryId) {
        this.retryId = retryId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public LocalDateTime getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(LocalDateTime updateDt) {
        this.updateDt = updateDt;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public RetryResponseWebVO getResponseVO() {
        return responseVO;
    }

    public void setResponseVO(RetryResponseWebVO responseVO) {
        this.responseVO = responseVO;
    }
}
