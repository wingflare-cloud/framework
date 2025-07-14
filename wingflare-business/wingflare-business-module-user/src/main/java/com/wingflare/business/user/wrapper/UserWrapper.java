package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.business.user.db.UserDO;
import com.wingflare.facade.module.user.bo.UserSearchBO;
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
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<UserDO> getLambdaQueryWrapper(UserSearchBO bo) {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, UserDO::getIsDelete, bo.getDataScope());

        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(UserDO::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(UserDO::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserId, bo.getNotin_userId());
        }
        // super_administrator 开始
        if (bo.getEq_superAdministrator() != null) {
            queryWrapper.eq(UserDO::getSuperAdministrator, bo.getEq_superAdministrator());
        }
        if (bo.getNeq_superAdministrator() != null) {
            queryWrapper.ne(UserDO::getSuperAdministrator, bo.getNeq_superAdministrator());
        }
        if (bo.getGt_superAdministrator() != null) {
            queryWrapper.gt(UserDO::getSuperAdministrator, bo.getGt_superAdministrator());
        }
        if (bo.getLt_superAdministrator() != null) {
            queryWrapper.lt(UserDO::getSuperAdministrator, bo.getLt_superAdministrator());
        }
        if (bo.getEgt_superAdministrator() != null) {
            queryWrapper.ge(UserDO::getSuperAdministrator, bo.getEgt_superAdministrator());
        }
        if (bo.getElt_superAdministrator() != null) {
            queryWrapper.le(UserDO::getSuperAdministrator, bo.getElt_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_superAdministrator())) {
            WrapperUtil.between(queryWrapper, UserDO::getSuperAdministrator, bo.getBetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_superAdministrator())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getSuperAdministrator, bo.getNotbetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getIn_superAdministrator())) {
            WrapperUtil.in(queryWrapper, UserDO::getSuperAdministrator, bo.getIn_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_superAdministrator())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getSuperAdministrator, bo.getNotin_superAdministrator());
        }
        // ban_state 开始
        if (bo.getEq_banState() != null) {
            queryWrapper.eq(UserDO::getBanState, bo.getEq_banState());
        }
        if (bo.getNeq_banState() != null) {
            queryWrapper.ne(UserDO::getBanState, bo.getNeq_banState());
        }
        if (bo.getGt_banState() != null) {
            queryWrapper.gt(UserDO::getBanState, bo.getGt_banState());
        }
        if (bo.getLt_banState() != null) {
            queryWrapper.lt(UserDO::getBanState, bo.getLt_banState());
        }
        if (bo.getEgt_banState() != null) {
            queryWrapper.ge(UserDO::getBanState, bo.getEgt_banState());
        }
        if (bo.getElt_banState() != null) {
            queryWrapper.le(UserDO::getBanState, bo.getElt_banState());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_banState())) {
            WrapperUtil.between(queryWrapper, UserDO::getBanState, bo.getBetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_banState())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getBanState, bo.getNotbetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getIn_banState())) {
            WrapperUtil.in(queryWrapper, UserDO::getBanState, bo.getIn_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_banState())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getBanState, bo.getNotin_banState());
        }
        // sex 开始
        if (bo.getEq_sex() != null) {
            queryWrapper.eq(UserDO::getSex, bo.getEq_sex());
        }
        if (bo.getNeq_sex() != null) {
            queryWrapper.ne(UserDO::getSex, bo.getNeq_sex());
        }
        if (bo.getGt_sex() != null) {
            queryWrapper.gt(UserDO::getSex, bo.getGt_sex());
        }
        if (bo.getLt_sex() != null) {
            queryWrapper.lt(UserDO::getSex, bo.getLt_sex());
        }
        if (bo.getEgt_sex() != null) {
            queryWrapper.ge(UserDO::getSex, bo.getEgt_sex());
        }
        if (bo.getElt_sex() != null) {
            queryWrapper.le(UserDO::getSex, bo.getElt_sex());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sex())) {
            WrapperUtil.between(queryWrapper, UserDO::getSex, bo.getBetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sex())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getSex, bo.getNotbetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sex())) {
            WrapperUtil.in(queryWrapper, UserDO::getSex, bo.getIn_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sex())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getSex, bo.getNotin_sex());
        }
        // user_channel 开始
        if (bo.getEq_userChannel() != null) {
            queryWrapper.eq(UserDO::getUserChannel, bo.getEq_userChannel());
        }
        if (bo.getNeq_userChannel() != null) {
            queryWrapper.ne(UserDO::getUserChannel, bo.getNeq_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userChannel())) {
            queryWrapper.like(UserDO::getUserChannel, bo.getLike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userChannel())) {
            queryWrapper.likeRight(UserDO::getUserChannel, bo.getLiker_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userChannel())) {
            queryWrapper.likeLeft(UserDO::getUserChannel, bo.getLikel_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userChannel())) {
            queryWrapper.notLike(UserDO::getUserChannel, bo.getNotlike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userChannel())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserChannel, bo.getIn_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userChannel())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserChannel, bo.getNotin_userChannel());
        }
        // account_type 开始
        if (bo.getEq_accountType() != null) {
            queryWrapper.eq(UserDO::getAccountType, bo.getEq_accountType());
        }
        if (bo.getNeq_accountType() != null) {
            queryWrapper.ne(UserDO::getAccountType, bo.getNeq_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_accountType())) {
            queryWrapper.like(UserDO::getAccountType, bo.getLike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_accountType())) {
            queryWrapper.likeRight(UserDO::getAccountType, bo.getLiker_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_accountType())) {
            queryWrapper.likeLeft(UserDO::getAccountType, bo.getLikel_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_accountType())) {
            queryWrapper.notLike(UserDO::getAccountType, bo.getNotlike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_accountType())) {
            WrapperUtil.in(queryWrapper, UserDO::getAccountType, bo.getIn_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_accountType())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getAccountType, bo.getNotin_accountType());
        }
        // user_name 开始
        if (bo.getEq_userName() != null) {
            queryWrapper.eq(UserDO::getUserName, bo.getEq_userName());
        }
        if (bo.getNeq_userName() != null) {
            queryWrapper.ne(UserDO::getUserName, bo.getNeq_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userName())) {
            queryWrapper.like(UserDO::getUserName, bo.getLike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userName())) {
            queryWrapper.likeRight(UserDO::getUserName, bo.getLiker_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userName())) {
            queryWrapper.likeLeft(UserDO::getUserName, bo.getLikel_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userName())) {
            queryWrapper.notLike(UserDO::getUserName, bo.getNotlike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userName())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserName, bo.getIn_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userName())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserName, bo.getNotin_userName());
        }
        // avatar 开始
        if (bo.getEq_avatar() != null) {
            queryWrapper.eq(UserDO::getAvatar, bo.getEq_avatar());
        }
        if (bo.getNeq_avatar() != null) {
            queryWrapper.ne(UserDO::getAvatar, bo.getNeq_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLike_avatar())) {
            queryWrapper.like(UserDO::getAvatar, bo.getLike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_avatar())) {
            queryWrapper.likeRight(UserDO::getAvatar, bo.getLiker_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_avatar())) {
            queryWrapper.likeLeft(UserDO::getAvatar, bo.getLikel_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_avatar())) {
            queryWrapper.notLike(UserDO::getAvatar, bo.getNotlike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getIn_avatar())) {
            WrapperUtil.in(queryWrapper, UserDO::getAvatar, bo.getIn_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_avatar())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getAvatar, bo.getNotin_avatar());
        }
        // user_account 开始
        if (bo.getEq_userAccount() != null) {
            queryWrapper.eq(UserDO::getUserAccount, bo.getEq_userAccount());
        }
        if (bo.getNeq_userAccount() != null) {
            queryWrapper.ne(UserDO::getUserAccount, bo.getNeq_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userAccount())) {
            queryWrapper.like(UserDO::getUserAccount, bo.getLike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userAccount())) {
            queryWrapper.likeRight(UserDO::getUserAccount, bo.getLiker_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userAccount())) {
            queryWrapper.likeLeft(UserDO::getUserAccount, bo.getLikel_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userAccount())) {
            queryWrapper.notLike(UserDO::getUserAccount, bo.getNotlike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userAccount())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserAccount, bo.getIn_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userAccount())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserAccount, bo.getNotin_userAccount());
        }
        // user_phone 开始
        if (bo.getEq_userPhone() != null) {
            queryWrapper.eq(UserDO::getUserPhone, bo.getEq_userPhone());
        }
        if (bo.getNeq_userPhone() != null) {
            queryWrapper.ne(UserDO::getUserPhone, bo.getNeq_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPhone())) {
            queryWrapper.like(UserDO::getUserPhone, bo.getLike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPhone())) {
            queryWrapper.likeRight(UserDO::getUserPhone, bo.getLiker_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPhone())) {
            queryWrapper.likeLeft(UserDO::getUserPhone, bo.getLikel_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPhone())) {
            queryWrapper.notLike(UserDO::getUserPhone, bo.getNotlike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPhone())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserPhone, bo.getIn_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPhone())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserPhone, bo.getNotin_userPhone());
        }
        // user_email 开始
        if (bo.getEq_userEmail() != null) {
            queryWrapper.eq(UserDO::getUserEmail, bo.getEq_userEmail());
        }
        if (bo.getNeq_userEmail() != null) {
            queryWrapper.ne(UserDO::getUserEmail, bo.getNeq_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userEmail())) {
            queryWrapper.like(UserDO::getUserEmail, bo.getLike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userEmail())) {
            queryWrapper.likeRight(UserDO::getUserEmail, bo.getLiker_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userEmail())) {
            queryWrapper.likeLeft(UserDO::getUserEmail, bo.getLikel_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userEmail())) {
            queryWrapper.notLike(UserDO::getUserEmail, bo.getNotlike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userEmail())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserEmail, bo.getIn_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userEmail())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserEmail, bo.getNotin_userEmail());
        }
        // user_passwd 开始
        if (bo.getEq_userPasswd() != null) {
            queryWrapper.eq(UserDO::getUserPasswd, bo.getEq_userPasswd());
        }
        if (bo.getNeq_userPasswd() != null) {
            queryWrapper.ne(UserDO::getUserPasswd, bo.getNeq_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPasswd())) {
            queryWrapper.like(UserDO::getUserPasswd, bo.getLike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPasswd())) {
            queryWrapper.likeRight(UserDO::getUserPasswd, bo.getLiker_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPasswd())) {
            queryWrapper.likeLeft(UserDO::getUserPasswd, bo.getLikel_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPasswd())) {
            queryWrapper.notLike(UserDO::getUserPasswd, bo.getNotlike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPasswd())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserPasswd, bo.getIn_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPasswd())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserPasswd, bo.getNotin_userPasswd());
        }
        // last_login_ip 开始
        if (bo.getEq_lastLoginIp() != null) {
            queryWrapper.eq(UserDO::getLastLoginIp, bo.getEq_lastLoginIp());
        }
        if (bo.getNeq_lastLoginIp() != null) {
            queryWrapper.ne(UserDO::getLastLoginIp, bo.getNeq_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLike_lastLoginIp())) {
            queryWrapper.like(UserDO::getLastLoginIp, bo.getLike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_lastLoginIp())) {
            queryWrapper.likeRight(UserDO::getLastLoginIp, bo.getLiker_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_lastLoginIp())) {
            queryWrapper.likeLeft(UserDO::getLastLoginIp, bo.getLikel_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_lastLoginIp())) {
            queryWrapper.notLike(UserDO::getLastLoginIp, bo.getNotlike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginIp())) {
            WrapperUtil.in(queryWrapper, UserDO::getLastLoginIp, bo.getIn_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginIp())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getLastLoginIp, bo.getNotin_lastLoginIp());
        }
        // last_login_time 开始
        if (bo.getEq_lastLoginTime() != null) {
            queryWrapper.eq(UserDO::getLastLoginTime, bo.getEq_lastLoginTime());
        }
        if (bo.getNeq_lastLoginTime() != null) {
            queryWrapper.ne(UserDO::getLastLoginTime, bo.getNeq_lastLoginTime());
        }
        if (bo.getGt_lastLoginTime() != null) {
            queryWrapper.gt(UserDO::getLastLoginTime, bo.getGt_lastLoginTime());
        }
        if (bo.getLt_lastLoginTime() != null) {
            queryWrapper.lt(UserDO::getLastLoginTime, bo.getLt_lastLoginTime());
        }
        if (bo.getEgt_lastLoginTime() != null) {
            queryWrapper.ge(UserDO::getLastLoginTime, bo.getEgt_lastLoginTime());
        }
        if (bo.getElt_lastLoginTime() != null) {
            queryWrapper.le(UserDO::getLastLoginTime, bo.getElt_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_lastLoginTime())) {
            WrapperUtil.between(queryWrapper, UserDO::getLastLoginTime, bo.getBetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_lastLoginTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getLastLoginTime, bo.getNotbetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginTime())) {
            WrapperUtil.in(queryWrapper, UserDO::getLastLoginTime, bo.getIn_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginTime())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getLastLoginTime, bo.getNotin_lastLoginTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(UserDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(UserDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(UserDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(UserDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(UserDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(UserDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, UserDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, UserDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(UserDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(UserDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(UserDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(UserDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(UserDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(UserDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, UserDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, UserDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(UserDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(UserDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(UserDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(UserDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(UserDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(UserDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, UserDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(UserDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(UserDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(UserDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(UserDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(UserDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(UserDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, UserDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(UserDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(UserDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(UserDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(UserDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(UserDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(UserDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, UserDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(UserDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(UserDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(UserDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(UserDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(UserDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(UserDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, UserDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<UserDO> getJoinLambdaQueryWrapper(UserSearchBO bo) {
        JoinLambdaQueryWrapper<UserDO> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, UserDO::getIsDelete, bo.getDataScope());

        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(UserDO::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(UserDO::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserId, bo.getNotin_userId());
        }
        // super_administrator 开始
        if (bo.getEq_superAdministrator() != null) {
            queryWrapper.eq(UserDO::getSuperAdministrator, bo.getEq_superAdministrator());
        }
        if (bo.getNeq_superAdministrator() != null) {
            queryWrapper.ne(UserDO::getSuperAdministrator, bo.getNeq_superAdministrator());
        }
        if (bo.getGt_superAdministrator() != null) {
            queryWrapper.gt(UserDO::getSuperAdministrator, bo.getGt_superAdministrator());
        }
        if (bo.getLt_superAdministrator() != null) {
            queryWrapper.lt(UserDO::getSuperAdministrator, bo.getLt_superAdministrator());
        }
        if (bo.getEgt_superAdministrator() != null) {
            queryWrapper.ge(UserDO::getSuperAdministrator, bo.getEgt_superAdministrator());
        }
        if (bo.getElt_superAdministrator() != null) {
            queryWrapper.le(UserDO::getSuperAdministrator, bo.getElt_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_superAdministrator())) {
            WrapperUtil.between(queryWrapper, UserDO::getSuperAdministrator, bo.getBetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_superAdministrator())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getSuperAdministrator, bo.getNotbetween_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getIn_superAdministrator())) {
            WrapperUtil.in(queryWrapper, UserDO::getSuperAdministrator, bo.getIn_superAdministrator());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_superAdministrator())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getSuperAdministrator, bo.getNotin_superAdministrator());
        }
        // ban_state 开始
        if (bo.getEq_banState() != null) {
            queryWrapper.eq(UserDO::getBanState, bo.getEq_banState());
        }
        if (bo.getNeq_banState() != null) {
            queryWrapper.ne(UserDO::getBanState, bo.getNeq_banState());
        }
        if (bo.getGt_banState() != null) {
            queryWrapper.gt(UserDO::getBanState, bo.getGt_banState());
        }
        if (bo.getLt_banState() != null) {
            queryWrapper.lt(UserDO::getBanState, bo.getLt_banState());
        }
        if (bo.getEgt_banState() != null) {
            queryWrapper.ge(UserDO::getBanState, bo.getEgt_banState());
        }
        if (bo.getElt_banState() != null) {
            queryWrapper.le(UserDO::getBanState, bo.getElt_banState());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_banState())) {
            WrapperUtil.between(queryWrapper, UserDO::getBanState, bo.getBetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_banState())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getBanState, bo.getNotbetween_banState());
        }
        if (StringUtil.isNotEmpty(bo.getIn_banState())) {
            WrapperUtil.in(queryWrapper, UserDO::getBanState, bo.getIn_banState());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_banState())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getBanState, bo.getNotin_banState());
        }
        // sex 开始
        if (bo.getEq_sex() != null) {
            queryWrapper.eq(UserDO::getSex, bo.getEq_sex());
        }
        if (bo.getNeq_sex() != null) {
            queryWrapper.ne(UserDO::getSex, bo.getNeq_sex());
        }
        if (bo.getGt_sex() != null) {
            queryWrapper.gt(UserDO::getSex, bo.getGt_sex());
        }
        if (bo.getLt_sex() != null) {
            queryWrapper.lt(UserDO::getSex, bo.getLt_sex());
        }
        if (bo.getEgt_sex() != null) {
            queryWrapper.ge(UserDO::getSex, bo.getEgt_sex());
        }
        if (bo.getElt_sex() != null) {
            queryWrapper.le(UserDO::getSex, bo.getElt_sex());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sex())) {
            WrapperUtil.between(queryWrapper, UserDO::getSex, bo.getBetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sex())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getSex, bo.getNotbetween_sex());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sex())) {
            WrapperUtil.in(queryWrapper, UserDO::getSex, bo.getIn_sex());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sex())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getSex, bo.getNotin_sex());
        }
        // user_channel 开始
        if (bo.getEq_userChannel() != null) {
            queryWrapper.eq(UserDO::getUserChannel, bo.getEq_userChannel());
        }
        if (bo.getNeq_userChannel() != null) {
            queryWrapper.ne(UserDO::getUserChannel, bo.getNeq_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userChannel())) {
            queryWrapper.like(UserDO::getUserChannel, bo.getLike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userChannel())) {
            queryWrapper.likeRight(UserDO::getUserChannel, bo.getLiker_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userChannel())) {
            queryWrapper.likeLeft(UserDO::getUserChannel, bo.getLikel_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userChannel())) {
            queryWrapper.notLike(UserDO::getUserChannel, bo.getNotlike_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userChannel())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserChannel, bo.getIn_userChannel());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userChannel())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserChannel, bo.getNotin_userChannel());
        }
        // account_type 开始
        if (bo.getEq_accountType() != null) {
            queryWrapper.eq(UserDO::getAccountType, bo.getEq_accountType());
        }
        if (bo.getNeq_accountType() != null) {
            queryWrapper.ne(UserDO::getAccountType, bo.getNeq_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_accountType())) {
            queryWrapper.like(UserDO::getAccountType, bo.getLike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_accountType())) {
            queryWrapper.likeRight(UserDO::getAccountType, bo.getLiker_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_accountType())) {
            queryWrapper.likeLeft(UserDO::getAccountType, bo.getLikel_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_accountType())) {
            queryWrapper.notLike(UserDO::getAccountType, bo.getNotlike_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_accountType())) {
            WrapperUtil.in(queryWrapper, UserDO::getAccountType, bo.getIn_accountType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_accountType())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getAccountType, bo.getNotin_accountType());
        }
        // user_name 开始
        if (bo.getEq_userName() != null) {
            queryWrapper.eq(UserDO::getUserName, bo.getEq_userName());
        }
        if (bo.getNeq_userName() != null) {
            queryWrapper.ne(UserDO::getUserName, bo.getNeq_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userName())) {
            queryWrapper.like(UserDO::getUserName, bo.getLike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userName())) {
            queryWrapper.likeRight(UserDO::getUserName, bo.getLiker_userName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userName())) {
            queryWrapper.likeLeft(UserDO::getUserName, bo.getLikel_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userName())) {
            queryWrapper.notLike(UserDO::getUserName, bo.getNotlike_userName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userName())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserName, bo.getIn_userName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userName())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserName, bo.getNotin_userName());
        }
        // avatar 开始
        if (bo.getEq_avatar() != null) {
            queryWrapper.eq(UserDO::getAvatar, bo.getEq_avatar());
        }
        if (bo.getNeq_avatar() != null) {
            queryWrapper.ne(UserDO::getAvatar, bo.getNeq_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLike_avatar())) {
            queryWrapper.like(UserDO::getAvatar, bo.getLike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_avatar())) {
            queryWrapper.likeRight(UserDO::getAvatar, bo.getLiker_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_avatar())) {
            queryWrapper.likeLeft(UserDO::getAvatar, bo.getLikel_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_avatar())) {
            queryWrapper.notLike(UserDO::getAvatar, bo.getNotlike_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getIn_avatar())) {
            WrapperUtil.in(queryWrapper, UserDO::getAvatar, bo.getIn_avatar());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_avatar())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getAvatar, bo.getNotin_avatar());
        }
        // user_account 开始
        if (bo.getEq_userAccount() != null) {
            queryWrapper.eq(UserDO::getUserAccount, bo.getEq_userAccount());
        }
        if (bo.getNeq_userAccount() != null) {
            queryWrapper.ne(UserDO::getUserAccount, bo.getNeq_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userAccount())) {
            queryWrapper.like(UserDO::getUserAccount, bo.getLike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userAccount())) {
            queryWrapper.likeRight(UserDO::getUserAccount, bo.getLiker_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userAccount())) {
            queryWrapper.likeLeft(UserDO::getUserAccount, bo.getLikel_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userAccount())) {
            queryWrapper.notLike(UserDO::getUserAccount, bo.getNotlike_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userAccount())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserAccount, bo.getIn_userAccount());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userAccount())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserAccount, bo.getNotin_userAccount());
        }
        // user_phone 开始
        if (bo.getEq_userPhone() != null) {
            queryWrapper.eq(UserDO::getUserPhone, bo.getEq_userPhone());
        }
        if (bo.getNeq_userPhone() != null) {
            queryWrapper.ne(UserDO::getUserPhone, bo.getNeq_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPhone())) {
            queryWrapper.like(UserDO::getUserPhone, bo.getLike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPhone())) {
            queryWrapper.likeRight(UserDO::getUserPhone, bo.getLiker_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPhone())) {
            queryWrapper.likeLeft(UserDO::getUserPhone, bo.getLikel_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPhone())) {
            queryWrapper.notLike(UserDO::getUserPhone, bo.getNotlike_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPhone())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserPhone, bo.getIn_userPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPhone())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserPhone, bo.getNotin_userPhone());
        }
        // user_email 开始
        if (bo.getEq_userEmail() != null) {
            queryWrapper.eq(UserDO::getUserEmail, bo.getEq_userEmail());
        }
        if (bo.getNeq_userEmail() != null) {
            queryWrapper.ne(UserDO::getUserEmail, bo.getNeq_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userEmail())) {
            queryWrapper.like(UserDO::getUserEmail, bo.getLike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userEmail())) {
            queryWrapper.likeRight(UserDO::getUserEmail, bo.getLiker_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userEmail())) {
            queryWrapper.likeLeft(UserDO::getUserEmail, bo.getLikel_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userEmail())) {
            queryWrapper.notLike(UserDO::getUserEmail, bo.getNotlike_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userEmail())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserEmail, bo.getIn_userEmail());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userEmail())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserEmail, bo.getNotin_userEmail());
        }
        // user_passwd 开始
        if (bo.getEq_userPasswd() != null) {
            queryWrapper.eq(UserDO::getUserPasswd, bo.getEq_userPasswd());
        }
        if (bo.getNeq_userPasswd() != null) {
            queryWrapper.ne(UserDO::getUserPasswd, bo.getNeq_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userPasswd())) {
            queryWrapper.like(UserDO::getUserPasswd, bo.getLike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userPasswd())) {
            queryWrapper.likeRight(UserDO::getUserPasswd, bo.getLiker_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userPasswd())) {
            queryWrapper.likeLeft(UserDO::getUserPasswd, bo.getLikel_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userPasswd())) {
            queryWrapper.notLike(UserDO::getUserPasswd, bo.getNotlike_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userPasswd())) {
            WrapperUtil.in(queryWrapper, UserDO::getUserPasswd, bo.getIn_userPasswd());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userPasswd())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUserPasswd, bo.getNotin_userPasswd());
        }
        // last_login_ip 开始
        if (bo.getEq_lastLoginIp() != null) {
            queryWrapper.eq(UserDO::getLastLoginIp, bo.getEq_lastLoginIp());
        }
        if (bo.getNeq_lastLoginIp() != null) {
            queryWrapper.ne(UserDO::getLastLoginIp, bo.getNeq_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLike_lastLoginIp())) {
            queryWrapper.like(UserDO::getLastLoginIp, bo.getLike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_lastLoginIp())) {
            queryWrapper.likeRight(UserDO::getLastLoginIp, bo.getLiker_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_lastLoginIp())) {
            queryWrapper.likeLeft(UserDO::getLastLoginIp, bo.getLikel_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_lastLoginIp())) {
            queryWrapper.notLike(UserDO::getLastLoginIp, bo.getNotlike_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginIp())) {
            WrapperUtil.in(queryWrapper, UserDO::getLastLoginIp, bo.getIn_lastLoginIp());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginIp())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getLastLoginIp, bo.getNotin_lastLoginIp());
        }
        // last_login_time 开始
        if (bo.getEq_lastLoginTime() != null) {
            queryWrapper.eq(UserDO::getLastLoginTime, bo.getEq_lastLoginTime());
        }
        if (bo.getNeq_lastLoginTime() != null) {
            queryWrapper.ne(UserDO::getLastLoginTime, bo.getNeq_lastLoginTime());
        }
        if (bo.getGt_lastLoginTime() != null) {
            queryWrapper.gt(UserDO::getLastLoginTime, bo.getGt_lastLoginTime());
        }
        if (bo.getLt_lastLoginTime() != null) {
            queryWrapper.lt(UserDO::getLastLoginTime, bo.getLt_lastLoginTime());
        }
        if (bo.getEgt_lastLoginTime() != null) {
            queryWrapper.ge(UserDO::getLastLoginTime, bo.getEgt_lastLoginTime());
        }
        if (bo.getElt_lastLoginTime() != null) {
            queryWrapper.le(UserDO::getLastLoginTime, bo.getElt_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_lastLoginTime())) {
            WrapperUtil.between(queryWrapper, UserDO::getLastLoginTime, bo.getBetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_lastLoginTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getLastLoginTime, bo.getNotbetween_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_lastLoginTime())) {
            WrapperUtil.in(queryWrapper, UserDO::getLastLoginTime, bo.getIn_lastLoginTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_lastLoginTime())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getLastLoginTime, bo.getNotin_lastLoginTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(UserDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(UserDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(UserDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(UserDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(UserDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(UserDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, UserDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, UserDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(UserDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(UserDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(UserDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(UserDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(UserDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(UserDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, UserDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, UserDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, UserDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(UserDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(UserDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(UserDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(UserDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(UserDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(UserDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, UserDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(UserDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(UserDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(UserDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(UserDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(UserDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(UserDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, UserDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(UserDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(UserDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(UserDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(UserDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(UserDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(UserDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, UserDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(UserDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(UserDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(UserDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(UserDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(UserDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(UserDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, UserDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, UserDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}