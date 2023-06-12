package com.wingflare.gateway.exceptions;

/**
 * @author naizui_ycx
 * @className OpenApiException
 * @email chenxi2511@qqq.com
 * @date 2023/06/11
 * @description 开放平台基础异常类
 */
public class OpenApiException extends RuntimeException {

    public OpenApiException(String message) {
        super(message);
    }

}
