package com.wingflare.facade.module.user.dto;


import java.math.BigInteger;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 职级分类表 输出对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelClassifyDTO {

    private BigInteger levelClassifyId;

    /**
     * 分类名 
     */
    private String classifyName;

    /**
     * 分类代码 
     */
    private String classifyCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    private String createUser;

    private BigInteger createUserId;

    private String updateUser;

    private BigInteger updateUserId;

    private Integer version;

    public BigInteger getLevelClassifyId() {
        return levelClassifyId;
    }

    public JobLevelClassifyDTO setLevelClassifyId(BigInteger levelClassifyId) {
        this.levelClassifyId = levelClassifyId;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public JobLevelClassifyDTO setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public JobLevelClassifyDTO setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public JobLevelClassifyDTO setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public JobLevelClassifyDTO setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public JobLevelClassifyDTO setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public BigInteger getCreateUserId() {
        return createUserId;
    }

    public JobLevelClassifyDTO setCreateUserId(BigInteger createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public JobLevelClassifyDTO setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public BigInteger getUpdateUserId() {
        return updateUserId;
    }

    public JobLevelClassifyDTO setUpdateUserId(BigInteger updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JobLevelClassifyDTO setVersion(Integer version) {
        this.version = version;
        return this;
    }

	@Override
	public String toString() {
        return "JoblevelclassifyDo{" +
        "levelClassifyId = " + levelClassifyId +
        ", classifyName = " + classifyName +
        ", classifyCode = " + classifyCode +
        ", version = " + version +
        "}";
    }

}