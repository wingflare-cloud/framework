package com.wingflare.facade.module.auth.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 登陆tokenDto
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
public class LoginTokenDto
{
	
    private String tokenId;
	
	/**
     * 登陆id
     */
    private String loginId;
	
	/**
     * tokenKey
     */
    private String tokenKey;
	
	/**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    public LoginTokenDto setTokenId(String tokenId)
    {
        this.tokenId = tokenId;
        return this;
    }

    public String getTokenId()
    {
        return tokenId;
    }
	
    public LoginTokenDto setLoginId(String loginId)
    {
        this.loginId = loginId;
        return this;
    }

    public String getLoginId()
    {
        return loginId;
    }
	
    public LoginTokenDto setTokenKey(String tokenKey)
    {
        this.tokenKey = tokenKey;
        return this;
    }

    public String getTokenKey()
    {
        return tokenKey;
    }
	
    public LoginTokenDto setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
        return this;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }
	
    public LoginTokenDto setCreatedTime(Date createdTime)
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
            .append("tokenId", getTokenId())
            .append("loginId", getLoginId())
            .append("tokenKey", getTokenKey())
            .append("expireTime", getExpireTime())
            .append("createdTime", getCreatedTime())
            .toString();
    }
	
}
