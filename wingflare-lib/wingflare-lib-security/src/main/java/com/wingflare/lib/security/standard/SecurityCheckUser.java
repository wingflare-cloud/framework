package com.wingflare.lib.security.standard;


import com.wingflare.lib.standard.model.UserAuth;

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
    boolean hasPermission(String businessSystem, String key, UserAuth user, String permissionCode);

    /**
     * 通过token key获取登陆用户信息
     *
     * @param tokenKey
     * @return
     */
    UserAuth getUserByTokenKey(String tokenKey);

    /**
     * 删除用户通过token key
     *
     * @param tokenKey
     */
    void removeUserByTokenKey(String tokenKey);

    /**
     * 保存登陆用户信息
     *
     * @param tokenKey
     * @param user
     */
    void setUser(String tokenKey, UserAuth user, final Long timeout, final TimeUnit timeUnit);

}
