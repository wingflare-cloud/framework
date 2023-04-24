package com.wingflare.business.user.db;


import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户角色Do
 * 
 * @author naizui_ycx
 * @date Wed Apr 05 21:03:51 CST 2023
 */
@TableName("sys_user_role")
public class UserRoleDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String userId;

    private String systemCode;

    private String roleId;

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
	public UserRoleDo setPk(String id)
	{
		setId(id);
		return this;
	}

	@Override
	public String getPk()
	{
		return getId();
	}

	
    public UserRoleDo setId(String id)
    {
		addNewField("id");
        this.id = id;
        return this;
    }

    public String getId()
    {
        return id;
    }
	
    public UserRoleDo setUserId(String userId)
    {
		addNewField("userId");
        this.userId = userId;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }
	
    public UserRoleDo setSystemCode(String systemCode)
    {
		addNewField("systemCode");
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public UserRoleDo setRoleId(String roleId)
    {
		addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
	
    public UserRoleDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public UserRoleDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public UserRoleDo setCreateUserId(String createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public UserRoleDo setIsDelete(Integer isDelete)
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
	public UserRoleDo clearNullNewField()
	{

		if (getId() == null) {
			removeNewField("id");
		}

		if (getUserId() == null) {
			removeNewField("userId");
		}

		if (getSystemCode() == null) {
			removeNewField("systemCode");
		}

		if (getRoleId() == null) {
			removeNewField("roleId");
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

		return this;
	}

	public UserRoleDo setOnNew(UserRoleDo newDo)
	{
	    UserRoleDo oldFieldObj = new UserRoleDo();

		if (newDo.getUserId() != null && !newDo.getUserId().equals(getUserId())) {
			oldFieldObj.setUserId(getUserId());
			setUserId(newDo.getUserId());
		}

		if (newDo.getSystemCode() != null && !newDo.getSystemCode().equals(getSystemCode())) {
			oldFieldObj.setSystemCode(getSystemCode());
			setSystemCode(newDo.getSystemCode());
		}

		if (newDo.getRoleId() != null && !newDo.getRoleId().equals(getRoleId())) {
			oldFieldObj.setRoleId(getRoleId());
			setRoleId(newDo.getRoleId());
		}

		if (!oldFieldObj.hasNewField()) {
			return null;
		}

		return oldFieldObj;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("systemCode", getSystemCode())
            .append("roleId", getRoleId())
            .append("createdTime", getCreatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .append("isDelete", getIsDelete())
            .toString();
    }
	
}
