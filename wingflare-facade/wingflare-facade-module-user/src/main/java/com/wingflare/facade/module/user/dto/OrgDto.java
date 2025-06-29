package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Date;

/**
 * 组织机构Dto
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
public class OrgDto
{
	
    private BigInteger orgId;
	
	/**
     * 父级机构id
     */
    private BigInteger parentOrgId;
	
	/**
     * 组织状态
     */
    private Integer state;
	
	/**
     * 组织类型
     */
    private String orgType;
	
	/**
     * 组织名称
     */
    private String orgName;
	
	/**
     * 组织手机或固话号
     */
    private String orgPhone;
	
	/**
     * 详细地址
     */
    private String orgAddress;
	
	/**
     * 机构基础角色id
     */
    private BigInteger roleId;
	
	/**
     * 机构主要负责人id
     */
    private BigInteger userId;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private BigInteger createUserId;
	
    private String updateUser;
	
    private BigInteger updateUserId;
	
    private Integer version;
	
    public OrgDto setOrgId(BigInteger orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
	
    public OrgDto setParentOrgId(BigInteger parentOrgId)
    {
        this.parentOrgId = parentOrgId;
        return this;
    }

    public BigInteger getParentOrgId()
    {
        return parentOrgId;
    }
	
    public OrgDto setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public OrgDto setOrgType(String orgType)
    {
        this.orgType = orgType;
        return this;
    }

    public String getOrgType()
    {
        return orgType;
    }
	
    public OrgDto setOrgName(String orgName)
    {
        this.orgName = orgName;
        return this;
    }

    public String getOrgName()
    {
        return orgName;
    }
	
    public OrgDto setOrgPhone(String orgPhone)
    {
        this.orgPhone = orgPhone;
        return this;
    }

    public String getOrgPhone()
    {
        return orgPhone;
    }
	
    public OrgDto setOrgAddress(String orgAddress)
    {
        this.orgAddress = orgAddress;
        return this;
    }

    public String getOrgAddress()
    {
        return orgAddress;
    }
	
    public OrgDto setRoleId(BigInteger roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
	
    public OrgDto setUserId(BigInteger userId)
    {
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId()
    {
        return userId;
    }
	
    public OrgDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public OrgDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public OrgDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public OrgDto setCreateUserId(BigInteger createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public OrgDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public OrgDto setUpdateUserId(BigInteger updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public OrgDto setVersion(Integer version)
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
            .append("orgId", getOrgId())
            .append("parentOrgId", getParentOrgId())
            .append("state", getState())
            .append("orgType", getOrgType())
            .append("orgName", getOrgName())
            .append("orgPhone", getOrgPhone())
            .append("orgAddress", getOrgAddress())
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
