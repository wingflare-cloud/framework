package com.wingflare.engine.task.client.retry.core.exception;

import com.wingflare.engine.task.common.core.exception.BaseTaskException;

public class RetryArgSerializeException extends BaseTaskException {
    public RetryArgSerializeException(String message) {
        super(message);
    }

    public RetryArgSerializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetryArgSerializeException(Throwable cause) {
        super(cause);
    }

    public RetryArgSerializeException(String message, Object... arguments) {
        super(message, arguments);
    }

    public RetryArgSerializeException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public RetryArgSerializeException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public RetryArgSerializeException(String message, Object argument) {
        super(message, argument);
    }
}
