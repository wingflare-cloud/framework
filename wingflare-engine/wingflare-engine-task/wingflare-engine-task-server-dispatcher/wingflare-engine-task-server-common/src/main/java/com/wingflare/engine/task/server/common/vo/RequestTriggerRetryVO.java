package com.wingflare.engine.task.server.common.vo;

import jakarta.validation.constraints.NotNull;


@Deprecated
public class RequestTriggerRetryVO {
    @NotNull(message = "id cannot be null")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
