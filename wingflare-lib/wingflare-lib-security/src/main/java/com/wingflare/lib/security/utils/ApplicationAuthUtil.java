package com.wingflare.lib.security.utils;


import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.standard.model.ApplicationAuth;
import com.wingflare.lib.standard.utils.SecurityUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.security.constants.SecurityErrorCode;
import com.wingflare.lib.security.standard.SecurityCheckApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author naizui_ycx
 * @date {2023/01/10}
 * @description 应用认证工具类
 */
@Component
@ConditionalOnBean(SecurityCheckApplication.class)
public class ApplicationAuthUtil {

    @Resource
    private SecurityCheckApplication securityCheck;

    /**
     * 获取当前应用信息
     *
     * @return
     */
    public ApplicationAuth getApp()
    {
        appAuth();
        return ContextHolder.get(Ctx.CONTEXT_KEY_APP, ApplicationAuth.class);
    }

    /**
     * 获取当前应用父级应用信息
     *
     * @return
     */
    public ApplicationAuth getParentApp(){
        appAuth();
        return ContextHolder.get(Ctx.CONTEXT_KEY_PARENT_APP, ApplicationAuth.class);
    }

    /**
     * 验证应用是否通过授权
     */
    public void checkApp() {
        if (getApp() == null) {
            throw new BusinessLogicException(SecurityErrorCode.AUTH_APP_INVALID);
        }
    }

    /**
     * 认证应用信息初始化方法
     */
    private void appAuth() {
        if (!ContextHolder.has(Ctx.CONTEXT_KEY_APP)) {
            String appId = SecurityUtil.getAppId();
            String parentAppId = SecurityUtil.getParentAppId();

            if (StringUtil.isNotEmpty(appId) && !"1".equals(appId)) {
                ContextHolder.set(Ctx.CONTEXT_KEY_APP, getApp(appId));
            }

            if (StringUtil.isNotEmpty(parentAppId) && !"1".equals(parentAppId)) {
                ContextHolder.set(Ctx.CONTEXT_KEY_PARENT_APP, getApp(parentAppId));
            }
        }
    }

    /**
     * 获取当前应用信息
     *
     * @return
     */
    public ApplicationAuth getApp(String appId)
    {
        return securityCheck.getApplicationByAppId(
                String.format("%s:%s", Ctx.PREFIX_APP_INFO, appId)
        );
    }

}
