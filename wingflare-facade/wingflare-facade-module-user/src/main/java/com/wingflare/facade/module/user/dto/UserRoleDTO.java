package com.wingflare.facade.module.user.dto;


import java.math.BigInteger;
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
public class UserRoleDTO {

    private BigInteger id;

    private BigInteger userId;

    private String systemCode;

    private BigInteger roleId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    private String createUser;

    private BigInteger createUserId;

    public BigInteger getId() {
        return id;
    }

    public UserRoleDTO setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public UserRoleDTO setUserId(BigInteger userId) {
        this.userId = userId;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public UserRoleDTO setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        return this;
    }

    public BigInteger getRoleId() {
        return roleId;
    }

    public UserRoleDTO setRoleId(BigInteger roleId) {
        this.roleId = roleId;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public UserRoleDTO setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public UserRoleDTO setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public BigInteger getCreateUserId() {
        return createUserId;
    }

    public UserRoleDTO setCreateUserId(BigInteger createUserId) {
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