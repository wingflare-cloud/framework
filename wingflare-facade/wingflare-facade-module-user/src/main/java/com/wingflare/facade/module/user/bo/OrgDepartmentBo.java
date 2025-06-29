package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import jakarta.validation.constraints.Min;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;

/**
 * 机构部门Bo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
public class OrgDepartmentBo
{

    @Min(message = "org.departmentId.error", value = 1, groups = Update.class)
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

    private Integer version;
    
	public OrgDepartmentBo setDepartmentId(BigInteger departmentId)
    {
        this.departmentId = departmentId;
        return this;
    }

    public BigInteger getDepartmentId()
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
    
	public OrgDepartmentBo setOrgId(BigInteger orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
    
	public OrgDepartmentBo setParentDepartmentId(BigInteger parentDepartmentId)
    {
        this.parentDepartmentId = parentDepartmentId;
        return this;
    }

    public BigInteger getParentDepartmentId()
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
    
	public OrgDepartmentBo setRoleId(BigInteger roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
    
	public OrgDepartmentBo setUserId(BigInteger userId)
    {
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId()
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
