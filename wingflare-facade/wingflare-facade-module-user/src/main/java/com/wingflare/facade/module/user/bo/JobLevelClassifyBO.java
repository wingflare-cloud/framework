package com.wingflare.facade.module.user.bo;


import java.math.BigInteger;

/**
 * <p>
 * 职级分类表 业务对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelClassifyBO {

    private BigInteger levelClassifyId;

    /**
     * 分类名 
     */
    private String classifyName;

    /**
     * 分类代码 
     */
    private String classifyCode;

    private Integer version;

    public BigInteger getLevelClassifyId() {
        return levelClassifyId;
    }

    public JobLevelClassifyBO setLevelClassifyId(BigInteger levelClassifyId) {
        this.levelClassifyId = levelClassifyId;
        return this;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public JobLevelClassifyBO setClassifyName(String classifyName) {
        this.classifyName = classifyName;
        return this;
    }

    public String getClassifyCode() {
        return classifyCode;
    }

    public JobLevelClassifyBO setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public JobLevelClassifyBO setVersion(Integer version) {
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