package com.wingflare.engine.task.client.common.exception;

import com.wingflare.engine.task.common.core.exception.BaseTaskException;

/**
 * @author: opensnail
 * @date : 2022-03-03 14:49
 */
public class TaskClientException extends BaseTaskException {

    public TaskClientException(String message) {
        super(message);
    }

    public TaskClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskClientException(Throwable cause) {
        super(cause);
    }

    public TaskClientException(String message, Object... arguments) {
        super(message, arguments);
    }

    public TaskClientException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public TaskClientException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public TaskClientException(String message, Object argument) {
        super(message, argument);
    }
}
