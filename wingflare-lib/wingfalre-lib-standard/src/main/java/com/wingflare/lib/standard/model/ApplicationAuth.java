package com.wingflare.lib.standard.model;

import java.io.Serializable;
import java.math.BigInteger;
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
    private BigInteger appId;

    /**
     * 应用类型
     */
    private String appType;

    /**
     * 父级应用id
     */
    private BigInteger parentAppId;

    /**
     * 商户id
     */
    private BigInteger merchantId;

    /**
     * 父级商户id
     */
    private BigInteger parentMerchantId;

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


    public BigInteger getAppId() {
        return appId;
    }

    public void setAppId(BigInteger appId) {
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

    public BigInteger getParentAppId() {
        return parentAppId;
    }

    public void setParentAppId(BigInteger parentAppId) {
        this.parentAppId = parentAppId;
    }

    public BigInteger getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(BigInteger merchantId) {
        this.merchantId = merchantId;
    }

    public BigInteger getParentMerchantId() {
        return parentMerchantId;
    }

    public void setParentMerchantId(BigInteger parentMerchantId) {
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
