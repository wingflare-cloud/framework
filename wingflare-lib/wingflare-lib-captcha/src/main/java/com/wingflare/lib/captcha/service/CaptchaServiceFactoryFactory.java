package com.wingflare.lib.captcha.service;


import com.wingflare.abstraction.lib.captcha.CaptchaServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author naizui_ycx
 * @className CaptchaServiceFactoryFactory
 * @email chenxi2511@qq.com
 * @date 2023/04/20
 * @description 验证码工厂工厂
 */
public class CaptchaServiceFactoryFactory {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaServiceFactoryFactory.class);

    private static final Map<String, CaptchaServerFactory> CAPTCHA_SERVICE_FACTORY = new HashMap<>();


    static {
        ServiceLoader<CaptchaServerFactory> cacheServices = ServiceLoader.load(CaptchaServerFactory.class);

        for (CaptchaServerFactory item : cacheServices) {
            CAPTCHA_SERVICE_FACTORY.put(item.captchaType(), item);
        }

        logger.info("supported-captchaCache-service:{}", CAPTCHA_SERVICE_FACTORY.keySet());
    }

    /**
     * 获取验证码工厂
     *
     * @param captchaType
     * @return
     */
    public static CaptchaServerFactory getFactory(String captchaType) {

        CaptchaServerFactory ret = CAPTCHA_SERVICE_FACTORY.get(captchaType);

        if (ret == null) {
            throw new RuntimeException("unsupported-[captcha.type]=" + captchaType);
        }

        return ret;
    }

}
