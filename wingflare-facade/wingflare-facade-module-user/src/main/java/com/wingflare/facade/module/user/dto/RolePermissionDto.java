package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统角色权限Dto
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
public class RolePermissionDto
{
	
    private String id;
	
    private String roleId;
	
    private String systemCode;
	
    private String permissionCode;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    private String createUser;
	
    private String createUserId;
	
    public RolePermissionDto setId(String id)
    {
        this.id = id;
        return this;
    }

    public String getId()
    {
        return id;
    }
	
    public RolePermissionDto setRoleId(String roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
	
    public RolePermissionDto setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public RolePermissionDto setPermissionCode(String permissionCode)
    {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getPermissionCode()
    {
        return permissionCode;
    }
	
    public RolePermissionDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public RolePermissionDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public RolePermissionDto setCreateUserId(String createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("systemCode", getSystemCode())
            .append("permissionCode", getPermissionCode())
            .append("createdTime", getCreatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .toString();
    }
	
}
