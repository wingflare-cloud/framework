package com.wingflare.api.security;


import com.wingflare.api.core.PageResult;
import com.wingflare.api.security.annotation.RequiresPermissions;

import java.math.BigInteger;
import java.time.Duration;

/**
 * 用户认证服务
 */
public interface UserAuthServer {

    /**
     * 获取登录用户信息
     */
    UserAuth getUser();

    /**
     * 移除登陆token
     */
    UserAuth removeToken(String tokenId);

    /**
     * 获取登录用户信息
     */
    UserAuth getUser(String tokenId);

    /**
     * 获取当前系统的登录用户
     *
     * @param pageSize
     * @param startIndex
     * @return
     */
    PageResult<UserAuth> getLoginUsers(long pageSize, long startIndex);

    /**
     * 获取指定用户的登录信息
     *
     * @param userId
     * @param pageSize
     * @param startIndex
     * @return
     */
    PageResult<UserAuth> getUserLoginInfos(BigInteger userId, long pageSize, long startIndex);

    /**
     * 设置登陆用户
     *
     * @param userAuth
     */
    void setUser(UserAuth userAuth, Duration duration);

    /**
     * 验证是否登录
     */
    void checkUser();

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    String encryptPassword(String password);

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    boolean matchesPassword(String rawPassword, String encodedPassword);

    /**
     * 根据注解(@RequiresPermissions)鉴权, 如果验证未通过，则抛出异常: NotPermissionException
     *
     * @param requiresPermissions 注解对象
     */
    void checkPermissions(RequiresPermissions requiresPermissions);

}
