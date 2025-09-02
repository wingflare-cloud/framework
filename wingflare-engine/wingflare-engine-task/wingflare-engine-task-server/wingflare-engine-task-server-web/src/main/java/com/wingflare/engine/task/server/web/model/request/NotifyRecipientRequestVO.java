package com.wingflare.engine.task.server.web.model.request;


import com.wingflare.lib.core.validation.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author opensnail
 * @date 2024-04-17 22:03:33
 * @since sj_1.0.0
 */
public class NotifyRecipientRequestVO {

    @NotNull(message = "id cannot be null", groups = Update.class)
    private Long id;

    /**
     * 接收人名称
     */
    @NotBlank(message = "Recipient name cannot be empty")
    private String recipientName;

    /**
     * 通知类型 1、钉钉 2、邮件 3、企业微信 4 飞书
     */
    @NotNull(message = "Notification type cannot be empty")
    private String notifyType;

    /**
     * 配置属性
     */
    @NotBlank(message = "Configuration properties cannot be empty")
    private String notifyAttribute;

    /**
     * 描述
     */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyAttribute() {
        return notifyAttribute;
    }

    public void setNotifyAttribute(String notifyAttribute) {
        this.notifyAttribute = notifyAttribute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
