package com.wingflare.business.auth.db;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 登陆tokenDo
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
@TableName("sys_login_token")
public class LoginTokenDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
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
    private Date expireTime;

	@TableField(fill = FieldFill.INSERT)
    private Date createdTime;

	@Override
	public LoginTokenDo setPk(String tokenId)
	{
		setTokenId(tokenId);
		return this;
	}

	@Override
	public String getPk()
	{
		return getTokenId();
	}

	
    public LoginTokenDo setTokenId(String tokenId)
    {
		addNewField("tokenId");
        this.tokenId = tokenId;
        return this;
    }

    public String getTokenId()
    {
        return tokenId;
    }
	
    public LoginTokenDo setLoginId(String loginId)
    {
		addNewField("loginId");
        this.loginId = loginId;
        return this;
    }

    public String getLoginId()
    {
        return loginId;
    }
	
    public LoginTokenDo setTokenKey(String tokenKey)
    {
		addNewField("tokenKey");
        this.tokenKey = tokenKey;
        return this;
    }

    public String getTokenKey()
    {
        return tokenKey;
    }
	
    public LoginTokenDo setExpireTime(Date expireTime)
    {
		addNewField("expireTime");
        this.expireTime = expireTime;
        return this;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }
	
    public LoginTokenDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

	@Override
	public LoginTokenDo clearNullNewField()
	{

		if (getTokenId() == null) {
			removeNewField("tokenId");
		}

		if (getLoginId() == null) {
			removeNewField("loginId");
		}

		if (getTokenKey() == null) {
			removeNewField("tokenKey");
		}

		if (getExpireTime() == null) {
			removeNewField("expireTime");
		}

		if (getCreatedTime() == null) {
			removeNewField("createdTime");
		}

		return this;
	}

	public LoginTokenDo setOnNew(LoginTokenDo newDo)
	{
	    LoginTokenDo oldFieldObj = new LoginTokenDo();

		if (newDo.getLoginId() != null && !newDo.getLoginId().equals(getLoginId())) {
			oldFieldObj.setLoginId(getLoginId());
			setLoginId(newDo.getLoginId());
		}

		if (newDo.getTokenKey() != null && !newDo.getTokenKey().equals(getTokenKey())) {
			oldFieldObj.setTokenKey(getTokenKey());
			setTokenKey(newDo.getTokenKey());
		}

		if (newDo.getExpireTime() != null && !newDo.getExpireTime().equals(getExpireTime())) {
			oldFieldObj.setExpireTime(getExpireTime());
			setExpireTime(newDo.getExpireTime());
		}

		if (!oldFieldObj.hasNewField()) {
			return null;
		}
		
		return oldFieldObj;
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
