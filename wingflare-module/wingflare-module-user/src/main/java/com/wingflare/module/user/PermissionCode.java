package com.wingflare.module.user;

public interface PermissionCode {

    /**
     * 用户数据查看权限
     */
    String USER_VIEW = "base.user.view";

    /**
     * 用户创建权限
     */
    String USER_CREATE = "base.user.create";

    /**
     * 用户更新权限
     */
    String USER_UPDATE = "base.user.update";

    /**
     * 用户删除权限
     */
    String USER_DELETE = "base.user.delete";

    /**
     * 角色数据查看权限
     */
    String ROLE_VIEW = "base.role.view";

    /**
     * 角色创建权限
     */
    String ROLE_CREATE = "base.role.create";

    /**
     * 角色权限保存
     */
    String ROLE_SAVE_PERMISSION = "base.role.savePermission";

    /**
     * 获取角色权限
     */
    String ROLE_PERMISSION_VIEW = "base.role.permission.view";

    /**
     * 角色更新权限
     */
    String ROLE_UPDATE = "base.role.update";

    /**
     * 角色删除权限
     */
    String ROLE_DELETE = "base.role.delete";

}
