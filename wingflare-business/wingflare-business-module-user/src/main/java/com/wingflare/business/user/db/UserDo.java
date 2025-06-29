package com.wingflare.business.user.db;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Date;

/**
 * 系统用户Do
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 19:05:46 CST 2023
 */
@TableName("sys_user")
public class UserDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
    private BigInteger userId;

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
     * 最后登录时间
     */
    private Date lastLoginTime;

	@TableField(fill = FieldFill.INSERT)
    private Date createdTime;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

	@TableField(fill = FieldFill.INSERT)
    private String createUser;

	@TableField(fill = FieldFill.INSERT)
    private BigInteger createUserId;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

	@TableField(fill = FieldFill.INSERT_UPDATE)
    private BigInteger updateUserId;

	@TableLogic
	@TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

	@Version
	@TableField(fill = FieldFill.INSERT)
    private Integer version;

	@Override
	public void setPk(BigInteger userId)
	{
		setUserId(userId);
	}

	@Override
	public BigInteger getPk()
	{
		return getUserId();
	}

	
    public UserDo setUserId(BigInteger userId)
    {
		addNewField("userId");
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId()
    {
        return userId;
    }
    
    public UserDo setSuperAdministrator(Integer superAdministrator)
    {
		addNewField("superAdministrator");
        this.superAdministrator = superAdministrator;
        return this;
    }

    public Integer getSuperAdministrator()
    {
        return superAdministrator;
    }
	
    public UserDo setBanState(Integer banState)
    {
		addNewField("banState");
        this.banState = banState;
        return this;
    }

    public Integer getBanState()
    {
        return banState;
    }
	
    public UserDo setSex(Integer sex)
    {
		addNewField("sex");
        this.sex = sex;
        return this;
    }

    public Integer getSex()
    {
        return sex;
    }
	
    public UserDo setUserChannel(String userChannel)
    {
		addNewField("userChannel");
        this.userChannel = userChannel;
        return this;
    }

    public String getUserChannel()
    {
        return userChannel;
    }
	
    public UserDo setAccountType(JSONArray accountType)
    {
		addNewField("accountType");
        this.accountType = accountType;
        return this;
    }

    public JSONArray getAccountType()
    {
        return accountType;
    }
	
    public UserDo setUserName(String userName)
    {
		addNewField("userName");
        this.userName = userName;
        return this;
    }

    public String getUserName()
    {
        return userName;
    }
	
    public UserDo setAvatar(String avatar)
    {
		addNewField("avatar");
        this.avatar = avatar;
        return this;
    }

    public String getAvatar()
    {
        return avatar;
    }
	
    public UserDo setUserAccount(String userAccount)
    {
		addNewField("userAccount");
        this.userAccount = userAccount;
        return this;
    }

    public String getUserAccount()
    {
        return userAccount;
    }
	
    public UserDo setUserPhone(String userPhone)
    {
		addNewField("userPhone");
        this.userPhone = userPhone;
        return this;
    }

    public String getUserPhone()
    {
        return userPhone;
    }
	
    public UserDo setUserEmail(String userEmail)
    {
		addNewField("userEmail");
        this.userEmail = userEmail;
        return this;
    }

    public String getUserEmail()
    {
        return userEmail;
    }
	
    public UserDo setUserPasswd(String userPasswd)
    {
		addNewField("userPasswd");
        this.userPasswd = userPasswd;
        return this;
    }

    public String getUserPasswd()
    {
        return userPasswd;
    }
	
    public UserDo setLastLoginIp(String lastLoginIp)
    {
		addNewField("lastLoginIp");
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public String getLastLoginIp()
    {
        return lastLoginIp;
    }
	
    public UserDo setLastLoginTime(Date lastLoginTime)
    {
		addNewField("lastLoginTime");
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public Date getLastLoginTime()
    {
        return lastLoginTime;
    }
	
    public UserDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public UserDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public UserDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public UserDo setCreateUserId(BigInteger createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public UserDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public UserDo setUpdateUserId(BigInteger updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public UserDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public UserDo setVersion(Integer version)
    {
		addNewField("version");
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }

	@Override
	public void clearNullNewField()
	{

		if (getUserId() == null) {
			removeNewField("userId");
		}

		if (getSuperAdministrator() == null) {
			removeNewField("superAdministrator");
		}

		if (getBanState() == null) {
			removeNewField("banState");
		}

		if (getSex() == null) {
			removeNewField("sex");
		}

		if (getUserChannel() == null) {
			removeNewField("userChannel");
		}

		if (getAccountType() == null) {
			removeNewField("accountType");
		}

		if (getUserName() == null) {
			removeNewField("userName");
		}

		if (getAvatar() == null) {
			removeNewField("avatar");
		}

		if (getUserAccount() == null) {
			removeNewField("userAccount");
		}

		if (getUserPhone() == null) {
			removeNewField("userPhone");
		}

		if (getUserEmail() == null) {
			removeNewField("userEmail");
		}

		if (getUserPasswd() == null) {
			removeNewField("userPasswd");
		}

		if (getLastLoginIp() == null) {
			removeNewField("lastLoginIp");
		}

		if (getLastLoginTime() == null) {
			removeNewField("lastLoginTime");
		}

		if (getCreatedTime() == null) {
			removeNewField("createdTime");
		}

		if (getUpdatedTime() == null) {
			removeNewField("updatedTime");
		}

		if (getCreateUser() == null) {
			removeNewField("createUser");
		}

		if (getCreateUserId() == null) {
			removeNewField("createUserId");
		}

		if (getUpdateUser() == null) {
			removeNewField("updateUser");
		}

		if (getUpdateUserId() == null) {
			removeNewField("updateUserId");
		}

		if (getIsDelete() == null) {
			removeNewField("isDelete");
		}

		if (getVersion() == null) {
			removeNewField("version");
		}
	}

	public UserDo setOnNew(UserDo newDo)
	{
	    UserDo oldFieldObj = new UserDo();

		if (newDo.getSuperAdministrator() != null && !newDo.getSuperAdministrator().equals(getSuperAdministrator())) {
			oldFieldObj.setSuperAdministrator(getSuperAdministrator());
			setSuperAdministrator(newDo.getSuperAdministrator());
		}

		if (newDo.getBanState() != null && !newDo.getBanState().equals(getBanState())) {
			oldFieldObj.setBanState(getBanState());
			setBanState(newDo.getBanState());
		}

		if (newDo.getSex() != null && !newDo.getSex().equals(getSex())) {
			oldFieldObj.setSex(getSex());
			setSex(newDo.getSex());
		}

		if (newDo.getUserChannel() != null && !newDo.getUserChannel().equals(getUserChannel())) {
			oldFieldObj.setUserChannel(getUserChannel());
			setUserChannel(newDo.getUserChannel());
		}

		if (newDo.getAccountType() != null && !newDo.getAccountType().equals(getAccountType())) {
			oldFieldObj.setAccountType(getAccountType());
			setAccountType(newDo.getAccountType());
		}

		if (newDo.getUserName() != null && !newDo.getUserName().equals(getUserName())) {
			oldFieldObj.setUserName(getUserName());
			setUserName(newDo.getUserName());
		}

		if (newDo.getAvatar() != null && !newDo.getAvatar().equals(getAvatar())) {
			oldFieldObj.setAvatar(getAvatar());
			setAvatar(newDo.getAvatar());
		}

		if (newDo.getUserAccount() != null && !newDo.getUserAccount().equals(getUserAccount())) {
			oldFieldObj.setUserAccount(getUserAccount());
			setUserAccount(newDo.getUserAccount());
		}

		if (newDo.getUserPhone() != null && !newDo.getUserPhone().equals(getUserPhone())) {
			oldFieldObj.setUserPhone(getUserPhone());
			setUserPhone(newDo.getUserPhone());
		}

		if (newDo.getUserEmail() != null && !newDo.getUserEmail().equals(getUserEmail())) {
			oldFieldObj.setUserEmail(getUserEmail());
			setUserEmail(newDo.getUserEmail());
		}

		if (newDo.getUserPasswd() != null && !newDo.getUserPasswd().equals(getUserPasswd())) {
			oldFieldObj.setUserPasswd(getUserPasswd());
			setUserPasswd(newDo.getUserPasswd());
		}

		if (newDo.getLastLoginIp() != null && !newDo.getLastLoginIp().equals(getLastLoginIp())) {
			oldFieldObj.setLastLoginIp(getLastLoginIp());
			setLastLoginIp(newDo.getLastLoginIp());
		}

		if (newDo.getLastLoginTime() != null && !newDo.getLastLoginTime().equals(getLastLoginTime())) {
			oldFieldObj.setLastLoginTime(getLastLoginTime());
			setLastLoginTime(newDo.getLastLoginTime());
		}

		if (newDo.getVersion() != null && !newDo.getVersion().equals(getVersion())) {
			oldFieldObj.setVersion(getVersion());
			setVersion(newDo.getVersion());
		}

		if (!oldFieldObj.hasNewField()) {
		    return null;
        }
		
		return oldFieldObj;
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
            .append("isDelete", getIsDelete())
            .append("version", getVersion())
            .toString();
    }
	
}
