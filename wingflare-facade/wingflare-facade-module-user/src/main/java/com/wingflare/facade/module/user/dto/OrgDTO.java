package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * 组织机构Dto
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
public class OrgDTO
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
	
    public OrgDTO setOrgId(BigInteger orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
	
    public OrgDTO setParentOrgId(BigInteger parentOrgId)
    {
        this.parentOrgId = parentOrgId;
        return this;
    }

    public BigInteger getParentOrgId()
    {
        return parentOrgId;
    }
	
    public OrgDTO setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public OrgDTO setOrgType(String orgType)
    {
        this.orgType = orgType;
        return this;
    }

    public String getOrgType()
    {
        return orgType;
    }
	
    public OrgDTO setOrgName(String orgName)
    {
        this.orgName = orgName;
        return this;
    }

    public String getOrgName()
    {
        return orgName;
    }
	
    public OrgDTO setOrgPhone(String orgPhone)
    {
        this.orgPhone = orgPhone;
        return this;
    }

    public String getOrgPhone()
    {
        return orgPhone;
    }
	
    public OrgDTO setOrgAddress(String orgAddress)
    {
        this.orgAddress = orgAddress;
        return this;
    }

    public String getOrgAddress()
    {
        return orgAddress;
    }
	
    public OrgDTO setRoleId(BigInteger roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
	
    public OrgDTO setUserId(BigInteger userId)
    {
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId()
    {
        return userId;
    }
	
    public OrgDTO setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public OrgDTO setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public OrgDTO setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public OrgDTO setCreateUserId(BigInteger createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public OrgDTO setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public OrgDTO setUpdateUserId(BigInteger updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public OrgDTO setVersion(Integer version)
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
        return "OrgDTO{" +
                "orgId=" + orgId +
                ", parentOrgId=" + parentOrgId +
                ", state=" + state +
                ", orgType='" + orgType + '\'' +
                ", orgName='" + orgName + '\'' +
                ", orgPhone='" + orgPhone + '\'' +
                ", orgAddress='" + orgAddress + '\'' +
                ", roleId=" + roleId +
                ", userId=" + userId +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", createUser='" + createUser + '\'' +
                ", createUserId=" + createUserId +
                ", updateUser='" + updateUser + '\'' +
                ", updateUserId=" + updateUserId +
                ", version=" + version +
                '}';
    }

}
