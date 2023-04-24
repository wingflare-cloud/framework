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
     * 业务重试错误码
     */
    int RETRY_ERR_STATUS_CODE = 506;

    /**
     * 未授权错误码
     */
    int UNAUTHORIZED_ERR_STATUS_CODE = 401;



}
