package com.wingflare.facade.module.user.bo;


/**
 * <p>
 * 职级表 业务对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelBo {

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

    private Integer version;

    public String getJobLevelId() {
        return jobLevelId;
    }

    public JobLevelBo setJobLevelId(String jobLevelId) {
        this.jobLevelId = jobLevelId;
        return this;
    }

    public String getLevelClassifyId() {
        return levelClassifyId;
    }

    public JobLevelBo setLevelClassifyId(String levelClassifyId) {
        this.levelClassifyId = levelClassifyId;
        return this;
    }

    public String getLevelName() {
        return levelName;
    }

    public JobLevelBo setLevelName(String levelName) {
        this.levelName = levelName;
        return this;
    }

    public Integer getRootLevel() {
        return rootLevel;
    }

    public JobLevelBo setRootLevel(Integer rootLevel) {
        this.rootLevel = rootLevel;
        return this;
    }

    public Integer getClassifyLevel() {
        return classifyLevel;
    }

    public JobLevelBo setClassifyLevel(Integer classifyLevel) {
        this.classifyLevel = classifyLevel;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JobLevelBo setVersion(Integer version) {
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