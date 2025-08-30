package com.wingflare.engine.task.client.common.exception;

import com.wingflare.engine.task.common.core.exception.BaseTaskException;

/**
 * @author: opensnail
 * @date : 2022-03-03 14:49
 */
public class TaskClientTimeOutException extends BaseTaskException {

    public TaskClientTimeOutException(String message) {
        super(message);
    }

    public TaskClientTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskClientTimeOutException(Throwable cause) {
        super(cause);
    }

    public TaskClientTimeOutException(String message, Object... arguments) {
        super(message, arguments);
    }

    public TaskClientTimeOutException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public TaskClientTimeOutException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public TaskClientTimeOutException(String message, Object argument) {
        super(message, argument);
    }
}
