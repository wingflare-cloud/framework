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
 * 机构部门Do
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
@TableName("base_org_department")
public class OrgDepartmentDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private BigInteger departmentId;

	/**
     * 部门状态
     */
    private Integer state;

	/**
     * 组织id
     */
    private BigInteger orgId;

	/**
     * 父级部门
     */
    private BigInteger parentDepartmentId;

	/**
     * 部门名称
     */
    private String departmentName;

	/**
     * 部门基础角色id
     */
    private BigInteger roleId;

	/**
     * 部门主要负责人id
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
	public void setPk(BigInteger departmentId)
	{
		setDepartmentId(departmentId);
	}

	@Override
	public BigInteger getPk()
	{
		return getDepartmentId();
	}

	
    public OrgDepartmentDo setDepartmentId(BigInteger departmentId)
    {
		addNewField("departmentId");
        this.departmentId = departmentId;
        return this;
    }

    public BigInteger getDepartmentId()
    {
        return departmentId;
    }
	
    public OrgDepartmentDo setState(Integer state)
    {
		addNewField("state");
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public OrgDepartmentDo setOrgId(BigInteger orgId)
    {
		addNewField("orgId");
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
	
    public OrgDepartmentDo setParentDepartmentId(BigInteger parentDepartmentId)
    {
		addNewField("parentDepartmentId");
        this.parentDepartmentId = parentDepartmentId;
        return this;
    }

    public BigInteger getParentDepartmentId()
    {
        return parentDepartmentId;
    }
	
    public OrgDepartmentDo setDepartmentName(String departmentName)
    {
		addNewField("departmentName");
        this.departmentName = departmentName;
        return this;
    }

    public String getDepartmentName()
    {
        return departmentName;
    }
	
    public OrgDepartmentDo setRoleId(BigInteger roleId)
    {
		addNewField("roleId");
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
	
    public OrgDepartmentDo setUserId(BigInteger userId)
    {
		addNewField("userId");
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId()
    {
        return userId;
    }
	
    public OrgDepartmentDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public OrgDepartmentDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public OrgDepartmentDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public OrgDepartmentDo setCreateUserId(BigInteger createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public OrgDepartmentDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public OrgDepartmentDo setUpdateUserId(BigInteger updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public OrgDepartmentDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public OrgDepartmentDo setVersion(Integer version)
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

		if (getDepartmentId() == null) {
			removeNewField("departmentId");
		}

		if (getState() == null) {
			removeNewField("state");
		}

		if (getOrgId() == null) {
			removeNewField("orgId");
		}

		if (getParentDepartmentId() == null) {
			removeNewField("parentDepartmentId");
		}

		if (getDepartmentName() == null) {
			removeNewField("departmentName");
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

	public OrgDepartmentDo setOnNew(OrgDepartmentDo newDo)
	{
	    OrgDepartmentDo oldFieldObj = new OrgDepartmentDo();

		if (newDo.getState() != null && !newDo.getState().equals(getState())) {
			oldFieldObj.setState(getState());
			setState(newDo.getState());
		}

		if (newDo.getOrgId() != null && !newDo.getOrgId().equals(getOrgId())) {
			oldFieldObj.setOrgId(getOrgId());
			setOrgId(newDo.getOrgId());
		}

		if (newDo.getParentDepartmentId() != null && !newDo.getParentDepartmentId().equals(getParentDepartmentId())) {
			oldFieldObj.setParentDepartmentId(getParentDepartmentId());
			setParentDepartmentId(newDo.getParentDepartmentId());
		}

		if (newDo.getDepartmentName() != null && !newDo.getDepartmentName().equals(getDepartmentName())) {
			oldFieldObj.setDepartmentName(getDepartmentName());
			setDepartmentName(newDo.getDepartmentName());
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
            .append("departmentId", getDepartmentId())
            .append("state", getState())
            .append("orgId", getOrgId())
            .append("parentDepartmentId", getParentDepartmentId())
            .append("departmentName", getDepartmentName())
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
