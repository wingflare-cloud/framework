package com.wingflare.engine.task.client.retry.core.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestTriggerRetryDTO {
    @NotNull(message = "id cannot be null")
    private Long id;
}
