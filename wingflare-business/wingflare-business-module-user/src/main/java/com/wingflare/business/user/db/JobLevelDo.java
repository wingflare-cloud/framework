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
 * 职级Do
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
@TableName("base_job_level")
public class JobLevelDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private String jobLevelId;

	/**
     * 职级名称
     */
    private String levelName;

	/**
     * 职级数值
     */
    private Long level;

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
	public JobLevelDo setPk(String jobLevelId)
	{
		setJobLevelId(jobLevelId);
		return this;
	}

	@Override
	public String getPk()
	{
		return getJobLevelId();
	}

	
    public JobLevelDo setJobLevelId(String jobLevelId)
    {
		addNewField("jobLevelId");
        this.jobLevelId = jobLevelId;
        return this;
    }

    public String getJobLevelId()
    {
        return jobLevelId;
    }
	
    public JobLevelDo setLevelName(String levelName)
    {
		addNewField("levelName");
        this.levelName = levelName;
        return this;
    }

    public String getLevelName()
    {
        return levelName;
    }
	
    public JobLevelDo setLevel(Long level)
    {
		addNewField("level");
        this.level = level;
        return this;
    }

    public Long getLevel()
    {
        return level;
    }
	
    public JobLevelDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public JobLevelDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public JobLevelDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public JobLevelDo setCreateUserId(String createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public JobLevelDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public JobLevelDo setUpdateUserId(String updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public JobLevelDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public JobLevelDo setVersion(Integer version)
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
	public JobLevelDo clearNullNewField()
	{

		if (getJobLevelId() == null) {
			removeNewField("jobLevelId");
		}

		if (getLevelName() == null) {
			removeNewField("levelName");
		}

		if (getLevel() == null) {
			removeNewField("level");
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

		return this;
	}

	public JobLevelDo setOnNew(JobLevelDo newDo)
	{
	    JobLevelDo oldFieldObj = new JobLevelDo();

		if (newDo.getLevelName() != null && !newDo.getLevelName().equals(getLevelName())) {
			oldFieldObj.setLevelName(getLevelName());
			setLevelName(newDo.getLevelName());
		}

		if (newDo.getLevel() != null && !newDo.getLevel().equals(getLevel())) {
			oldFieldObj.setLevel(getLevel());
			setLevel(newDo.getLevel());
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
            .append("jobLevelId", getJobLevelId())
            .append("levelName", getLevelName())
            .append("level", getLevel())
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
