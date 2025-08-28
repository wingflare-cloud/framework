package com.wingflare.facade.module.task.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 服务端调度重试入参
 *
 * @auther opensnail
 * @date 2022/03/25 10:06
 */
public class StopRetryRequest {
    @NotBlank(message = "namespaceId cannot be null")
    private String namespaceId;
    @NotBlank(message = "group cannot be null")
    private String groupName;
    @NotBlank(message = "scene cannot be null")
    private String scene;
    @NotNull(message = "retryTaskId cannot be null")
    private Long retryTaskId;
    @NotNull(message = "retryId cannot be null")
    private Long retryId;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Long getRetryTaskId() {
        return retryTaskId;
    }

    public void setRetryTaskId(Long retryTaskId) {
        this.retryTaskId = retryTaskId;
    }

    public Long getRetryId() {
        return retryId;
    }

    public void setRetryId(Long retryId) {
        this.retryId = retryId;
    }
}
