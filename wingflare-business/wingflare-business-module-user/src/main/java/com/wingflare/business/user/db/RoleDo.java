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

import java.math.BigInteger;
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
    private BigInteger roleId;

	/**
     * 角色状态
     */
    private Integer state;

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
    private BigInteger createUserId;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private BigInteger updateUserId;

	@TableLogic
	@TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

	@Version
	@TableField(fill = FieldFill.INSERT)
    private Integer version;

	@Override
	public void setPk(BigInteger roleId)
	{
		setRoleId(roleId);
	}

	@Override
	public BigInteger getPk()
	{
		return getRoleId();
	}

	
    public RoleDo setRoleId(BigInteger roleId)
    {
		addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
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
	
    public RoleDo setCreateUserId(BigInteger createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
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
	
    public RoleDo setUpdateUserId(BigInteger updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
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
