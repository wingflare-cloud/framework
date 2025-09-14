package com.wingflare.facade.module.user.bo;


import com.wingflare.api.core.validate.Update;
import jakarta.validation.constraints.Min;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;

/**
 * 系统角色Bo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
public class RoleBO
{

    @Min(message = "user.roleId.error", value = 1, groups = Update.class)
    private BigInteger roleId;

	/**
     * 角色状态
     */
    private Integer state;

	/**
     * 角色分组id
     */
    private BigInteger roleGroupId;

	/**
     * 父级角色id
     */
    private BigInteger parentRoleId;

	/**
     * 角色id层级路径
     */
    private String roleIdPath;

	/**
     * 角色名称
     */
    private String roleName;

	/**
     * 角色备注
     */
    private String roleRemark;

    private Integer version;
    
	public RoleBO setRoleId(BigInteger roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
    
	public RoleBO setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public RoleBO setRoleGroupId(BigInteger roleGroupId)
    {
        this.roleGroupId = roleGroupId;
        return this;
    }

    public BigInteger getRoleGroupId()
    {
        return roleGroupId;
    }
    
	public RoleBO setParentRoleId(BigInteger parentRoleId)
    {
        this.parentRoleId = parentRoleId;
        return this;
    }

    public BigInteger getParentRoleId()
    {
        return parentRoleId;
    }
    
	public RoleBO setRoleIdPath(String roleIdPath)
    {
        this.roleIdPath = roleIdPath;
        return this;
    }

    public String getRoleIdPath()
    {
        return roleIdPath;
    }
    
	public RoleBO setRoleName(String roleName)
    {
        this.roleName = roleName;
        return this;
    }

    public String getRoleName()
    {
        return roleName;
    }
    
	public RoleBO setRoleRemark(String roleRemark)
    {
        this.roleRemark = roleRemark;
        return this;
    }

    public String getRoleRemark()
    {
        return roleRemark;
    }
    
	public RoleBO setVersion(Integer version)
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
            .append("roleId", getRoleId())
            .append("state", getState())
            .append("roleGroupId", getRoleGroupId())
            .append("parentRoleId", getParentRoleId())
            .append("roleIdPath", getRoleIdPath())
            .append("roleName", getRoleName())
            .append("roleRemark", getRoleRemark())
            .append("version", getVersion())
            .toString();
    }
	
}
