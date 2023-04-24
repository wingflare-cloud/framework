package com.wingflare.gateway.dto;

/**
 * 验证码返回dto
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class CaptchaBodyDto<T> {

    /**
     * 验证码类型
     */
    private String type;

    /**
     * 验证码id
     */
    private String captchaId;

    /**
     * 验证码内容
     */
    private T body;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
