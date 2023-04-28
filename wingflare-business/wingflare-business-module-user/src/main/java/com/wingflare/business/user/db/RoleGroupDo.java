package com.wingflare.business.user.db;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统角色分组Do
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 19:07:36 CST 2023
 */
@TableName("sys_role_group")
public class RoleGroupDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private String roleGroupId;

	/**
     * 角色分组名称
     */
    private String groupName;

	/**
     * 角色分组备注
     */
    private String groupRemark;

	@TableField(fill = FieldFill.INSERT)
    private Date createdTime;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

	@TableField(fill = FieldFill.INSERT)
    private String createUser;

	@TableField(fill = FieldFill.INSERT)
    private String createUserId;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

	@TableLogic
	@TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

	@Version
	@TableField(fill = FieldFill.INSERT)
    private Integer version;

	@Override
	public void setPk(String roleGroupId)
	{
		setRoleGroupId(roleGroupId);
	}

	@Override
	public String getPk()
	{
		return getRoleGroupId();
	}

	
    public RoleGroupDo setRoleGroupId(String roleGroupId)
    {
		addNewField("roleGroupId");
        this.roleGroupId = roleGroupId;
        return this;
    }

    public String getRoleGroupId()
    {
        return roleGroupId;
    }
	
    public RoleGroupDo setGroupName(String groupName)
    {
		addNewField("groupName");
        this.groupName = groupName;
        return this;
    }

    public String getGroupName()
    {
        return groupName;
    }
	
    public RoleGroupDo setGroupRemark(String groupRemark)
    {
		addNewField("groupRemark");
        this.groupRemark = groupRemark;
        return this;
    }

    public String getGroupRemark()
    {
        return groupRemark;
    }
	
    public RoleGroupDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public RoleGroupDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public RoleGroupDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public RoleGroupDo setCreateUserId(String createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public RoleGroupDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public RoleGroupDo setUpdateUserId(String updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public RoleGroupDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public RoleGroupDo setVersion(Integer version)
    {
		addNewField("version");
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }

	@Override
	public void clearNullNewField()
	{

		if (getRoleGroupId() == null) {
			removeNewField("roleGroupId");
		}

		if (getGroupName() == null) {
			removeNewField("groupName");
		}

		if (getGroupRemark() == null) {
			removeNewField("groupRemark");
		}

		if (getCreatedTime() == null) {
			removeNewField("createdTime");
		}

		if (getUpdatedTime() == null) {
			removeNewField("updatedTime");
		}

		if (getCreateUser() == null) {
			removeNewField("createUser");
		}

		if (getCreateUserId() == null) {
			removeNewField("createUserId");
		}

		if (getUpdateUser() == null) {
			removeNewField("updateUser");
		}

		if (getUpdateUserId() == null) {
			removeNewField("updateUserId");
		}

		if (getIsDelete() == null) {
			removeNewField("isDelete");
		}

		if (getVersion() == null) {
			removeNewField("version");
		}

	}

	public RoleGroupDo setOnNew(RoleGroupDo newDo)
	{
	    RoleGroupDo oldFieldObj = new RoleGroupDo();

		if (newDo.getGroupName() != null && !newDo.getGroupName().equals(getGroupName())) {
			oldFieldObj.setGroupName(getGroupName());
			setGroupName(newDo.getGroupName());
		}

		if (newDo.getGroupRemark() != null && !newDo.getGroupRemark().equals(getGroupRemark())) {
			oldFieldObj.setGroupRemark(getGroupRemark());
			setGroupRemark(newDo.getGroupRemark());
		}

		if (newDo.getVersion() != null && !newDo.getVersion().equals(getVersion())) {
			oldFieldObj.setVersion(getVersion());
			setVersion(newDo.getVersion());
		}

        if (!oldFieldObj.hasNewField()) {
            return null;
        }
		
		return oldFieldObj;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleGroupId", getRoleGroupId())
            .append("groupName", getGroupName())
            .append("groupRemark", getGroupRemark())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .append("updateUser", getUpdateUser())
            .append("updateUserId", getUpdateUserId())
            .append("isDelete", getIsDelete())
            .append("version", getVersion())
            .toString();
    }
	
}
