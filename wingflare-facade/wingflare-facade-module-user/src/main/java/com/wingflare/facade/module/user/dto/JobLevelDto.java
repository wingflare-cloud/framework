package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <p>
 * 职级表 输出对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelDto {

    private String jobLevelId;

    /**
     * 职级分类id 
     */
    private String levelClassifyId;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    private String createUser;

    private String createUserId;

    private String updateUser;

    private String updateUserId;

    private Integer version;

    public String getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(String jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public String getLevelClassifyId() {
        return levelClassifyId;
    }

    public void setLevelClassifyId(String levelClassifyId) {
        this.levelClassifyId = levelClassifyId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getRootLevel() {
        return rootLevel;
    }

    public void setRootLevel(Integer rootLevel) {
        this.rootLevel = rootLevel;
    }

    public Integer getClassifyLevel() {
        return classifyLevel;
    }

    public void setClassifyLevel(Integer classifyLevel) {
        this.classifyLevel = classifyLevel;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

	public String toString() {
        return "JoblevelDo{" +
        "jobLevelId = " + jobLevelId +
        ", levelClassifyId = " + levelClassifyId +
        ", levelName = " + levelName +
        ", rootLevel = " + rootLevel +
        ", classifyLevel = " + classifyLevel +
        ", version = " + version +
        "}";
    }

}