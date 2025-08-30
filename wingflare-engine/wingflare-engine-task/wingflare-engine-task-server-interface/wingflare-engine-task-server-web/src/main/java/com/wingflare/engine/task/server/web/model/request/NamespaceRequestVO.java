package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotBlank;

/**
 * @author: xiaowoniu
 * @date : 2023-11-21 15:15
 * @since : 2.5.0
 */
public class NamespaceRequestVO {

    private Long id;

    /**
     * 命名空间唯一标识
     */
    String uniqueId;

    /**
     * 名称
     */
    @NotBlank(message = "name cannot be empty")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
