package com.wingflare.engine.task.server.common.exception;


import com.wingflare.engine.task.common.core.exception.BaseSnailJobException;

/**
 * 服务端异常类
 *
 * @author: opensnail
 * @date : 2021-11-19 15:01
 */
public class SnailJobServerException extends BaseSnailJobException {

    public SnailJobServerException(String message) {
        super(message);
    }

    public SnailJobServerException(String message, Object... arguments) {
        super(message, arguments);
    }
}
