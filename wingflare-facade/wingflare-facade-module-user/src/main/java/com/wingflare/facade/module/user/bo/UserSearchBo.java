package com.wingflare.facade.module.user.bo;


import com.wingflare.lib.standard.BaseSearchBo;

import java.util.Date;

/**
 * UserSearchBo
 *
 * @author naizui_ycx
 * @date Tue Mar 07 17:34:13 CST 2023
 */
public class UserSearchBo extends BaseSearchBo {

    private String eq_userId;

    private String neq_userId;

    private String in_userId;

    private String roleId;

    private String notin_userId;

    private Integer eq_superAdministrator;

    private Integer neq_superAdministrator;

    private Integer gt_superAdministrator;

    private Integer lt_superAdministrator;

    private Integer egt_superAdministrator;

    private Integer elt_superAdministrator;

    private String between_superAdministrator;

    private String notbetween_superAdministrator;

    private String in_superAdministrator;

    private String notin_superAdministrator;

    private Integer eq_banState;

    private Integer neq_banState;

    private Integer gt_banState;

    private Integer lt_banState;

    private Integer egt_banState;

    private Integer elt_banState;

    private String between_banState;

    private String notbetween_banState;

    private String in_banState;

    private String notin_banState;

    private Integer eq_sex;

    private Integer neq_sex;

    private Integer gt_sex;

    private Integer lt_sex;

    private Integer egt_sex;

    private Integer elt_sex;

    private String between_sex;

    private String notbetween_sex;

    private String in_sex;

    private String notin_sex;

    private String eq_userChannel;

    private String neq_userChannel;

    private String like_userChannel;

    private String liker_userChannel;

    private String likel_userChannel;

    private String notlike_userChannel;

    private String notliker_userChannel;

    private String notlikel_userChannel;

    private String in_userChannel;

    private String notin_userChannel;

    private String eq_accountType;

    private String neq_accountType;

    private String like_accountType;

    private String liker_accountType;

    private String likel_accountType;

    private String notlike_accountType;

    private String notliker_accountType;

    private String notlikel_accountType;

    private String in_accountType;

    private String notin_accountType;

    private String eq_userName;

    private String neq_userName;

    private String like_userName;

    private String liker_userName;

    private String likel_userName;

    private String notlike_userName;

    private String notliker_userName;

    private String notlikel_userName;

    private String in_userName;

    private String notin_userName;

    private String eq_avatar;

    private String neq_avatar;

    private String like_avatar;

    private String liker_avatar;

    private String likel_avatar;

    private String notlike_avatar;

    private String notliker_avatar;

    private String notlikel_avatar;

    private String in_avatar;

    private String notin_avatar;

    private String eq_userAccount;

    private String neq_userAccount;

    private String like_userAccount;

    private String liker_userAccount;

    private String likel_userAccount;

    private String notlike_userAccount;

    private String notliker_userAccount;

    private String notlikel_userAccount;

    private String in_userAccount;

    private String notin_userAccount;

    private String eq_userPhone;

    private String neq_userPhone;

    private String like_userPhone;

    private String liker_userPhone;

    private String likel_userPhone;

    private String notlike_userPhone;

    private String notliker_userPhone;

    private String notlikel_userPhone;

    private String in_userPhone;

    private String notin_userPhone;

    private String eq_userEmail;

    private String neq_userEmail;

    private String like_userEmail;

    private String liker_userEmail;

    private String likel_userEmail;

    private String notlike_userEmail;

    private String notliker_userEmail;

    private String notlikel_userEmail;

    private String in_userEmail;

    private String notin_userEmail;

    private String eq_userPasswd;

    private String neq_userPasswd;

    private String like_userPasswd;

    private String liker_userPasswd;

    private String likel_userPasswd;

    private String notlike_userPasswd;

    private String notliker_userPasswd;

    private String notlikel_userPasswd;

    private String in_userPasswd;

    private String notin_userPasswd;

    private String eq_lastLoginIp;

    private String neq_lastLoginIp;

    private String like_lastLoginIp;

    private String liker_lastLoginIp;

    private String likel_lastLoginIp;

    private String notlike_lastLoginIp;

    private String notliker_lastLoginIp;

    private String notlikel_lastLoginIp;

    private String in_lastLoginIp;

    private String notin_lastLoginIp;

    private Date eq_lastLoginTime;

    private Date neq_lastLoginTime;

    private Date gt_lastLoginTime;

    private Date lt_lastLoginTime;

    private Date egt_lastLoginTime;

    private Date elt_lastLoginTime;

    private String between_lastLoginTime;

    private String notbetween_lastLoginTime;

    private String in_lastLoginTime;

    private String notin_lastLoginTime;

    private Date eq_createdTime;

    private Date neq_createdTime;

    private Date gt_createdTime;

    private Date lt_createdTime;

    private Date egt_createdTime;

    private Date elt_createdTime;

    private String between_createdTime;

    private String notbetween_createdTime;

    private String in_createdTime;

    private String notin_createdTime;

    private Date eq_updatedTime;

    private Date neq_updatedTime;

    private Date gt_updatedTime;

    private Date lt_updatedTime;

    private Date egt_updatedTime;

    private Date elt_updatedTime;

    private String between_updatedTime;

    private String notbetween_updatedTime;

    private String in_updatedTime;

    private String notin_updatedTime;

    private String eq_createUser;

    private String neq_createUser;

    private String like_createUser;

    private String liker_createUser;

    private String likel_createUser;

    private String notlike_createUser;

    private String notliker_createUser;

    private String notlikel_createUser;

    private String in_createUser;

    private String notin_createUser;

    private String eq_createUserId;

    private String neq_createUserId;

    private String like_createUserId;

    private String liker_createUserId;

    private String likel_createUserId;

    private String notlike_createUserId;

    private String notliker_createUserId;

    private String notlikel_createUserId;

    private String in_createUserId;

    private String notin_createUserId;

    private String eq_updateUser;

    private String neq_updateUser;

    private String like_updateUser;

    private String liker_updateUser;

    private String likel_updateUser;

    private String notlike_updateUser;

    private String notliker_updateUser;

    private String notlikel_updateUser;

    private String in_updateUser;

    private String notin_updateUser;

    private String eq_updateUserId;

    private String neq_updateUserId;

    private String like_updateUserId;

    private String liker_updateUserId;

    private String likel_updateUserId;

    private String notlike_updateUserId;

    private String notliker_updateUserId;

    private String notlikel_updateUserId;

    private String in_updateUserId;

    private String notin_updateUserId;


    public String getEq_userId() {
        return eq_userId;
    }

    public UserSearchBo setEq_userId(String eq_userId) {
        this.eq_userId = eq_userId;
        return this;
    }

    public String getNeq_userId() {
        return neq_userId;
    }

    public UserSearchBo setNeq_userId(String neq_userId) {
        this.neq_userId = neq_userId;
        return this;
    }

    public String getIn_userId() {
        return in_userId;
    }

    public UserSearchBo setIn_userId(String in_userId) {
        this.in_userId = in_userId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public UserSearchBo setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getNotin_userId() {
        return notin_userId;
    }

    public void setNotin_userId(String notin_userId) {
        this.notin_userId = notin_userId;
    }

    public Integer getEq_superAdministrator() {
        return eq_superAdministrator;
    }

    public UserSearchBo setEq_superAdministrator(Integer eq_superAdministrator) {
        this.eq_superAdministrator = eq_superAdministrator;
        return this;
    }

    public Integer getNeq_superAdministrator() {
        return neq_superAdministrator;
    }

    public UserSearchBo setNeq_superAdministrator(Integer neq_superAdministrator) {
        this.neq_superAdministrator = neq_superAdministrator;
        return this;
    }

    public Integer getGt_superAdministrator() {
        return gt_superAdministrator;
    }

    public UserSearchBo setGt_superAdministrator(Integer gt_superAdministrator) {
        this.gt_superAdministrator = gt_superAdministrator;
        return this;
    }

    public Integer getLt_superAdministrator() {
        return lt_superAdministrator;
    }

    public UserSearchBo setLt_superAdministrator(Integer lt_superAdministrator) {
        this.lt_superAdministrator = lt_superAdministrator;
        return this;
    }

    public Integer getEgt_superAdministrator() {
        return egt_superAdministrator;
    }

    public UserSearchBo setEgt_superAdministrator(Integer egt_superAdministrator) {
        this.egt_superAdministrator = egt_superAdministrator;
        return this;
    }

    public Integer getElt_superAdministrator() {
        return elt_superAdministrator;
    }

    public UserSearchBo setElt_superAdministrator(Integer elt_superAdministrator) {
        this.elt_superAdministrator = elt_superAdministrator;
        return this;
    }

    public String getBetween_superAdministrator() {
        return between_superAdministrator;
    }

    public UserSearchBo setBetween_superAdministrator(String between_superAdministrator) {
        this.between_superAdministrator = between_superAdministrator;
        return this;
    }

    public String getNotbetween_superAdministrator() {
        return notbetween_superAdministrator;
    }

    public UserSearchBo setNotbetween_superAdministrator(String notbetween_superAdministrator) {
        this.notbetween_superAdministrator = notbetween_superAdministrator;
        return this;
    }

    public String getIn_superAdministrator() {
        return in_superAdministrator;
    }

    public UserSearchBo setIn_superAdministrator(String in_superAdministrator) {
        this.in_superAdministrator = in_superAdministrator;
        return this;
    }

    public String getNotin_superAdministrator() {
        return notin_superAdministrator;
    }

    public void setNotin_superAdministrator(String notin_superAdministrator) {
        this.notin_superAdministrator = notin_superAdministrator;
    }

    public Integer getEq_banState() {
        return eq_banState;
    }

    public UserSearchBo setEq_banState(Integer eq_banState) {
        this.eq_banState = eq_banState;
        return this;
    }

    public Integer getNeq_banState() {
        return neq_banState;
    }

    public UserSearchBo setNeq_banState(Integer neq_banState) {
        this.neq_banState = neq_banState;
        return this;
    }

    public Integer getGt_banState() {
        return gt_banState;
    }

    public UserSearchBo setGt_banState(Integer gt_banState) {
        this.gt_banState = gt_banState;
        return this;
    }

    public Integer getLt_banState() {
        return lt_banState;
    }

    public UserSearchBo setLt_banState(Integer lt_banState) {
        this.lt_banState = lt_banState;
        return this;
    }

    public Integer getEgt_banState() {
        return egt_banState;
    }

    public UserSearchBo setEgt_banState(Integer egt_banState) {
        this.egt_banState = egt_banState;
        return this;
    }

    public Integer getElt_banState() {
        return elt_banState;
    }

    public UserSearchBo setElt_banState(Integer elt_banState) {
        this.elt_banState = elt_banState;
        return this;
    }

    public String getBetween_banState() {
        return between_banState;
    }

    public UserSearchBo setBetween_banState(String between_banState) {
        this.between_banState = between_banState;
        return this;
    }

    public String getNotbetween_banState() {
        return notbetween_banState;
    }

    public UserSearchBo setNotbetween_banState(String notbetween_banState) {
        this.notbetween_banState = notbetween_banState;
        return this;
    }

    public String getIn_banState() {
        return in_banState;
    }

    public UserSearchBo setIn_banState(String in_banState) {
        this.in_banState = in_banState;
        return this;
    }

    public String getNotin_banState() {
        return notin_banState;
    }

    public void setNotin_banState(String notin_banState) {
        this.notin_banState = notin_banState;
    }

    public Integer getEq_sex() {
        return eq_sex;
    }

    public UserSearchBo setEq_sex(Integer eq_sex) {
        this.eq_sex = eq_sex;
        return this;
    }

    public Integer getNeq_sex() {
        return neq_sex;
    }

    public UserSearchBo setNeq_sex(Integer neq_sex) {
        this.neq_sex = neq_sex;
        return this;
    }

    public Integer getGt_sex() {
        return gt_sex;
    }

    public UserSearchBo setGt_sex(Integer gt_sex) {
        this.gt_sex = gt_sex;
        return this;
    }

    public Integer getLt_sex() {
        return lt_sex;
    }

    public UserSearchBo setLt_sex(Integer lt_sex) {
        this.lt_sex = lt_sex;
        return this;
    }

    public Integer getEgt_sex() {
        return egt_sex;
    }

    public UserSearchBo setEgt_sex(Integer egt_sex) {
        this.egt_sex = egt_sex;
        return this;
    }

    public Integer getElt_sex() {
        return elt_sex;
    }

    public UserSearchBo setElt_sex(Integer elt_sex) {
        this.elt_sex = elt_sex;
        return this;
    }

    public String getBetween_sex() {
        return between_sex;
    }

    public UserSearchBo setBetween_sex(String between_sex) {
        this.between_sex = between_sex;
        return this;
    }

    public String getNotbetween_sex() {
        return notbetween_sex;
    }

    public UserSearchBo setNotbetween_sex(String notbetween_sex) {
        this.notbetween_sex = notbetween_sex;
        return this;
    }

    public String getIn_sex() {
        return in_sex;
    }

    public UserSearchBo setIn_sex(String in_sex) {
        this.in_sex = in_sex;
        return this;
    }

    public String getNotin_sex() {
        return notin_sex;
    }

    public void setNotin_sex(String notin_sex) {
        this.notin_sex = notin_sex;
    }

    public String getEq_userChannel() {
        return eq_userChannel;
    }

    public UserSearchBo setEq_userChannel(String eq_userChannel) {
        this.eq_userChannel = eq_userChannel;
        return this;
    }

    public String getNeq_userChannel() {
        return neq_userChannel;
    }

    public UserSearchBo setNeq_userChannel(String neq_userChannel) {
        this.neq_userChannel = neq_userChannel;
        return this;
    }

    public String getLike_userChannel() {
        return like_userChannel;
    }

    public UserSearchBo setLike_userChannel(String like_userChannel) {
        this.like_userChannel = like_userChannel;
        return this;
    }

    public String getLiker_userChannel() {
        return liker_userChannel;
    }

    public UserSearchBo setLiker_userChannel(String liker_userChannel) {
        this.liker_userChannel = liker_userChannel;
        return this;
    }

    public String getLikel_userChannel() {
        return likel_userChannel;
    }

    public UserSearchBo setLikel_userChannel(String likel_userChannel) {
        this.likel_userChannel = likel_userChannel;
        return this;
    }

    public String getNotlike_userChannel() {
        return notlike_userChannel;
    }

    public UserSearchBo setNotlike_userChannel(String notlike_userChannel) {
        this.notlike_userChannel = notlike_userChannel;
        return this;
    }

    public String getNotliker_userChannel() {
        return notliker_userChannel;
    }

    public UserSearchBo setNotliker_userChannel(String notliker_userChannel) {
        this.notliker_userChannel = notliker_userChannel;
        return this;
    }

    public String getNotlikel_userChannel() {
        return notlikel_userChannel;
    }

    public UserSearchBo setNotlikel_userChannel(String notlikel_userChannel) {
        this.notlikel_userChannel = notlikel_userChannel;
        return this;
    }

    public String getIn_userChannel() {
        return in_userChannel;
    }

    public UserSearchBo setIn_userChannel(String in_userChannel) {
        this.in_userChannel = in_userChannel;
        return this;
    }

    public String getNotin_userChannel() {
        return notin_userChannel;
    }

    public void setNotin_userChannel(String notin_userChannel) {
        this.notin_userChannel = notin_userChannel;
    }

    public String getEq_accountType() {
        return eq_accountType;
    }

    public UserSearchBo setEq_accountType(String eq_accountType) {
        this.eq_accountType = eq_accountType;
        return this;
    }

    public String getNeq_accountType() {
        return neq_accountType;
    }

    public UserSearchBo setNeq_accountType(String neq_accountType) {
        this.neq_accountType = neq_accountType;
        return this;
    }

    public String getLike_accountType() {
        return like_accountType;
    }

    public UserSearchBo setLike_accountType(String like_accountType) {
        this.like_accountType = like_accountType;
        return this;
    }

    public String getLiker_accountType() {
        return liker_accountType;
    }

    public UserSearchBo setLiker_accountType(String liker_accountType) {
        this.liker_accountType = liker_accountType;
        return this;
    }

    public String getLikel_accountType() {
        return likel_accountType;
    }

    public UserSearchBo setLikel_accountType(String likel_accountType) {
        this.likel_accountType = likel_accountType;
        return this;
    }

    public String getNotlike_accountType() {
        return notlike_accountType;
    }

    public UserSearchBo setNotlike_accountType(String notlike_accountType) {
        this.notlike_accountType = notlike_accountType;
        return this;
    }

    public String getNotliker_accountType() {
        return notliker_accountType;
    }

    public UserSearchBo setNotliker_accountType(String notliker_accountType) {
        this.notliker_accountType = notliker_accountType;
        return this;
    }

    public String getNotlikel_accountType() {
        return notlikel_accountType;
    }

    public UserSearchBo setNotlikel_accountType(String notlikel_accountType) {
        this.notlikel_accountType = notlikel_accountType;
        return this;
    }

    public String getIn_accountType() {
        return in_accountType;
    }

    public UserSearchBo setIn_accountType(String in_accountType) {
        this.in_accountType = in_accountType;
        return this;
    }

    public String getNotin_accountType() {
        return notin_accountType;
    }

    public void setNotin_accountType(String notin_accountType) {
        this.notin_accountType = notin_accountType;
    }

    public String getEq_userName() {
        return eq_userName;
    }

    public UserSearchBo setEq_userName(String eq_userName) {
        this.eq_userName = eq_userName;
        return this;
    }

    public String getNeq_userName() {
        return neq_userName;
    }

    public UserSearchBo setNeq_userName(String neq_userName) {
        this.neq_userName = neq_userName;
        return this;
    }

    public String getLike_userName() {
        return like_userName;
    }

    public UserSearchBo setLike_userName(String like_userName) {
        this.like_userName = like_userName;
        return this;
    }

    public String getLiker_userName() {
        return liker_userName;
    }

    public UserSearchBo setLiker_userName(String liker_userName) {
        this.liker_userName = liker_userName;
        return this;
    }

    public String getLikel_userName() {
        return likel_userName;
    }

    public UserSearchBo setLikel_userName(String likel_userName) {
        this.likel_userName = likel_userName;
        return this;
    }

    public String getNotlike_userName() {
        return notlike_userName;
    }

    public UserSearchBo setNotlike_userName(String notlike_userName) {
        this.notlike_userName = notlike_userName;
        return this;
    }

    public String getNotliker_userName() {
        return notliker_userName;
    }

    public UserSearchBo setNotliker_userName(String notliker_userName) {
        this.notliker_userName = notliker_userName;
        return this;
    }

    public String getNotlikel_userName() {
        return notlikel_userName;
    }

    public UserSearchBo setNotlikel_userName(String notlikel_userName) {
        this.notlikel_userName = notlikel_userName;
        return this;
    }

    public String getIn_userName() {
        return in_userName;
    }

    public UserSearchBo setIn_userName(String in_userName) {
        this.in_userName = in_userName;
        return this;
    }

    public String getNotin_userName() {
        return notin_userName;
    }

    public void setNotin_userName(String notin_userName) {
        this.notin_userName = notin_userName;
    }

    public String getEq_avatar() {
        return eq_avatar;
    }

    public UserSearchBo setEq_avatar(String eq_avatar) {
        this.eq_avatar = eq_avatar;
        return this;
    }

    public String getNeq_avatar() {
        return neq_avatar;
    }

    public UserSearchBo setNeq_avatar(String neq_avatar) {
        this.neq_avatar = neq_avatar;
        return this;
    }

    public String getLike_avatar() {
        return like_avatar;
    }

    public UserSearchBo setLike_avatar(String like_avatar) {
        this.like_avatar = like_avatar;
        return this;
    }

    public String getLiker_avatar() {
        return liker_avatar;
    }

    public UserSearchBo setLiker_avatar(String liker_avatar) {
        this.liker_avatar = liker_avatar;
        return this;
    }

    public String getLikel_avatar() {
        return likel_avatar;
    }

    public UserSearchBo setLikel_avatar(String likel_avatar) {
        this.likel_avatar = likel_avatar;
        return this;
    }

    public String getNotlike_avatar() {
        return notlike_avatar;
    }

    public UserSearchBo setNotlike_avatar(String notlike_avatar) {
        this.notlike_avatar = notlike_avatar;
        return this;
    }

    public String getNotliker_avatar() {
        return notliker_avatar;
    }

    public UserSearchBo setNotliker_avatar(String notliker_avatar) {
        this.notliker_avatar = notliker_avatar;
        return this;
    }

    public String getNotlikel_avatar() {
        return notlikel_avatar;
    }

    public UserSearchBo setNotlikel_avatar(String notlikel_avatar) {
        this.notlikel_avatar = notlikel_avatar;
        return this;
    }

    public String getIn_avatar() {
        return in_avatar;
    }

    public UserSearchBo setIn_avatar(String in_avatar) {
        this.in_avatar = in_avatar;
        return this;
    }

    public String getNotin_avatar() {
        return notin_avatar;
    }

    public void setNotin_avatar(String notin_avatar) {
        this.notin_avatar = notin_avatar;
    }

    public String getEq_userAccount() {
        return eq_userAccount;
    }

    public UserSearchBo setEq_userAccount(String eq_userAccount) {
        this.eq_userAccount = eq_userAccount;
        return this;
    }

    public String getNeq_userAccount() {
        return neq_userAccount;
    }

    public UserSearchBo setNeq_userAccount(String neq_userAccount) {
        this.neq_userAccount = neq_userAccount;
        return this;
    }

    public String getLike_userAccount() {
        return like_userAccount;
    }

    public UserSearchBo setLike_userAccount(String like_userAccount) {
        this.like_userAccount = like_userAccount;
        return this;
    }

    public String getLiker_userAccount() {
        return liker_userAccount;
    }

    public UserSearchBo setLiker_userAccount(String liker_userAccount) {
        this.liker_userAccount = liker_userAccount;
        return this;
    }

    public String getLikel_userAccount() {
        return likel_userAccount;
    }

    public UserSearchBo setLikel_userAccount(String likel_userAccount) {
        this.likel_userAccount = likel_userAccount;
        return this;
    }

    public String getNotlike_userAccount() {
        return notlike_userAccount;
    }

    public UserSearchBo setNotlike_userAccount(String notlike_userAccount) {
        this.notlike_userAccount = notlike_userAccount;
        return this;
    }

    public String getNotliker_userAccount() {
        return notliker_userAccount;
    }

    public UserSearchBo setNotliker_userAccount(String notliker_userAccount) {
        this.notliker_userAccount = notliker_userAccount;
        return this;
    }

    public String getNotlikel_userAccount() {
        return notlikel_userAccount;
    }

    public UserSearchBo setNotlikel_userAccount(String notlikel_userAccount) {
        this.notlikel_userAccount = notlikel_userAccount;
        return this;
    }

    public String getIn_userAccount() {
        return in_userAccount;
    }

    public UserSearchBo setIn_userAccount(String in_userAccount) {
        this.in_userAccount = in_userAccount;
        return this;
    }

    public String getNotin_userAccount() {
        return notin_userAccount;
    }

    public void setNotin_userAccount(String notin_userAccount) {
        this.notin_userAccount = notin_userAccount;
    }

    public String getEq_userPhone() {
        return eq_userPhone;
    }

    public UserSearchBo setEq_userPhone(String eq_userPhone) {
        this.eq_userPhone = eq_userPhone;
        return this;
    }

    public String getNeq_userPhone() {
        return neq_userPhone;
    }

    public UserSearchBo setNeq_userPhone(String neq_userPhone) {
        this.neq_userPhone = neq_userPhone;
        return this;
    }

    public String getLike_userPhone() {
        return like_userPhone;
    }

    public UserSearchBo setLike_userPhone(String like_userPhone) {
        this.like_userPhone = like_userPhone;
        return this;
    }

    public String getLiker_userPhone() {
        return liker_userPhone;
    }

    public UserSearchBo setLiker_userPhone(String liker_userPhone) {
        this.liker_userPhone = liker_userPhone;
        return this;
    }

    public String getLikel_userPhone() {
        return likel_userPhone;
    }

    public UserSearchBo setLikel_userPhone(String likel_userPhone) {
        this.likel_userPhone = likel_userPhone;
        return this;
    }

    public String getNotlike_userPhone() {
        return notlike_userPhone;
    }

    public UserSearchBo setNotlike_userPhone(String notlike_userPhone) {
        this.notlike_userPhone = notlike_userPhone;
        return this;
    }

    public String getNotliker_userPhone() {
        return notliker_userPhone;
    }

    public UserSearchBo setNotliker_userPhone(String notliker_userPhone) {
        this.notliker_userPhone = notliker_userPhone;
        return this;
    }

    public String getNotlikel_userPhone() {
        return notlikel_userPhone;
    }

    public UserSearchBo setNotlikel_userPhone(String notlikel_userPhone) {
        this.notlikel_userPhone = notlikel_userPhone;
        return this;
    }

    public String getIn_userPhone() {
        return in_userPhone;
    }

    public UserSearchBo setIn_userPhone(String in_userPhone) {
        this.in_userPhone = in_userPhone;
        return this;
    }

    public String getNotin_userPhone() {
        return notin_userPhone;
    }

    public void setNotin_userPhone(String notin_userPhone) {
        this.notin_userPhone = notin_userPhone;
    }

    public String getEq_userEmail() {
        return eq_userEmail;
    }

    public UserSearchBo setEq_userEmail(String eq_userEmail) {
        this.eq_userEmail = eq_userEmail;
        return this;
    }

    public String getNeq_userEmail() {
        return neq_userEmail;
    }

    public UserSearchBo setNeq_userEmail(String neq_userEmail) {
        this.neq_userEmail = neq_userEmail;
        return this;
    }

    public String getLike_userEmail() {
        return like_userEmail;
    }

    public UserSearchBo setLike_userEmail(String like_userEmail) {
        this.like_userEmail = like_userEmail;
        return this;
    }

    public String getLiker_userEmail() {
        return liker_userEmail;
    }

    public UserSearchBo setLiker_userEmail(String liker_userEmail) {
        this.liker_userEmail = liker_userEmail;
        return this;
    }

    public String getLikel_userEmail() {
        return likel_userEmail;
    }

    public UserSearchBo setLikel_userEmail(String likel_userEmail) {
        this.likel_userEmail = likel_userEmail;
        return this;
    }

    public String getNotlike_userEmail() {
        return notlike_userEmail;
    }

    public UserSearchBo setNotlike_userEmail(String notlike_userEmail) {
        this.notlike_userEmail = notlike_userEmail;
        return this;
    }

    public String getNotliker_userEmail() {
        return notliker_userEmail;
    }

    public UserSearchBo setNotliker_userEmail(String notliker_userEmail) {
        this.notliker_userEmail = notliker_userEmail;
        return this;
    }

    public String getNotlikel_userEmail() {
        return notlikel_userEmail;
    }

    public UserSearchBo setNotlikel_userEmail(String notlikel_userEmail) {
        this.notlikel_userEmail = notlikel_userEmail;
        return this;
    }

    public String getIn_userEmail() {
        return in_userEmail;
    }

    public UserSearchBo setIn_userEmail(String in_userEmail) {
        this.in_userEmail = in_userEmail;
        return this;
    }

    public String getNotin_userEmail() {
        return notin_userEmail;
    }

    public void setNotin_userEmail(String notin_userEmail) {
        this.notin_userEmail = notin_userEmail;
    }

    public String getEq_userPasswd() {
        return eq_userPasswd;
    }

    public UserSearchBo setEq_userPasswd(String eq_userPasswd) {
        this.eq_userPasswd = eq_userPasswd;
        return this;
    }

    public String getNeq_userPasswd() {
        return neq_userPasswd;
    }

    public UserSearchBo setNeq_userPasswd(String neq_userPasswd) {
        this.neq_userPasswd = neq_userPasswd;
        return this;
    }

    public String getLike_userPasswd() {
        return like_userPasswd;
    }

    public UserSearchBo setLike_userPasswd(String like_userPasswd) {
        this.like_userPasswd = like_userPasswd;
        return this;
    }

    public String getLiker_userPasswd() {
        return liker_userPasswd;
    }

    public UserSearchBo setLiker_userPasswd(String liker_userPasswd) {
        this.liker_userPasswd = liker_userPasswd;
        return this;
    }

    public String getLikel_userPasswd() {
        return likel_userPasswd;
    }

    public UserSearchBo setLikel_userPasswd(String likel_userPasswd) {
        this.likel_userPasswd = likel_userPasswd;
        return this;
    }

    public String getNotlike_userPasswd() {
        return notlike_userPasswd;
    }

    public UserSearchBo setNotlike_userPasswd(String notlike_userPasswd) {
        this.notlike_userPasswd = notlike_userPasswd;
        return this;
    }

    public String getNotliker_userPasswd() {
        return notliker_userPasswd;
    }

    public UserSearchBo setNotliker_userPasswd(String notliker_userPasswd) {
        this.notliker_userPasswd = notliker_userPasswd;
        return this;
    }

    public String getNotlikel_userPasswd() {
        return notlikel_userPasswd;
    }

    public UserSearchBo setNotlikel_userPasswd(String notlikel_userPasswd) {
        this.notlikel_userPasswd = notlikel_userPasswd;
        return this;
    }

    public String getIn_userPasswd() {
        return in_userPasswd;
    }

    public UserSearchBo setIn_userPasswd(String in_userPasswd) {
        this.in_userPasswd = in_userPasswd;
        return this;
    }

    public String getNotin_userPasswd() {
        return notin_userPasswd;
    }

    public void setNotin_userPasswd(String notin_userPasswd) {
        this.notin_userPasswd = notin_userPasswd;
    }

    public String getEq_lastLoginIp() {
        return eq_lastLoginIp;
    }

    public UserSearchBo setEq_lastLoginIp(String eq_lastLoginIp) {
        this.eq_lastLoginIp = eq_lastLoginIp;
        return this;
    }

    public String getNeq_lastLoginIp() {
        return neq_lastLoginIp;
    }

    public UserSearchBo setNeq_lastLoginIp(String neq_lastLoginIp) {
        this.neq_lastLoginIp = neq_lastLoginIp;
        return this;
    }

    public String getLike_lastLoginIp() {
        return like_lastLoginIp;
    }

    public UserSearchBo setLike_lastLoginIp(String like_lastLoginIp) {
        this.like_lastLoginIp = like_lastLoginIp;
        return this;
    }

    public String getLiker_lastLoginIp() {
        return liker_lastLoginIp;
    }

    public UserSearchBo setLiker_lastLoginIp(String liker_lastLoginIp) {
        this.liker_lastLoginIp = liker_lastLoginIp;
        return this;
    }

    public String getLikel_lastLoginIp() {
        return likel_lastLoginIp;
    }

    public UserSearchBo setLikel_lastLoginIp(String likel_lastLoginIp) {
        this.likel_lastLoginIp = likel_lastLoginIp;
        return this;
    }

    public String getNotlike_lastLoginIp() {
        return notlike_lastLoginIp;
    }

    public UserSearchBo setNotlike_lastLoginIp(String notlike_lastLoginIp) {
        this.notlike_lastLoginIp = notlike_lastLoginIp;
        return this;
    }

    public String getNotliker_lastLoginIp() {
        return notliker_lastLoginIp;
    }

    public UserSearchBo setNotliker_lastLoginIp(String notliker_lastLoginIp) {
        this.notliker_lastLoginIp = notliker_lastLoginIp;
        return this;
    }

    public String getNotlikel_lastLoginIp() {
        return notlikel_lastLoginIp;
    }

    public UserSearchBo setNotlikel_lastLoginIp(String notlikel_lastLoginIp) {
        this.notlikel_lastLoginIp = notlikel_lastLoginIp;
        return this;
    }

    public String getIn_lastLoginIp() {
        return in_lastLoginIp;
    }

    public UserSearchBo setIn_lastLoginIp(String in_lastLoginIp) {
        this.in_lastLoginIp = in_lastLoginIp;
        return this;
    }

    public String getNotin_lastLoginIp() {
        return notin_lastLoginIp;
    }

    public void setNotin_lastLoginIp(String notin_lastLoginIp) {
        this.notin_lastLoginIp = notin_lastLoginIp;
    }

    public Date getEq_lastLoginTime() {
        return eq_lastLoginTime;
    }

    public UserSearchBo setEq_lastLoginTime(Date eq_lastLoginTime) {
        this.eq_lastLoginTime = eq_lastLoginTime;
        return this;
    }

    public Date getNeq_lastLoginTime() {
        return neq_lastLoginTime;
    }

    public UserSearchBo setNeq_lastLoginTime(Date neq_lastLoginTime) {
        this.neq_lastLoginTime = neq_lastLoginTime;
        return this;
    }

    public Date getGt_lastLoginTime() {
        return gt_lastLoginTime;
    }

    public UserSearchBo setGt_lastLoginTime(Date gt_lastLoginTime) {
        this.gt_lastLoginTime = gt_lastLoginTime;
        return this;
    }

    public Date getLt_lastLoginTime() {
        return lt_lastLoginTime;
    }

    public UserSearchBo setLt_lastLoginTime(Date lt_lastLoginTime) {
        this.lt_lastLoginTime = lt_lastLoginTime;
        return this;
    }

    public Date getEgt_lastLoginTime() {
        return egt_lastLoginTime;
    }

    public UserSearchBo setEgt_lastLoginTime(Date egt_lastLoginTime) {
        this.egt_lastLoginTime = egt_lastLoginTime;
        return this;
    }

    public Date getElt_lastLoginTime() {
        return elt_lastLoginTime;
    }

    public UserSearchBo setElt_lastLoginTime(Date elt_lastLoginTime) {
        this.elt_lastLoginTime = elt_lastLoginTime;
        return this;
    }

    public String getBetween_lastLoginTime() {
        return between_lastLoginTime;
    }

    public UserSearchBo setBetween_lastLoginTime(String between_lastLoginTime) {
        this.between_lastLoginTime = between_lastLoginTime;
        return this;
    }

    public String getNotbetween_lastLoginTime() {
        return notbetween_lastLoginTime;
    }

    public UserSearchBo setNotbetween_lastLoginTime(String notbetween_lastLoginTime) {
        this.notbetween_lastLoginTime = notbetween_lastLoginTime;
        return this;
    }

    public String getIn_lastLoginTime() {
        return in_lastLoginTime;
    }

    public UserSearchBo setIn_lastLoginTime(String in_lastLoginTime) {
        this.in_lastLoginTime = in_lastLoginTime;
        return this;
    }

    public String getNotin_lastLoginTime() {
        return notin_lastLoginTime;
    }

    public void setNotin_lastLoginTime(String notin_lastLoginTime) {
        this.notin_lastLoginTime = notin_lastLoginTime;
    }

    public Date getEq_createdTime() {
        return eq_createdTime;
    }

    public UserSearchBo setEq_createdTime(Date eq_createdTime) {
        this.eq_createdTime = eq_createdTime;
        return this;
    }

    public Date getNeq_createdTime() {
        return neq_createdTime;
    }

    public UserSearchBo setNeq_createdTime(Date neq_createdTime) {
        this.neq_createdTime = neq_createdTime;
        return this;
    }

    public Date getGt_createdTime() {
        return gt_createdTime;
    }

    public UserSearchBo setGt_createdTime(Date gt_createdTime) {
        this.gt_createdTime = gt_createdTime;
        return this;
    }

    public Date getLt_createdTime() {
        return lt_createdTime;
    }

    public UserSearchBo setLt_createdTime(Date lt_createdTime) {
        this.lt_createdTime = lt_createdTime;
        return this;
    }

    public Date getEgt_createdTime() {
        return egt_createdTime;
    }

    public UserSearchBo setEgt_createdTime(Date egt_createdTime) {
        this.egt_createdTime = egt_createdTime;
        return this;
    }

    public Date getElt_createdTime() {
        return elt_createdTime;
    }

    public UserSearchBo setElt_createdTime(Date elt_createdTime) {
        this.elt_createdTime = elt_createdTime;
        return this;
    }

    public String getBetween_createdTime() {
        return between_createdTime;
    }

    public UserSearchBo setBetween_createdTime(String between_createdTime) {
        this.between_createdTime = between_createdTime;
        return this;
    }

    public String getNotbetween_createdTime() {
        return notbetween_createdTime;
    }

    public UserSearchBo setNotbetween_createdTime(String notbetween_createdTime) {
        this.notbetween_createdTime = notbetween_createdTime;
        return this;
    }

    public String getIn_createdTime() {
        return in_createdTime;
    }

    public UserSearchBo setIn_createdTime(String in_createdTime) {
        this.in_createdTime = in_createdTime;
        return this;
    }

    public String getNotin_createdTime() {
        return notin_createdTime;
    }

    public void setNotin_createdTime(String notin_createdTime) {
        this.notin_createdTime = notin_createdTime;
    }

    public Date getEq_updatedTime() {
        return eq_updatedTime;
    }

    public UserSearchBo setEq_updatedTime(Date eq_updatedTime) {
        this.eq_updatedTime = eq_updatedTime;
        return this;
    }

    public Date getNeq_updatedTime() {
        return neq_updatedTime;
    }

    public UserSearchBo setNeq_updatedTime(Date neq_updatedTime) {
        this.neq_updatedTime = neq_updatedTime;
        return this;
    }

    public Date getGt_updatedTime() {
        return gt_updatedTime;
    }

    public UserSearchBo setGt_updatedTime(Date gt_updatedTime) {
        this.gt_updatedTime = gt_updatedTime;
        return this;
    }

    public Date getLt_updatedTime() {
        return lt_updatedTime;
    }

    public UserSearchBo setLt_updatedTime(Date lt_updatedTime) {
        this.lt_updatedTime = lt_updatedTime;
        return this;
    }

    public Date getEgt_updatedTime() {
        return egt_updatedTime;
    }

    public UserSearchBo setEgt_updatedTime(Date egt_updatedTime) {
        this.egt_updatedTime = egt_updatedTime;
        return this;
    }

    public Date getElt_updatedTime() {
        return elt_updatedTime;
    }

    public UserSearchBo setElt_updatedTime(Date elt_updatedTime) {
        this.elt_updatedTime = elt_updatedTime;
        return this;
    }

    public String getBetween_updatedTime() {
        return between_updatedTime;
    }

    public UserSearchBo setBetween_updatedTime(String between_updatedTime) {
        this.between_updatedTime = between_updatedTime;
        return this;
    }

    public String getNotbetween_updatedTime() {
        return notbetween_updatedTime;
    }

    public UserSearchBo setNotbetween_updatedTime(String notbetween_updatedTime) {
        this.notbetween_updatedTime = notbetween_updatedTime;
        return this;
    }

    public String getIn_updatedTime() {
        return in_updatedTime;
    }

    public UserSearchBo setIn_updatedTime(String in_updatedTime) {
        this.in_updatedTime = in_updatedTime;
        return this;
    }

    public String getNotin_updatedTime() {
        return notin_updatedTime;
    }

    public void setNotin_updatedTime(String notin_updatedTime) {
        this.notin_updatedTime = notin_updatedTime;
    }

    public String getEq_createUser() {
        return eq_createUser;
    }

    public UserSearchBo setEq_createUser(String eq_createUser) {
        this.eq_createUser = eq_createUser;
        return this;
    }

    public String getNeq_createUser() {
        return neq_createUser;
    }

    public UserSearchBo setNeq_createUser(String neq_createUser) {
        this.neq_createUser = neq_createUser;
        return this;
    }

    public String getLike_createUser() {
        return like_createUser;
    }

    public UserSearchBo setLike_createUser(String like_createUser) {
        this.like_createUser = like_createUser;
        return this;
    }

    public String getLiker_createUser() {
        return liker_createUser;
    }

    public UserSearchBo setLiker_createUser(String liker_createUser) {
        this.liker_createUser = liker_createUser;
        return this;
    }

    public String getLikel_createUser() {
        return likel_createUser;
    }

    public UserSearchBo setLikel_createUser(String likel_createUser) {
        this.likel_createUser = likel_createUser;
        return this;
    }

    public String getNotlike_createUser() {
        return notlike_createUser;
    }

    public UserSearchBo setNotlike_createUser(String notlike_createUser) {
        this.notlike_createUser = notlike_createUser;
        return this;
    }

    public String getNotliker_createUser() {
        return notliker_createUser;
    }

    public UserSearchBo setNotliker_createUser(String notliker_createUser) {
        this.notliker_createUser = notliker_createUser;
        return this;
    }

    public String getNotlikel_createUser() {
        return notlikel_createUser;
    }

    public UserSearchBo setNotlikel_createUser(String notlikel_createUser) {
        this.notlikel_createUser = notlikel_createUser;
        return this;
    }

    public String getIn_createUser() {
        return in_createUser;
    }

    public UserSearchBo setIn_createUser(String in_createUser) {
        this.in_createUser = in_createUser;
        return this;
    }

    public String getNotin_createUser() {
        return notin_createUser;
    }

    public void setNotin_createUser(String notin_createUser) {
        this.notin_createUser = notin_createUser;
    }

    public String getEq_createUserId() {
        return eq_createUserId;
    }

    public UserSearchBo setEq_createUserId(String eq_createUserId) {
        this.eq_createUserId = eq_createUserId;
        return this;
    }

    public String getNeq_createUserId() {
        return neq_createUserId;
    }

    public UserSearchBo setNeq_createUserId(String neq_createUserId) {
        this.neq_createUserId = neq_createUserId;
        return this;
    }

    public String getLike_createUserId() {
        return like_createUserId;
    }

    public UserSearchBo setLike_createUserId(String like_createUserId) {
        this.like_createUserId = like_createUserId;
        return this;
    }

    public String getLiker_createUserId() {
        return liker_createUserId;
    }

    public UserSearchBo setLiker_createUserId(String liker_createUserId) {
        this.liker_createUserId = liker_createUserId;
        return this;
    }

    public String getLikel_createUserId() {
        return likel_createUserId;
    }

    public UserSearchBo setLikel_createUserId(String likel_createUserId) {
        this.likel_createUserId = likel_createUserId;
        return this;
    }

    public String getNotlike_createUserId() {
        return notlike_createUserId;
    }

    public UserSearchBo setNotlike_createUserId(String notlike_createUserId) {
        this.notlike_createUserId = notlike_createUserId;
        return this;
    }

    public String getNotliker_createUserId() {
        return notliker_createUserId;
    }

    public UserSearchBo setNotliker_createUserId(String notliker_createUserId) {
        this.notliker_createUserId = notliker_createUserId;
        return this;
    }

    public String getNotlikel_createUserId() {
        return notlikel_createUserId;
    }

    public UserSearchBo setNotlikel_createUserId(String notlikel_createUserId) {
        this.notlikel_createUserId = notlikel_createUserId;
        return this;
    }

    public String getIn_createUserId() {
        return in_createUserId;
    }

    public UserSearchBo setIn_createUserId(String in_createUserId) {
        this.in_createUserId = in_createUserId;
        return this;
    }

    public String getNotin_createUserId() {
        return notin_createUserId;
    }

    public void setNotin_createUserId(String notin_createUserId) {
        this.notin_createUserId = notin_createUserId;
    }

    public String getEq_updateUser() {
        return eq_updateUser;
    }

    public UserSearchBo setEq_updateUser(String eq_updateUser) {
        this.eq_updateUser = eq_updateUser;
        return this;
    }

    public String getNeq_updateUser() {
        return neq_updateUser;
    }

    public UserSearchBo setNeq_updateUser(String neq_updateUser) {
        this.neq_updateUser = neq_updateUser;
        return this;
    }

    public String getLike_updateUser() {
        return like_updateUser;
    }

    public UserSearchBo setLike_updateUser(String like_updateUser) {
        this.like_updateUser = like_updateUser;
        return this;
    }

    public String getLiker_updateUser() {
        return liker_updateUser;
    }

    public UserSearchBo setLiker_updateUser(String liker_updateUser) {
        this.liker_updateUser = liker_updateUser;
        return this;
    }

    public String getLikel_updateUser() {
        return likel_updateUser;
    }

    public UserSearchBo setLikel_updateUser(String likel_updateUser) {
        this.likel_updateUser = likel_updateUser;
        return this;
    }

    public String getNotlike_updateUser() {
        return notlike_updateUser;
    }

    public UserSearchBo setNotlike_updateUser(String notlike_updateUser) {
        this.notlike_updateUser = notlike_updateUser;
        return this;
    }

    public String getNotliker_updateUser() {
        return notliker_updateUser;
    }

    public UserSearchBo setNotliker_updateUser(String notliker_updateUser) {
        this.notliker_updateUser = notliker_updateUser;
        return this;
    }

    public String getNotlikel_updateUser() {
        return notlikel_updateUser;
    }

    public UserSearchBo setNotlikel_updateUser(String notlikel_updateUser) {
        this.notlikel_updateUser = notlikel_updateUser;
        return this;
    }

    public String getIn_updateUser() {
        return in_updateUser;
    }

    public UserSearchBo setIn_updateUser(String in_updateUser) {
        this.in_updateUser = in_updateUser;
        return this;
    }

    public String getNotin_updateUser() {
        return notin_updateUser;
    }

    public void setNotin_updateUser(String notin_updateUser) {
        this.notin_updateUser = notin_updateUser;
    }

    public String getEq_updateUserId() {
        return eq_updateUserId;
    }

    public UserSearchBo setEq_updateUserId(String eq_updateUserId) {
        this.eq_updateUserId = eq_updateUserId;
        return this;
    }

    public String getNeq_updateUserId() {
        return neq_updateUserId;
    }

    public UserSearchBo setNeq_updateUserId(String neq_updateUserId) {
        this.neq_updateUserId = neq_updateUserId;
        return this;
    }

    public String getLike_updateUserId() {
        return like_updateUserId;
    }

    public UserSearchBo setLike_updateUserId(String like_updateUserId) {
        this.like_updateUserId = like_updateUserId;
        return this;
    }

    public String getLiker_updateUserId() {
        return liker_updateUserId;
    }

    public UserSearchBo setLiker_updateUserId(String liker_updateUserId) {
        this.liker_updateUserId = liker_updateUserId;
        return this;
    }

    public String getLikel_updateUserId() {
        return likel_updateUserId;
    }

    public UserSearchBo setLikel_updateUserId(String likel_updateUserId) {
        this.likel_updateUserId = likel_updateUserId;
        return this;
    }

    public String getNotlike_updateUserId() {
        return notlike_updateUserId;
    }

    public UserSearchBo setNotlike_updateUserId(String notlike_updateUserId) {
        this.notlike_updateUserId = notlike_updateUserId;
        return this;
    }

    public String getNotliker_updateUserId() {
        return notliker_updateUserId;
    }

    public UserSearchBo setNotliker_updateUserId(String notliker_updateUserId) {
        this.notliker_updateUserId = notliker_updateUserId;
        return this;
    }

    public String getNotlikel_updateUserId() {
        return notlikel_updateUserId;
    }

    public UserSearchBo setNotlikel_updateUserId(String notlikel_updateUserId) {
        this.notlikel_updateUserId = notlikel_updateUserId;
        return this;
    }

    public String getIn_updateUserId() {
        return in_updateUserId;
    }

    public UserSearchBo setIn_updateUserId(String in_updateUserId) {
        this.in_updateUserId = in_updateUserId;
        return this;
    }

    public String getNotin_updateUserId() {
        return notin_updateUserId;
    }

    public void setNotin_updateUserId(String notin_updateUserId) {
        this.notin_updateUserId = notin_updateUserId;
    }
}
