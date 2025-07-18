package com.wingflare.facade.module.user.bo;


import java.math.BigInteger;

/**
 * <p>
 * 职级表 业务对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelBO {

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

    private Integer version;

    public BigInteger getJobLevelId() {
        return jobLevelId;
    }

    public JobLevelBO setJobLevelId(BigInteger jobLevelId) {
        this.jobLevelId = jobLevelId;
        return this;
    }

    public BigInteger getLevelClassifyId() {
        return levelClassifyId;
    }

    public JobLevelBO setLevelClassifyId(BigInteger levelClassifyId) {
        this.levelClassifyId = levelClassifyId;
        return this;
    }

    public String getLevelName() {
        return levelName;
    }

    public JobLevelBO setLevelName(String levelName) {
        this.levelName = levelName;
        return this;
    }

    public Integer getRootLevel() {
        return rootLevel;
    }

    public JobLevelBO setRootLevel(Integer rootLevel) {
        this.rootLevel = rootLevel;
        return this;
    }

    public Integer getClassifyLevel() {
        return classifyLevel;
    }

    public JobLevelBO setClassifyLevel(Integer classifyLevel) {
        this.classifyLevel = classifyLevel;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JobLevelBO setVersion(Integer version) {
        this.version = version;
        return this;
    }

	@Override
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