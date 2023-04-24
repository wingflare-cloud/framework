package com.wingflare.gateway.exceptions;


import com.wingflare.lib.core.exceptions.BusinessLogicException;

/**
 * @author naizui_ycx
 * @date {2022/1/24}
 * @description 验证异常
 */
public class ValidateException extends BusinessLogicException {

    public static final String defaultMessage = "gateway.captcha.invalid";

    public ValidateException() {
        this(defaultMessage);
    }

    public ValidateException(Throwable e) {
        super(e.getMessage(), e);
    }

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
