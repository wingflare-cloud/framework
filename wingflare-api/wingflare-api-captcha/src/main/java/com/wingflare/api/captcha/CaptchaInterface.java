package com.wingflare.api.captcha;


/**
 * 验证码接口
 */
public interface CaptchaInterface {

    /**
     * 验证码生成
     *
     * @param captchaId 验证码id
     * @return 验证码对象
     */
    Object generate(String captchaId);

    /**
     * 验证验证码
     *
     * @param captchaId 验证码id
     * @param value 验证码值
     *
     * @return 验证结果
     */
    boolean verify(String captchaId, String value);

}
