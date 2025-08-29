package com.wingflare.engine.task.common.log.lang;

import cn.hutool.core.exceptions.UtilException;

import java.io.Serial;
import java.io.Serializable;

/**
 * 通过StackTrace方式获取调用者。此方式效率最低，不推荐使用
 *
 * @author wodeyangzipingpingwuqi
 */
public class StackTraceCaller implements Caller, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int OFFSET = 2;

    @Override
    public Class<?> getCallerCaller() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (OFFSET + 2 >= stackTrace.length) {
            return null;
        }
        final String className = stackTrace[OFFSET + 2].getClassName();
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new UtilException(e, "[{}] not found!", className);
        }
    }

}
