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
 * 系统角色Do
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 19:04:02 CST 2023
 */
@TableName("sys_role")
public class RoleDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
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
	public void setPk(String roleId)
	{
		setRoleId(roleId);
	}

	@Override
	public String getPk()
	{
		return getRoleId();
	}

	
    public RoleDo setRoleId(String roleId)
    {
		addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
	
    public RoleDo setState(Integer state)
    {
		addNewField("state");
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public RoleDo setRoleGroupId(String roleGroupId)
    {
		addNewField("roleGroupId");
        this.roleGroupId = roleGroupId;
        return this;
    }

    public String getRoleGroupId()
    {
        return roleGroupId;
    }
	
    public RoleDo setParentRoleId(String parentRoleId)
    {
		addNewField("parentRoleId");
        this.parentRoleId = parentRoleId;
        return this;
    }

    public String getParentRoleId()
    {
        return parentRoleId;
    }
	
    public RoleDo setRoleIdPath(String roleIdPath)
    {
		addNewField("roleIdPath");
        this.roleIdPath = roleIdPath;
        return this;
    }

    public String getRoleIdPath()
    {
        return roleIdPath;
    }
	
    public RoleDo setRoleName(String roleName)
    {
		addNewField("roleName");
        this.roleName = roleName;
        return this;
    }

    public String getRoleName()
    {
        return roleName;
    }
	
    public RoleDo setRoleRemark(String roleRemark)
    {
		addNewField("roleRemark");
        this.roleRemark = roleRemark;
        return this;
    }

    public String getRoleRemark()
    {
        return roleRemark;
    }
	
    public RoleDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public RoleDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public RoleDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public RoleDo setCreateUserId(String createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public RoleDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public RoleDo setUpdateUserId(String updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public RoleDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public RoleDo setVersion(Integer version)
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

		if (getRoleId() == null) {
			removeNewField("roleId");
		}

		if (getState() == null) {
			removeNewField("state");
		}

		if (getRoleGroupId() == null) {
			removeNewField("roleGroupId");
		}

		if (getParentRoleId() == null) {
			removeNewField("parentRoleId");
		}

		if (getRoleIdPath() == null) {
			removeNewField("roleIdPath");
		}

		if (getRoleName() == null) {
			removeNewField("roleName");
		}

		if (getRoleRemark() == null) {
			removeNewField("roleRemark");
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

	public RoleDo setOnNew(RoleDo newDo)
	{
	    RoleDo oldFieldObj = new RoleDo();

		if (newDo.getState() != null && !newDo.getState().equals(getState())) {
			oldFieldObj.setState(getState());
			setState(newDo.getState());
		}

		if (newDo.getRoleGroupId() != null && !newDo.getRoleGroupId().equals(getRoleGroupId())) {
			oldFieldObj.setRoleGroupId(getRoleGroupId());
			setRoleGroupId(newDo.getRoleGroupId());
		}

		if (newDo.getParentRoleId() != null && !newDo.getParentRoleId().equals(getParentRoleId())) {
			oldFieldObj.setParentRoleId(getParentRoleId());
			setParentRoleId(newDo.getParentRoleId());
		}

		if (newDo.getRoleIdPath() != null && !newDo.getRoleIdPath().equals(getRoleIdPath())) {
			oldFieldObj.setRoleIdPath(getRoleIdPath());
			setRoleIdPath(newDo.getRoleIdPath());
		}

		if (newDo.getRoleName() != null && !newDo.getRoleName().equals(getRoleName())) {
			oldFieldObj.setRoleName(getRoleName());
			setRoleName(newDo.getRoleName());
		}

		if (newDo.getRoleRemark() != null && !newDo.getRoleRemark().equals(getRoleRemark())) {
			oldFieldObj.setRoleRemark(getRoleRemark());
			setRoleRemark(newDo.getRoleRemark());
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
            .append("roleId", getRoleId())
            .append("state", getState())
            .append("roleGroupId", getRoleGroupId())
            .append("parentRoleId", getParentRoleId())
            .append("roleIdPath", getRoleIdPath())
            .append("roleName", getRoleName())
            .append("roleRemark", getRoleRemark())
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
