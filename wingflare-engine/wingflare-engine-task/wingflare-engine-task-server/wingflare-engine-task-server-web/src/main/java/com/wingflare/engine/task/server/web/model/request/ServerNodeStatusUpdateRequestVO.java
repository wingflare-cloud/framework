package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotNull;

/**
 * @author opensnail
 * @date 2023-10-15 16:06:20
 * @since 2.4.0
 */
public class ServerNodeStatusUpdateRequestVO {

    @NotNull(message = "id cannot be null")
    private Long id;

    @NotNull(message = "serverNodeStatus cannot be null")
    private Integer serverNodeStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getServerNodeStatus() {
        return serverNodeStatus;
    }

    public void setServerNodeStatus(Integer serverNodeStatus) {
        this.serverNodeStatus = serverNodeStatus;
    }
}
