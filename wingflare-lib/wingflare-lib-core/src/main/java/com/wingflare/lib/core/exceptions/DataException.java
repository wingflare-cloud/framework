package com.wingflare.lib.core.exceptions;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date {2021/12/13}
 * @description 数据错误
 */
public class DataException extends BusinessLogicException {

    private static final long serialVersionUID = 5361592208440949400L;

    public static final String defaultMessage = "data.exception";

    public DataException() {
        this(defaultMessage);
    }

    public DataException(Throwable e) {
        super(e.getMessage(), e);
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable throwable)
    {
        super(message, throwable);
    }

}
