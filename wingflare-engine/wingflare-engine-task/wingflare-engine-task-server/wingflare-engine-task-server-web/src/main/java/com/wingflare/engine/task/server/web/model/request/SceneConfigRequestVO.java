package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.common.core.constant.SystemConstants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

/**
 * @author opensnail
 * @date 2023-10-25 08:40:57
 * @since 2.4.0
 */
public class SceneConfigRequestVO {

    @NotBlank(message = "Group name cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]{1,64}$", message = "Only supports 1~64 characters, including numbers, letters, underscores, and hyphens")
    private String groupName;

    @NotBlank(message = "Scene name cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]{1,64}$", message = "Only supports 1~64 characters, including numbers, letters, underscores, and hyphens")
    private String sceneName;

    @NotNull(message = "Scene status cannot be null")
    private Integer sceneStatus;

    @Max(message = "Maximum retry times", value = 9999999)
    @Min(message = "Minimum retry times", value = 0)
    private Integer maxRetryCount;

    @NotNull(message = "Backoff strategy cannot be null 1. Default level 2. Fixed interval 3. CRON expression")
    private Integer backOff;

    @NotNull(message = "Routing strategy cannot be null")
    private Integer routeKey;

    /**
     * @see: RetryBlockStrategyEnum
     */
    @NotNull(message = "Blocking strategy cannot be null")
    private Integer blockStrategy;

    /**
     * 描述
     */
    private String description;

    /**
     * 退避策略为固定间隔时间必填
     */
    private String triggerInterval;

    /**
     * Deadline Request 调用链超时 单位毫秒
     * 默认值为 60*10*1000
     */
    @Max(message = "Maximum 60000 milliseconds", value = SystemConstants.DEFAULT_DDL)
    @Min(message = "Minimum 100ms", value = 100)
    @NotNull(message = "Call chain timeout cannot be null")
    private Long deadlineRequest;

    @Max(message = "Maximum 60 seconds", value = 60)
    @Min(message = "Minimum 1 second", value = 1)
    @NotNull(message = "Execution timeout cannot be null")
    private Integer executorTimeout;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 通知告警场景配置id列表
     */
    private Set<Long> notifyIds;

    /**
     * 回调状态 0、不开启 1、开启
     */
    @NotNull(message = "Callback status cannot be null")
    private Integer cbStatus;

    /**
     * 回调触发类型
     */
    @NotNull(message = "Callback trigger type cannot be null")
    private Integer cbTriggerType;

    /**
     * 回调的最大执行次数
     */
    @NotNull(message = "Maximum callback execution times cannot be null")
    private int cbMaxCount;

    /**
     * 回调间隔时间
     */
    private String cbTriggerInterval;

    /**
     * 负责人id
     */
    private Long ownerId;

    /**
     * 标签
     */
    private String labels;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTriggerInterval() {
        return triggerInterval;
    }

    public void setTriggerInterval(String triggerInterval) {
        this.triggerInterval = triggerInterval;
    }

    public Long getDeadlineRequest() {
        return deadlineRequest;
    }

    public void setDeadlineRequest(Long deadlineRequest) {
        this.deadlineRequest = deadlineRequest;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
