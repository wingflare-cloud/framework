package com.wingflare.lib.core.exceptions;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 数据未找到错误
 */
public class DataNotFoundException extends BusinessLogicException {

    private static final long serialVersionUID = 212711563831375034L;

    public static final String defaultMessage = "data.notfound";

    public DataNotFoundException() {
        this(defaultMessage);
    }

    public DataNotFoundException(Throwable e) {
        super(e.getMessage(), e);
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
