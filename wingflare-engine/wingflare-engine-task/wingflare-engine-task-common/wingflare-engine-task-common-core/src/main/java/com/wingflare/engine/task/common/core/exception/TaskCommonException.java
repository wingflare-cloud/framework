package com.wingflare.engine.task.common.core.exception;


/**
 * 异常信息
 *
 * @author: opensnail
 * @date : 2021-11-19 15:01
 */
public class TaskCommonException extends BaseTaskException {

    public TaskCommonException(String message) {
        super(message);
    }

    public TaskCommonException(String message, Object... arguments) {
        super(message, arguments);
    }

    public TaskCommonException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public TaskCommonException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public TaskCommonException(String message, Object argument) {
        super(message, argument);
    }
}
