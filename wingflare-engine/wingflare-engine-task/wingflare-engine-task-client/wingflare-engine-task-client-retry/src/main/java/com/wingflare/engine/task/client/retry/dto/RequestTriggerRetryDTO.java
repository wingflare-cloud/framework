package com.wingflare.engine.task.client.retry.dto;


import jakarta.validation.constraints.NotNull;

public class RequestTriggerRetryDTO {
    @NotNull(message = "id cannot be null")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
