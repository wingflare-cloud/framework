package com.wingflare.lib.core.exceptions;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 权限未认证错误
 */
public class NoAuthException extends BusinessLogicException {

    private static final long serialVersionUID = 1748496415202200415L;

    public static final String defaultMessage = "auth.notVerified";

    public NoAuthException() {
        this(defaultMessage);
    }

    public NoAuthException(Throwable e) {
        super(e.getMessage(), e);
    }

    public NoAuthException(String message) {
        super(message);
    }

    public NoAuthException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
