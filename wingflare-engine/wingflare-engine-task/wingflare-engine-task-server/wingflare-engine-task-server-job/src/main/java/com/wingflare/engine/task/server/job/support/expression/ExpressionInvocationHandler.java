package com.wingflare.engine.task.server.job.support.expression;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.server.common.exception.TaskServerException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaowoniu
 * @date 2023-12-30 17:22:51
 * @since 2.6.0
 */
public class ExpressionInvocationHandler implements InvocationHandler {

    private final Object expressionEngine;

    public ExpressionInvocationHandler(Object expressionEngine) {
        this.expressionEngine = expressionEngine;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {

        try {
            Object[] expressionParams = (Object[]) args[1];
            String params = (String) expressionParams[0];
            Map<String, Object> contextMap = new HashMap<>();
            if (StrUtil.isNotBlank(params)) {
                try {
                    contextMap = JsonUtil.parseHashMap(params);
                } catch (Exception e) {
                    contextMap.put(SystemConstants.SINGLE_PARAM, params);
                }
            }

            args[1] = new Object[]{contextMap};
            return method.invoke(expressionEngine, args);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            throw new TaskServerException(targetException.getMessage());
        } catch (Exception e) {
            throw new TaskServerException("Expression execution failed", e);
        }
    }
}
