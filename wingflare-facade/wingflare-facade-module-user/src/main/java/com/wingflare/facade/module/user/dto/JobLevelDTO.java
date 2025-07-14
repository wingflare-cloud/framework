package com.wingflare.facade.module.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.Date;

/**
 * <p>
 * 职级表 输出对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelDTO {

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    private String createUser;

    private BigInteger createUserId;

    private String updateUser;

    private BigInteger updateUserId;

    private Integer version;

    public BigInteger getJobLevelId() {
        return jobLevelId;
    }

    public void setJobLevelId(BigInteger jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public BigInteger getLevelClassifyId() {
        return levelClassifyId;
    }

    public void setLevelClassifyId(BigInteger levelClassifyId) {
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

    public BigInteger getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(BigInteger createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public BigInteger getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(BigInteger updateUserId) {
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