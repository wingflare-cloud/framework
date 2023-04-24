package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统角色Dto
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
public class RoleDto
{
	
    private String roleId;
	
	/**
     * 角色状态
     */
    private Integer state;
	
	/**
     * 角色分组id
     */
    private String roleGroupId;
	
	/**
     * 父级角色id
     */
    private String parentRoleId;
	
	/**
     * 角色id层级路径
     */
    private String roleIdPath;
	
	/**
     * 角色名称
     */
    private String roleName;
	
	/**
     * 角色备注
     */
    private String roleRemark;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private String createUserId;
	
    private String updateUser;
	
    private String updateUserId;
	
    private Integer version;
	
    public RoleDto setRoleId(String roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
	
    public RoleDto setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public RoleDto setRoleGroupId(String roleGroupId)
    {
        this.roleGroupId = roleGroupId;
        return this;
    }

    public String getRoleGroupId()
    {
        return roleGroupId;
    }
	
    public RoleDto setParentRoleId(String parentRoleId)
    {
        this.parentRoleId = parentRoleId;
        return this;
    }

    public String getParentRoleId()
    {
        return parentRoleId;
    }
	
    public RoleDto setRoleIdPath(String roleIdPath)
    {
        this.roleIdPath = roleIdPath;
        return this;
    }

    public String getRoleIdPath()
    {
        return roleIdPath;
    }
	
    public RoleDto setRoleName(String roleName)
    {
        this.roleName = roleName;
        return this;
    }

    public String getRoleName()
    {
        return roleName;
    }
	
    public RoleDto setRoleRemark(String roleRemark)
    {
        this.roleRemark = roleRemark;
        return this;
    }

    public String getRoleRemark()
    {
        return roleRemark;
    }
	
    public RoleDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public RoleDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public RoleDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public RoleDto setCreateUserId(String createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public RoleDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public RoleDto setUpdateUserId(String updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public RoleDto setVersion(Integer version)
    {
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("state", getState())
            .append("roleGroupId", getRoleGroupId())
            .append("parentRoleId", getParentRoleId())
            .append("roleIdPath", getRoleIdPath())
            .append("roleName", getRoleName())
            .append("roleRemark", getRoleRemark())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .append("updateUser", getUpdateUser())
            .append("updateUserId", getUpdateUserId())
            .append("version", getVersion())
            .toString();
    }
	
}
