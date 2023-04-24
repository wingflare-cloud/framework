package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 组织机构Bo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
public class OrgBo
{

    @NotBlank(message = "org.orgId.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "org.orgId.formatError", groups = Update.class)
    private String orgId;

	/**
     * 父级机构id
     */
    private String parentOrgId;

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
    private String roleId;

	/**
     * 机构主要负责人id
     */
    private String userId;

    private Integer version;
    
	public OrgBo setOrgId(String orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public String getOrgId()
    {
        return orgId;
    }
    
	public OrgBo setParentOrgId(String parentOrgId)
    {
        this.parentOrgId = parentOrgId;
        return this;
    }

    public String getParentOrgId()
    {
        return parentOrgId;
    }
    
	public OrgBo setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public OrgBo setOrgType(String orgType)
    {
        this.orgType = orgType;
        return this;
    }

    public String getOrgType()
    {
        return orgType;
    }
    
	public OrgBo setOrgName(String orgName)
    {
        this.orgName = orgName;
        return this;
    }

    public String getOrgName()
    {
        return orgName;
    }
    
	public OrgBo setOrgPhone(String orgPhone)
    {
        this.orgPhone = orgPhone;
        return this;
    }

    public String getOrgPhone()
    {
        return orgPhone;
    }
    
	public OrgBo setOrgAddress(String orgAddress)
    {
        this.orgAddress = orgAddress;
        return this;
    }

    public String getOrgAddress()
    {
        return orgAddress;
    }
    
	public OrgBo setRoleId(String roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
    
	public OrgBo setUserId(String userId)
    {
        this.userId = userId;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }
    
	public OrgBo setVersion(Integer version)
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
            .append("version", getVersion())
            .toString();
    }
	
}
