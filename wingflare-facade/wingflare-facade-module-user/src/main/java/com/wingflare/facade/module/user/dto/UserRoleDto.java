package com.wingflare.facade.module.user.dto;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 系统用户角色表 输出对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
public class UserRoleDto {

    private String id;

    private String userId;

    private String systemCode;

    private String roleId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private String createUser;

    private String createUserId;

    public String getId() {
        return id;
    }

    public UserRoleDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserRoleDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public UserRoleDto setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public UserRoleDto setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public UserRoleDto setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public UserRoleDto setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public UserRoleDto setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        return this;
    }

	@Override
	public String toString() {
        return "UserRoleDo{" +
        "id = " + id +
        ", userId = " + userId +
        ", systemCode = " + systemCode +
        ", roleId = " + roleId +
        "}";
    }

}