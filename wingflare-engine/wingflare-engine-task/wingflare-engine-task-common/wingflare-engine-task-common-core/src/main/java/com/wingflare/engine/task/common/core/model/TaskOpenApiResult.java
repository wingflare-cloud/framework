package com.wingflare.engine.task.common.core.model;


/**
 * @author: opensnail
 * @date : 2025-07-05 14:07
 */
public class TaskOpenApiResult extends Result<Object> {

    public TaskOpenApiResult(int status, String message, Object data) {
        super(status, message, data);
    }

    public TaskOpenApiResult() {
    }

    public TaskOpenApiResult(Object data) {
        super(data);
    }
}
