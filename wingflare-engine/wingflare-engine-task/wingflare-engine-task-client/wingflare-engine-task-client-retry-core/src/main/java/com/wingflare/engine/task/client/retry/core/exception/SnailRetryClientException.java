package com.wingflare.engine.task.client.retry.core.exception;

import com.wingflare.engine.task.common.core.exception.BaseTaskException;

/**
 * @author: opensnail
 * @date : 2022-03-03 14:49
 */
public class SnailRetryClientException extends BaseTaskException {

    public SnailRetryClientException(String message) {
        super(message);
    }

    public SnailRetryClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public SnailRetryClientException(Throwable cause) {
        super(cause);
    }

    public SnailRetryClientException(String message, Object... arguments) {
        super(message, arguments);
    }

    public SnailRetryClientException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public SnailRetryClientException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public SnailRetryClientException(String message, Object argument) {
        super(message, argument);
    }
}
