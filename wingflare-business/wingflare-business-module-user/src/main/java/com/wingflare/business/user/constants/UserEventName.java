package com.wingflare.business.user.constants;

public interface UserEventName extends com.wingflare.facade.module.user.constants.UserEventName {

    /**
     * 用户创建事件
     */
    String USER_CREATE = "event.sys.user.create";

    /**
     * 用户更新事件
     */
    String USER_UPDATE = "event.sys.user.update";

    /**
     * 用户密码更改事件
     */
    String USER_CHANGE_PWD = "event.sys.user.changePwd";

    /**
     * 用户删除事件
     */
    String USER_DELETE = "event.sys.user.delete";

    /**
     * 角色创建事件
     */
    String ROLE_CREATE = "event.sys.role.create";

    /**
     * 角色更新事件
     */
    String ROLE_UPDATE = "event.sys.role.update";

    /**
     * 角色删除事件
     */
    String ROLE_DELETE = "event.sys.role.delete";

    /**
     * 角色分组创建事件
     */
    String ROLE_GROUP_CREATE = "event.sys.roleGroup.create";

    /**
     * 角色分组更新事件
     */
    String ROLE_GROUP_UPDATE = "event.sys.roleGroup.update";

    /**
     * 角色分组删除事件
     */
    String ROLE_GROUP_DELETE = "event.sys.roleGroup.delete";

    /**
     * 岗位身份删除事件
     */
    String IDENTITY_DELETE = "event.sys.identity.delete";

}
