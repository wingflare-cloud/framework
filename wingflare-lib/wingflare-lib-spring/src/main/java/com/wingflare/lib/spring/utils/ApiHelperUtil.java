package com.wingflare.lib.spring.utils;


import com.wingflare.lib.spring.configure.properties.SystemInternalProperties;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.constants.Wf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 *
 */
public class ApiHelperUtil {

    /**
     * 系统上下文key
     */
    private final static List<String> systemCxtKeys = new ArrayList<String>(){{
        add(Wf.INTERNAL_API_AUTH_CONTEXT_KEY);
        add(Wf.PERMISSION_RESULT_CONTEXT_KEY);
        add(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY);
        add(Wf.INTERNAL_API_DEFAULT_SECRET_STR);
        add(Wf.RESPONSE_FORCE_ORIGINAL_CONTEXT_KEY);
        add(Wf.REQUEST_AUTO_HEADER_CONTEXT_KEY);
        add(Wf.REQUEST_AUTO_THROW_ERR_CONTEXT_KEY);
        add(Wf.FUNC_MARK_CONTEXT_KEY);
        add(Ctx.AUTH_MODE_CONTEXT);
        add(Ctx.CONTEXT_KEY_CHECK_USER_OK);
        add(Wf.REQUEST_HEADER_PENETRATION_CONTEXT_KEY);
    }};

    /**
     * 验证当前请求是否为安全流量
     *
     * @return
     */
    public static boolean checkSecuritySource() {
        Boolean fromSource;

        if (!ContextHolder.has(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY)) {
            Boolean permissionSuccess = ContextHolder.get(Wf.PERMISSION_RESULT_CONTEXT_KEY, null, Boolean.class);

            if (permissionSuccess == null || !permissionSuccess) {
                SystemInternalProperties systemInternalProperties = SpringUtil.getBean(SystemInternalProperties.class);
                String secret = ContextHolder.get(Wf.INTERNAL_API_AUTH_CONTEXT_KEY, null, String.class);
                String secretKey = systemInternalProperties.getSecret();

                if (StringUtil.isEmpty(secretKey)) {
                    secretKey = Wf.INTERNAL_API_DEFAULT_SECRET_STR;
                }

                // 内部请求验证
                if (!StringUtil.equals(secretKey, secret)) {
                    ContextHolder.set(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY, false);
                    return false;
                } else {
                    ContextHolder.set(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY, true);
                }
            }

            fromSource = true;
        } else {
            fromSource = ContextHolder.get(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY, null, Boolean.class);
        }

        return fromSource;
    }

    /**
     * 判断是否为内网流量
     *
     * @return
     */
    public static boolean checkInternalSource() {
        Boolean fromSource;

        if (!ContextHolder.has(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY)) {
            SystemInternalProperties systemInternalProperties = SpringUtil.getBean(SystemInternalProperties.class);
            String secret = ContextHolder.get(Wf.INTERNAL_API_AUTH_CONTEXT_KEY, null, String.class);
            String secretKey = systemInternalProperties.getSecret();

            if (StringUtil.isEmpty(secretKey)) {
                secretKey = Wf.INTERNAL_API_DEFAULT_SECRET_STR;
            }

            // 内部请求验证
            if (!StringUtil.equals(secretKey, secret)) {
                ContextHolder.set(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY, false);
                return false;
            } else {
                ContextHolder.set(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY, true);
            }

            fromSource = true;
        } else {
            fromSource = ContextHolder.get(Wf.FROM_SOURCE_RESULT_CONTEXT_KEY, null, Boolean.class);
        }

        return fromSource;
    }

    /**
     * 判断当前是否需要返回文本内容
     *
     * @return
     */
    public static boolean checkIsOriginalResp() {
        return ContextHolder.get(Wf.RESPONSE_FORCE_ORIGINAL_CONTEXT_KEY, false, Boolean.class);
    }

    /**
     * 判断当前请求是否自动装载头部
     *
     * @return
     */
    public static boolean checkRequestAutoHeader() {
        return ContextHolder.get(Wf.REQUEST_AUTO_HEADER_CONTEXT_KEY, false, Boolean.class);
    }

    /**
     * 判断当前请求是否穿透上下文
     *
     * @return
     */
    public static boolean checkRequestHeaderPenetration() {
        return ContextHolder.get(Wf.REQUEST_HEADER_PENETRATION_CONTEXT_KEY, false, Boolean.class);
    }

    /**
     * 判断当前操作是否自动抛出异常
     *
     * @return
     */
    public static boolean checkRequestAutoThrowErr() {
        return ContextHolder.get(Wf.REQUEST_AUTO_THROW_ERR_CONTEXT_KEY, true, Boolean.class);
    }

    /**
     * 强制接口原样返回数据
     *
     * @return
     */
    public static void setOriginalResp(boolean b) {
        ContextHolder.set(Wf.RESPONSE_FORCE_ORIGINAL_CONTEXT_KEY, b);
    }

    /**
     * 设置请求自动装载头部
     *
     * @return
     */
    public static void setRequestAutoHeader(boolean b) {
        ContextHolder.set(Wf.REQUEST_AUTO_HEADER_CONTEXT_KEY, b);
    }

    /**
     * 设置请求上下文穿透
     *
     * @return
     */
    public static void setRequestHeaderPenetration(boolean b) {
        ContextHolder.set(Wf.REQUEST_HEADER_PENETRATION_CONTEXT_KEY, b);
    }

    /**
     * 设置操作是否自动抛出异常
     *
     * @return
     */
    public static void setRequestAutoThrowErr(boolean b) {
        ContextHolder.set(Wf.REQUEST_AUTO_THROW_ERR_CONTEXT_KEY, b);
    }

    /**
     * 获取功能标识串
     *
     * @return
     */
    public static String getFuncMark() {
        return ContextHolder.get(Wf.FUNC_MARK_CONTEXT_KEY, "");
    }

    /**
     * 获取系统内置上下文key list
     *
     * @return key list
     */
    public static List<String> getSystemCxtKeys() {
        return systemCxtKeys;
    }

    /**
     * 无异常接口请求
     *
     * @param apiCallable
     * @param <T>
     *
     * @return
     */
    public static <T> T nonThrowErrApi(Supplier<T> apiCallable) {
        ApiHelperUtil.setRequestAutoThrowErr(false);
        T o = apiCallable.get();
        ApiHelperUtil.setRequestAutoThrowErr(true);
        return o;
    }

}
