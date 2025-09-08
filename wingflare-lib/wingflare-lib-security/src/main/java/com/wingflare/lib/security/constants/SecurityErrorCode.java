package com.wingflare.lib.security.constants;

/**
 * @author naizui_ycx
 * @date {2022/1/5}
 * @description
 */
public interface SecurityErrorCode {

    /**
     * token已失效
     */
    String AUTH_TOKEN_INVALID = "sec.auth.token.invalid";

    /**
     * 无权限
     */
    String AUTH_NOT_PERMISSION = "sec.auth.noPermission";

    /**
     * 无权限
     */
    String AUTH_NOT_PERMISSION_OR = "sec.auth.noPermissionOr";

    /**
     * 无权限
     */
    String AUTH_NOT_PERMISSION_SYSTEM = "sec.auth.noPermissionSystem";

}
