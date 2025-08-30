package com.wingflare.engine.task.common.core.exception;


/**
 * @author: opensnail
 * @date : 2024-04-16
 * @since : 1.0.0
 */
public class TaskAuthenticationException extends BaseTaskException {
    private final Integer errorCode = 5001;

    public TaskAuthenticationException(final String message) {
        super(message);
    }

    public TaskAuthenticationException(String message, Object... arguments) {
        super(message, arguments);
    }

    public TaskAuthenticationException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public TaskAuthenticationException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public TaskAuthenticationException(String message, Object argument) {
        super(message, argument);
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
