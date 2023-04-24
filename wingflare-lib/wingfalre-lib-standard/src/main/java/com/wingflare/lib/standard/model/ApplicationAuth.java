package com.wingflare.lib.standard.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @author naizui_ycx
 * @date {2023/01/10}
 * @description 认证应用类
 */
public class ApplicationAuth implements Serializable {

    private static final long serialVersionUID = 626142766294314958L;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 父级应用id
     */
    private String parentAppId;

    /**
     * 商户id
     */
    private String merchantId;

    /**
     * 父级商户id
     */
    private String parentMerchantId;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 是否内部应用
     */
    private Boolean isInternalApp;

    /**
     * 其他额外属性
     */
    private Map<String, Object> attribute;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Boolean getInternalApp() {
        return isInternalApp;
    }

    public void setInternalApp(Boolean internalApp) {
        isInternalApp = internalApp;
    }

    public String getParentAppId() {
        return parentAppId;
    }

    public void setParentAppId(String parentAppId) {
        this.parentAppId = parentAppId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getParentMerchantId() {
        return parentMerchantId;
    }

    public void setParentMerchantId(String parentMerchantId) {
        this.parentMerchantId = parentMerchantId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Boolean isInternalApp() {
        return isInternalApp;
    }

    public void setIsInternalApp(Boolean isInternalApp) {
        this.isInternalApp = isInternalApp;
    }

    public Map<String, Object> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

}
