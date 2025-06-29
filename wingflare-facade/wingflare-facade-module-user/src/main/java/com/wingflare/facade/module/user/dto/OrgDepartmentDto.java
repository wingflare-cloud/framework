package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Date;

/**
 * 机构部门Dto
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
public class OrgDepartmentDto
{
	
    private BigInteger departmentId;
	
	/**
     * 部门状态
     */
    private Integer state;
	
	/**
     * 组织id
     */
    private BigInteger orgId;
	
	/**
     * 父级部门
     */
    private BigInteger parentDepartmentId;
	
	/**
     * 部门名称
     */
    private String departmentName;
	
	/**
     * 部门基础角色id
     */
    private BigInteger roleId;
	
	/**
     * 部门主要负责人id
     */
    private BigInteger userId;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private String createUserId;
	
    private String updateUser;
	
    private String updateUserId;
	
    private Integer version;
	
    public OrgDepartmentDto setDepartmentId(BigInteger departmentId)
    {
        this.departmentId = departmentId;
        return this;
    }

    public BigInteger getDepartmentId()
    {
        return departmentId;
    }
	
    public OrgDepartmentDto setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public OrgDepartmentDto setOrgId(BigInteger orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
	
    public OrgDepartmentDto setParentDepartmentId(BigInteger parentDepartmentId)
    {
        this.parentDepartmentId = parentDepartmentId;
        return this;
    }

    public BigInteger getParentDepartmentId()
    {
        return parentDepartmentId;
    }
	
    public OrgDepartmentDto setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
        return this;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }
	
    public OrgDepartmentDto setRoleId(BigInteger roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
	
    public OrgDepartmentDto setUserId(BigInteger userId)
    {
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId()
    {
        return userId;
    }
	
    public OrgDepartmentDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public OrgDepartmentDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public OrgDepartmentDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public OrgDepartmentDto setCreateUserId(String createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public OrgDepartmentDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public OrgDepartmentDto setUpdateUserId(String updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public OrgDepartmentDto setVersion(Integer version)
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
            .append("departmentId", getDepartmentId())
            .append("state", getState())
            .append("orgId", getOrgId())
            .append("parentDepartmentId", getParentDepartmentId())
            .append("departmentName", getDepartmentName())
            .append("roleId", getRoleId())
            .append("userId", getUserId())
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
