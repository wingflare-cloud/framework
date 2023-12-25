package com.wingflare.lib.standard;

/**
 * 应用实体
 */
public class ApplicationEntity {

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 应用密钥
     */
    private String secretKey;

    /**
     * 商户id
     */
    private String mchId;

    /**
     * 父级应用id
     */
    private String parentAppId;

    /**
     * 父级商户id
     */
    private String parentMchId;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getParentAppId() {
        return parentAppId;
    }

    public void setParentAppId(String parentAppId) {
        this.parentAppId = parentAppId;
    }

    public String getParentMchId() {
        return parentMchId;
    }

    public void setParentMchId(String parentMchId) {
        this.parentMchId = parentMchId;
    }
}
