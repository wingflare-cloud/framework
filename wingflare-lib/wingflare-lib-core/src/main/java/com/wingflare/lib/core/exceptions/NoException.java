package com.wingflare.lib.core.exceptions;


/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 无错误
 */
public class NoException extends RuntimeException {

    private static final long serialVersionUID = -5355526288183385742L;

    private Object data;

    public NoException() {
    }

    public NoException(Throwable e) {
        super(e.getMessage(), e);
    }

    public NoException(String message) {
        super(message);
    }

    public NoException(String message, Object data) {
        super(message);
        setData(data);
    }

    public NoException(String message, Throwable throwable)
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
