package com.wingflare.lib.task.alarm.attribute;


import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-05-04 16:13
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
