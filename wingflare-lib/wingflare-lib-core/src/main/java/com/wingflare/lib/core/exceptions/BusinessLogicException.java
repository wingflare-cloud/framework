package com.wingflare.lib.core.exceptions;

/**
 * @author naizui_ycx
 * @date {2021/12/22}
 * @description 业务逻辑错误异常
 */
public class BusinessLogicException extends RuntimeException {

    private static final long serialVersionUID = 6929128789309454528L;

    public static final String defaultMessage = "logic.exception";

    private Object data;

    public BusinessLogicException() {
        this(defaultMessage);
    }

    public BusinessLogicException(Throwable e) {
        super(e.getMessage(), e);
    }

    public BusinessLogicException(String message) {
        super(message);
    }

    public BusinessLogicException(String message, Object data) {
        super(message);
        setData(data);
    }

    public BusinessLogicException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
