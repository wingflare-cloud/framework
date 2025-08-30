package com.wingflare.engine.task.server.common.vo;

import jakarta.validation.constraints.NotNull;


@Deprecated
public class RequestUpdateRetryStatusVO {
    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "retryStatus cannot be null")
    private Integer retryStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Integer retryStatus) {
        this.retryStatus = retryStatus;
    }
}
