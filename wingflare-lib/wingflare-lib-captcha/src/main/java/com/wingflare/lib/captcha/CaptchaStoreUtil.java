package com.wingflare.lib.captcha;

import com.wingflare.facade.lib.captcha.CaptchaStoreInterface;

import java.util.ServiceLoader;

/**
 * 验证码存储工具
 */
public class CaptchaStoreUtil {

    /**
     * 验证码存储对象
     */
    private static volatile CaptchaStoreInterface store;

    /**
     * 获取存储对象
     * @return
     */
    public static CaptchaStoreInterface getStore() {
        if (store == null) {
            synchronized (CaptchaStoreUtil.class) {
                if (store == null) {
                    ServiceLoader<CaptchaStoreInterface> s = ServiceLoader.load(CaptchaStoreInterface.class);
                    for (CaptchaStoreInterface nowStore : s) {
                        store = nowStore;
                        break;
                    }
                }
            }
        }

        return store;
    }

}
