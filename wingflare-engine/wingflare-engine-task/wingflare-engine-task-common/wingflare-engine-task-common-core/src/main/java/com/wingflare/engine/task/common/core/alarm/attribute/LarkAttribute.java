package com.wingflare.engine.task.common.core.alarm.attribute;


import java.util.List;

/**
 * 飞书
 *
 * @author: opensnail
 * @date : 2023-05-31 13:45
 * @since 1.4.0
 */
public class LarkAttribute {

    private String webhookUrl;

    private List<String> ats;

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public List<String> getAts() {
        return ats;
    }

    public void setAts(List<String> ats) {
        this.ats = ats;
    }
}
