package com.wingflare.engine.task.datasource.template.persistence.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 场景配置
 *
 * @author zuojunlin
 * @date 2023-11-19 22:05:25
 * @since 2.5.0
 */
@TableName("wf_task_retry_scene_config")
public class RetrySceneConfig extends CreateUpdateDt {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String namespaceId;

    private String groupName;

    private String sceneName;

    private Integer blockStrategy;

    private Integer sceneStatus;

    private Integer maxRetryCount;

    private Integer backOff;

    private String triggerInterval;

    private String description;

    private Long deadlineRequest;

    private Integer routeKey;

    private Integer executorTimeout;

    /**
     * 通知告警场景配置id列表
     */
    private String notifyIds;

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
     * 标签
     */
    private String labels;

    /**
     * 负责人id
     */
    private Long ownerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getBlockStrategy() {
        return blockStrategy;
    }

    public void setBlockStrategy(Integer blockStrategy) {
        this.blockStrategy = blockStrategy;
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

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public String getNotifyIds() {
        return notifyIds;
    }

    public void setNotifyIds(String notifyIds) {
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

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
