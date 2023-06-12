package com.wingflare.gateway.exceptions;

/**
 * 开放平台签名验证异常
 * @author naizui
 */
public class OpenApiSignException extends OpenApiException {

    public OpenApiSignException(String message) {
        super(message);
    }

}
