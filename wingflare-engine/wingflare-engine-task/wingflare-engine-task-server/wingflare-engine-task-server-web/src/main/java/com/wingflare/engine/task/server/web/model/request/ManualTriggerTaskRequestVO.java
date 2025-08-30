package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-09-11 22:00:26
 * @since 2.3.0
 */
public class ManualTriggerTaskRequestVO {

    @NotBlank(message = "groupName cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]{1,64}$", message = "Only supports 1~64 characters, including numbers, letters, underscores, and hyphens")
    private String groupName;

    @NotEmpty(message = "retryIds cannot be null")
    private List<Long> retryIds;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Long> getRetryIds() {
        return retryIds;
    }

    public void setRetryIds(List<Long> retryIds) {
        this.retryIds = retryIds;
    }
}
