package com.wingflare.engine.task.server.web.model.response;


import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author: opensnail
 * @date : 2022-03-03 10:56
 */
public class SceneConfigResponseVO {

    private Long id;

    private String groupName;

    private String sceneName;

    private Integer sceneStatus;

    private Integer maxRetryCount;

    private Integer backOff;

    private String triggerInterval;

    private String description;

    private Long deadlineRequest;

    private Integer routeKey;

    private Integer blockStrategy;

    private Integer executorTimeout;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    /**
     * 通知告警场景配置id列表
     */
    private Set<Long> notifyIds;

    /**
     * 回调状态 0、不开启 1、开启
     */
    private Integer cbStatus;

    /**
     * 回调触发类型
     */
    private Integer cbTriggerType;

    /**
     * 回调的最大执行次数
     */
    private int cbMaxCount;

    /**
     * 回调间隔时间
     */
    private String cbTriggerInterval;

    /**
     * 负责人名称
     */
    private String ownerName;

    private Long ownerId;

    /**
     * 标签
     */
    private String labels;

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

    public Integer getSceneStatus() {
        return sceneStatus;
    }

    public void setSceneStatus(Integer sceneStatus) {
        this.sceneStatus = sceneStatus;
    }

    public Integer getMaxRetryCount() {
        return maxRetryCount;
    }

    public void setMaxRetryCount(Integer maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    public Integer getBackOff() {
        return backOff;
    }

    public void setBackOff(Integer backOff) {
        this.backOff = backOff;
    }

    public String getTriggerInterval() {
        return triggerInterval;
    }

    public void setTriggerInterval(String triggerInterval) {
        this.triggerInterval = triggerInterval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDeadlineRequest() {
        return deadlineRequest;
    }

    public void setDeadlineRequest(Long deadlineRequest) {
        this.deadlineRequest = deadlineRequest;
    }

    public Integer getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(Integer routeKey) {
        this.routeKey = routeKey;
    }

    public Integer getBlockStrategy() {
        return blockStrategy;
    }

    public void setBlockStrategy(Integer blockStrategy) {
        this.blockStrategy = blockStrategy;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
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

    public Set<Long> getNotifyIds() {
        return notifyIds;
    }

    public void setNotifyIds(Set<Long> notifyIds) {
        this.notifyIds = notifyIds;
    }

    public Integer getCbStatus() {
        return cbStatus;
    }

    public void setCbStatus(Integer cbStatus) {
        this.cbStatus = cbStatus;
    }

    public Integer getCbTriggerType() {
        return cbTriggerType;
    }

    public void setCbTriggerType(Integer cbTriggerType) {
        this.cbTriggerType = cbTriggerType;
    }

    public int getCbMaxCount() {
        return cbMaxCount;
    }

    public void setCbMaxCount(int cbMaxCount) {
        this.cbMaxCount = cbMaxCount;
    }

    public String getCbTriggerInterval() {
        return cbTriggerInterval;
    }

    public void setCbTriggerInterval(String cbTriggerInterval) {
        this.cbTriggerInterval = cbTriggerInterval;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
