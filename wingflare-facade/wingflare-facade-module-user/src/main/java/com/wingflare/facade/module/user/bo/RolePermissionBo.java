package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import jakarta.validation.constraints.Min;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;

/**
 * 系统角色权限Bo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
public class RolePermissionBo
{

    @Min(message = "user.id.error", value = 1, groups = Update.class)
    private BigInteger id;

    private BigInteger roleId;

    private String systemCode;

    private String permissionCode;
    
	public RolePermissionBo setId(BigInteger id)
    {
        this.id = id;
        return this;
    }

    public BigInteger getId()
    {
        return id;
    }
    
	public RolePermissionBo setRoleId(BigInteger roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
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
