package com.wingflare.engine.task.server.common.exception;


import com.wingflare.engine.task.common.core.exception.BaseTaskException;

/**
 * 服务端异常类
 *
 * @author: opensnail
 * @date : 2021-11-19 15:01
 */
public class TaskServerException extends BaseTaskException {

    public TaskServerException(String message) {
        super(message);
    }

    public TaskServerException(String message, Object... arguments) {
        super(message, arguments);
    }
}
