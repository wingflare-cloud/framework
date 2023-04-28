package com.wingflare.business.user.db;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统角色互斥关系Do
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
@TableName("sys_role_mutex")
public class RoleMutexDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String roleId;

    private String mutexRoleId;

	@TableField(fill = FieldFill.INSERT)
    private Date createdTime;

	@TableField(fill = FieldFill.INSERT)
    private String createUser;

	@TableField(fill = FieldFill.INSERT)
    private String createUserId;

	@TableLogic
	@TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

	@Override
	public void setPk(String id)
	{
		setId(id);
	}

	@Override
	public String getPk()
	{
		return getId();
	}

	
    public RoleMutexDo setId(String id)
    {
		addNewField("id");
        this.id = id;
        return this;
    }

    public String getId()
    {
        return id;
    }
	
    public RoleMutexDo setRoleId(String roleId)
    {
		addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
	
    public RoleMutexDo setMutexRoleId(String mutexRoleId)
    {
		addNewField("mutexRoleId");
        this.mutexRoleId = mutexRoleId;
        return this;
    }

    public String getMutexRoleId()
    {
        return mutexRoleId;
    }
	
    public RoleMutexDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public RoleMutexDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public RoleMutexDo setCreateUserId(String createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public RoleMutexDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
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

		if (getMutexRoleId() == null) {
			removeNewField("mutexRoleId");
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

		if (getIsDelete() == null) {
			removeNewField("isDelete");
		}
	}

	public RoleMutexDo setOnNew(RoleMutexDo newDo)
	{
        RoleGroupDo oldFieldObj = new RoleGroupDo();

		if (newDo.getId() != null && !newDo.getId().equals(getId())) {
			setId(newDo.getId());
		}

		if (newDo.getRoleId() != null && !newDo.getRoleId().equals(getRoleId())) {
			setRoleId(newDo.getRoleId());
		}

		if (newDo.getMutexRoleId() != null && !newDo.getMutexRoleId().equals(getMutexRoleId())) {
			setMutexRoleId(newDo.getMutexRoleId());
		}

        if (!oldFieldObj.hasNewField()) {
            return null;
        }
		
		return this;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("mutexRoleId", getMutexRoleId())
            .append("createdTime", getCreatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .append("isDelete", getIsDelete())
            .toString();
    }
	
}
