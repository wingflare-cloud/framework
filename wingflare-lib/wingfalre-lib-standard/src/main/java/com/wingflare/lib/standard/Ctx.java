package com.wingflare.lib.standard;

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
     * 用户信息
     */
    String CONTEXT_KEY_AUTH_USER = "authUser";

    /**
     * 业务系统头
     */
    String HEADER_KEY_BUSINESS_SYSTEM = "Business-System";

    /**
     * 业务系统上下文Key
     */
    String CONTEXT_KEY_BUSINESS_SYSTEM = "businessSystem";

    /**
     * 应用id
     */
    String CONTEXT_KEY_APP_ID = "appId";
    /**
     * 父应用id
     */
    String CONTEXT_KEY_PARENT_APP_ID = "parentAppId";
    /**
     * 商户id
     */
    String CONTEXT_KEY_MERCHANT_ID = "merchantId";
    /**
     * 父商户id
     */
    String CONTEXT_KEY_PARENT_MERCHANT_ID = "parentMerchantId";
    /**
     * 应用信息
     */
    String CONTEXT_KEY_APP = "app";
    /**
     * 父级应用信息
     */
    String CONTEXT_KEY_PARENT_APP = "parentApp";

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

    /**
     * 权限存储标识
     */
    String PREFIX_APP_PERMISSION_KEY = "appPermission";

    /**
     * 应用信息存储标识
     */
    String PREFIX_APP_INFO = "appInfo";

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
     * 认证模式上下文
     */
    String AUTH_MODE_CONTEXT = "sys:ctx:authMode";

    /**
     * 认证json验签字段key
     */
    String AUTH_JSON_SIGN_KEY = "sign";

    /**
     * 验证码存储key format
     */
    String CAPTCHA_KEY_FORMAT = "captcha:%s:%s";

    /**
     * 动态表名上下文
     */
    String DYNAMIC_TABLE_NAME_CONTEXT = "sys:ctx:dynamicTableName";

    /**
     * 事件上下文数据
     */
    String EVENT_CTX_DATA_CONTEXT = "sys:ctx:eventData";

}
