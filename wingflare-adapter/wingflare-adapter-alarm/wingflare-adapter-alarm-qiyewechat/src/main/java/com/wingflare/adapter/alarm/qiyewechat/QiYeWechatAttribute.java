package com.wingflare.adapter.alarm.qiyewechat;


import java.util.List;

/**
 * 企业微信
 *
 * @author lizhongyuan
 */
public class QiYeWechatAttribute {

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
