package com.wingflare.lib.captcha;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码接口
 */
public sealed interface CaptchaInterface permits ImagesAbstractCaptcha {

    /**
     * 输出验证码到输出流
     */
    void out(OutputStream os) throws IOException;

    /**
     * 获取验证码文本
     */
    String text();

    /**
     * 设置验证码生成配置
     * @param config
     */
    void config(CaptchaConfigInterface config);

}
