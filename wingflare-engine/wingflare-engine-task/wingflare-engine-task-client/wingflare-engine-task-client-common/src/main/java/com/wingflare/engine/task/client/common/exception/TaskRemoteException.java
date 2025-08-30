package com.wingflare.engine.task.client.common.exception;

import com.wingflare.engine.task.common.core.exception.BaseTaskException;

/**
 * @author: opensnail
 * @date : 2022-03-03 14:49
 */
public class TaskRemoteException extends BaseTaskException {

    public TaskRemoteException(String message) {
        super(message);
    }

    public TaskRemoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskRemoteException(Throwable cause) {
        super(cause);
    }

    public TaskRemoteException(String message, Object... arguments) {
        super(message, arguments);
    }

    public TaskRemoteException(String message, Object[] arguments, Throwable cause) {
        super(message, arguments, cause);
    }

    public TaskRemoteException(String message, Object argument, Throwable cause) {
        super(message, argument, cause);
    }

    public TaskRemoteException(String message, Object argument) {
        super(message, argument);
    }
}
