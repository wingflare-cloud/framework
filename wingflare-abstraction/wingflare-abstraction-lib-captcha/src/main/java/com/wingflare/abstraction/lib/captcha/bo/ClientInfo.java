package com.wingflare.abstraction.lib.captcha.bo;

/**
 * 客户端信息
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class ClientInfo {

    /**
     * 系统代码
     */
    private String systemCode;

    /**
     * 验证码类型
     */
    private String captchaType;

    /**
     * ip
     */
    private String ipaddr;

    /**
     * 客户代理
     */
    private String userAgent;

    /**
     * 验证码id
     */
    private String captchaId;


    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        this.captchaType = captchaType;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }
}
