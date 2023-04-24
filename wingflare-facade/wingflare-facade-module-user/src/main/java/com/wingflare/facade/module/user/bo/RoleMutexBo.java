package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 系统角色互斥关系Bo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
public class RoleMutexBo
{

    @NotBlank(message = "user.id.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "user.id.formatError", groups = Update.class)
    private String id;

    private String roleId;

    private String mutexRoleId;
    
	public RoleMutexBo setId(String id)
    {
        this.id = id;
        return this;
    }

    public String getId()
    {
        return id;
    }
    
	public RoleMutexBo setRoleId(String roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
    
	public RoleMutexBo setMutexRoleId(String mutexRoleId)
    {
        this.mutexRoleId = mutexRoleId;
        return this;
    }

    public String getMutexRoleId()
    {
        return mutexRoleId;
    }

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("mutexRoleId", getMutexRoleId())
            .toString();
    }
	
}
