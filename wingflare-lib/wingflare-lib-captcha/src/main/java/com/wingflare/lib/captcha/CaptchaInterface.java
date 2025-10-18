package com.wingflare.lib.captcha;


/**
 * 验证码接口
 */
public interface CaptchaInterface {

    /**
     * 输出验证码到输出流
     */
    Object generate(String captchaId);

    /**
     * 验证码验证
     * @param captchaId
     * @return
     */
    boolean verify(String captchaId, String value);

}
