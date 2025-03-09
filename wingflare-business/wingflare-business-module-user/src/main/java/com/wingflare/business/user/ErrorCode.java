package com.wingflare.business.user;

/**
 * @InterfaceName ErrorCode
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 错误代码
 */
public interface ErrorCode {

    /**
     * 用户创建失败
     */
    String SYS_USER_CREATE_ERROR = "sys.user.createError";

    /**
     * 用户更新失败
     */
    String SYS_USER_UPDATE_ERROR = "sys.user.updateError";

    /**
     * 用户名重复
     */
    String SYS_USER_NAME_REPEAT = "sys.user.nameRepeat";

    /**
     * 用户邮箱重复
     */
    String SYS_USER_EMAIL_REPEAT = "sys.user.mailRepeat";

    /**
     * 用户手机号重复
     */
    String SYS_USER_PHONE_REPEAT = "sys.user.phoneRepeat";

    /**
     * 用户登录账号重复
     */
    String SYS_USER_ACCOUNT_REPEAT = "sys.user.accountRepeat";

    /**
     * 没有密码更新权限
     */
    String SYS_USER_UPDATE_PASSWD_NO_POWER = "sys.user.updatePasswdNoPower";

    /**
     * 原始密码不匹配
     */
    String SYS_USER_OLD_PWD_ERR = "sys.user.oldPwdErr";

    /**
     * 不存在的用户
     */
    String SYS_USER_NON_EXISTENT = "sys.user.nonExistent";

    /**
     * 非特权账户无法直接绑定角色
     */
    String SYS_USER_NON_PRIVILEGE_NOT_BIND_ROLE = "sys.user.nonPrivilegeNotBindRole";

    /**
     * 用户账户异常
     */
    String SYS_USER_ACCOUNT_TYPE_ERROR = "sys.user.accountTypeError";

    /**
     * 用户绑定角色失败
     */
    String SYS_USER_BIND_ROLE_ERROR = "sys.user.bindRoleError";

    /**
     * 角色创建失败
     */
    String SYS_ROLE_CREATE_ERROR = "sys.role.createError";

    /**
     * 角色更新失败
     */
    String SYS_ROLE_UPDATE_ERROR = "sys.role.updateError";

    /**
     * 角色删除失败
     */
    String SYS_ROLE_DELETE_ERROR = "sys.role.deleteError";

    /**
     * 角色名重复
     */
    String SYS_ROLE_NAME_REPEAT = "sys.role.nameRepeat";

    /**
     * 未找到父级角色
     */
    String SYS_ROLE_PARENT_NOTFOUND = "sys.role.parent.notfound";

    /**
     * 角色不存在
     */
    String SYS_ROLE_NON_EXISTENT = "sys.role.nonExistent";

    /**
     * 不存在的系统
     */
    String SYSTEM_NON_EXISTENT = "sys.name.nonExistent";

    /**
     * 角色禁止更新父级角色
     */
    String SYS_ROLE_UPDATE_FORBID_PARENT = "sys.role.update.forbidParent";

    /**
     * 职级名字重复
     */
    String SYS_JOB_LEVEL_NAME_REPEAT = "sys.job.nameRepeat";

    /**
     * 岗位身份名字重复
     */
    String SYS_IDENTITY_NAME_REPEAT = "sys.identity.nameRepeat";

    /**
     * 组织部门名字重复
     */
    String SYS_ORG_DEPARTMENT_NAME_REPEAT = "sys.orgDepartment.nameRepeat";

    /**
     * 组织不存在
     */
    String SYS_ORG_NON_EXISTENT = "sys.org.nonExistent";

    /**
     * 父级部门不存在
     */
    String SYS_PARENT_DEPARTMENT_NON_EXISTENT = "sys.department.parentNonExistent";

    /**
     * 部门不存在
     */
    String SYS_DEPARTMENT_NON_EXISTENT = "sys.department.nonExistent";

    /**
     * 职级不存在
     */
    String SYS_JOB_LEVEL_NON_EXISTENT = "sys.jobLevel.nonExistent";

    /**
     * 职级分类不存在
     */
    String SYS_JOB_LEVEL_CLASSIFY_NON_EXISTENT = "sys.jobLevelClassify.nonExistent";

    /**
     * 职级分类已存在
     */
    String SYS_JOB_LEVEL_CLASSIFY_EXISTENT = "sys.jobLevelClassify.existent";

}
