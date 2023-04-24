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
    String AUTHENTICATION_PREFIX = "Payload ";

    /**
     * 用户id
     */
    String HEADER_KEY_USER_ID = "user-id";
    /**
     * 用户名
     */
    String HEADER_KEY_USER_NAME = "user-name";
    /**
     * TOKEN ID
     */
    String HEADER_KEY_TOKEN_ID = "token-id";
    /**
     * user type
     */
    String HEADER_KEY_USER_TYPE = "user-type";
    /**
     * user role ids
     */
    String HEADER_KEY_USER_ROLES = "user-roles";
    /**
     * identity
     */
    String HEADER_KEY_IDENTITY = "identity";
    /**
     * org ids
     */
    String HEADER_KEY_ORG = "org";
    /**
     * current org id
     */
    String HEADER_KEY_CURRENT_ORG = "current-org";

    /**
     * 用户id
     */
    String CONTEXT_KEY_USER_ID = "userId";
    /**
     * 用户名
     */
    String CONTEXT_KEY_USER_NAME = "userName";
    /**
     * token id
     */
    String CONTEXT_KEY_TOKEN_ID = "tokenId";

    /**
     * 是否存在已认证用户上下文
     */
    String CONTEXT_KEY_CHECK_USER_OK = "sys:ctx:checkUser";

    /**
     * 用户信息
     */
    String CONTEXT_KEY_USER = "user";

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
    String HEADER_KEY_APP_ID = "app-id";
    /**
     * 父应用id
     */
    String HEADER_KEY_PARENT_APP_ID = "parent-app-id";
    /**
     * 商户id
     */
    String HEADER_KEY_MERCHANT_ID = "merchant-id";
    /**
     * 父商户id
     */
    String HEADER_KEY_PARENT_MERCHANT_ID = "parent-merchant-id";


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
     * refreshToken存储前缀
     */
    String PREFIX_REFRESH_TOKEN = "refresh_tokens";

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
