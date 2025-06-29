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
 * 职级分类表 数据对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
@TableName("base_job_level_classify")
public class JobLevelClassifyDo extends BaseDoAbstract {

    @TableId(value = "level_classify_id", type = IdType.ASSIGN_ID)
    private BigInteger levelClassifyId;

    /**
     * 分类名 
     */
    private String classifyName;

    /**
     * 分类代码 
     */
    private String classifyCode;

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
    public void setPk(BigInteger levelClassifyId)
    {
        setLevelClassifyId(levelClassifyId);
    }

	@Override
    public BigInteger getPk()
    {
        return getLevelClassifyId();
    }

    public BigInteger getLevelClassifyId() {
        return levelClassifyId;
    }

    public JobLevelClassifyDo setLevelClassifyId(BigInteger levelClassifyId) {
        addNewField("levelClassifyId");
        this.levelClassifyId = levelClassifyId;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public JobLevelClassifyDo setClassifyName(String classifyName) {
        addNewField("classifyName");
        this.classifyName = classifyName;
        return this;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public JobLevelClassifyDo setClassifyCode(String classifyCode) {
        addNewField("classifyCode");
        this.classifyCode = classifyCode;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public JobLevelClassifyDo setCreatedTime(Date createdTime) {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public JobLevelClassifyDo setUpdatedTime(Date updatedTime) {
        addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public JobLevelClassifyDo setCreateUser(String createUser) {
        addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public BigInteger getCreateUserId() {
        return createUserId;
    }

    public JobLevelClassifyDo setCreateUserId(BigInteger createUserId) {
        addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public JobLevelClassifyDo setUpdateUser(String updateUser) {
        addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public BigInteger getUpdateUserId() {
        return updateUserId;
    }

    public JobLevelClassifyDo setUpdateUserId(BigInteger updateUserId) {
        addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public JobLevelClassifyDo setIsDelete(Byte isDelete) {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JobLevelClassifyDo setVersion(Integer version) {
        addNewField("version");
        this.version = version;
        return this;
    }

	@Override
    public void clearNullNewField() {

	    if (getLevelClassifyId() == null) {
            removeNewField("levelClassifyId");
        }

	    if (getClassifyName() == null) {
            removeNewField("classifyName");
        }

	    if (getClassifyCode() == null) {
            removeNewField("classifyCode");
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

    public JobLevelClassifyDo setOnNew(JobLevelClassifyDo newDo)
    {
        JobLevelClassifyDo oldFieldObj = new JobLevelClassifyDo();

        if (newDo.getLevelClassifyId() != null && !newDo.getLevelClassifyId().equals(getLevelClassifyId())) {
            oldFieldObj.setLevelClassifyId(getLevelClassifyId());
            setLevelClassifyId(newDo.getLevelClassifyId());
        }

        if (newDo.getClassifyName() != null && !newDo.getClassifyName().equals(getClassifyName())) {
            oldFieldObj.setClassifyName(getClassifyName());
            setClassifyName(newDo.getClassifyName());
        }

        if (newDo.getClassifyCode() != null && !newDo.getClassifyCode().equals(getClassifyCode())) {
            oldFieldObj.setClassifyCode(getClassifyCode());
            setClassifyCode(newDo.getClassifyCode());
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
        return "JobLevelClassifyDo{" +
        "levelClassifyId = " + levelClassifyId +
        ", classifyName = " + classifyName +
        ", classifyCode = " + classifyCode +
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
