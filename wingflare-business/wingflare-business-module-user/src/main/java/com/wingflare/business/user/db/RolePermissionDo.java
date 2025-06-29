package com.wingflare.business.user.db;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Date;

/**
 * 系统角色权限Do
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
@TableName("sys_role_permission")
public class RolePermissionDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private BigInteger id;

    private BigInteger roleId;

    private String systemCode;

    private String permissionCode;

	@TableField(fill = FieldFill.INSERT)
    private Date createdTime;

	@TableField(fill = FieldFill.INSERT)
    private String createUser;

	@TableField(fill = FieldFill.INSERT)
    private BigInteger createUserId;

	@Override
	public void setPk(BigInteger id)
	{
		setId(id);
	}

	@Override
	public BigInteger getPk()
	{
		return getId();
	}

	
    public RolePermissionDo setId(BigInteger id)
    {
		addNewField("id");
        this.id = id;
        return this;
    }

    public BigInteger getId()
    {
        return id;
    }
	
    public RolePermissionDo setRoleId(BigInteger roleId)
    {
		addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
	
    public RolePermissionDo setSystemCode(String systemCode)
    {
		addNewField("systemCode");
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public RolePermissionDo setPermissionCode(String permissionCode)
    {
		addNewField("permissionCode");
        this.permissionCode = permissionCode;
        return this;
    }

    public String getPermissionCode()
    {
        return permissionCode;
    }
	
    public RolePermissionDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public RolePermissionDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public RolePermissionDo setCreateUserId(BigInteger createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }

	@Override
	public void clearNullNewField()
	{

		if (getId() == null) {
			removeNewField("id");
		}

		if (getRoleId() == null) {
			removeNewField("roleId");
		}

		if (getSystemCode() == null) {
			removeNewField("systemCode");
		}

		if (getPermissionCode() == null) {
			removeNewField("permissionCode");
		}

		if (getCreatedTime() == null) {
			removeNewField("createdTime");
		}

		if (getCreateUser() == null) {
			removeNewField("createUser");
		}

		if (getCreateUserId() == null) {
			removeNewField("createUserId");
		}
	}

	public RolePermissionDo setOnNew(RolePermissionDo newDo)
	{

		if (newDo.getId() != null && !newDo.getId().equals(getId())) {
			setId(newDo.getId());
		}

		if (newDo.getRoleId() != null && !newDo.getRoleId().equals(getRoleId())) {
			setRoleId(newDo.getRoleId());
		}

		if (newDo.getSystemCode() != null && !newDo.getSystemCode().equals(getSystemCode())) {
			setSystemCode(newDo.getSystemCode());
		}

		if (newDo.getPermissionCode() != null && !newDo.getPermissionCode().equals(getPermissionCode())) {
			setPermissionCode(newDo.getPermissionCode());
		}
		
		return this;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("systemCode", getSystemCode())
            .append("permissionCode", getPermissionCode())
            .append("createdTime", getCreatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .toString();
    }
	
}
