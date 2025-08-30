package com.wingflare.engine.task.common.core.exception;

/**
 * @author: opensnail
 * @date : 2022-03-03 14:49
 */
public class TaskRemotingTimeOutException extends BaseTaskException {

    public TaskRemotingTimeOutException(String message) {
        super(message);
    }

    public TaskRemotingTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskRemotingTimeOutException(Throwable cause) {
        super(cause);
    }

    public TaskRemotingTimeOutException(String message, Object... arguments) {
        super(message, arguments);
    }

    public TaskRemotingTimeOutException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public TaskRemotingTimeOutException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public TaskRemotingTimeOutException(String message, Object argument) {
        super(message, argument);
    }
}
