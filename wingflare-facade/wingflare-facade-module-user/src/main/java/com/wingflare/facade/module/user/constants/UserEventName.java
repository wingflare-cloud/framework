package com.wingflare.facade.module.user.constants;

public interface UserEventName {

    /**
     * 用户创建事件
     */
    String USER_CREATE = "event.sys.user.create";

    /**
     * 用户创建成功事件
     */
    String USER_CREATED = "event.sys.user.created";

    /**
     * 用户更新成功事件
     */
    String USER_UPDATED = "event.sys.user.updated";

    /**
     * 用户更新事件
     */
    String USER_UPDATE = "event.sys.user.update";

    /**
     * 用户密码更改事件
     */
    String USER_CHANGE_PWD = "event.sys.user.changePwd";

    /**
     * 用户密码更改成功事件
     */
    String USER_CHANGED_PWD = "event.sys.user.changedPwd";

    /**
     * 用户删除事件
     */
    String USER_DELETE = "event.sys.user.delete";

    /**
     * 用户删除成功事件
     */
    String USER_DELETED = "event.sys.user.deleted";

    /**
     * 角色创建事件
     */
    String ROLE_CREATE = "event.sys.role.create";

    /**
     * 角色创建成功事件
     */
    String ROLE_CREATED = "event.sys.role.created";

    /**
     * 角色更新事件
     */
    String ROLE_UPDATE = "event.sys.role.update";

    /**
     * 角色更新成功事件
     */
    String ROLE_UPDATED = "event.sys.role.updated";

    /**
     * 角色删除事件
     */
    String ROLE_DELETE = "event.sys.role.delete";

    /**
     * 角色删除成功事件
     */
    String ROLE_DELETED = "event.sys.role.deleted";

    /**
     * 角色分组创建事件
     */
    String ROLE_GROUP_CREATE = "event.sys.roleGroup.create";

    /**
     * 角色分组创建成功事件
     */
    String ROLE_GROUP_CREATED = "event.sys.roleGroup.created";

    /**
     * 角色分组更新事件
     */
    String ROLE_GROUP_UPDATE = "event.sys.roleGroup.update";

    /**
     * 角色分组更新成功事件
     */
    String ROLE_GROUP_UPDATED = "event.v.roleGroup.updated";

    /**
     * 角色分组删除事件
     */
    String ROLE_GROUP_DELETE = "event.sys.roleGroup.delete";

    /**
     * 角色分组删除成功事件
     */
    String ROLE_GROUP_DELETED = "event.sys.roleGroup.deleted";

    /**
     * 岗位身份删除事件
     */
    String IDENTITY_DELETE = "event.sys.identity.delete";

    /**
     * 岗位身份删除成功事件
     */
    String IDENTITY_DELETED = "event.sys.identity.deleted";

}
