package com.wingflare.engine.task.common.core.exception;

public class TaskInnerExecutorException extends BaseTaskException {
    public TaskInnerExecutorException(String message) {
        super(message);
    }

    public TaskInnerExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskInnerExecutorException(Throwable cause) {
        super(cause);
    }

    public TaskInnerExecutorException(String message, Object... arguments) {
        super(message, arguments);
    }

    public TaskInnerExecutorException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public TaskInnerExecutorException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public TaskInnerExecutorException(String message, Object argument) {
        super(message, argument);
    }
}
