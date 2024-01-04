package com.wingflare.abstraction.lib.captcha;


import java.util.Properties;

/**
 * @author naizui_ycx
 * @interfaceName CaptchaServiceFactory
 * @email chenxi2511@qq.com
 * @date 2023/04/20
 * @description 验证码服务工厂
 */
public interface CaptchaServerFactory<B, F extends CaptchaGenerateServer<B>> {

    /**
     * 创建验证码服务
     *
     * @param config 验证码配置
     *
     * @return
     */
    F create(Properties config);

    /**
     * 验证码类型
     *
     * @return
     */
    String captchaType();

}
