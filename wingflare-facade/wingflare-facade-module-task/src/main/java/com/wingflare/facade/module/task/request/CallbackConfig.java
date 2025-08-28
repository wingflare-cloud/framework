package com.wingflare.facade.module.task.request;


/**
 * 回调节点配置
 *
 * @author xiaowoniu
 * @date 2023-12-30 11:18:14
 * @since 2.6.0
 */
@Deprecated(since = "1.7.0")
public class CallbackConfig {

    /**
     * webhook
     */
    private String webhook;

    /**
     * 请求类型
     */
    private Integer contentType;

    /**
     * 秘钥
     */
    private String secret;

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
