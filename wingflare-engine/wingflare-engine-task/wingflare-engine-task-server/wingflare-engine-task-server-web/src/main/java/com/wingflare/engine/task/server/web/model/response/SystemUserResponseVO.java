package com.wingflare.engine.task.server.web.model.response;


import java.time.LocalDateTime;
import java.util.List;

/**
 * @author opensnail
 * @date 2022-03-05
 * @since 2.0
 */
public class SystemUserResponseVO {

    private Long id;

    private String username;

    private Integer role;

    private List<String> groupNameList;

    private List<NamespaceResponseVO> namespaceIds;

    private List<PermissionsResponseVO> permissions;

    private String token;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    private String mode;

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

    public List<String> getGroupNameList() {
        return groupNameList;
    }

    public void setGroupNameList(List<String> groupNameList) {
        this.groupNameList = groupNameList;
    }

    public List<NamespaceResponseVO> getNamespaceIds() {
        return namespaceIds;
    }

    public void setNamespaceIds(List<NamespaceResponseVO> namespaceIds) {
        this.namespaceIds = namespaceIds;
    }

    public List<PermissionsResponseVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionsResponseVO> permissions) {
        this.permissions = permissions;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
