package com.wingflare.lib.standard.utils;


import com.wingflare.api.core.Ctx;
import com.wingflare.api.security.UserAuth;
import com.wingflare.lib.core.context.ContextHolder;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/22}
 * @description 安全以及认证工具
 */
public class SecurityUtil {

    /**
     * 获取客户端id
     */
    public static String getClientId() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_CLIENT_ID, null, String.class);
    }

    /**
     * 获取用户ID
     */
    public static UserAuth getUser() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_AUTH_USER, null, UserAuth.class);
    }

    /**
     * 获取业务系统
     */
    public static String getBusinessSystem() {
        return ContextHolder.get(Ctx.CONTEXT_KEY_BUSINESS_SYSTEM);
    }

}
