package com.wingflare.facade.module.auth.bo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 登陆信息Bo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
public class LoginInfoBo
{

    @NotBlank(message = "auth.loginId.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "auth.loginId.formatError", groups = Update.class)
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
    
	public LoginInfoBo setLoginId(String loginId)
    {
        this.loginId = loginId;
        return this;
    }

    public String getLoginId()
    {
        return loginId;
    }
    
	public LoginInfoBo setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
    
	public LoginInfoBo setUserId(String userId)
    {
        this.userId = userId;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }
    
	public LoginInfoBo setOrgId(String orgId)
    {
        this.orgId = orgId;
        return this;
    }

    public String getOrgId()
    {
        return orgId;
    }
    
	public LoginInfoBo setIdentityId(String identityId)
    {
        this.identityId = identityId;
        return this;
    }

    public String getIdentityId()
    {
        return identityId;
    }
    
	public LoginInfoBo setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }
    
	public LoginInfoBo setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
        return this;
    }

    public String getUserAgent()
    {
        return userAgent;
    }
    
	public LoginInfoBo setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
        return this;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }
    
	public LoginInfoBo setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
        return this;
    }

    public Date getExpireTime()
    {
        return expireTime;
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
            .toString();
    }
	
}
