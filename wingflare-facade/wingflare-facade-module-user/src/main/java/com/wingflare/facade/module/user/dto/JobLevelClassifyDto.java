package com.wingflare.facade.module.user.dto;


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
public class JobLevelClassifyDto {

    private String levelClassifyId;

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

    private String createUserId;

    private String updateUser;

    private String updateUserId;

    private Integer version;

    public String getLevelClassifyId() {
        return levelClassifyId;
    }

    public JobLevelClassifyDto setLevelClassifyId(String levelClassifyId) {
        this.levelClassifyId = levelClassifyId;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public JobLevelClassifyDto setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public JobLevelClassifyDto setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public JobLevelClassifyDto setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public JobLevelClassifyDto setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public JobLevelClassifyDto setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public JobLevelClassifyDto setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public JobLevelClassifyDto setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public JobLevelClassifyDto setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JobLevelClassifyDto setVersion(Integer version) {
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