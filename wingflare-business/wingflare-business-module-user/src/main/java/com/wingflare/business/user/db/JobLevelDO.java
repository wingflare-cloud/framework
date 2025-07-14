package com.wingflare.business.user.db;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;

import java.math.BigInteger;
import java.util.Date;

/**
 * <p>
 * 职级表 数据对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
@TableName("base_job_level")
public class JobLevelDO extends BaseDoAbstract {

    @TableId(value = "job_level_id", type = IdType.ASSIGN_ID)
    private BigInteger jobLevelId;

    /**
     * 职级分类id 
     */
    private BigInteger levelClassifyId;

    /**
     * 职级名称 
     */
    private String levelName;

    /**
     * 职级全局数值 
     */
    private Integer rootLevel;

    /**
     * 职级分类数值 
     */
    private Integer classifyLevel;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.INSERT)
    private BigInteger createUserId;

    private String updateUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private BigInteger updateUserId;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Byte isDelete;

    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

	@Override
    public void setPk(BigInteger jobLevelId)
    {
        setJobLevelId(jobLevelId);
    }

	@Override
    public BigInteger getPk()
    {
        return getJobLevelId();
    }

    public BigInteger getJobLevelId() {
        return jobLevelId;
    }

    public JobLevelDO setJobLevelId(BigInteger jobLevelId) {
        addNewField("jobLevelId");
        this.jobLevelId = jobLevelId;
        return this;
    }

    public BigInteger getLevelClassifyId() {
        return levelClassifyId;
    }

    public JobLevelDO setLevelClassifyId(BigInteger levelClassifyId) {
        addNewField("levelClassifyId");
        this.levelClassifyId = levelClassifyId;
        return this;
    }

    public String getLevelName() {
        return levelName;
    }

    public JobLevelDO setLevelName(String levelName) {
        addNewField("levelName");
        this.levelName = levelName;
        return this;
    }

    public Integer getRootLevel() {
        return rootLevel;
    }

    public JobLevelDO setRootLevel(Integer rootLevel) {
        addNewField("rootLevel");
        this.rootLevel = rootLevel;
        return this;
    }

    public Integer getClassifyLevel() {
        return classifyLevel;
    }

    public JobLevelDO setClassifyLevel(Integer classifyLevel) {
        addNewField("classifyLevel");
        this.classifyLevel = classifyLevel;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public JobLevelDO setCreatedTime(Date createdTime) {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public JobLevelDO setUpdatedTime(Date updatedTime) {
        addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public JobLevelDO setCreateUser(String createUser) {
        addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public BigInteger getCreateUserId() {
        return createUserId;
    }

    public JobLevelDO setCreateUserId(BigInteger createUserId) {
        addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public JobLevelDO setUpdateUser(String updateUser) {
        addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public BigInteger getUpdateUserId() {
        return updateUserId;
    }

    public JobLevelDO setUpdateUserId(BigInteger updateUserId) {
        addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public JobLevelDO setIsDelete(Byte isDelete) {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JobLevelDO setVersion(Integer version) {
        addNewField("version");
        this.version = version;
        return this;
    }

	@Override
    public void clearNullNewField() {

	    if (getJobLevelId() == null) {
            removeNewField("jobLevelId");
        }

	    if (getLevelClassifyId() == null) {
            removeNewField("levelClassifyId");
        }

	    if (getLevelName() == null) {
            removeNewField("levelName");
        }

	    if (getRootLevel() == null) {
            removeNewField("rootLevel");
        }

	    if (getClassifyLevel() == null) {
            removeNewField("classifyLevel");
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

    public JobLevelDO setOnNew(JobLevelDO newDo)
    {
        JobLevelDO oldFieldObj = new JobLevelDO();

        if (newDo.getJobLevelId() != null && !newDo.getJobLevelId().equals(getJobLevelId())) {
            oldFieldObj.setJobLevelId(getJobLevelId());
            setJobLevelId(newDo.getJobLevelId());
        }

        if (newDo.getLevelClassifyId() != null && !newDo.getLevelClassifyId().equals(getLevelClassifyId())) {
            oldFieldObj.setLevelClassifyId(getLevelClassifyId());
            setLevelClassifyId(newDo.getLevelClassifyId());
        }

        if (newDo.getLevelName() != null && !newDo.getLevelName().equals(getLevelName())) {
            oldFieldObj.setLevelName(getLevelName());
            setLevelName(newDo.getLevelName());
        }

        if (newDo.getRootLevel() != null && !newDo.getRootLevel().equals(getRootLevel())) {
            oldFieldObj.setRootLevel(getRootLevel());
            setRootLevel(newDo.getRootLevel());
        }

        if (newDo.getClassifyLevel() != null && !newDo.getClassifyLevel().equals(getClassifyLevel())) {
            oldFieldObj.setClassifyLevel(getClassifyLevel());
            setClassifyLevel(newDo.getClassifyLevel());
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
        return "JoblevelDo{" +
        "jobLevelId = " + jobLevelId +
        ", levelClassifyId = " + levelClassifyId +
        ", levelName = " + levelName +
        ", rootLevel = " + rootLevel +
        ", classifyLevel = " + classifyLevel +
        ", createdTime = " + createdTime +
        ", updatedTime = " + updatedTime +
        ", createUser = " + createUser +
        ", createUserId = " + createUserId +
        ", updateUser = " + updateUser +
        ", updateUserId = " + updateUserId +
        ", isDelete = " + isDelete +
        ", version = " + version +
        "}";
    }

}
