package com.wingflare.lib.standard.model;

import java.io.Serializable;

/**
 * @author naizui_ycx
 * @className AppAuth
 * @email chenxi2511@qq.com
 * @date 2023/06/13
 * @description 认证应用类
 */
public class AppAuth implements Serializable {

    private static final long serialVersionUID = 6630035246953943906L;

    private String appId;

    private String parentAppId;

    private String mchId;

    private String parentMchId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getParentAppId() {
        return parentAppId;
    }

    public void setParentAppId(String parentAppId) {
        this.parentAppId = parentAppId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getParentMchId() {
        return parentMchId;
    }

    public void setParentMchId(String parentMchId) {
        this.parentMchId = parentMchId;
    }
}
