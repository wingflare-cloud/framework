package com.wingflare.lib.captcha.service;


import com.wingflare.abstraction.lib.captcha.CaptchaServerFactory;

import java.util.Properties;


/**
 * kaptcha验证码
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public class KaptchaServerFactory implements CaptchaServerFactory<String, KaptchaGenerateServer> {


    @Override
    public KaptchaGenerateServer create(Properties config) {
        return new KaptchaGenerateServer(config);
    }

    @Override
    public String captchaType() {
        return "kaptcha";
    }

}
