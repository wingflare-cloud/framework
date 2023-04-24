package com.wingflare.facade.module.user.constants;

public interface UserEventName {

    /**
     * 用户创建成功事件
     */
    String USER_CREATED = "event.sys.user.created";

    /**
     * 用户更新成功事件
     */
    String USER_UPDATED = "event.sys.user.updated";

    /**
     * 用户密码更改成功事件
     */
    String USER_CHANGED_PWD = "event.sys.user.changedPwd";

    /**
     * 用户删除成功事件
     */
    String USER_DELETED = "event.sys.user.deleted";

    /**
     * 角色创建成功事件
     */
    String ROLE_CREATED = "event.sys.role.created";

    /**
     * 角色更新成功事件
     */
    String ROLE_UPDATED = "event.sys.role.updated";

    /**
     * 角色删除成功事件
     */
    String ROLE_DELETED = "event.sys.role.deleted";

    /**
     * 角色分组创建成功事件
     */
    String ROLE_GROUP_CREATED = "event.sys.roleGroup.created";

    /**
     * 角色分组更新成功事件
     */
    String ROLE_GROUP_UPDATED = "event.v.roleGroup.updated";

    /**
     * 角色分组删除成功事件
     */
    String ROLE_GROUP_DELETED = "event.sys.roleGroup.deleted";

    /**
     * 岗位身份删除成功事件
     */
    String IDENTITY_DELETED = "event.sys.identity.deleted";

}
