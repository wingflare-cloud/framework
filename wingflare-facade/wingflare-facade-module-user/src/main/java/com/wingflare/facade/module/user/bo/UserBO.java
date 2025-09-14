package com.wingflare.facade.module.user.bo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wingflare.api.core.validate.Update;
import com.wingflare.api.security.annotation.Decrypt;
import jakarta.validation.constraints.Min;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 系统用户Bo
 *
 * @author naizui_ycx
 * @date Tue Mar 07 17:34:13 CST 2023
 */
public class UserBO {

    @Min(message = "user.userId.error", value = 1, groups = Update.class)
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
    private List<String> accountType;

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
    @Decrypt(type = "RSA")
    private String userPasswd;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 用户角色
     */
    private List<BigInteger> userRole;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    private Integer version;

    public UserBO setUserId(BigInteger userId) {
        this.userId = userId;
        return this;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public UserBO setSuperAdministrator(Integer superAdministrator) {
        this.superAdministrator = superAdministrator;
        return this;
    }

    public Integer getSuperAdministrator() {
        return superAdministrator;
    }

    public UserBO setBanState(Integer banState) {
        this.banState = banState;
        return this;
    }

    public Integer getBanState() {
        return banState;
    }

    public UserBO setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public UserBO setUserChannel(String userChannel) {
        this.userChannel = userChannel;
        return this;
    }

    public String getUserChannel() {
        return userChannel;
    }

    public UserBO setAccountType(List<String> accountType) {
        this.accountType = accountType;
        return this;
    }

    public List<String> getAccountType() {
        return accountType;
    }

    public UserBO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserBO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserBO setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public UserBO setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public UserBO setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserBO setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
        return this;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public UserBO setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
        return this;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public List<BigInteger> getUserRole() {
        return userRole;
    }

    public UserBO setUserRole(List<BigInteger> userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserBO setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public UserBO setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
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
                .append("version", getVersion())
                .toString();
    }

}
