package com.wingflare.engine.task.common.core.model;


/**
 * @author: opensnail
 * @date : 2025-07-05 14:07
 */
public class SnailJobOpenApiResult extends Result<Object> {

    public SnailJobOpenApiResult(int status, String message, Object data) {
        super(status, message, data);
    }

    public SnailJobOpenApiResult() {
    }

    public SnailJobOpenApiResult(Object data) {
        super(data);
    }
}
