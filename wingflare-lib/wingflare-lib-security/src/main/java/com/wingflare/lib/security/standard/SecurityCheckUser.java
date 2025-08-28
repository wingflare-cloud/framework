package com.wingflare.lib.security.standard;


import com.wingflare.lib.standard.PageResult;
import com.wingflare.lib.standard.model.UserAuth;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;


public interface SecurityCheckUser {

    /**
     * 权限判断方法
     *
     * @param businessSystem 业务系统
     * @param key 权限key
     * @param user 当前上下文中的user对象
     * @param permissionCode 权限代码
     *
     * @return
     */
    boolean hasPermissionAnd(String businessSystem, String key, UserAuth user, String ... permissionCode);

    /**
     * 权限判断方法
     *
     * @param businessSystem 业务系统
     * @param key 权限key
     * @param user 当前上下文中的user对象
     * @param permissionCode 权限代码
     *
     * @return
     */
    boolean hasPermissionOr(String businessSystem, String key, UserAuth user, String ... permissionCode);

    /**
     * 通过token id获取登陆用户信息
     *
     * @param tokenId
     * @return
     */
    UserAuth getUserByTokenId(String tokenId);

    /**
     * 删除登录用户
     *
     * @param user
     */
    void removeUser(UserAuth user);

    /**
     * 保存登陆用户信息
     *
     * @param user
     */
    void setUser(UserAuth user, final Long timeout, final TimeUnit timeUnit);

    /**
     * 获取登录用户列表
     *
     * @param pageSize
     * @param startIndex
     *
     * @return
     */
    PageResult<UserAuth> getLoginUsers(final long pageSize, final long startIndex);

    /**
     * 获取指定用户全部登录信息
     *
     * @param userId
     * @param pageSize
     * @param startIndex
     *
     * @return
     */
    PageResult<UserAuth> getUserAllLoginInfo(final BigInteger userId, final long pageSize, final long startIndex);

}
