package com.wingflare.business.auth;

/**
 * @InterfaceName ErrorCode
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 错误代码
 */
public interface ErrorCode {

    /**
     * 账户不存在或状态异常
     */
    String USER_LOGIN_ABNORMAL = "sys.auth.user.abnormal";

    /**
     * 重复登录
     */
    String AUTH_REPEAT_LOGIN = "sys.auth.repeatLogin";

    /**
     * 账户不存在或账户密码错误
     */
    String USER_LOGIN_ERROR = "sys.auth.user.loginError";

    /**
     * 账户被禁用
     */
    String USER_BAN = "sys.auth.user.ban";

    /**
     * token生成错误
     */
    String TOKEN_GENERATE_ERR = "sys.token.generateErr";

    /**
     * token生成异常
     */
    String TOKEN_GENERATE_WRONG = "sys.token.generateWrong";

    /**
     * 无组织机构权限
     */
    String ORG_NO_PERMISSION = "sys.auth.orgNoPermission";

    /**
     * 无组身份权限
     */
    String ORG_NO_IDENTITY = "sys.auth.orgNoIdentity";

    /**
     * refresh token异常
     */
    String REFRESH_TOKEN_EXCEPTION = "sys.refreshToken.exception";

    /**
     * 登陆信息未找到
     */
    String LOGIN_INFO_NOTFOUND_OR_EXPIRE = "sys.loginInfo.notfoundOrExpire";

    /**
     * 刷新token失败
     */
    String REFRESH_TOKEN_DEFEATED = "sys.refreshToken.defeated";

}
