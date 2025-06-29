package com.wingflare.facade.module.user.bo;


import java.math.BigInteger;

/**
 * <p>
 * 系统用户角色表 业务对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
public class UserRoleBo {

    private BigInteger id;

    private String userId;

    private String systemCode;

    private BigInteger roleId;

    public BigInteger getId() {
        return id;
    }

    public UserRoleBo setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public UserRoleBo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public UserRoleBo setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        return this;
    }

    public BigInteger getRoleId() {
        return roleId;
    }

    public UserRoleBo setRoleId(BigInteger roleId) {
        this.roleId = roleId;
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