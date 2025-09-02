package com.wingflare.adapter.alarm.webhook;


/**
 * @author: 奶嘴
 * @date : 2025-09-02 16:15
 */
public class WebhookAttribute {

    /**
     * webhook
     */
    private String webhookUrl;

    /**
     * 请求类型
     */
    private String contentType;

    /**
     * 秘钥
     */
    private String secret;


    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
