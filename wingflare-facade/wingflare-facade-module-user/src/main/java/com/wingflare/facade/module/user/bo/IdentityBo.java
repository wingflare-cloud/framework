package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.Regexp;
import com.wingflare.lib.core.validation.Update;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 岗位身份Bo
 * 
 * @author naizui_ycx
 * @date Sun Apr 02 10:29:44 CST 2023
 */
public class IdentityBo
{

    @NotBlank(message = "org.identityId.notBlank", groups = Update.class)
    @Pattern(regexp = Regexp.SNOWFLAKE_ID, message = "org.identityId.formatError", groups = Update.class)
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

    private Integer version;
    
	public IdentityBo setIdentityId(String identityId)
    {
        this.identityId = identityId;
        return this;
    }

    public String getIdentityId()
    {
        return identityId;
    }
    
	public IdentityBo setOrgId(String orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public String getOrgId()
    {
        return orgId;
    }
    
	public IdentityBo setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
        return this;
    }

    public String getDepartmentId()
    {
        return departmentId;
    }
    
	public IdentityBo setIdentityName(String identityName)
    {
        this.identityName = identityName;
        return this;
    }

    public String getIdentityName()
    {
        return identityName;
    }
    
	public IdentityBo setJobLevelId(String jobLevelId)
    {
        this.jobLevelId = jobLevelId;
        return this;
    }

    public String getJobLevelId()
    {
        return jobLevelId;
    }
    
	public IdentityBo setVersion(Integer version)
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
            .append("version", getVersion())
            .toString();
    }
	
}
