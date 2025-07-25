package com.wingflare.business.base.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.business.base.db.SettingDO;
import com.wingflare.facade.module.base.bo.SettingSearchBO;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 系统设置Wrapper
 *
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
public class SettingWrapper
{
	
	/**
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<SettingDO> getLambdaQueryWrapper(SettingSearchBO bo) {
        LambdaQueryWrapper<SettingDO> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, SettingDO::getIsDelete, bo.getDataScope());

        // setting_id 开始
        if (bo.getEq_settingId() != null) {
            queryWrapper.eq(SettingDO::getSettingId, bo.getEq_settingId());
        }
        if (bo.getNeq_settingId() != null) {
            queryWrapper.ne(SettingDO::getSettingId, bo.getNeq_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingId())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingId, bo.getIn_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingId())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingId, bo.getNotin_settingId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(SettingDO::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(SettingDO::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(SettingDO::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(SettingDO::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(SettingDO::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(SettingDO::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, SettingDO::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, SettingDO::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, SettingDO::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getState, bo.getNotin_state());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(SettingDO::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(SettingDO::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(SettingDO::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(SettingDO::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(SettingDO::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(SettingDO::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSystemCode, bo.getNotin_systemCode());
        }
        // setting_code 开始
        if (bo.getEq_settingCode() != null) {
            queryWrapper.eq(SettingDO::getSettingCode, bo.getEq_settingCode());
        }
        if (bo.getNeq_settingCode() != null) {
            queryWrapper.ne(SettingDO::getSettingCode, bo.getNeq_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingCode())) {
            queryWrapper.like(SettingDO::getSettingCode, bo.getLike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingCode())) {
            queryWrapper.likeRight(SettingDO::getSettingCode, bo.getLiker_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingCode())) {
            queryWrapper.likeLeft(SettingDO::getSettingCode, bo.getLikel_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingCode())) {
            queryWrapper.notLike(SettingDO::getSettingCode, bo.getNotlike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingCode())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingCode, bo.getIn_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingCode())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingCode, bo.getNotin_settingCode());
        }
        // setting_name 开始
        if (bo.getEq_settingName() != null) {
            queryWrapper.eq(SettingDO::getSettingName, bo.getEq_settingName());
        }
        if (bo.getNeq_settingName() != null) {
            queryWrapper.ne(SettingDO::getSettingName, bo.getNeq_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingName())) {
            queryWrapper.like(SettingDO::getSettingName, bo.getLike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingName())) {
            queryWrapper.likeRight(SettingDO::getSettingName, bo.getLiker_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingName())) {
            queryWrapper.likeLeft(SettingDO::getSettingName, bo.getLikel_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingName())) {
            queryWrapper.notLike(SettingDO::getSettingName, bo.getNotlike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingName())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingName, bo.getIn_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingName())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingName, bo.getNotin_settingName());
        }
        // setting_value 开始
        if (bo.getEq_settingValue() != null) {
            queryWrapper.eq(SettingDO::getSettingValue, bo.getEq_settingValue());
        }
        if (bo.getNeq_settingValue() != null) {
            queryWrapper.ne(SettingDO::getSettingValue, bo.getNeq_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingValue())) {
            queryWrapper.like(SettingDO::getSettingValue, bo.getLike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingValue())) {
            queryWrapper.likeRight(SettingDO::getSettingValue, bo.getLiker_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingValue())) {
            queryWrapper.likeLeft(SettingDO::getSettingValue, bo.getLikel_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingValue())) {
            queryWrapper.notLike(SettingDO::getSettingValue, bo.getNotlike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingValue())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingValue, bo.getIn_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingValue())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingValue, bo.getNotin_settingValue());
        }
        // setting_text 开始
        if (bo.getEq_settingText() != null) {
            queryWrapper.eq(SettingDO::getSettingText, bo.getEq_settingText());
        }
        if (bo.getNeq_settingText() != null) {
            queryWrapper.ne(SettingDO::getSettingText, bo.getNeq_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingText())) {
            queryWrapper.like(SettingDO::getSettingText, bo.getLike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingText())) {
            queryWrapper.likeRight(SettingDO::getSettingText, bo.getLiker_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingText())) {
            queryWrapper.likeLeft(SettingDO::getSettingText, bo.getLikel_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingText())) {
            queryWrapper.notLike(SettingDO::getSettingText, bo.getNotlike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingText())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingText, bo.getIn_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingText())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingText, bo.getNotin_settingText());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(SettingDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(SettingDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(SettingDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(SettingDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(SettingDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(SettingDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, SettingDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, SettingDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, SettingDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(SettingDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(SettingDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(SettingDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(SettingDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(SettingDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(SettingDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, SettingDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, SettingDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, SettingDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(SettingDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(SettingDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(SettingDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(SettingDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(SettingDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(SettingDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, SettingDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(SettingDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(SettingDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(SettingDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(SettingDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(SettingDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(SettingDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, SettingDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(SettingDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(SettingDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(SettingDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(SettingDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(SettingDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(SettingDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, SettingDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(SettingDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(SettingDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(SettingDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(SettingDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(SettingDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(SettingDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, SettingDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<SettingDO> getJoinLambdaQueryWrapper(SettingSearchBO bo) {
        JoinLambdaQueryWrapper<SettingDO> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, SettingDO::getIsDelete, bo.getDataScope());

        // setting_id 开始
        if (bo.getEq_settingId() != null) {
            queryWrapper.eq(SettingDO::getSettingId, bo.getEq_settingId());
        }
        if (bo.getNeq_settingId() != null) {
            queryWrapper.ne(SettingDO::getSettingId, bo.getNeq_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingId())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingId, bo.getIn_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingId())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingId, bo.getNotin_settingId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(SettingDO::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(SettingDO::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(SettingDO::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(SettingDO::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(SettingDO::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(SettingDO::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, SettingDO::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, SettingDO::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, SettingDO::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getState, bo.getNotin_state());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(SettingDO::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(SettingDO::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(SettingDO::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(SettingDO::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(SettingDO::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(SettingDO::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSystemCode, bo.getNotin_systemCode());
        }
        // setting_code 开始
        if (bo.getEq_settingCode() != null) {
            queryWrapper.eq(SettingDO::getSettingCode, bo.getEq_settingCode());
        }
        if (bo.getNeq_settingCode() != null) {
            queryWrapper.ne(SettingDO::getSettingCode, bo.getNeq_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingCode())) {
            queryWrapper.like(SettingDO::getSettingCode, bo.getLike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingCode())) {
            queryWrapper.likeRight(SettingDO::getSettingCode, bo.getLiker_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingCode())) {
            queryWrapper.likeLeft(SettingDO::getSettingCode, bo.getLikel_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingCode())) {
            queryWrapper.notLike(SettingDO::getSettingCode, bo.getNotlike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingCode())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingCode, bo.getIn_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingCode())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingCode, bo.getNotin_settingCode());
        }
        // setting_name 开始
        if (bo.getEq_settingName() != null) {
            queryWrapper.eq(SettingDO::getSettingName, bo.getEq_settingName());
        }
        if (bo.getNeq_settingName() != null) {
            queryWrapper.ne(SettingDO::getSettingName, bo.getNeq_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingName())) {
            queryWrapper.like(SettingDO::getSettingName, bo.getLike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingName())) {
            queryWrapper.likeRight(SettingDO::getSettingName, bo.getLiker_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingName())) {
            queryWrapper.likeLeft(SettingDO::getSettingName, bo.getLikel_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingName())) {
            queryWrapper.notLike(SettingDO::getSettingName, bo.getNotlike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingName())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingName, bo.getIn_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingName())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingName, bo.getNotin_settingName());
        }
        // setting_value 开始
        if (bo.getEq_settingValue() != null) {
            queryWrapper.eq(SettingDO::getSettingValue, bo.getEq_settingValue());
        }
        if (bo.getNeq_settingValue() != null) {
            queryWrapper.ne(SettingDO::getSettingValue, bo.getNeq_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingValue())) {
            queryWrapper.like(SettingDO::getSettingValue, bo.getLike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingValue())) {
            queryWrapper.likeRight(SettingDO::getSettingValue, bo.getLiker_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingValue())) {
            queryWrapper.likeLeft(SettingDO::getSettingValue, bo.getLikel_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingValue())) {
            queryWrapper.notLike(SettingDO::getSettingValue, bo.getNotlike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingValue())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingValue, bo.getIn_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingValue())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingValue, bo.getNotin_settingValue());
        }
        // setting_text 开始
        if (bo.getEq_settingText() != null) {
            queryWrapper.eq(SettingDO::getSettingText, bo.getEq_settingText());
        }
        if (bo.getNeq_settingText() != null) {
            queryWrapper.ne(SettingDO::getSettingText, bo.getNeq_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingText())) {
            queryWrapper.like(SettingDO::getSettingText, bo.getLike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingText())) {
            queryWrapper.likeRight(SettingDO::getSettingText, bo.getLiker_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingText())) {
            queryWrapper.likeLeft(SettingDO::getSettingText, bo.getLikel_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingText())) {
            queryWrapper.notLike(SettingDO::getSettingText, bo.getNotlike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingText())) {
            WrapperUtil.in(queryWrapper, SettingDO::getSettingText, bo.getIn_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingText())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getSettingText, bo.getNotin_settingText());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(SettingDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(SettingDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(SettingDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(SettingDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(SettingDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(SettingDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, SettingDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, SettingDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, SettingDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(SettingDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(SettingDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(SettingDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(SettingDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(SettingDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(SettingDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, SettingDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, SettingDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, SettingDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(SettingDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(SettingDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(SettingDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(SettingDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(SettingDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(SettingDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, SettingDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(SettingDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(SettingDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(SettingDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(SettingDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(SettingDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(SettingDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, SettingDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(SettingDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(SettingDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(SettingDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(SettingDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(SettingDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(SettingDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, SettingDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(SettingDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(SettingDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(SettingDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(SettingDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(SettingDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(SettingDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, SettingDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, SettingDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}