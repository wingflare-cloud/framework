package com.wingflare.engine.task.server.retry.dto;


import java.util.List;
import java.util.Set;

/**
 * @author opensnail
 * @date 2025-01-11
 * @since 1.3.0-beta1.1
 */
public class NotifyConfigDTO {

    private Long id;

    private Set<Long> recipientIds;

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

    public Set<Long> getRecipientIds() {
        return recipientIds;
    }

    public void setRecipientIds(Set<Long> recipientIds) {
        this.recipientIds = recipientIds;
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
