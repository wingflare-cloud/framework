package com.wingflare.lib.standard;

import java.util.Date;

/**
 * 开放平台传参Bo
 */
public class OpenApiInputBo {

    /**
     * 应用id
     */
    private String appId;

    /**
     * 子应用授权token
     */
    private String appAuthToken;

    /**
     * 发送请求的时间
     */
    private Date timestamp;

    /**
     * 回调通知url
     */
    private String notifyUrl;

    /**
     * 业务参数JSON串
     */
    private String bizContent;

    /**
     * 参数签名值
     */
    private String sign;

    /**
     * 随机字符串
     */
    private String nonceStr;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppAuthToken() {
        return appAuthToken;
    }

    public void setAppAuthToken(String appAuthToken) {
        this.appAuthToken = appAuthToken;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getBizContent() {
        return bizContent;
    }

    public void setBizContent(String bizContent) {
        this.bizContent = bizContent;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }
}
