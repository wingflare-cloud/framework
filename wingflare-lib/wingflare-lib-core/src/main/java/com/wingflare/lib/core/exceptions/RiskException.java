package com.wingflare.lib.core.exceptions;

/**
 * @author naizui_ycx
 * @date {2025/08/08}
 * @description 风险异常
 */
public class RiskException extends RuntimeException {

    private static final long serialVersionUID = 6827128965309456938L;

    public static final String defaultMessage = "risk.exception";

    private Object data;

    public RiskException() {
        this(defaultMessage);
    }

    public RiskException(Throwable e) {
        super(e.getMessage(), e);
    }

    public RiskException(String message) {
        super(message);
    }

    public RiskException(String message, Object data) {
        super(message);
        setData(data);
    }

    public RiskException(String message, Object data, Throwable throwable) {
        super(message, throwable);
        setData(data);
    }

    public RiskException(String message, Throwable throwable)
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
