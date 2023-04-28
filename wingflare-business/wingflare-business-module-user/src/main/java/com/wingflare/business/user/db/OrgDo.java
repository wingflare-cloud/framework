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
 * 组织机构Do
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
@TableName("base_org")
public class OrgDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private String orgId;

	/**
     * 父级机构id
     */
    private String parentOrgId;

	/**
     * 组织状态
     */
    private Integer state;

	/**
     * 组织类型
     */
    private String orgType;

	/**
     * 组织名称
     */
    private String orgName;

	/**
     * 组织手机或固话号
     */
    private String orgPhone;

	/**
     * 详细地址
     */
    private String orgAddress;

	/**
     * 机构基础角色id
     */
    private String roleId;

	/**
     * 机构主要负责人id
     */
    private String userId;

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
	public void setPk(String orgId)
	{
		setOrgId(orgId);
	}

	@Override
	public String getPk()
	{
		return getOrgId();
	}

	
    public OrgDo setOrgId(String orgId)
    {
		addNewField("orgId");
        this.orgId = orgId;
        return this;
    }

    public String getOrgId()
    {
        return orgId;
    }
	
    public OrgDo setParentOrgId(String parentOrgId)
    {
		addNewField("parentOrgId");
        this.parentOrgId = parentOrgId;
        return this;
    }

    public String getParentOrgId()
    {
        return parentOrgId;
    }
	
    public OrgDo setState(Integer state)
    {
		addNewField("state");
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public OrgDo setOrgType(String orgType)
    {
		addNewField("orgType");
        this.orgType = orgType;
        return this;
    }

    public String getOrgType()
    {
        return orgType;
    }
	
    public OrgDo setOrgName(String orgName)
    {
		addNewField("orgName");
        this.orgName = orgName;
        return this;
    }

    public String getOrgName()
    {
        return orgName;
    }
	
    public OrgDo setOrgPhone(String orgPhone)
    {
		addNewField("orgPhone");
        this.orgPhone = orgPhone;
        return this;
    }

    public String getOrgPhone()
    {
        return orgPhone;
    }
	
    public OrgDo setOrgAddress(String orgAddress)
    {
		addNewField("orgAddress");
        this.orgAddress = orgAddress;
        return this;
    }

    public String getOrgAddress()
    {
        return orgAddress;
    }
	
    public OrgDo setRoleId(String roleId)
    {
		addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
	
    public OrgDo setUserId(String userId)
    {
		addNewField("userId");
        this.userId = userId;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }
	
    public OrgDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public OrgDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public OrgDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public OrgDo setCreateUserId(String createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public OrgDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public OrgDo setUpdateUserId(String updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public OrgDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public OrgDo setVersion(Integer version)
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

		if (getOrgId() == null) {
			removeNewField("orgId");
		}

		if (getParentOrgId() == null) {
			removeNewField("parentOrgId");
		}

		if (getState() == null) {
			removeNewField("state");
		}

		if (getOrgType() == null) {
			removeNewField("orgType");
		}

		if (getOrgName() == null) {
			removeNewField("orgName");
		}

		if (getOrgPhone() == null) {
			removeNewField("orgPhone");
		}

		if (getOrgAddress() == null) {
			removeNewField("orgAddress");
		}

		if (getRoleId() == null) {
			removeNewField("roleId");
		}

		if (getUserId() == null) {
			removeNewField("userId");
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

	public OrgDo setOnNew(OrgDo newDo)
	{
	    OrgDo oldFieldObj = new OrgDo();

		if (newDo.getParentOrgId() != null && !newDo.getParentOrgId().equals(getParentOrgId())) {
			oldFieldObj.setParentOrgId(getParentOrgId());
			setParentOrgId(newDo.getParentOrgId());
		}

		if (newDo.getState() != null && !newDo.getState().equals(getState())) {
			oldFieldObj.setState(getState());
			setState(newDo.getState());
		}

		if (newDo.getOrgType() != null && !newDo.getOrgType().equals(getOrgType())) {
			oldFieldObj.setOrgType(getOrgType());
			setOrgType(newDo.getOrgType());
		}

		if (newDo.getOrgName() != null && !newDo.getOrgName().equals(getOrgName())) {
			oldFieldObj.setOrgName(getOrgName());
			setOrgName(newDo.getOrgName());
		}

		if (newDo.getOrgPhone() != null && !newDo.getOrgPhone().equals(getOrgPhone())) {
			oldFieldObj.setOrgPhone(getOrgPhone());
			setOrgPhone(newDo.getOrgPhone());
		}

		if (newDo.getOrgAddress() != null && !newDo.getOrgAddress().equals(getOrgAddress())) {
			oldFieldObj.setOrgAddress(getOrgAddress());
			setOrgAddress(newDo.getOrgAddress());
		}

		if (newDo.getRoleId() != null && !newDo.getRoleId().equals(getRoleId())) {
			oldFieldObj.setRoleId(getRoleId());
			setRoleId(newDo.getRoleId());
		}

		if (newDo.getUserId() != null && !newDo.getUserId().equals(getUserId())) {
			oldFieldObj.setUserId(getUserId());
			setUserId(newDo.getUserId());
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
            .append("orgId", getOrgId())
            .append("parentOrgId", getParentOrgId())
            .append("state", getState())
            .append("orgType", getOrgType())
            .append("orgName", getOrgName())
            .append("orgPhone", getOrgPhone())
            .append("orgAddress", getOrgAddress())
            .append("roleId", getRoleId())
            .append("userId", getUserId())
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
