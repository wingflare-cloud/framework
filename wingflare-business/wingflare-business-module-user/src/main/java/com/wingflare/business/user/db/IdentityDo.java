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
 * 岗位身份Do
 * 
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
@TableName("base_identity")
public class IdentityDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private BigInteger identityId;

	/**
     * 组织机构id
     */
    private BigInteger orgId;

	/**
     * 部门id
     */
    private BigInteger departmentId;

	/**
     * 岗位名称
     */
    private String identityName;

	/**
     * 职级id
     */
    private BigInteger jobLevelId;

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
	public void setPk(BigInteger identityId)
	{
		setIdentityId(identityId);
	}

	@Override
	public BigInteger getPk()
	{
		return getIdentityId();
	}

	
    public IdentityDo setIdentityId(BigInteger identityId)
    {
		addNewField("identityId");
        this.identityId = identityId;
        return this;
    }

    public BigInteger getIdentityId()
    {
        return identityId;
    }
	
    public IdentityDo setOrgId(BigInteger orgId)
    {
		addNewField("orgId");
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
	
    public IdentityDo setDepartmentId(BigInteger departmentId)
    {
		addNewField("departmentId");
        this.departmentId = departmentId;
        return this;
    }

    public BigInteger getDepartmentId()
    {
        return departmentId;
    }
	
    public IdentityDo setIdentityName(String identityName)
    {
		addNewField("identityName");
        this.identityName = identityName;
        return this;
    }

    public String getIdentityName()
    {
        return identityName;
    }
	
    public IdentityDo setJobLevelId(BigInteger jobLevelId)
    {
		addNewField("jobLevelId");
        this.jobLevelId = jobLevelId;
        return this;
    }

    public BigInteger getJobLevelId()
    {
        return jobLevelId;
    }
	
    public IdentityDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public IdentityDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public IdentityDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public IdentityDo setCreateUserId(BigInteger createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public IdentityDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public IdentityDo setUpdateUserId(BigInteger updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public IdentityDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public IdentityDo setVersion(Integer version)
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

		if (getIdentityId() == null) {
			removeNewField("identityId");
		}

		if (getOrgId() == null) {
			removeNewField("orgId");
		}

		if (getDepartmentId() == null) {
			removeNewField("departmentId");
		}

		if (getIdentityName() == null) {
			removeNewField("identityName");
		}

		if (getJobLevelId() == null) {
			removeNewField("jobLevelId");
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

	public IdentityDo setOnNew(IdentityDo newDo)
	{
	    IdentityDo oldFieldObj = new IdentityDo();

		if (newDo.getOrgId() != null && !newDo.getOrgId().equals(getOrgId())) {
			oldFieldObj.setOrgId(getOrgId());
			setOrgId(newDo.getOrgId());
		}

		if (newDo.getDepartmentId() != null && !newDo.getDepartmentId().equals(getDepartmentId())) {
			oldFieldObj.setDepartmentId(getDepartmentId());
			setDepartmentId(newDo.getDepartmentId());
		}

		if (newDo.getIdentityName() != null && !newDo.getIdentityName().equals(getIdentityName())) {
			oldFieldObj.setIdentityName(getIdentityName());
			setIdentityName(newDo.getIdentityName());
		}

		if (newDo.getJobLevelId() != null && !newDo.getJobLevelId().equals(getJobLevelId())) {
			oldFieldObj.setJobLevelId(getJobLevelId());
			setJobLevelId(newDo.getJobLevelId());
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
            .append("identityId", getIdentityId())
            .append("orgId", getOrgId())
            .append("departmentId", getDepartmentId())
            .append("identityName", getIdentityName())
            .append("jobLevelId", getJobLevelId())
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
