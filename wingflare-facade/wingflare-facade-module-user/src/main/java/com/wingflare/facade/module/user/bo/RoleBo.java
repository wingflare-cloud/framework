package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 系统角色Bo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
public class RoleBo
{

    @NotBlank(message = "user.roleId.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "user.roleId.formatError", groups = Update.class)
    private String roleId;

	/**
     * 角色状态
     */
    private Integer state;

	/**
     * 角色分组id
     */
    private String roleGroupId;

	/**
     * 父级角色id
     */
    private String parentRoleId;

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
    
	public RoleBo setRoleId(String roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
    
	public RoleBo setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public RoleBo setRoleGroupId(String roleGroupId)
    {
        this.roleGroupId = roleGroupId;
        return this;
    }

    public String getRoleGroupId()
    {
        return roleGroupId;
    }
    
	public RoleBo setParentRoleId(String parentRoleId)
    {
        this.parentRoleId = parentRoleId;
        return this;
    }

    public String getParentRoleId()
    {
        return parentRoleId;
    }
    
	public RoleBo setRoleIdPath(String roleIdPath)
    {
        this.roleIdPath = roleIdPath;
        return this;
    }

    public String getRoleIdPath()
    {
        return roleIdPath;
    }
    
	public RoleBo setRoleName(String roleName)
    {
        this.roleName = roleName;
        return this;
    }

    public String getRoleName()
    {
        return roleName;
    }
    
	public RoleBo setRoleRemark(String roleRemark)
    {
        this.roleRemark = roleRemark;
        return this;
    }

    public String getRoleRemark()
    {
        return roleRemark;
    }
    
	public RoleBo setVersion(Integer version)
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
