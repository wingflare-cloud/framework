package com.wingflare.lib.core.exceptions;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 参数错误
 */
public class ParameterException extends BusinessLogicException {

    private static final long serialVersionUID = 5041586091502652748L;

    public static final String defaultMessage = "parameter.exception";

    public ParameterException() {
        this(defaultMessage);
    }

    public ParameterException(Throwable e) {
        super(e.getMessage(), e);
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Object data) {
        super(message);
        setData(data);
    }

    public ParameterException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
