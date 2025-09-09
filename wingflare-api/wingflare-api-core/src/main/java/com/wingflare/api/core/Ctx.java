package com.wingflare.api.core;

/**
 * @author naizui_ycx
 * @date {2021/12/18}
 * @description 框架内置上下文信息
 */
public interface Ctx {

    /**
     * jwt头key
     */
    String AUTHENTICATION = "Authorization";

    /**
     * 安全头信息过滤跳过token
     */
    String HEADER_KEY_SEC_TOKEN = "Sec-Header-Token";

    /**
     * 授权标识
     */
    String AUTHENTICATION_PREFIX = "Bearer ";
    /**
     * TOKEN ID
     */
    String HEADER_KEY_TOKEN_ID = "token-id";

    /**
     * 登录用户头信息
     */
    String HEADER_KEY_AUTH_USER = "auth-user";

    /**
     * 启用验证码验证头
     */
    String HEADER_KEY_CAPTCHA_OPEN = "x-captcha-open";

    /**
     * 验证码保护url头
     */
    String HEADER_KEY_CAPTCHA_URL = "x-captcha-url";

    /**
     * 用户信息
     */
    String CONTEXT_KEY_AUTH_USER = "authUser";

    /**
     * 客户端id
     */
    String CONTEXT_KEY_CLIENT_ID = "clientId";


    /**
     * 业务系统头
     */
    String HEADER_KEY_BUSINESS_SYSTEM = "Business-System";

    /**
     * 业务系统上下文Key
     */
    String CONTEXT_KEY_BUSINESS_SYSTEM = "businessSystem";

    /**
     * 登录token存储前缀
     */
    String PREFIX_ACCESS_TOKEN = "login_tokens";

    /**
     * 登录token id前缀
     */
    String PREFIX_ACCESS_TOKEN_ID = "id_tokens";

    /**
     * 登录token用户标识前缀
     */
    String PREFIX_ACCESS_TOKEN_MARK = "user_mark";

    /**
     * 登录数据SECRET
     */
    String LOGIN_DEFAULT_SECRET = "wingflarelogin";

    /**
     * 权限存储标识
     */
    String PREFIX_USER_PERMISSION_KEY = "permission";

    String DP_PAYLOAD_TYPE_C = "condition";
    String DP_PAYLOAD_TYPE_O = "operator";

    String DP_LOGICAL_AND = "and";
    String DP_LOGICAL_OR = "or";

    /**
     * 数据权限存储前缀
     */
    String PREFIX_DATA_PERMISSION_KEY = "dp";

    /**
     * 数据权限
     */
    String BASE_DATA_CONDITION_NAME = "baseDataConditionName";

    /**
     * 数据权限条件前缀
     */
    String PREFIX_DATA_PERMISSION_CONDITION = "dpc";

    /**
     * 数据权限上下文
     */
    String DATA_PERMISSION_CONTEXT = "sys:ctx:dataPermission";

    /**
     * 认证json验签字段key
     */
    String AUTH_JSON_SIGN_KEY = "sign";

    /**
     * 动态表名上下文
     */
    String DYNAMIC_TABLE_NAME_CONTEXT = "sys:ctx:dynamicTableName";

}
