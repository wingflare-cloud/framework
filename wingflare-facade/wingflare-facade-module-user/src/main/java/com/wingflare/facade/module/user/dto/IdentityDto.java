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
public class IdentityDto
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
	
    public IdentityDto setIdentityId(BigInteger identityId)
    {
        this.identityId = identityId;
        return this;
    }

    public BigInteger getIdentityId()
    {
        return identityId;
    }
	
    public IdentityDto setOrgId(BigInteger orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
	
    public IdentityDto setDepartmentId(BigInteger departmentId)
    {
        this.departmentId = departmentId;
        return this;
    }

    public BigInteger getDepartmentId()
    {
        return departmentId;
    }
	
    public IdentityDto setIdentityName(String identityName)
    {
        this.identityName = identityName;
        return this;
    }

    public String getIdentityName()
    {
        return identityName;
    }
	
    public IdentityDto setJobLevelId(BigInteger jobLevelId)
    {
        this.jobLevelId = jobLevelId;
        return this;
    }

    public BigInteger getJobLevelId()
    {
        return jobLevelId;
    }
	
    public IdentityDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public IdentityDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public IdentityDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public IdentityDto setCreateUserId(BigInteger createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public IdentityDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public IdentityDto setUpdateUserId(BigInteger updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public IdentityDto setVersion(Integer version)
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
