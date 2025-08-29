package com.wingflare.engine.task.common.model.request;

import jakarta.validation.constraints.NotBlank;

/**
 * 生成idempotentId模型
 *
 * @auther opensnail
 * @date 2022/03/25 10:06
 */
public class RetryArgsDeserializeRequest {
    @NotBlank(message = "namespace cannot be null")
    private String namespaceId;
    @NotBlank(message = "group cannot be null")
    private String group;
    @NotBlank(message = "scene cannot be null")
    private String scene;
    @NotBlank(message = "parameters cannot be null")
    private String argsStr;
    @NotBlank(message = "executorName cannot be null")
    private String executorName;
    @NotBlank(message = "serializerName cannot be null")
    private String serializerName;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getArgsStr() {
        return argsStr;
    }

    public void setArgsStr(String argsStr) {
        this.argsStr = argsStr;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getSerializerName() {
        return serializerName;
    }

    public void setSerializerName(String serializerName) {
        this.serializerName = serializerName;
    }
}
