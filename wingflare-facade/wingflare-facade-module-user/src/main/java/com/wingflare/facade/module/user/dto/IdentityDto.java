package com.wingflare.facade.module.user.dto;


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
	
    private String identityId;
	
	/**
     * 组织机构id
     */
    private String orgId;
	
	/**
     * 部门id
     */
    private String departmentId;
	
	/**
     * 岗位名称
     */
    private String identityName;
	
	/**
     * 职级id
     */
    private String jobLevelId;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private String createUserId;
	
    private String updateUser;
	
    private String updateUserId;
	
    private Integer version;
	
    public IdentityDto setIdentityId(String identityId)
    {
        this.identityId = identityId;
        return this;
    }

    public String getIdentityId()
    {
        return identityId;
    }
	
    public IdentityDto setOrgId(String orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public String getOrgId()
    {
        return orgId;
    }
	
    public IdentityDto setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
        return this;
    }

    public String getDepartmentId()
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
	
    public IdentityDto setJobLevelId(String jobLevelId)
    {
        this.jobLevelId = jobLevelId;
        return this;
    }

    public String getJobLevelId()
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
	
    public IdentityDto setCreateUserId(String createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
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
	
    public IdentityDto setUpdateUserId(String updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
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
