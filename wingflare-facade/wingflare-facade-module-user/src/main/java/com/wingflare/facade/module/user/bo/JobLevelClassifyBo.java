package com.wingflare.facade.module.user.bo;


/**
 * <p>
 * 职级分类表 业务对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelClassifyBo {

    private String levelClassifyId;

    /**
     * 分类名 
     */
    private String classifyName;

    /**
     * 分类代码 
     */
    private String classifyCode;

    private Integer version;

    public String getLevelClassifyId() {
        return levelClassifyId;
    }

    public JobLevelClassifyBo setLevelClassifyId(String levelClassifyId) {
        this.levelClassifyId = levelClassifyId;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public JobLevelClassifyBo setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public JobLevelClassifyBo setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JobLevelClassifyBo setVersion(Integer version) {
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