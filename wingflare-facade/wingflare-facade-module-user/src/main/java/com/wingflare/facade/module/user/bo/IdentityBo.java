package com.wingflare.facade.module.user.bo;


import java.math.BigInteger;

/**
 * <p>
 * 岗位身份数据表 业务对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class IdentityBo {

    private BigInteger identityId;

    /**
     * 身份代码 
     */
    private String identityCode;

    /**
     * 组织机构id 
     */
    private BigInteger orgId;

    /**
     * 部门id 
     */
    private BigInteger departmentId;

    /**
     * 岗位名称 
     */
    private String identityName;

    /**
     * 职级id 
     */
    private BigInteger jobLevelId;

    private Integer version;

    public BigInteger getIdentityId() {
        return identityId;
    }

    public IdentityBo setIdentityId(BigInteger identityId) {
        this.identityId = identityId;
        return this;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public IdentityBo setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
        return this;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public IdentityBo setOrgId(BigInteger orgId) {
        this.orgId = orgId;
        return this;
    }

    public BigInteger getDepartmentId() {
        return departmentId;
    }

    public IdentityBo setDepartmentId(BigInteger departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getIdentityName() {
        return identityName;
    }

    public IdentityBo setIdentityName(String identityName) {
        this.identityName = identityName;
        return this;
    }

    public BigInteger getJobLevelId() {
        return jobLevelId;
    }

    public IdentityBo setJobLevelId(BigInteger jobLevelId) {
        this.jobLevelId = jobLevelId;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public IdentityBo setVersion(Integer version) {
        this.version = version;
        return this;
    }

	@Override
	public String toString() {
        return "IdentityDo{" +
        "identityId = " + identityId +
        ", identityCode = " + identityCode +
        ", orgId = " + orgId +
        ", departmentId = " + departmentId +
        ", identityName = " + identityName +
        ", jobLevelId = " + jobLevelId +
        ", version = " + version +
        "}";
    }

}