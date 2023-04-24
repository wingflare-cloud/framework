package com.wingflare.lib.core.exceptions;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 无权限错误
 */
public class NoPermissionException extends BusinessLogicException {

    private static final long serialVersionUID = 1748496415202200415L;

    public static final String defaultMessage = "permission.notHas";

    public NoPermissionException() {
        this(defaultMessage);
    }

    public NoPermissionException(Throwable e) {
        super(e.getMessage(), e);
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
