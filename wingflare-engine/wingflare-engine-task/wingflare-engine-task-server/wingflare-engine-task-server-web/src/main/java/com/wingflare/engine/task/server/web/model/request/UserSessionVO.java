package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.web.annotation.RoleEnum;

import java.util.List;
import java.util.Objects;

/**
 * @author xiaowoniu
 * @date 2023-11-22 22:42:26
 * @since 2.5.0
 */
public final class UserSessionVO {

    private Long id;

    private String username;

    private Integer role;

    private String namespaceId;

    private List<String> groupNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public List<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }

    /**
     * 是否是管理员用户
     */
    public boolean isAdmin() {
        return Objects.equals(this.role, RoleEnum.ADMIN.getRoleId());
    }

    /**
     * 是否是普通用户
     */
    public boolean isUser() {
        return Objects.equals(this.role, RoleEnum.USER.getRoleId());
    }


}
