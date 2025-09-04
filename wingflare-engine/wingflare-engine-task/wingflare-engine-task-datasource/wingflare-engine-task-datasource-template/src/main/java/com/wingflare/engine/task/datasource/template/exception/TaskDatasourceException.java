package com.wingflare.engine.task.datasource.template.exception;

import com.wingflare.engine.task.common.core.exception.BaseTaskException;

/**
 * 数据源模块异常类
 *
 * @author: opensnail
 * @date : 2021-11-19 15:01
 */
public class TaskDatasourceException extends BaseTaskException {

    public TaskDatasourceException(String message) {
        super(message);
    }

    public TaskDatasourceException(String message, Object... arguments) {
        super(message, arguments);
    }
}
