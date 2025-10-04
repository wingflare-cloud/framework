package com.wingflare.engine.task.client.retry.retryer;


import com.wingflare.engine.task.common.core.enums.RetryResultStatusEnum;

/**
 * @author: opensnail
 * @date : 2022-03-04 14:52
 */
public class RetryerResultContext {

    private Object result;

    private RetryerInfo retryerInfo;

    private RetryResultStatusEnum retryResultStatusEnum;

    private String message;

    private Throwable throwable;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public RetryerInfo getRetryerInfo() {
        return retryerInfo;
    }

    public void setRetryerInfo(RetryerInfo retryerInfo) {
        this.retryerInfo = retryerInfo;
    }

    public RetryResultStatusEnum getRetryResultStatusEnum() {
        return retryResultStatusEnum;
    }

    public void setRetryResultStatusEnum(RetryResultStatusEnum retryResultStatusEnum) {
        this.retryResultStatusEnum = retryResultStatusEnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
