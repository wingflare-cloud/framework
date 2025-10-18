package com.wingflare.api.captcha;

/**
 * 验证码存储SPI接口
 */
public interface CaptchaStoreInterface {

    /**
     * 保存验证码数据
     *
     * @param captchaId
     * @param captchaValue
     */
    void save(String captchaId, String captchaValue);

    /**
     * 获取验证码
     * @param captchaId
     * @return
     */
    String get(String captchaId);

    /**
     * 删除验证码数据
     * @param captchaId
     */
    void delete(String captchaId);

}
