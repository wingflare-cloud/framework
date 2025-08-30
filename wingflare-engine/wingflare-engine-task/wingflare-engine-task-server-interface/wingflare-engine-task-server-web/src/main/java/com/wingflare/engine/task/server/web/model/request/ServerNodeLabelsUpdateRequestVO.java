package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author opensnail
 * @date 2023-10-15 16:06:20
 * @since 2.4.0
 */
public class ServerNodeLabelsUpdateRequestVO {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "labels cannot be null")
    @Size(max = 512, message = "labels length must be less than or equal to 512")
    private String labels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
