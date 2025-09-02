package com.wingflare.adapter.alarm.dingding;


import java.util.List;

/**
 * @author: 奶嘴
 * @date : 2025-09-02 16:00
 */
public class DingDingAttribute {

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
