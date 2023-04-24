package com.wingflare.abstraction.lib.captcha.dto;


/**
 * 验证码dto
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class CaptchaDto <B> {

    private String value;

    private B body;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public B getBody() {
        return body;
    }

    public void setBody(B body) {
        this.body = body;
    }

}
