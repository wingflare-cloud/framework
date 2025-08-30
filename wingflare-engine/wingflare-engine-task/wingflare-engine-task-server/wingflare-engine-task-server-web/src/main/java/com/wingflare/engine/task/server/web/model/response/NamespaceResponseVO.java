package com.wingflare.engine.task.server.web.model.response;


import java.time.LocalDateTime;

/**
 * @author: xiaowoniu
 * @date : 2023-11-21 15:39
 * @since : 2.5.0
 */
public class NamespaceResponseVO {

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 唯一id
     */
    private String uniqueId;

    /**
     * 创建时间
     */
    private LocalDateTime createDt;

    /**
     * 修改时间
     */
    private LocalDateTime updateDt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public LocalDateTime getCreateDt() {
        return createDt;
    }

    public void setCreateDt(LocalDateTime createDt) {
        this.createDt = createDt;
    }

    public LocalDateTime getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(LocalDateTime updateDt) {
        this.updateDt = updateDt;
    }
}
