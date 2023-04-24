package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 系统角色权限Bo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
public class RolePermissionBo
{

    @NotBlank(message = "user.id.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "user.id.formatError", groups = Update.class)
    private String id;

    private String roleId;

    private String systemCode;

    private String permissionCode;
    
	public RolePermissionBo setId(String id)
    {
        this.id = id;
        return this;
    }

    public String getId()
    {
        return id;
    }
    
	public RolePermissionBo setRoleId(String roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
    
	public RolePermissionBo setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
    
	public RolePermissionBo setPermissionCode(String permissionCode)
    {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getPermissionCode()
    {
        return permissionCode;
    }

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("systemCode", getSystemCode())
            .append("permissionCode", getPermissionCode())
            .toString();
    }
	
}
