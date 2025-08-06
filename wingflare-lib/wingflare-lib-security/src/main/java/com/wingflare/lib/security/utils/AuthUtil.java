package com.wingflare.lib.security.utils;


import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.standard.utils.SecurityUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.security.annotation.RequiresPermissions;
import com.wingflare.lib.security.constants.SecurityErrorCode;
import com.wingflare.lib.security.enums.Logical;
import com.wingflare.lib.security.standard.SecurityCheckUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

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

    @Resource
    private SecurityCheckUser userSecurityCheck;

    @Resource
    private UserAuthUtil userAuthUtil;

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
        for (String permission : permissions) {
            if (!hasPermission(permission)) {
                BusinessLogicException exception = new BusinessLogicException(SecurityErrorCode.AUTH_NOT_PERMISSION);
                List<String> list = new ArrayList<>();
                list.add(String.format("{:%s}", permission.replace(".", "_" )));
                exception.setData(list);
                throw exception;
            }
        }
    }

    /**
     * 验证用户是否含有指定权限，只需包含其中一个
     *
     * @param permissions 权限码数组
     */
    public void checkPermissionsOr(String... permissions) {
        for (String permission : permissions) {
            if (hasPermission(permission)) {
                return;
            }
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


    public boolean hasPermission(String permission) {
        String businessSystem = SecurityUtil.getBusinessSystem();

        if (StringUtil.isEmpty(businessSystem)) {
            return false;
        }

        return hasPermission(businessSystem, permission);
    }

    /**
     * 判断是否包含权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermission(String businessSystem, String permission) {
        UserAuth user = userAuthUtil.getUser();

        if (user != null) {
            if (user.isSuperAdmin() == null || !user.isSuperAdmin()) {
                if (StringUtil.isEmpty(businessSystem) || user.getUserId() == null || user.getUserId().compareTo(BigInteger.ZERO) == 0) {
                    return false;
                }

                return userSecurityCheck.hasPermission(
                        businessSystem, Ctx.PREFIX_USER_PERMISSION_KEY, user, permission);
            }

            return true;
        }

        return false;
    }

}
