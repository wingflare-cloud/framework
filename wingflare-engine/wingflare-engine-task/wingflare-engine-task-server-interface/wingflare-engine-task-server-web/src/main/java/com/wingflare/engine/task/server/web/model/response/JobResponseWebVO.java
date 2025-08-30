package com.wingflare.engine.task.server.web.model.response;

import com.wingflare.engine.task.common.model.response.base.JobResponse;


/**
 * @author opensnail
 * @date 2023-10-11 22:30:00
 * @since 2.4.0
 */
public class JobResponseWebVO extends JobResponse {

    /**
     * 负责人名称
     */
    private String ownerName;

    /**
     * 负责人id
     */
    private Long ownerId;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
