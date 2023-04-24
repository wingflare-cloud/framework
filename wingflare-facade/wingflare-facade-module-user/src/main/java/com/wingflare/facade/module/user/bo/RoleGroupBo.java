package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 系统角色分组表Bo
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
public class RoleGroupBo
{

    @NotBlank(message = "user.roleGroupId.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "user.roleGroupId.formatError", groups = Update.class)
    private String roleGroupId;

	/**
     * 角色分组名称
     */
    private String groupName;

	/**
     * 角色分组备注
     */
    private String groupRemark;

    private Integer version;
    
	public RoleGroupBo setRoleGroupId(String roleGroupId)
    {
        this.roleGroupId = roleGroupId;
        return this;
    }

    public String getRoleGroupId()
    {
        return roleGroupId;
    }
    
	public RoleGroupBo setGroupName(String groupName)
    {
        this.groupName = groupName;
        return this;
    }

    public String getGroupName()
    {
        return groupName;
    }
    
	public RoleGroupBo setGroupRemark(String groupRemark)
    {
        this.groupRemark = groupRemark;
        return this;
    }

    public String getGroupRemark()
    {
        return groupRemark;
    }
    
	public RoleGroupBo setVersion(Integer version)
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
            .append("roleGroupId", getRoleGroupId())
            .append("groupName", getGroupName())
            .append("groupRemark", getGroupRemark())
            .append("version", getVersion())
            .toString();
    }
	
}
