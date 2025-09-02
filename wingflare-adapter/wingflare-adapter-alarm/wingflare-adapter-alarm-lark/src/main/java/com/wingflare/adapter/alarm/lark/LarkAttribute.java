package com.wingflare.adapter.alarm.lark;


import java.util.List;

/**
 * 飞书
 *
 * @author: 奶嘴
 * @date : 2025-09-02 13:00
 * @since 1.0.0
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
