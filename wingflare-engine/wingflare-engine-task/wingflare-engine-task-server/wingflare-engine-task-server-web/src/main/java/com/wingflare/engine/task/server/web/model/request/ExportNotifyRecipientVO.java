package com.wingflare.engine.task.server.web.model.request;


import java.util.Set;

/**
 * @author: opensnail
 * @date : 2024-05-31
 * @since : sj_1.0.0
 */
public class ExportNotifyRecipientVO {

    private Set<Long> notifyRecipientIds;

    private String notifyType;

    private String recipientName;

    public Set<Long> getNotifyRecipientIds() {
        return notifyRecipientIds;
    }

    public void setNotifyRecipientIds(Set<Long> notifyRecipientIds) {
        this.notifyRecipientIds = notifyRecipientIds;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}
