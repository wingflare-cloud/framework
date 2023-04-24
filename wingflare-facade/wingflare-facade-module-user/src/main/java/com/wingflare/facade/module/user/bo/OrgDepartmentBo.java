package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 机构部门Bo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
public class OrgDepartmentBo
{

    @NotBlank(message = "org.departmentId.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "org.departmentId.formatError", groups = Update.class)
    private String departmentId;

	/**
     * 部门状态
     */
    private Integer state;

	/**
     * 组织id
     */
    private String orgId;

	/**
     * 父级部门
     */
    private String parentDepartmentId;

	/**
     * 部门名称
     */
    private String departmentName;

	/**
     * 部门基础角色id
     */
    private String roleId;

	/**
     * 部门主要负责人id
     */
    private String userId;

    private Integer version;
    
	public OrgDepartmentBo setDepartmentId(String departmentId)
    {
        this.departmentId = departmentId;
        return this;
    }

    public String getDepartmentId()
    {
        return departmentId;
    }
    
	public OrgDepartmentBo setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public OrgDepartmentBo setOrgId(String orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public String getOrgId()
    {
        return orgId;
    }
    
	public OrgDepartmentBo setParentDepartmentId(String parentDepartmentId)
    {
        this.parentDepartmentId = parentDepartmentId;
        return this;
    }

    public String getParentDepartmentId()
    {
        return parentDepartmentId;
    }
    
	public OrgDepartmentBo setDepartmentName(String departmentName)
    {
        this.departmentName = departmentName;
        return this;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }
    
	public OrgDepartmentBo setRoleId(String roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
    
	public OrgDepartmentBo setUserId(String userId)
    {
        this.userId = userId;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }
    
	public OrgDepartmentBo setVersion(Integer version)
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
            .append("version", getVersion())
            .toString();
    }
	
}
