package com.wingflare.adapter.spring.security.utils;


import com.wingflare.abstraction.security.SecurityCheckUser;
import com.wingflare.adapter.spring.security.constants.SecurityErrorCode;
import com.wingflare.api.core.Ctx;
import com.wingflare.api.core.PageResult;
import com.wingflare.api.security.UserAuth;
import com.wingflare.api.security.UserAuthServer;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.api.security.enums.Logical;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


/**
 * @author naizui_ycx
 * @date {2023/01/10}
 * @description 用户认证工具类
 */
@ConditionalOnBean(SecurityCheckUser.class)
public class UserAuthUtil implements UserAuthServer {

    private final SecurityCheckUser securityCheck;

    public UserAuthUtil(SecurityCheckUser securityCheck) {
        this.securityCheck = securityCheck;
    }

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
    public PageResult<UserAuth> getUserLoginInfos(String userId, long pageSize, long startIndex) {
        return securityCheck.getUserAllLoginInfo(userId, pageSize, startIndex);
    }

    /**
     * 设置登陆用户
     *
     * @param userAuth
     */
    public void setUser(UserAuth userAuth, Duration duration) {
        securityCheck.setUser(userAuth, duration);
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
    public String encryptPassword(String password) {
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
    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 根据注解(@RequiresPermissions)鉴权, 如果验证未通过，则抛出异常: NotPermissionException
     *
     * @param requiresPermissions 注解对象
     */
    @Override
    public void checkPermissions(RequiresPermissions requiresPermissions) {
        if (requiresPermissions.logical() == Logical.AND) {
            checkPermissionsAnd(requiresPermissions.value());
        } else {
            checkPermissionsOr(requiresPermissions.value());
        }
    }

    /**
     * 验证用户或应用是否含有指定权限，必须全部拥有
     *
     * @param permissions 权限列表
     */
    public void checkPermissionsAnd(String... permissions) {
        if (!hasPermissionAnd(permissions)) {
            BusinessLogicException exception = new BusinessLogicException(SecurityErrorCode.AUTH_NOT_PERMISSION);
            List<String> list = new ArrayList<>();

            for (String permission : permissions) {
                list.add(String.format("{:%s}", permission.replace(".", "_" )));
            }

            exception.setData(list);
            throw exception;
        }
    }

    /**
     * 验证用户是否含有指定权限，只需包含其中一个
     *
     * @param permissions 权限码数组
     */
    public void checkPermissionsOr(String... permissions) {
        if (hasPermissionOr(permissions)) {
            return;
        }

        if (permissions.length > 0) {
            BusinessLogicException exception = new BusinessLogicException(SecurityErrorCode.AUTH_NOT_PERMISSION_OR);
            List<String> list = new ArrayList<>();

            for (String permission : permissions) {
                list.add(String.format("{:%s}", permission.replace(".", "_" )));
            }
            exception.setData(list);
            throw exception;
        }
    }


    public boolean hasPermissionAnd(String ... permission) {
        String businessSystem = SecurityUtil.getBusinessSystem();

        if (StringUtil.isEmpty(businessSystem)) {
            return false;
        }

        return hasPermissionAnd(businessSystem, permission);
    }

    public boolean hasPermissionOr(String ... permission) {
        String businessSystem = SecurityUtil.getBusinessSystem();

        if (StringUtil.isEmpty(businessSystem)) {
            return false;
        }

        return hasPermissionOr(businessSystem, permission);
    }

    /**
     * 判断是否包含权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermissionAnd(String businessSystem, String ... permission) {
        UserAuth user = getUser();

        if (user != null) {
            if (user.isSuperAdmin() == null || !user.isSuperAdmin()) {
                if (StringUtil.isEmpty(businessSystem) || StringUtil.isBlank(user.getUserId())) {
                    return false;
                }

                return securityCheck.hasPermissionAnd(
                        businessSystem, Ctx.PREFIX_USER_PERMISSION_KEY, user, permission);
            }

            return true;
        }

        return false;
    }

    /**
     * 判断是否包含权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermissionOr(String businessSystem, String ... permission) {
        UserAuth user = getUser();

        if (user != null) {
            if (user.isSuperAdmin() == null || !user.isSuperAdmin()) {
                if (StringUtil.isEmpty(businessSystem) || StringUtil.isBlank(user.getUserId())) {
                    return false;
                }

                return securityCheck.hasPermissionOr(
                        businessSystem, Ctx.PREFIX_USER_PERMISSION_KEY, user, permission);
            }

            return true;
        }

        return false;
    }

}
