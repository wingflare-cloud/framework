package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 * @author opensnail
 * @date 2022-03-05
 * @since 2.0
 */
public class SystemUserRequestVO {

    @NotNull(groups = {PutMapping.class})
    private Long id;

    @NotBlank(message = "Username cannot be empty", groups = PostMapping.class)
    private String username;

    @NotBlank(message = "Password cannot be empty", groups = PostMapping.class)
    private String password;

    @NotNull(groups = {PutMapping.class, PostMapping.class})
    private Integer role;

    private List<UserPermissionRequestVO> permissions;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public List<UserPermissionRequestVO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UserPermissionRequestVO> permissions) {
        this.permissions = permissions;
    }
}
