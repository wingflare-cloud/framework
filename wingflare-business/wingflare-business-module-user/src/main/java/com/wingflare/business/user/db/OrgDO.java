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
 * 组织机构Do
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
@TableName("base_org")
public class OrgDO extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private BigInteger orgId;

	/**
     * 父级机构id
     */
    private BigInteger parentOrgId;

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
    private BigInteger userId;

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
	public void setPk(BigInteger orgId)
	{
		setOrgId(orgId);
	}

	@Override
	public BigInteger getPk()
	{
		return getOrgId();
	}

	
    public OrgDO setOrgId(BigInteger orgId)
    {
		addNewField("orgId");
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
	
    public OrgDO setParentOrgId(BigInteger parentOrgId)
    {
		addNewField("parentOrgId");
        this.parentOrgId = parentOrgId;
        return this;
    }

    public BigInteger getParentOrgId()
    {
        return parentOrgId;
    }
	
    public OrgDO setState(Integer state)
    {
		addNewField("state");
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public OrgDO setOrgType(String orgType)
    {
		addNewField("orgType");
        this.orgType = orgType;
        return this;
    }

    public String getOrgType()
    {
        return orgType;
    }
	
    public OrgDO setOrgName(String orgName)
    {
		addNewField("orgName");
        this.orgName = orgName;
        return this;
    }

    public String getOrgName()
    {
        return orgName;
    }
	
    public OrgDO setOrgPhone(String orgPhone)
    {
		addNewField("orgPhone");
        this.orgPhone = orgPhone;
        return this;
    }

    public String getOrgPhone()
    {
        return orgPhone;
    }
	
    public OrgDO setOrgAddress(String orgAddress)
    {
		addNewField("orgAddress");
        this.orgAddress = orgAddress;
        return this;
    }

    public String getOrgAddress()
    {
        return orgAddress;
    }
	
    public OrgDO setRoleId(String roleId)
    {
		addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public String getRoleId()
    {
        return roleId;
    }
	
    public OrgDO setUserId(BigInteger userId)
    {
		addNewField("userId");
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId()
    {
        return userId;
    }
	
    public OrgDO setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public OrgDO setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public OrgDO setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public OrgDO setCreateUserId(BigInteger createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public OrgDO setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public OrgDO setUpdateUserId(BigInteger updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public OrgDO setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public OrgDO setVersion(Integer version)
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

	public OrgDO setOnNew(OrgDO newDo)
	{
	    OrgDO oldFieldObj = new OrgDO();

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
