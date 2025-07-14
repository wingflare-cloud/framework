package com.wingflare.facade.module.user.dto;


import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 岗位身份Dto
 * 
 * @author naizui_ycx
 * @date Sun Apr 02 10:29:44 CST 2023
 */
public class IdentityDTO
{
	
    private BigInteger identityId;
	
	/**
     * 组织机构id
     */
    private BigInteger orgId;
	
	/**
     * 部门id
     */
    private BigInteger departmentId;
	
	/**
     * 岗位名称
     */
    private String identityName;
	
	/**
     * 职级id
     */
    private BigInteger jobLevelId;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private BigInteger createUserId;
	
    private String updateUser;
	
    private BigInteger updateUserId;
	
    private Integer version;
	
    public IdentityDTO setIdentityId(BigInteger identityId)
    {
        this.identityId = identityId;
        return this;
    }

    public BigInteger getIdentityId()
    {
        return identityId;
    }
	
    public IdentityDTO setOrgId(BigInteger orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
	
    public IdentityDTO setDepartmentId(BigInteger departmentId)
    {
        this.departmentId = departmentId;
        return this;
    }

    public BigInteger getDepartmentId()
    {
        return departmentId;
    }
	
    public IdentityDTO setIdentityName(String identityName)
    {
        this.identityName = identityName;
        return this;
    }

    public String getIdentityName()
    {
        return identityName;
    }
	
    public IdentityDTO setJobLevelId(BigInteger jobLevelId)
    {
        this.jobLevelId = jobLevelId;
        return this;
    }

    public BigInteger getJobLevelId()
    {
        return jobLevelId;
    }
	
    public IdentityDTO setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public IdentityDTO setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public IdentityDTO setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public IdentityDTO setCreateUserId(BigInteger createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public IdentityDTO setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public IdentityDTO setUpdateUserId(BigInteger updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public IdentityDTO setVersion(Integer version)
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
            .append("identityId", getIdentityId())
            .append("orgId", getOrgId())
            .append("departmentId", getDepartmentId())
            .append("identityName", getIdentityName())
            .append("jobLevelId", getJobLevelId())
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
