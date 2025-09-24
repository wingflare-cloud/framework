package com.wingflare.engine.task.client.retry.core.exception;

import com.wingflare.engine.task.common.core.exception.BaseTaskException;

/**
 * @author: opensnail
 * @date : 2022-03-03 14:49
 */
public class TaskRetryClientException extends BaseTaskException {

    public TaskRetryClientException(String message) {
        super(message);
    }

    public TaskRetryClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskRetryClientException(Throwable cause) {
        super(cause);
    }

    public TaskRetryClientException(String message, Object... arguments) {
        super(message, arguments);
    }

    public TaskRetryClientException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public TaskRetryClientException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public TaskRetryClientException(String message, Object argument) {
        super(message, argument);
    }
}
