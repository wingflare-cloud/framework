package com.wingflare.facade.module.user.bo;


/**
 * <p>
 * 岗位身份数据表 业务对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class IdentityBo {

    private String identityId;

    /**
     * 身份代码 
     */
    private String identityCode;

    /**
     * 组织机构id 
     */
    private String orgId;

    /**
     * 部门id 
     */
    private String departmentId;

    /**
     * 岗位名称 
     */
    private String identityName;

    /**
     * 职级id 
     */
    private String jobLevelId;

    private Integer version;

    public String getIdentityId() {
        return identityId;
    }

    public IdentityBo setIdentityId(String identityId) {
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

    public String getOrgId() {
        return orgId;
    }

    public IdentityBo setOrgId(String orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public IdentityBo setDepartmentId(String departmentId) {
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

    public String getJobLevelId() {
        return jobLevelId;
    }

    public IdentityBo setJobLevelId(String jobLevelId) {
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