package com.wingflare.abstraction.lib.captcha;


import com.wingflare.abstraction.lib.captcha.dto.CaptchaDto;

/**
 * @author naizui_ycx
 * @interfaceName CaptchaService
 * @email chenxi2511@qqq.com
 * @date 2023/04/20
 * @description 验证码服务
 */
public interface CaptchaGenerateServer<B> {

    /**
     * 获取验证码
     *
     * @return
     */
    CaptchaDto<B> generate();

    /**
     * 对比两个验证值
     *
     * @param v1
     * @param v2
     *
     * @return
     */
    boolean compare(String v1, String v2);

}
