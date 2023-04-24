package com.wingflare.facade.module.auth.bo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wingflare.lib.core.validation.Update;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 登陆tokenBo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
public class LoginTokenBo
{

    @NotBlank(message = "auth.tokenId.notBlank", groups = Update.class)
    @Pattern(regexp = "^$|^[0-9a-zA-Z]{1,32}$", message = "auth.tokenId.formatError", groups = Update.class)
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
    
	public LoginTokenBo setTokenId(String tokenId)
    {
        this.tokenId = tokenId;
        return this;
    }

    public String getTokenId()
    {
        return tokenId;
    }
    
	public LoginTokenBo setLoginId(String loginId)
    {
        this.loginId = loginId;
        return this;
    }

    public String getLoginId()
    {
        return loginId;
    }
    
	public LoginTokenBo setTokenKey(String tokenKey)
    {
        this.tokenKey = tokenKey;
        return this;
    }

    public String getTokenKey()
    {
        return tokenKey;
    }
    
	public LoginTokenBo setExpireTime(Date expireTime)
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
            .append("tokenId", getTokenId())
            .append("loginId", getLoginId())
            .append("tokenKey", getTokenKey())
            .append("expireTime", getExpireTime())
            .toString();
    }
	
}
