package com.wingflare.facade.module.task.request.base;


import jakarta.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-25
 */
public class TriggerRetryRequest {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
