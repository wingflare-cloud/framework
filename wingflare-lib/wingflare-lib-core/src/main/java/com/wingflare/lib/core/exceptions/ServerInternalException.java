package com.wingflare.lib.core.exceptions;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 服务器内部错误
 */
public class ServerInternalException extends RuntimeException {

    private static final long serialVersionUID = 3140011530148852182L;

    public static final String defaultMessage = "server.exception";

    private Object data;

    public ServerInternalException() {
        this(defaultMessage);
    }

    public ServerInternalException(Throwable e) {
        super(e.getMessage(), e);
    }

    public ServerInternalException(String message) {
        super(message);
    }

    public ServerInternalException(String message, Object data) {
        super(message);
        setData(data);
    }

    public ServerInternalException(String message, Throwable throwable)
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
