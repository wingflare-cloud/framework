package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.UserDo;
import com.wingflare.facade.module.user.bo.UserSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 系统用户Wrapper
 *
 * @author naizui_ycx
 * @date Tue Mar 07 17:34:13 CST 2023
 */
public class UserWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<UserDo> getQueryWrapper(UserSearchBo bo) {
        QueryWrapper<UserDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq("user_id", bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne("user_id", bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, "user_id", bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, "user_id", bo.getNotin_userId());
        }
        // super_administrator 开始
        if (bo.getEq_superAdministrator() != null) {
            queryWrapper.eq("super_administrator", bo.getEq_superAdministrator());
        }
        if (bo.getNeq_superAdministrator() != null) {
            queryWrapper.ne("super_administrator", bo.getNeq_superAdministrator());
        }
        if (bo.getGt_superAdministrator() != null) {
            queryWrapper.gt("super_administrator", bo.getGt_superAdministrator());
        }
        if (bo.getLt_superAdministrator() != null) {
            queryWrapper.lt("super_administrator", bo.getLt_superAdministrator());
        }
        if (bo.getEgt_superAdministrator() != null) {
            queryWrapper.ge("super_administrator", bo.getEgt_superAdministrator());
        }
        if (bo.getElt_superAdministrator() != null) {
            queryWrapper.le("super_administrator", bo.getElt_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_superAdministrator())) {
            WrapperUtil.between(queryWrapper, "super_administrator", bo.getBetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_superAdministrator())) {
            WrapperUtil.notBetween(queryWrapper, "super_administrator", bo.getNotbetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getIn_superAdministrator())) {
            WrapperUtil.in(queryWrapper, "super_administrator", bo.getIn_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_superAdministrator())) {
            WrapperUtil.notIn(queryWrapper, "super_administrator", bo.getNotin_superAdministrator());
        }
        // ban_state 开始
        if (bo.getEq_banState() != null) {
            queryWrapper.eq("ban_state", bo.getEq_banState());
        }
        if (bo.getNeq_banState() != null) {
            queryWrapper.ne("ban_state", bo.getNeq_banState());
        }
        if (bo.getGt_banState() != null) {
            queryWrapper.gt("ban_state", bo.getGt_banState());
        }
        if (bo.getLt_banState() != null) {
            queryWrapper.lt("ban_state", bo.getLt_banState());
        }
        if (bo.getEgt_banState() != null) {
            queryWrapper.ge("ban_state", bo.getEgt_banState());
        }
        if (bo.getElt_banState() != null) {
            queryWrapper.le("ban_state", bo.getElt_banState());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_banState())) {
            WrapperUtil.between(queryWrapper, "ban_state", bo.getBetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_banState())) {
            WrapperUtil.notBetween(queryWrapper, "ban_state", bo.getNotbetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getIn_banState())) {
            WrapperUtil.in(queryWrapper, "ban_state", bo.getIn_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_banState())) {
            WrapperUtil.notIn(queryWrapper, "ban_state", bo.getNotin_banState());
        }
        // sex 开始
        if (bo.getEq_sex() != null) {
            queryWrapper.eq("sex", bo.getEq_sex());
        }
        if (bo.getNeq_sex() != null) {
            queryWrapper.ne("sex", bo.getNeq_sex());
        }
        if (bo.getGt_sex() != null) {
            queryWrapper.gt("sex", bo.getGt_sex());
        }
        if (bo.getLt_sex() != null) {
            queryWrapper.lt("sex", bo.getLt_sex());
        }
        if (bo.getEgt_sex() != null) {
            queryWrapper.ge("sex", bo.getEgt_sex());
        }
        if (bo.getElt_sex() != null) {
            queryWrapper.le("sex", bo.getElt_sex());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sex())) {
            WrapperUtil.between(queryWrapper, "sex", bo.getBetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sex())) {
            WrapperUtil.notBetween(queryWrapper, "sex", bo.getNotbetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sex())) {
            WrapperUtil.in(queryWrapper, "sex", bo.getIn_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sex())) {
            WrapperUtil.notIn(queryWrapper, "sex", bo.getNotin_sex());
        }
        // user_channel 开始
        if (bo.getEq_userChannel() != null) {
            queryWrapper.eq("user_channel", bo.getEq_userChannel());
        }
        if (bo.getNeq_userChannel() != null) {
            queryWrapper.ne("user_channel", bo.getNeq_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userChannel())) {
            queryWrapper.like("user_channel", bo.getLike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userChannel())) {
            queryWrapper.likeRight("user_channel", bo.getLiker_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userChannel())) {
            queryWrapper.likeLeft("user_channel", bo.getLikel_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userChannel())) {
            queryWrapper.notLike("user_channel", bo.getNotlike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userChannel())) {
            WrapperUtil.in(queryWrapper, "user_channel", bo.getIn_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userChannel())) {
            WrapperUtil.notIn(queryWrapper, "user_channel", bo.getNotin_userChannel());
        }
        // account_type 开始
        if (bo.getEq_accountType() != null) {
            queryWrapper.eq("account_type", bo.getEq_accountType());
        }
        if (bo.getNeq_accountType() != null) {
            queryWrapper.ne("account_type", bo.getNeq_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_accountType())) {
            queryWrapper.like("account_type", bo.getLike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_accountType())) {
            queryWrapper.likeRight("account_type", bo.getLiker_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_accountType())) {
            queryWrapper.likeLeft("account_type", bo.getLikel_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_accountType())) {
            queryWrapper.notLike("account_type", bo.getNotlike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_accountType())) {
            WrapperUtil.in(queryWrapper, "account_type", bo.getIn_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_accountType())) {
            WrapperUtil.notIn(queryWrapper, "account_type", bo.getNotin_accountType());
        }
        // user_name 开始
        if (bo.getEq_userName() != null) {
            queryWrapper.eq("user_name", bo.getEq_userName());
        }
        if (bo.getNeq_userName() != null) {
            queryWrapper.ne("user_name", bo.getNeq_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userName())) {
            queryWrapper.like("user_name", bo.getLike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userName())) {
            queryWrapper.likeRight("user_name", bo.getLiker_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userName())) {
            queryWrapper.likeLeft("user_name", bo.getLikel_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userName())) {
            queryWrapper.notLike("user_name", bo.getNotlike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userName())) {
            WrapperUtil.in(queryWrapper, "user_name", bo.getIn_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userName())) {
            WrapperUtil.notIn(queryWrapper, "user_name", bo.getNotin_userName());
        }
        // avatar 开始
        if (bo.getEq_avatar() != null) {
            queryWrapper.eq("avatar", bo.getEq_avatar());
        }
        if (bo.getNeq_avatar() != null) {
            queryWrapper.ne("avatar", bo.getNeq_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLike_avatar())) {
            queryWrapper.like("avatar", bo.getLike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_avatar())) {
            queryWrapper.likeRight("avatar", bo.getLiker_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_avatar())) {
            queryWrapper.likeLeft("avatar", bo.getLikel_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_avatar())) {
            queryWrapper.notLike("avatar", bo.getNotlike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getIn_avatar())) {
            WrapperUtil.in(queryWrapper, "avatar", bo.getIn_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_avatar())) {
            WrapperUtil.notIn(queryWrapper, "avatar", bo.getNotin_avatar());
        }
        // user_account 开始
        if (bo.getEq_userAccount() != null) {
            queryWrapper.eq("user_account", bo.getEq_userAccount());
        }
        if (bo.getNeq_userAccount() != null) {
            queryWrapper.ne("user_account", bo.getNeq_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userAccount())) {
            queryWrapper.like("user_account", bo.getLike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userAccount())) {
            queryWrapper.likeRight("user_account", bo.getLiker_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userAccount())) {
            queryWrapper.likeLeft("user_account", bo.getLikel_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userAccount())) {
            queryWrapper.notLike("user_account", bo.getNotlike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userAccount())) {
            WrapperUtil.in(queryWrapper, "user_account", bo.getIn_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userAccount())) {
            WrapperUtil.notIn(queryWrapper, "user_account", bo.getNotin_userAccount());
        }
        // user_phone 开始
        if (bo.getEq_userPhone() != null) {
            queryWrapper.eq("user_phone", bo.getEq_userPhone());
        }
        if (bo.getNeq_userPhone() != null) {
            queryWrapper.ne("user_phone", bo.getNeq_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPhone())) {
            queryWrapper.like("user_phone", bo.getLike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPhone())) {
            queryWrapper.likeRight("user_phone", bo.getLiker_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPhone())) {
            queryWrapper.likeLeft("user_phone", bo.getLikel_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPhone())) {
            queryWrapper.notLike("user_phone", bo.getNotlike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPhone())) {
            WrapperUtil.in(queryWrapper, "user_phone", bo.getIn_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPhone())) {
            WrapperUtil.notIn(queryWrapper, "user_phone", bo.getNotin_userPhone());
        }
        // user_email 开始
        if (bo.getEq_userEmail() != null) {
            queryWrapper.eq("user_email", bo.getEq_userEmail());
        }
        if (bo.getNeq_userEmail() != null) {
            queryWrapper.ne("user_email", bo.getNeq_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userEmail())) {
            queryWrapper.like("user_email", bo.getLike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userEmail())) {
            queryWrapper.likeRight("user_email", bo.getLiker_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userEmail())) {
            queryWrapper.likeLeft("user_email", bo.getLikel_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userEmail())) {
            queryWrapper.notLike("user_email", bo.getNotlike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userEmail())) {
            WrapperUtil.in(queryWrapper, "user_email", bo.getIn_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userEmail())) {
            WrapperUtil.notIn(queryWrapper, "user_email", bo.getNotin_userEmail());
        }
        // user_passwd 开始
        if (bo.getEq_userPasswd() != null) {
            queryWrapper.eq("user_passwd", bo.getEq_userPasswd());
        }
        if (bo.getNeq_userPasswd() != null) {
            queryWrapper.ne("user_passwd", bo.getNeq_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPasswd())) {
            queryWrapper.like("user_passwd", bo.getLike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPasswd())) {
            queryWrapper.likeRight("user_passwd", bo.getLiker_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPasswd())) {
            queryWrapper.likeLeft("user_passwd", bo.getLikel_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPasswd())) {
            queryWrapper.notLike("user_passwd", bo.getNotlike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPasswd())) {
            WrapperUtil.in(queryWrapper, "user_passwd", bo.getIn_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPasswd())) {
            WrapperUtil.notIn(queryWrapper, "user_passwd", bo.getNotin_userPasswd());
        }
        // last_login_ip 开始
        if (bo.getEq_lastLoginIp() != null) {
            queryWrapper.eq("last_login_ip", bo.getEq_lastLoginIp());
        }
        if (bo.getNeq_lastLoginIp() != null) {
            queryWrapper.ne("last_login_ip", bo.getNeq_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLike_lastLoginIp())) {
            queryWrapper.like("last_login_ip", bo.getLike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_lastLoginIp())) {
            queryWrapper.likeRight("last_login_ip", bo.getLiker_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_lastLoginIp())) {
            queryWrapper.likeLeft("last_login_ip", bo.getLikel_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_lastLoginIp())) {
            queryWrapper.notLike("last_login_ip", bo.getNotlike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginIp())) {
            WrapperUtil.in(queryWrapper, "last_login_ip", bo.getIn_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginIp())) {
            WrapperUtil.notIn(queryWrapper, "last_login_ip", bo.getNotin_lastLoginIp());
        }
        // last_login_time 开始
        if (bo.getEq_lastLoginTime() != null) {
            queryWrapper.eq("last_login_time", bo.getEq_lastLoginTime());
        }
        if (bo.getNeq_lastLoginTime() != null) {
            queryWrapper.ne("last_login_time", bo.getNeq_lastLoginTime());
        }
        if (bo.getGt_lastLoginTime() != null) {
            queryWrapper.gt("last_login_time", bo.getGt_lastLoginTime());
        }
        if (bo.getLt_lastLoginTime() != null) {
            queryWrapper.lt("last_login_time", bo.getLt_lastLoginTime());
        }
        if (bo.getEgt_lastLoginTime() != null) {
            queryWrapper.ge("last_login_time", bo.getEgt_lastLoginTime());
        }
        if (bo.getElt_lastLoginTime() != null) {
            queryWrapper.le("last_login_time", bo.getElt_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_lastLoginTime())) {
            WrapperUtil.between(queryWrapper, "last_login_time", bo.getBetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_lastLoginTime())) {
            WrapperUtil.notBetween(queryWrapper, "last_login_time", bo.getNotbetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginTime())) {
            WrapperUtil.in(queryWrapper, "last_login_time", bo.getIn_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginTime())) {
            WrapperUtil.notIn(queryWrapper, "last_login_time", bo.getNotin_lastLoginTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq("created_time", bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne("created_time", bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt("created_time", bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt("created_time", bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge("created_time", bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le("created_time", bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, "created_time", bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, "created_time", bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, "created_time", bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, "created_time", bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq("updated_time", bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne("updated_time", bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt("updated_time", bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt("updated_time", bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge("updated_time", bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le("updated_time", bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, "updated_time", bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, "updated_time", bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, "updated_time", bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, "updated_time", bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq("create_user", bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne("create_user", bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like("create_user", bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight("create_user", bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft("create_user", bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike("create_user", bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, "create_user", bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, "create_user", bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq("create_user_id", bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne("create_user_id", bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like("create_user_id", bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight("create_user_id", bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft("create_user_id", bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike("create_user_id", bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, "create_user_id", bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, "create_user_id", bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq("update_user", bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne("update_user", bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like("update_user", bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight("update_user", bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft("update_user", bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike("update_user", bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, "update_user", bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, "update_user", bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq("update_user_id", bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne("update_user_id", bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like("update_user_id", bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight("update_user_id", bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft("update_user_id", bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike("update_user_id", bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, "update_user_id", bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, "update_user_id", bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }
	
	/**
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<UserDo> getLambdaQueryWrapper(UserSearchBo bo) {
        LambdaQueryWrapper<UserDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, UserDo::getIsDelete, bo.getDataScope());

        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(UserDo::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(UserDo::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserId, bo.getNotin_userId());
        }
        // super_administrator 开始
        if (bo.getEq_superAdministrator() != null) {
            queryWrapper.eq(UserDo::getSuperAdministrator, bo.getEq_superAdministrator());
        }
        if (bo.getNeq_superAdministrator() != null) {
            queryWrapper.ne(UserDo::getSuperAdministrator, bo.getNeq_superAdministrator());
        }
        if (bo.getGt_superAdministrator() != null) {
            queryWrapper.gt(UserDo::getSuperAdministrator, bo.getGt_superAdministrator());
        }
        if (bo.getLt_superAdministrator() != null) {
            queryWrapper.lt(UserDo::getSuperAdministrator, bo.getLt_superAdministrator());
        }
        if (bo.getEgt_superAdministrator() != null) {
            queryWrapper.ge(UserDo::getSuperAdministrator, bo.getEgt_superAdministrator());
        }
        if (bo.getElt_superAdministrator() != null) {
            queryWrapper.le(UserDo::getSuperAdministrator, bo.getElt_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_superAdministrator())) {
            WrapperUtil.between(queryWrapper, UserDo::getSuperAdministrator, bo.getBetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_superAdministrator())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getSuperAdministrator, bo.getNotbetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getIn_superAdministrator())) {
            WrapperUtil.in(queryWrapper, UserDo::getSuperAdministrator, bo.getIn_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_superAdministrator())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getSuperAdministrator, bo.getNotin_superAdministrator());
        }
        // ban_state 开始
        if (bo.getEq_banState() != null) {
            queryWrapper.eq(UserDo::getBanState, bo.getEq_banState());
        }
        if (bo.getNeq_banState() != null) {
            queryWrapper.ne(UserDo::getBanState, bo.getNeq_banState());
        }
        if (bo.getGt_banState() != null) {
            queryWrapper.gt(UserDo::getBanState, bo.getGt_banState());
        }
        if (bo.getLt_banState() != null) {
            queryWrapper.lt(UserDo::getBanState, bo.getLt_banState());
        }
        if (bo.getEgt_banState() != null) {
            queryWrapper.ge(UserDo::getBanState, bo.getEgt_banState());
        }
        if (bo.getElt_banState() != null) {
            queryWrapper.le(UserDo::getBanState, bo.getElt_banState());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_banState())) {
            WrapperUtil.between(queryWrapper, UserDo::getBanState, bo.getBetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_banState())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getBanState, bo.getNotbetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getIn_banState())) {
            WrapperUtil.in(queryWrapper, UserDo::getBanState, bo.getIn_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_banState())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getBanState, bo.getNotin_banState());
        }
        // sex 开始
        if (bo.getEq_sex() != null) {
            queryWrapper.eq(UserDo::getSex, bo.getEq_sex());
        }
        if (bo.getNeq_sex() != null) {
            queryWrapper.ne(UserDo::getSex, bo.getNeq_sex());
        }
        if (bo.getGt_sex() != null) {
            queryWrapper.gt(UserDo::getSex, bo.getGt_sex());
        }
        if (bo.getLt_sex() != null) {
            queryWrapper.lt(UserDo::getSex, bo.getLt_sex());
        }
        if (bo.getEgt_sex() != null) {
            queryWrapper.ge(UserDo::getSex, bo.getEgt_sex());
        }
        if (bo.getElt_sex() != null) {
            queryWrapper.le(UserDo::getSex, bo.getElt_sex());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sex())) {
            WrapperUtil.between(queryWrapper, UserDo::getSex, bo.getBetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sex())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getSex, bo.getNotbetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sex())) {
            WrapperUtil.in(queryWrapper, UserDo::getSex, bo.getIn_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sex())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getSex, bo.getNotin_sex());
        }
        // user_channel 开始
        if (bo.getEq_userChannel() != null) {
            queryWrapper.eq(UserDo::getUserChannel, bo.getEq_userChannel());
        }
        if (bo.getNeq_userChannel() != null) {
            queryWrapper.ne(UserDo::getUserChannel, bo.getNeq_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userChannel())) {
            queryWrapper.like(UserDo::getUserChannel, bo.getLike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userChannel())) {
            queryWrapper.likeRight(UserDo::getUserChannel, bo.getLiker_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userChannel())) {
            queryWrapper.likeLeft(UserDo::getUserChannel, bo.getLikel_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userChannel())) {
            queryWrapper.notLike(UserDo::getUserChannel, bo.getNotlike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userChannel())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserChannel, bo.getIn_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userChannel())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserChannel, bo.getNotin_userChannel());
        }
        // account_type 开始
        if (bo.getEq_accountType() != null) {
            queryWrapper.eq(UserDo::getAccountType, bo.getEq_accountType());
        }
        if (bo.getNeq_accountType() != null) {
            queryWrapper.ne(UserDo::getAccountType, bo.getNeq_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_accountType())) {
            queryWrapper.like(UserDo::getAccountType, bo.getLike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_accountType())) {
            queryWrapper.likeRight(UserDo::getAccountType, bo.getLiker_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_accountType())) {
            queryWrapper.likeLeft(UserDo::getAccountType, bo.getLikel_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_accountType())) {
            queryWrapper.notLike(UserDo::getAccountType, bo.getNotlike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_accountType())) {
            WrapperUtil.in(queryWrapper, UserDo::getAccountType, bo.getIn_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_accountType())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getAccountType, bo.getNotin_accountType());
        }
        // user_name 开始
        if (bo.getEq_userName() != null) {
            queryWrapper.eq(UserDo::getUserName, bo.getEq_userName());
        }
        if (bo.getNeq_userName() != null) {
            queryWrapper.ne(UserDo::getUserName, bo.getNeq_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userName())) {
            queryWrapper.like(UserDo::getUserName, bo.getLike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userName())) {
            queryWrapper.likeRight(UserDo::getUserName, bo.getLiker_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userName())) {
            queryWrapper.likeLeft(UserDo::getUserName, bo.getLikel_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userName())) {
            queryWrapper.notLike(UserDo::getUserName, bo.getNotlike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userName())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserName, bo.getIn_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userName())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserName, bo.getNotin_userName());
        }
        // avatar 开始
        if (bo.getEq_avatar() != null) {
            queryWrapper.eq(UserDo::getAvatar, bo.getEq_avatar());
        }
        if (bo.getNeq_avatar() != null) {
            queryWrapper.ne(UserDo::getAvatar, bo.getNeq_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLike_avatar())) {
            queryWrapper.like(UserDo::getAvatar, bo.getLike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_avatar())) {
            queryWrapper.likeRight(UserDo::getAvatar, bo.getLiker_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_avatar())) {
            queryWrapper.likeLeft(UserDo::getAvatar, bo.getLikel_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_avatar())) {
            queryWrapper.notLike(UserDo::getAvatar, bo.getNotlike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getIn_avatar())) {
            WrapperUtil.in(queryWrapper, UserDo::getAvatar, bo.getIn_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_avatar())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getAvatar, bo.getNotin_avatar());
        }
        // user_account 开始
        if (bo.getEq_userAccount() != null) {
            queryWrapper.eq(UserDo::getUserAccount, bo.getEq_userAccount());
        }
        if (bo.getNeq_userAccount() != null) {
            queryWrapper.ne(UserDo::getUserAccount, bo.getNeq_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userAccount())) {
            queryWrapper.like(UserDo::getUserAccount, bo.getLike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userAccount())) {
            queryWrapper.likeRight(UserDo::getUserAccount, bo.getLiker_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userAccount())) {
            queryWrapper.likeLeft(UserDo::getUserAccount, bo.getLikel_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userAccount())) {
            queryWrapper.notLike(UserDo::getUserAccount, bo.getNotlike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userAccount())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserAccount, bo.getIn_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userAccount())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserAccount, bo.getNotin_userAccount());
        }
        // user_phone 开始
        if (bo.getEq_userPhone() != null) {
            queryWrapper.eq(UserDo::getUserPhone, bo.getEq_userPhone());
        }
        if (bo.getNeq_userPhone() != null) {
            queryWrapper.ne(UserDo::getUserPhone, bo.getNeq_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPhone())) {
            queryWrapper.like(UserDo::getUserPhone, bo.getLike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPhone())) {
            queryWrapper.likeRight(UserDo::getUserPhone, bo.getLiker_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPhone())) {
            queryWrapper.likeLeft(UserDo::getUserPhone, bo.getLikel_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPhone())) {
            queryWrapper.notLike(UserDo::getUserPhone, bo.getNotlike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPhone())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserPhone, bo.getIn_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPhone())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserPhone, bo.getNotin_userPhone());
        }
        // user_email 开始
        if (bo.getEq_userEmail() != null) {
            queryWrapper.eq(UserDo::getUserEmail, bo.getEq_userEmail());
        }
        if (bo.getNeq_userEmail() != null) {
            queryWrapper.ne(UserDo::getUserEmail, bo.getNeq_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userEmail())) {
            queryWrapper.like(UserDo::getUserEmail, bo.getLike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userEmail())) {
            queryWrapper.likeRight(UserDo::getUserEmail, bo.getLiker_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userEmail())) {
            queryWrapper.likeLeft(UserDo::getUserEmail, bo.getLikel_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userEmail())) {
            queryWrapper.notLike(UserDo::getUserEmail, bo.getNotlike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userEmail())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserEmail, bo.getIn_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userEmail())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserEmail, bo.getNotin_userEmail());
        }
        // user_passwd 开始
        if (bo.getEq_userPasswd() != null) {
            queryWrapper.eq(UserDo::getUserPasswd, bo.getEq_userPasswd());
        }
        if (bo.getNeq_userPasswd() != null) {
            queryWrapper.ne(UserDo::getUserPasswd, bo.getNeq_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPasswd())) {
            queryWrapper.like(UserDo::getUserPasswd, bo.getLike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPasswd())) {
            queryWrapper.likeRight(UserDo::getUserPasswd, bo.getLiker_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPasswd())) {
            queryWrapper.likeLeft(UserDo::getUserPasswd, bo.getLikel_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPasswd())) {
            queryWrapper.notLike(UserDo::getUserPasswd, bo.getNotlike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPasswd())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserPasswd, bo.getIn_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPasswd())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserPasswd, bo.getNotin_userPasswd());
        }
        // last_login_ip 开始
        if (bo.getEq_lastLoginIp() != null) {
            queryWrapper.eq(UserDo::getLastLoginIp, bo.getEq_lastLoginIp());
        }
        if (bo.getNeq_lastLoginIp() != null) {
            queryWrapper.ne(UserDo::getLastLoginIp, bo.getNeq_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLike_lastLoginIp())) {
            queryWrapper.like(UserDo::getLastLoginIp, bo.getLike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_lastLoginIp())) {
            queryWrapper.likeRight(UserDo::getLastLoginIp, bo.getLiker_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_lastLoginIp())) {
            queryWrapper.likeLeft(UserDo::getLastLoginIp, bo.getLikel_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_lastLoginIp())) {
            queryWrapper.notLike(UserDo::getLastLoginIp, bo.getNotlike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginIp())) {
            WrapperUtil.in(queryWrapper, UserDo::getLastLoginIp, bo.getIn_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginIp())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getLastLoginIp, bo.getNotin_lastLoginIp());
        }
        // last_login_time 开始
        if (bo.getEq_lastLoginTime() != null) {
            queryWrapper.eq(UserDo::getLastLoginTime, bo.getEq_lastLoginTime());
        }
        if (bo.getNeq_lastLoginTime() != null) {
            queryWrapper.ne(UserDo::getLastLoginTime, bo.getNeq_lastLoginTime());
        }
        if (bo.getGt_lastLoginTime() != null) {
            queryWrapper.gt(UserDo::getLastLoginTime, bo.getGt_lastLoginTime());
        }
        if (bo.getLt_lastLoginTime() != null) {
            queryWrapper.lt(UserDo::getLastLoginTime, bo.getLt_lastLoginTime());
        }
        if (bo.getEgt_lastLoginTime() != null) {
            queryWrapper.ge(UserDo::getLastLoginTime, bo.getEgt_lastLoginTime());
        }
        if (bo.getElt_lastLoginTime() != null) {
            queryWrapper.le(UserDo::getLastLoginTime, bo.getElt_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_lastLoginTime())) {
            WrapperUtil.between(queryWrapper, UserDo::getLastLoginTime, bo.getBetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_lastLoginTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getLastLoginTime, bo.getNotbetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginTime())) {
            WrapperUtil.in(queryWrapper, UserDo::getLastLoginTime, bo.getIn_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginTime())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getLastLoginTime, bo.getNotin_lastLoginTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(UserDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(UserDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(UserDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(UserDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(UserDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(UserDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, UserDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, UserDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(UserDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(UserDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(UserDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(UserDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(UserDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(UserDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, UserDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, UserDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(UserDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(UserDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(UserDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(UserDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(UserDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(UserDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, UserDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(UserDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(UserDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(UserDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(UserDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(UserDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(UserDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, UserDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(UserDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(UserDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(UserDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(UserDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(UserDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(UserDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, UserDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(UserDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(UserDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(UserDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(UserDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(UserDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(UserDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, UserDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<UserDo> getJoinLambdaQueryWrapper(UserSearchBo bo) {
        JoinLambdaQueryWrapper<UserDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, UserDo::getIsDelete, bo.getDataScope());

        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(UserDo::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(UserDo::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserId, bo.getNotin_userId());
        }
        // super_administrator 开始
        if (bo.getEq_superAdministrator() != null) {
            queryWrapper.eq(UserDo::getSuperAdministrator, bo.getEq_superAdministrator());
        }
        if (bo.getNeq_superAdministrator() != null) {
            queryWrapper.ne(UserDo::getSuperAdministrator, bo.getNeq_superAdministrator());
        }
        if (bo.getGt_superAdministrator() != null) {
            queryWrapper.gt(UserDo::getSuperAdministrator, bo.getGt_superAdministrator());
        }
        if (bo.getLt_superAdministrator() != null) {
            queryWrapper.lt(UserDo::getSuperAdministrator, bo.getLt_superAdministrator());
        }
        if (bo.getEgt_superAdministrator() != null) {
            queryWrapper.ge(UserDo::getSuperAdministrator, bo.getEgt_superAdministrator());
        }
        if (bo.getElt_superAdministrator() != null) {
            queryWrapper.le(UserDo::getSuperAdministrator, bo.getElt_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_superAdministrator())) {
            WrapperUtil.between(queryWrapper, UserDo::getSuperAdministrator, bo.getBetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_superAdministrator())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getSuperAdministrator, bo.getNotbetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getIn_superAdministrator())) {
            WrapperUtil.in(queryWrapper, UserDo::getSuperAdministrator, bo.getIn_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_superAdministrator())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getSuperAdministrator, bo.getNotin_superAdministrator());
        }
        // ban_state 开始
        if (bo.getEq_banState() != null) {
            queryWrapper.eq(UserDo::getBanState, bo.getEq_banState());
        }
        if (bo.getNeq_banState() != null) {
            queryWrapper.ne(UserDo::getBanState, bo.getNeq_banState());
        }
        if (bo.getGt_banState() != null) {
            queryWrapper.gt(UserDo::getBanState, bo.getGt_banState());
        }
        if (bo.getLt_banState() != null) {
            queryWrapper.lt(UserDo::getBanState, bo.getLt_banState());
        }
        if (bo.getEgt_banState() != null) {
            queryWrapper.ge(UserDo::getBanState, bo.getEgt_banState());
        }
        if (bo.getElt_banState() != null) {
            queryWrapper.le(UserDo::getBanState, bo.getElt_banState());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_banState())) {
            WrapperUtil.between(queryWrapper, UserDo::getBanState, bo.getBetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_banState())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getBanState, bo.getNotbetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getIn_banState())) {
            WrapperUtil.in(queryWrapper, UserDo::getBanState, bo.getIn_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_banState())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getBanState, bo.getNotin_banState());
        }
        // sex 开始
        if (bo.getEq_sex() != null) {
            queryWrapper.eq(UserDo::getSex, bo.getEq_sex());
        }
        if (bo.getNeq_sex() != null) {
            queryWrapper.ne(UserDo::getSex, bo.getNeq_sex());
        }
        if (bo.getGt_sex() != null) {
            queryWrapper.gt(UserDo::getSex, bo.getGt_sex());
        }
        if (bo.getLt_sex() != null) {
            queryWrapper.lt(UserDo::getSex, bo.getLt_sex());
        }
        if (bo.getEgt_sex() != null) {
            queryWrapper.ge(UserDo::getSex, bo.getEgt_sex());
        }
        if (bo.getElt_sex() != null) {
            queryWrapper.le(UserDo::getSex, bo.getElt_sex());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sex())) {
            WrapperUtil.between(queryWrapper, UserDo::getSex, bo.getBetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sex())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getSex, bo.getNotbetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sex())) {
            WrapperUtil.in(queryWrapper, UserDo::getSex, bo.getIn_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sex())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getSex, bo.getNotin_sex());
        }
        // user_channel 开始
        if (bo.getEq_userChannel() != null) {
            queryWrapper.eq(UserDo::getUserChannel, bo.getEq_userChannel());
        }
        if (bo.getNeq_userChannel() != null) {
            queryWrapper.ne(UserDo::getUserChannel, bo.getNeq_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userChannel())) {
            queryWrapper.like(UserDo::getUserChannel, bo.getLike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userChannel())) {
            queryWrapper.likeRight(UserDo::getUserChannel, bo.getLiker_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userChannel())) {
            queryWrapper.likeLeft(UserDo::getUserChannel, bo.getLikel_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userChannel())) {
            queryWrapper.notLike(UserDo::getUserChannel, bo.getNotlike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userChannel())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserChannel, bo.getIn_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userChannel())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserChannel, bo.getNotin_userChannel());
        }
        // account_type 开始
        if (bo.getEq_accountType() != null) {
            queryWrapper.eq(UserDo::getAccountType, bo.getEq_accountType());
        }
        if (bo.getNeq_accountType() != null) {
            queryWrapper.ne(UserDo::getAccountType, bo.getNeq_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_accountType())) {
            queryWrapper.like(UserDo::getAccountType, bo.getLike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_accountType())) {
            queryWrapper.likeRight(UserDo::getAccountType, bo.getLiker_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_accountType())) {
            queryWrapper.likeLeft(UserDo::getAccountType, bo.getLikel_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_accountType())) {
            queryWrapper.notLike(UserDo::getAccountType, bo.getNotlike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_accountType())) {
            WrapperUtil.in(queryWrapper, UserDo::getAccountType, bo.getIn_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_accountType())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getAccountType, bo.getNotin_accountType());
        }
        // user_name 开始
        if (bo.getEq_userName() != null) {
            queryWrapper.eq(UserDo::getUserName, bo.getEq_userName());
        }
        if (bo.getNeq_userName() != null) {
            queryWrapper.ne(UserDo::getUserName, bo.getNeq_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userName())) {
            queryWrapper.like(UserDo::getUserName, bo.getLike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userName())) {
            queryWrapper.likeRight(UserDo::getUserName, bo.getLiker_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userName())) {
            queryWrapper.likeLeft(UserDo::getUserName, bo.getLikel_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userName())) {
            queryWrapper.notLike(UserDo::getUserName, bo.getNotlike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userName())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserName, bo.getIn_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userName())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserName, bo.getNotin_userName());
        }
        // avatar 开始
        if (bo.getEq_avatar() != null) {
            queryWrapper.eq(UserDo::getAvatar, bo.getEq_avatar());
        }
        if (bo.getNeq_avatar() != null) {
            queryWrapper.ne(UserDo::getAvatar, bo.getNeq_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLike_avatar())) {
            queryWrapper.like(UserDo::getAvatar, bo.getLike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_avatar())) {
            queryWrapper.likeRight(UserDo::getAvatar, bo.getLiker_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_avatar())) {
            queryWrapper.likeLeft(UserDo::getAvatar, bo.getLikel_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_avatar())) {
            queryWrapper.notLike(UserDo::getAvatar, bo.getNotlike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getIn_avatar())) {
            WrapperUtil.in(queryWrapper, UserDo::getAvatar, bo.getIn_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_avatar())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getAvatar, bo.getNotin_avatar());
        }
        // user_account 开始
        if (bo.getEq_userAccount() != null) {
            queryWrapper.eq(UserDo::getUserAccount, bo.getEq_userAccount());
        }
        if (bo.getNeq_userAccount() != null) {
            queryWrapper.ne(UserDo::getUserAccount, bo.getNeq_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userAccount())) {
            queryWrapper.like(UserDo::getUserAccount, bo.getLike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userAccount())) {
            queryWrapper.likeRight(UserDo::getUserAccount, bo.getLiker_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userAccount())) {
            queryWrapper.likeLeft(UserDo::getUserAccount, bo.getLikel_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userAccount())) {
            queryWrapper.notLike(UserDo::getUserAccount, bo.getNotlike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userAccount())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserAccount, bo.getIn_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userAccount())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserAccount, bo.getNotin_userAccount());
        }
        // user_phone 开始
        if (bo.getEq_userPhone() != null) {
            queryWrapper.eq(UserDo::getUserPhone, bo.getEq_userPhone());
        }
        if (bo.getNeq_userPhone() != null) {
            queryWrapper.ne(UserDo::getUserPhone, bo.getNeq_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPhone())) {
            queryWrapper.like(UserDo::getUserPhone, bo.getLike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPhone())) {
            queryWrapper.likeRight(UserDo::getUserPhone, bo.getLiker_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPhone())) {
            queryWrapper.likeLeft(UserDo::getUserPhone, bo.getLikel_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPhone())) {
            queryWrapper.notLike(UserDo::getUserPhone, bo.getNotlike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPhone())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserPhone, bo.getIn_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPhone())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserPhone, bo.getNotin_userPhone());
        }
        // user_email 开始
        if (bo.getEq_userEmail() != null) {
            queryWrapper.eq(UserDo::getUserEmail, bo.getEq_userEmail());
        }
        if (bo.getNeq_userEmail() != null) {
            queryWrapper.ne(UserDo::getUserEmail, bo.getNeq_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userEmail())) {
            queryWrapper.like(UserDo::getUserEmail, bo.getLike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userEmail())) {
            queryWrapper.likeRight(UserDo::getUserEmail, bo.getLiker_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userEmail())) {
            queryWrapper.likeLeft(UserDo::getUserEmail, bo.getLikel_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userEmail())) {
            queryWrapper.notLike(UserDo::getUserEmail, bo.getNotlike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userEmail())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserEmail, bo.getIn_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userEmail())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserEmail, bo.getNotin_userEmail());
        }
        // user_passwd 开始
        if (bo.getEq_userPasswd() != null) {
            queryWrapper.eq(UserDo::getUserPasswd, bo.getEq_userPasswd());
        }
        if (bo.getNeq_userPasswd() != null) {
            queryWrapper.ne(UserDo::getUserPasswd, bo.getNeq_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPasswd())) {
            queryWrapper.like(UserDo::getUserPasswd, bo.getLike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPasswd())) {
            queryWrapper.likeRight(UserDo::getUserPasswd, bo.getLiker_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPasswd())) {
            queryWrapper.likeLeft(UserDo::getUserPasswd, bo.getLikel_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPasswd())) {
            queryWrapper.notLike(UserDo::getUserPasswd, bo.getNotlike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPasswd())) {
            WrapperUtil.in(queryWrapper, UserDo::getUserPasswd, bo.getIn_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPasswd())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUserPasswd, bo.getNotin_userPasswd());
        }
        // last_login_ip 开始
        if (bo.getEq_lastLoginIp() != null) {
            queryWrapper.eq(UserDo::getLastLoginIp, bo.getEq_lastLoginIp());
        }
        if (bo.getNeq_lastLoginIp() != null) {
            queryWrapper.ne(UserDo::getLastLoginIp, bo.getNeq_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLike_lastLoginIp())) {
            queryWrapper.like(UserDo::getLastLoginIp, bo.getLike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_lastLoginIp())) {
            queryWrapper.likeRight(UserDo::getLastLoginIp, bo.getLiker_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_lastLoginIp())) {
            queryWrapper.likeLeft(UserDo::getLastLoginIp, bo.getLikel_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_lastLoginIp())) {
            queryWrapper.notLike(UserDo::getLastLoginIp, bo.getNotlike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginIp())) {
            WrapperUtil.in(queryWrapper, UserDo::getLastLoginIp, bo.getIn_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginIp())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getLastLoginIp, bo.getNotin_lastLoginIp());
        }
        // last_login_time 开始
        if (bo.getEq_lastLoginTime() != null) {
            queryWrapper.eq(UserDo::getLastLoginTime, bo.getEq_lastLoginTime());
        }
        if (bo.getNeq_lastLoginTime() != null) {
            queryWrapper.ne(UserDo::getLastLoginTime, bo.getNeq_lastLoginTime());
        }
        if (bo.getGt_lastLoginTime() != null) {
            queryWrapper.gt(UserDo::getLastLoginTime, bo.getGt_lastLoginTime());
        }
        if (bo.getLt_lastLoginTime() != null) {
            queryWrapper.lt(UserDo::getLastLoginTime, bo.getLt_lastLoginTime());
        }
        if (bo.getEgt_lastLoginTime() != null) {
            queryWrapper.ge(UserDo::getLastLoginTime, bo.getEgt_lastLoginTime());
        }
        if (bo.getElt_lastLoginTime() != null) {
            queryWrapper.le(UserDo::getLastLoginTime, bo.getElt_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_lastLoginTime())) {
            WrapperUtil.between(queryWrapper, UserDo::getLastLoginTime, bo.getBetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_lastLoginTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getLastLoginTime, bo.getNotbetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginTime())) {
            WrapperUtil.in(queryWrapper, UserDo::getLastLoginTime, bo.getIn_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginTime())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getLastLoginTime, bo.getNotin_lastLoginTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(UserDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(UserDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(UserDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(UserDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(UserDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(UserDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, UserDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, UserDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(UserDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(UserDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(UserDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(UserDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(UserDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(UserDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, UserDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, UserDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(UserDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(UserDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(UserDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(UserDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(UserDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(UserDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, UserDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(UserDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(UserDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(UserDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(UserDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(UserDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(UserDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, UserDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(UserDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(UserDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(UserDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(UserDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(UserDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(UserDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, UserDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(UserDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(UserDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(UserDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(UserDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(UserDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(UserDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, UserDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, UserDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}