package com.wingflare.lib.captcha;


import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码接口
 */
public interface CaptchaInterface {

    /**
     * 输出验证码到输出流
     */
    void out(String captchaId, OutputStream os) throws IOException;

    /**
     * 设置验证码生成配置
     * @param config
     */
    void config(CaptchaConfigInterface config);

    /**
     * 验证码验证
     * @param captchaId
     * @return
     */
    boolean verify(String captchaId, String value);

}
