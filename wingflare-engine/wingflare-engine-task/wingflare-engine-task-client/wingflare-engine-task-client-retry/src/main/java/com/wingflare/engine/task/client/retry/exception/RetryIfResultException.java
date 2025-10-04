package com.wingflare.engine.task.client.retry.exception;

import com.wingflare.engine.task.common.core.exception.BaseTaskException;

public class RetryIfResultException extends BaseTaskException {
    public RetryIfResultException(String message) {
        super(message);
    }

    public RetryIfResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetryIfResultException(Throwable cause) {
        super(cause);
    }

    public RetryIfResultException(String message, Object... arguments) {
        super(message, arguments);
    }

    public RetryIfResultException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public RetryIfResultException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public RetryIfResultException(String message, Object argument) {
        super(message, argument);
    }
}
