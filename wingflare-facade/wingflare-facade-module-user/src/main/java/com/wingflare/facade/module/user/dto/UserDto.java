package com.wingflare.facade.module.user.dto;


import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 系统用户Dto
 * 
 * @author naizui_ycx
 * @date Tue Mar 07 17:34:13 CST 2023
 */
public class UserDto
{
	
    private String userId;


    private String userRoleId;
	
	/**
     * 是否为超管
     */
    private Integer superAdministrator;
	
	/**
     * 账户起禁用状态
     */
    private Integer banState;
	
	/**
     * 性别
     */
    private Integer sex;
	
	/**
     * 用户注册渠道系统
     */
    private String userChannel;
	
	/**
     * 账户类型
     */
    private JSONArray accountType;
	
	/**
     * 用户名
     */
    private String userName;
	
	/**
     * 用户头像
     */
    private String avatar;
	
	/**
     * 登录账户
     */
    private String userAccount;
	
	/**
     * 手机号
     */
    private String userPhone;
	
	/**
     * 邮箱号
     */
    private String userEmail;
	
	/**
     * 账户密码
     */
    private String userPasswd;
	
	/**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 用户角色
     */
    private List<String> userRole;
	
	/**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
	
    private String createUser;
	
    private String createUserId;
	
    private String updateUser;
	
    private String updateUserId;
	
    private Integer version;
	
    public UserDto setUserId(String userId)
    {
        this.userId = userId;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(String userRoleId) {
        this.userRoleId = userRoleId;
    }

    public UserDto setSuperAdministrator(Integer superAdministrator)
    {
        this.superAdministrator = superAdministrator;
        return this;
    }

    public Integer getSuperAdministrator()
    {
        return superAdministrator;
    }
	
    public UserDto setBanState(Integer banState)
    {
        this.banState = banState;
        return this;
    }

    public Integer getBanState()
    {
        return banState;
    }
	
    public UserDto setSex(Integer sex)
    {
        this.sex = sex;
        return this;
    }

    public Integer getSex()
    {
        return sex;
    }
	
    public UserDto setUserChannel(String userChannel)
    {
        this.userChannel = userChannel;
        return this;
    }

    public String getUserChannel()
    {
        return userChannel;
    }
	
    public UserDto setAccountType(JSONArray accountType)
    {
        this.accountType = accountType;
        return this;
    }

    public JSONArray getAccountType()
    {
        return accountType;
    }
	
    public UserDto setUserName(String userName)
    {
        this.userName = userName;
        return this;
    }

    public String getUserName()
    {
        return userName;
    }
	
    public UserDto setAvatar(String avatar)
    {
        this.avatar = avatar;
        return this;
    }

    public String getAvatar()
    {
        return avatar;
    }
	
    public UserDto setUserAccount(String userAccount)
    {
        this.userAccount = userAccount;
        return this;
    }

    public String getUserAccount()
    {
        return userAccount;
    }
	
    public UserDto setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
        return this;
    }

    public String getUserPhone()
    {
        return userPhone;
    }
	
    public UserDto setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserEmail()
    {
        return userEmail;
    }
	
    public UserDto setUserPasswd(String userPasswd)
    {
        this.userPasswd = userPasswd;
        return this;
    }

    public String getUserPasswd()
    {
        return userPasswd;
    }
	
    public UserDto setLastLoginIp(String lastLoginIp)
    {
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public String getLastLoginIp()
    {
        return lastLoginIp;
    }

    public List<String> getUserRole() {
        return userRole;
    }

    public UserDto setUserRole(List<String> userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserDto setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
	
    public UserDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public UserDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public UserDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public UserDto setCreateUserId(String createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public UserDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public UserDto setUpdateUserId(String updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public UserDto setVersion(Integer version)
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
            .append("userId", getUserId())
            .append("superAdministrator", getSuperAdministrator())
            .append("banState", getBanState())
            .append("sex", getSex())
            .append("userChannel", getUserChannel())
            .append("accountType", getAccountType())
            .append("userName", getUserName())
            .append("avatar", getAvatar())
            .append("userAccount", getUserAccount())
            .append("userPhone", getUserPhone())
            .append("userEmail", getUserEmail())
            .append("userPasswd", getUserPasswd())
            .append("lastLoginIp", getLastLoginIp())
            .append("lastLoginTime", getLastLoginTime())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .append("updateUser", getUpdateUser())
            .append("updateUserId", getUpdateUserId())
            .append("version", getVersion())
            .toString();
    }
	
}
