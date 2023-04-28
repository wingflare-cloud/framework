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
 * 登陆信息Do
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
@TableName("sys_login_info")
public class LoginInfoDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
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
    private Date expireTime;

	@TableField(fill = FieldFill.INSERT)
    private Date createdTime;

	@Override
	public void setPk(String loginId)
	{
		setLoginId(loginId);
	}

	@Override
	public String getPk()
	{
		return getLoginId();
	}

	
    public LoginInfoDo setLoginId(String loginId)
    {
		addNewField("loginId");
        this.loginId = loginId;
        return this;
    }

    public String getLoginId()
    {
        return loginId;
    }
	
    public LoginInfoDo setSystemCode(String systemCode)
    {
		addNewField("systemCode");
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public LoginInfoDo setUserId(String userId)
    {
		addNewField("userId");
        this.userId = userId;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }
	
    public LoginInfoDo setOrgId(String orgId)
    {
		addNewField("orgId");
        this.orgId = orgId;
        return this;
    }

    public String getOrgId()
    {
        return orgId;
    }
	
    public LoginInfoDo setIdentityId(String identityId)
    {
		addNewField("identityId");
        this.identityId = identityId;
        return this;
    }

    public String getIdentityId()
    {
        return identityId;
    }
	
    public LoginInfoDo setRefreshToken(String refreshToken)
    {
		addNewField("refreshToken");
        this.refreshToken = refreshToken;
        return this;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }
	
    public LoginInfoDo setUserAgent(String userAgent)
    {
		addNewField("userAgent");
        this.userAgent = userAgent;
        return this;
    }

    public String getUserAgent()
    {
        return userAgent;
    }
	
    public LoginInfoDo setIpaddr(String ipaddr)
    {
		addNewField("ipaddr");
        this.ipaddr = ipaddr;
        return this;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }
	
    public LoginInfoDo setExpireTime(Date expireTime)
    {
		addNewField("expireTime");
        this.expireTime = expireTime;
        return this;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }
	
    public LoginInfoDo setCreatedTime(Date createdTime)
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
	public void clearNullNewField()
	{

		if (getLoginId() == null) {
			removeNewField("loginId");
		}

		if (getSystemCode() == null) {
			removeNewField("systemCode");
		}

		if (getUserId() == null) {
			removeNewField("userId");
		}

		if (getOrgId() == null) {
			removeNewField("orgId");
		}

		if (getIdentityId() == null) {
			removeNewField("identityId");
		}

		if (getRefreshToken() == null) {
			removeNewField("refreshToken");
		}

		if (getUserAgent() == null) {
			removeNewField("userAgent");
		}

		if (getIpaddr() == null) {
			removeNewField("ipaddr");
		}

		if (getExpireTime() == null) {
			removeNewField("expireTime");
		}

		if (getCreatedTime() == null) {
			removeNewField("createdTime");
		}
	}

	public LoginInfoDo setOnNew(LoginInfoDo newDo)
	{
	    LoginInfoDo oldFieldObj = new LoginInfoDo();

		if (newDo.getSystemCode() != null && !newDo.getSystemCode().equals(getSystemCode())) {
			oldFieldObj.setSystemCode(getSystemCode());
			setSystemCode(newDo.getSystemCode());
		}

		if (newDo.getUserId() != null && !newDo.getUserId().equals(getUserId())) {
			oldFieldObj.setUserId(getUserId());
			setUserId(newDo.getUserId());
		}

		if (newDo.getOrgId() != null && !newDo.getOrgId().equals(getOrgId())) {
			oldFieldObj.setOrgId(getOrgId());
			setOrgId(newDo.getOrgId());
		}

		if (newDo.getIdentityId() != null && !newDo.getIdentityId().equals(getIdentityId())) {
			oldFieldObj.setIdentityId(getIdentityId());
			setIdentityId(newDo.getIdentityId());
		}

		if (newDo.getRefreshToken() != null && !newDo.getRefreshToken().equals(getRefreshToken())) {
			oldFieldObj.setRefreshToken(getRefreshToken());
			setRefreshToken(newDo.getRefreshToken());
		}

		if (newDo.getUserAgent() != null && !newDo.getUserAgent().equals(getUserAgent())) {
			oldFieldObj.setUserAgent(getUserAgent());
			setUserAgent(newDo.getUserAgent());
		}

		if (newDo.getIpaddr() != null && !newDo.getIpaddr().equals(getIpaddr())) {
			oldFieldObj.setIpaddr(getIpaddr());
			setIpaddr(newDo.getIpaddr());
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
