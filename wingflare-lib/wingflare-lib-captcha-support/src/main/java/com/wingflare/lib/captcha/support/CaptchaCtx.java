package com.wingflare.lib.captcha.support;

/**
 * 验证码上下文key
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 *
 */
public interface CaptchaCtx {

    /**
     * 验证码 id传递key
     */
    String CAPTCHA_ID_TRANSFER_KEY = "Captcha-Id";

    /**
     * 验证码值传递key
     */
    String CAPTCHA_VALUE_TRANSFER_KEY = "Captcha-Value";

    /**
     * 验证码name传递key
     */
    String CAPTCHA_NAME_TRANSFER_KEY = "Captcha-Name";

    /**
     * 验证码date传递key
     */
    String CAPTCHA_DATE_TIME_TRANSFER_KEY = "Captcha-Date-Time";

    /**
     * 验证码哈希传递key
     */
    String CAPTCHA_HASH_TRANSFER_KEY = "Captcha-Sec-Hash";

    /**
     * 验证码认证名
     */
    String CAPTCHA_CHECK_NAME_TRANSFER_KEY = "Captcha-Check-Name";

    /**
     * 验证码id上下文key
     */
    String CAPTCHA_CHECK_NAME_CTX_KEY = "captchaCheckName";

    /**
     * 验证码id上下文key
     */
    String CAPTCHA_ID_CTX_KEY = "captchaId";

}
