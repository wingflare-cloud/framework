package com.wingflare.lib.security.utils;


import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.standard.utils.SecurityUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.security.constants.SecurityErrorCode;
import com.wingflare.lib.security.standard.SecurityCheckUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author naizui_ycx
 * @date {2023/01/10}
 * @description 用户认证工具类
 */
@Component
@ConditionalOnBean(SecurityCheckUser.class)
public class UserAuthUtil {

    @Resource
    private SecurityCheckUser securityCheck;

    /**
     * 获取登录用户信息
     */
    public UserAuth getUser() {
        if (ContextHolder.has(Ctx.CONTEXT_KEY_CHECK_USER_OK)) {
            Boolean checkUser = ContextHolder.get(Ctx.CONTEXT_KEY_CHECK_USER_OK, Boolean.class);
            return checkUser ? ContextHolder.get(Ctx.CONTEXT_KEY_USER, UserAuth.class) : null;
        }

        UserAuth userAuth = getUser(null);

        if (userAuth != null) {
            ContextHolder.set(Ctx.CONTEXT_KEY_CHECK_USER_OK, true);
            ContextHolder.set(Ctx.CONTEXT_KEY_USER, userAuth);
        } else {
            ContextHolder.set(Ctx.CONTEXT_KEY_CHECK_USER_OK, false);
        }

        return userAuth;
    }

    /**
     * 移除登陆token
     */
    public UserAuth removeToken(String tokenId) {
        String tokenKey = null;
        UserAuth userAuth = null;

        if (StringUtil.isNotBlank(tokenId)) {
            tokenKey = SecurityUtil.getTokenKey(SecurityUtil.getTokenId());
        }

        if (StringUtil.isNotBlank(tokenKey)) {
            userAuth = removeTokenByKey(tokenKey);
        }

        return userAuth;
    }

    /**
     * 移除登陆token
     */
    public UserAuth removeTokenByKey(String tokenKey) {
        UserAuth userAuth = getUserByTokenKey(tokenKey);

        if (userAuth != null) {
            securityCheck.removeUserByTokenKey(tokenKey);
        }

        return userAuth;
    }

    /**
     * 获取登录用户信息
     */
    public UserAuth getUser(String tokenId) {
        if (StringUtil.isBlank(tokenId)) {
            tokenId = SecurityUtil.getTokenId();
        }

        if (StringUtil.isNotBlank(tokenId)) {
            return securityCheck.getUserByTokenKey(SecurityUtil.getTokenKey(tokenId));
        }

        return null;
    }

    /**
     * 获取登录用户信息
     */
    public UserAuth getUserByTokenKey(String tokenKey) {
        if (StringUtil.isBlank(tokenKey)) {
            tokenKey = SecurityUtil.getTokenKey(SecurityUtil.getTokenId());
        }

        if (StringUtil.isNotBlank(tokenKey)) {
            return securityCheck.getUserByTokenKey(tokenKey);
        }

        return null;
    }

    /**
     * 设置登陆用户
     *
     * @param tokenId
     * @param userAuth
     */
    public void setUser(String tokenId, UserAuth userAuth, final Long timeout, final TimeUnit timeUnit) {
        String tokenKey = null;

        if (StringUtil.isNotBlank(tokenId)) {
            tokenKey = SecurityUtil.getTokenKey(tokenId);
        }

        if (StringUtil.isNotBlank(tokenKey)) {
            securityCheck.setUser(tokenKey, userAuth, timeout, timeUnit);
        }
    }

    /**
     * 验证是否登录
     */
    public void checkUser() {
        if (getUser() == null) {
            throw new BusinessLogicException(SecurityErrorCode.AUTH_TOKEN_INVALID);
        }
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
