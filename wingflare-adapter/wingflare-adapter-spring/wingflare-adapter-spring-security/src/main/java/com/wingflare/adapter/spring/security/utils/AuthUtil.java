package com.wingflare.adapter.spring.security.utils;


import com.wingflare.adapter.spring.security.constants.SecurityErrorCode;
import com.wingflare.adapter.spring.security.standard.SecurityCheckUser;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.api.security.enums.Logical;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * @author naizui_ycx
 * @date {2022/1/5}
 * @description 认证工具类
 */
@Component
@ConditionalOnBean({
        UserAuthUtil.class
})
public class AuthUtil {

    private final SecurityCheckUser userSecurityCheck;

    private final UserAuthUtil userAuthUtil;

    public AuthUtil(SecurityCheckUser userSecurityCheck, UserAuthUtil userAuthUtil) {
        this.userSecurityCheck = userSecurityCheck;
        this.userAuthUtil = userAuthUtil;
    }

    /**
     * 根据注解(@RequiresPermissions)鉴权, 如果验证未通过，则抛出异常: NotPermissionException
     *
     * @param requiresPermissions 注解对象
     */
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
        UserAuth user = userAuthUtil.getUser();

        if (user != null) {
            if (user.isSuperAdmin() == null || !user.isSuperAdmin()) {
                if (StringUtil.isEmpty(businessSystem) || user.getUserId() == null || user.getUserId().compareTo(BigInteger.ZERO) == 0) {
                    return false;
                }

                return userSecurityCheck.hasPermissionAnd(
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
        UserAuth user = userAuthUtil.getUser();

        if (user != null) {
            if (user.isSuperAdmin() == null || !user.isSuperAdmin()) {
                if (StringUtil.isEmpty(businessSystem) || user.getUserId() == null || user.getUserId().compareTo(BigInteger.ZERO) == 0) {
                    return false;
                }

                return userSecurityCheck.hasPermissionOr(
                        businessSystem, Ctx.PREFIX_USER_PERMISSION_KEY, user, permission);
            }

            return true;
        }

        return false;
    }

}
