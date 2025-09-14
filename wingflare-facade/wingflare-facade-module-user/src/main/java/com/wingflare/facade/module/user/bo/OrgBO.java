package com.wingflare.facade.module.user.bo;


import com.wingflare.api.core.validate.Update;
import jakarta.validation.constraints.Min;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.math.BigInteger;

/**
 * 组织机构Bo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
public class OrgBO
{

    @Min(message = "org.orgId.error", value = 1, groups = Update.class)
    private BigInteger orgId;

	/**
     * 父级机构id
     */
    private BigInteger parentOrgId;

	/**
     * 组织状态
     */
    private Integer state;

	/**
     * 组织类型
     */
    private String orgType;

	/**
     * 组织名称
     */
    private String orgName;

	/**
     * 组织手机或固话号
     */
    private String orgPhone;

	/**
     * 详细地址
     */
    private String orgAddress;

	/**
     * 机构基础角色id
     */
    private BigInteger roleId;

	/**
     * 机构主要负责人id
     */
    private BigInteger userId;

    private Integer version;
    
	public OrgBO setOrgId(BigInteger orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public BigInteger getOrgId()
    {
        return orgId;
    }
    
	public OrgBO setParentOrgId(BigInteger parentOrgId)
    {
        this.parentOrgId = parentOrgId;
        return this;
    }

    public BigInteger getParentOrgId()
    {
        return parentOrgId;
    }
    
	public OrgBO setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public OrgBO setOrgType(String orgType)
    {
        this.orgType = orgType;
        return this;
    }

    public String getOrgType()
    {
        return orgType;
    }
    
	public OrgBO setOrgName(String orgName)
    {
        this.orgName = orgName;
        return this;
    }

    public String getOrgName()
    {
        return orgName;
    }
    
	public OrgBO setOrgPhone(String orgPhone)
    {
        this.orgPhone = orgPhone;
        return this;
    }

    public String getOrgPhone()
    {
        return orgPhone;
    }
    
	public OrgBO setOrgAddress(String orgAddress)
    {
        this.orgAddress = orgAddress;
        return this;
    }

    public String getOrgAddress()
    {
        return orgAddress;
    }
    
	public OrgBO setRoleId(BigInteger roleId)
    {
        this.roleId = roleId;
        return this;
    }

    public BigInteger getRoleId()
    {
        return roleId;
    }
    
	public OrgBO setUserId(BigInteger userId)
    {
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId()
    {
        return userId;
    }
    
	public OrgBO setVersion(Integer version)
    {
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orgId", getOrgId())
            .append("parentOrgId", getParentOrgId())
            .append("state", getState())
            .append("orgType", getOrgType())
            .append("orgName", getOrgName())
            .append("orgPhone", getOrgPhone())
            .append("orgAddress", getOrgAddress())
            .append("roleId", getRoleId())
            .append("userId", getUserId())
            .append("version", getVersion())
            .toString();
    }
	
}
