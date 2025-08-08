package com.wingflare.lib.standard;

/**
 * @author naizui_ycx
 * @date {2023/02/23}
 * @description 基础通用常量
 */
public interface Std {

    /**
     * 业务错误响应码
     */
    int ERR_STATUS_CODE = 400;

    /**
     * 参数错误响应码
     */
    int PARAM_ERR_STATUS_CODE = 422;

    /**
     * 无权限
     */
    int FORBIDDEN_STATUS_CODE = 403;

    /**
     * 用户客户端id错误
     */
    String USER_CLIENT_ID_ERROR = "user.ClientIdError";

}
