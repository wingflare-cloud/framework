package com.wingflare.adapter.spring.security.utils;


import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.security.constants.SecurityErrorCode;
import com.wingflare.lib.security.standard.SecurityCheckUser;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.PageResult;
import com.wingflare.lib.standard.model.UserAuth;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
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
        return getUser(null);
    }

    /**
     * 移除登陆token
     */
    public UserAuth removeToken(String tokenId) {
        UserAuth userAuth = null;

        if (StringUtil.isNotBlank(tokenId)) {
            userAuth = getUser(tokenId);
            securityCheck.removeUser(userAuth);
        }

        return userAuth;
    }

    /**
     * 获取登录用户信息
     */
    public UserAuth getUser(String tokenId) {
        if (StringUtil.isBlank(tokenId)) {
            return ContextHolder.get(Ctx.CONTEXT_KEY_AUTH_USER, null, UserAuth.class);
        }

        if (StringUtil.isNotBlank(tokenId)) {
            return securityCheck.getUserByTokenId(tokenId);
        }

        return null;
    }

    /**
     * 获取当前系统的登录用户
     *
     * @param pageSize
     * @param startIndex
     * @return
     */
    public PageResult<UserAuth> getLoginUsers(long pageSize, long startIndex) {
        return securityCheck.getLoginUsers(pageSize, startIndex);
    }

    /**
     * 获取指定用户的登录信息
     *
     * @param userId
     * @param pageSize
     * @param startIndex
     * @return
     */
    public PageResult<UserAuth> getUserLoginInfos(BigInteger userId, long pageSize, long startIndex) {
        return securityCheck.getUserAllLoginInfo(userId, pageSize, startIndex);
    }

    /**
     * 设置登陆用户
     *
     * @param userAuth
     */
    public void setUser(UserAuth userAuth, final Long timeout, final TimeUnit timeUnit) {
        securityCheck.setUser(userAuth, timeout, timeUnit);
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
