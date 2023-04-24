package com.wingflare.facade.module.auth.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 登陆信息Dto
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
public class LoginInfoDto
{
	
    private String loginId;
	
    private String systemCode;
	
	/**
     * 用户id
     */
    private String userId;
	
	/**
     * 机构id
     */
    private String orgId;
	
	/**
     * 身份id
     */
    private String identityId;
	
	/**
     * refresh_token
     */
    private String refreshToken;
	
	/**
     * ua
     */
    private String userAgent;
	
	/**
     * 登陆ip
     */
    private String ipaddr;
	
	/**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    public LoginInfoDto setLoginId(String loginId)
    {
        this.loginId = loginId;
        return this;
    }

    public String getLoginId()
    {
        return loginId;
    }
	
    public LoginInfoDto setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public LoginInfoDto setUserId(String userId)
    {
        this.userId = userId;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }
	
    public LoginInfoDto setOrgId(String orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public String getOrgId()
    {
        return orgId;
    }
	
    public LoginInfoDto setIdentityId(String identityId)
    {
        this.identityId = identityId;
        return this;
    }

    public String getIdentityId()
    {
        return identityId;
    }
	
    public LoginInfoDto setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }
	
    public LoginInfoDto setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
        return this;
    }

    public String getUserAgent()
    {
        return userAgent;
    }
	
    public LoginInfoDto setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
        return this;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }
	
    public LoginInfoDto setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
        return this;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }
	
    public LoginInfoDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("loginId", getLoginId())
            .append("systemCode", getSystemCode())
            .append("userId", getUserId())
            .append("orgId", getOrgId())
            .append("identityId", getIdentityId())
            .append("refreshToken", getRefreshToken())
            .append("userAgent", getUserAgent())
            .append("ipaddr", getIpaddr())
            .append("expireTime", getExpireTime())
            .append("createdTime", getCreatedTime())
            .toString();
    }
	
}
