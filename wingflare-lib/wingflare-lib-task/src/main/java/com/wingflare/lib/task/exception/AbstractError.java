package com.wingflare.lib.task.exception;


/**
 * @author opensnail
 * @date 2020/05/13
 */
public abstract class AbstractError {

    public static final AbstractError SUCCESS = new AbstractError("Operation succeeded", "success") {
    };

    public static final AbstractError ERROR = new AbstractError("Operation failed", "error") {
    };

    public static final AbstractError PARAM_INCORRECT = new AbstractError("Parameter exception", "param incorrect") {
    };

    private final String zhMsg;

    private final String enMsg;

    protected AbstractError(String zhMsg, String enMsg) {
        this.zhMsg = zhMsg;
        this.enMsg = enMsg;
    }

    public String getZhMsg() {
        return zhMsg;
    }

    public String getEnMsg() {
        return enMsg;
    }

    public static String getMsg(AbstractError abstractError) {
        return abstractError.zhMsg;
    }

    @Override
    public String toString() {
        return this.zhMsg;
    }
}
