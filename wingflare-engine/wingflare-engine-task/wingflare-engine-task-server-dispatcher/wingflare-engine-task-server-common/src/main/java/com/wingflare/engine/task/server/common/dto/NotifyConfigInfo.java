package com.wingflare.engine.task.server.common.dto;


import java.util.List;
import java.util.Set;

/**
 * @author xiaowoniu
 * @date 2023-12-03 10:02:43
 * @since 2.5.0
 */
public class NotifyConfigInfo {

    private Long id;

    private String namespaceId;

    private String groupName;

    // 业务id (scene_name或job_id或workflow_id)
    private String businessId;

    private Set<Long> recipientIds;

    // 任务类型 1、重试任务 2、回调任务、 3、JOB任务 4、WORKFLOW任务
    private Integer systemTaskType;

    private Integer notifyStatus;

    private Integer notifyThreshold;

    private Integer notifyScene;

    private Integer rateLimiterStatus;

    private Integer rateLimiterThreshold;

    private List<RecipientInfo> recipientInfos;

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

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Set<Long> getRecipientIds() {
        return recipientIds;
    }

    public void setRecipientIds(Set<Long> recipientIds) {
        this.recipientIds = recipientIds;
    }

    public Integer getSystemTaskType() {
        return systemTaskType;
    }

    public void setSystemTaskType(Integer systemTaskType) {
        this.systemTaskType = systemTaskType;
    }

    public Integer getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Integer notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public Integer getNotifyThreshold() {
        return notifyThreshold;
    }

    public void setNotifyThreshold(Integer notifyThreshold) {
        this.notifyThreshold = notifyThreshold;
    }

    public Integer getNotifyScene() {
        return notifyScene;
    }

    public void setNotifyScene(Integer notifyScene) {
        this.notifyScene = notifyScene;
    }

    public Integer getRateLimiterStatus() {
        return rateLimiterStatus;
    }

    public void setRateLimiterStatus(Integer rateLimiterStatus) {
        this.rateLimiterStatus = rateLimiterStatus;
    }

    public Integer getRateLimiterThreshold() {
        return rateLimiterThreshold;
    }

    public void setRateLimiterThreshold(Integer rateLimiterThreshold) {
        this.rateLimiterThreshold = rateLimiterThreshold;
    }

    public List<RecipientInfo> getRecipientInfos() {
        return recipientInfos;
    }

    public void setRecipientInfos(List<RecipientInfo> recipientInfos) {
        this.recipientInfos = recipientInfos;
    }


    public static class RecipientInfo {

        private Integer notifyType;

        private String notifyAttribute;

        public Integer getNotifyType() {
            return notifyType;
        }

        public void setNotifyType(Integer notifyType) {
            this.notifyType = notifyType;
        }

        public String getNotifyAttribute() {
            return notifyAttribute;
        }

        public void setNotifyAttribute(String notifyAttribute) {
            this.notifyAttribute = notifyAttribute;
        }
    }

}
