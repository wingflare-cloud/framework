package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统角色分组表Dto
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
public class RoleGroupDto
{
	
    private String roleGroupId;
	
	/**
     * 角色分组名称
     */
    private String groupName;
	
	/**
     * 角色分组备注
     */
    private String groupRemark;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private String createUserId;
	
    private String updateUser;
	
    private String updateUserId;
	
    private Integer version;
	
    public RoleGroupDto setRoleGroupId(String roleGroupId)
    {
        this.roleGroupId = roleGroupId;
        return this;
    }

    public String getRoleGroupId()
    {
        return roleGroupId;
    }
	
    public RoleGroupDto setGroupName(String groupName)
    {
        this.groupName = groupName;
        return this;
    }

    public String getGroupName()
    {
        return groupName;
    }
	
    public RoleGroupDto setGroupRemark(String groupRemark)
    {
        this.groupRemark = groupRemark;
        return this;
    }

    public String getGroupRemark()
    {
        return groupRemark;
    }
	
    public RoleGroupDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public RoleGroupDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public RoleGroupDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public RoleGroupDto setCreateUserId(String createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public RoleGroupDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public RoleGroupDto setUpdateUserId(String updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public RoleGroupDto setVersion(Integer version)
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
            .append("roleGroupId", getRoleGroupId())
            .append("groupName", getGroupName())
            .append("groupRemark", getGroupRemark())
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
