package com.wingflare.business.base.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.base.db.SettingDo;
import com.wingflare.facade.module.base.bo.SettingSearchBo;
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
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<SettingDo> getQueryWrapper(SettingSearchBo bo) {
        QueryWrapper<SettingDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // setting_id 开始
        if (bo.getEq_settingId() != null) {
            queryWrapper.eq("setting_id", bo.getEq_settingId());
        }
        if (bo.getNeq_settingId() != null) {
            queryWrapper.ne("setting_id", bo.getNeq_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingId())) {
            WrapperUtil.in(queryWrapper, "setting_id", bo.getIn_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingId())) {
            WrapperUtil.notIn(queryWrapper, "setting_id", bo.getNotin_settingId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq("state", bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne("state", bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt("state", bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt("state", bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge("state", bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le("state", bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, "state", bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, "state", bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, "state", bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, "state", bo.getNotin_state());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq("system_code", bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne("system_code", bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like("system_code", bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight("system_code", bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft("system_code", bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike("system_code", bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, "system_code", bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, "system_code", bo.getNotin_systemCode());
        }
        // setting_code 开始
        if (bo.getEq_settingCode() != null) {
            queryWrapper.eq("setting_code", bo.getEq_settingCode());
        }
        if (bo.getNeq_settingCode() != null) {
            queryWrapper.ne("setting_code", bo.getNeq_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingCode())) {
            queryWrapper.like("setting_code", bo.getLike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingCode())) {
            queryWrapper.likeRight("setting_code", bo.getLiker_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingCode())) {
            queryWrapper.likeLeft("setting_code", bo.getLikel_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingCode())) {
            queryWrapper.notLike("setting_code", bo.getNotlike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingCode())) {
            WrapperUtil.in(queryWrapper, "setting_code", bo.getIn_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingCode())) {
            WrapperUtil.notIn(queryWrapper, "setting_code", bo.getNotin_settingCode());
        }
        // setting_name 开始
        if (bo.getEq_settingName() != null) {
            queryWrapper.eq("setting_name", bo.getEq_settingName());
        }
        if (bo.getNeq_settingName() != null) {
            queryWrapper.ne("setting_name", bo.getNeq_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingName())) {
            queryWrapper.like("setting_name", bo.getLike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingName())) {
            queryWrapper.likeRight("setting_name", bo.getLiker_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingName())) {
            queryWrapper.likeLeft("setting_name", bo.getLikel_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingName())) {
            queryWrapper.notLike("setting_name", bo.getNotlike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingName())) {
            WrapperUtil.in(queryWrapper, "setting_name", bo.getIn_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingName())) {
            WrapperUtil.notIn(queryWrapper, "setting_name", bo.getNotin_settingName());
        }
        // setting_value 开始
        if (bo.getEq_settingValue() != null) {
            queryWrapper.eq("setting_value", bo.getEq_settingValue());
        }
        if (bo.getNeq_settingValue() != null) {
            queryWrapper.ne("setting_value", bo.getNeq_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingValue())) {
            queryWrapper.like("setting_value", bo.getLike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingValue())) {
            queryWrapper.likeRight("setting_value", bo.getLiker_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingValue())) {
            queryWrapper.likeLeft("setting_value", bo.getLikel_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingValue())) {
            queryWrapper.notLike("setting_value", bo.getNotlike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingValue())) {
            WrapperUtil.in(queryWrapper, "setting_value", bo.getIn_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingValue())) {
            WrapperUtil.notIn(queryWrapper, "setting_value", bo.getNotin_settingValue());
        }
        // setting_text 开始
        if (bo.getEq_settingText() != null) {
            queryWrapper.eq("setting_text", bo.getEq_settingText());
        }
        if (bo.getNeq_settingText() != null) {
            queryWrapper.ne("setting_text", bo.getNeq_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingText())) {
            queryWrapper.like("setting_text", bo.getLike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingText())) {
            queryWrapper.likeRight("setting_text", bo.getLiker_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingText())) {
            queryWrapper.likeLeft("setting_text", bo.getLikel_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingText())) {
            queryWrapper.notLike("setting_text", bo.getNotlike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingText())) {
            WrapperUtil.in(queryWrapper, "setting_text", bo.getIn_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingText())) {
            WrapperUtil.notIn(queryWrapper, "setting_text", bo.getNotin_settingText());
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
	public static LambdaQueryWrapper<SettingDo> getLambdaQueryWrapper(SettingSearchBo bo) {
        LambdaQueryWrapper<SettingDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, SettingDo::getIsDelete, bo.getDataScope());

        // setting_id 开始
        if (bo.getEq_settingId() != null) {
            queryWrapper.eq(SettingDo::getSettingId, bo.getEq_settingId());
        }
        if (bo.getNeq_settingId() != null) {
            queryWrapper.ne(SettingDo::getSettingId, bo.getNeq_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingId())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingId, bo.getIn_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingId())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingId, bo.getNotin_settingId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(SettingDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(SettingDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(SettingDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(SettingDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(SettingDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(SettingDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, SettingDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, SettingDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, SettingDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getState, bo.getNotin_state());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(SettingDo::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(SettingDo::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(SettingDo::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(SettingDo::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(SettingDo::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(SettingDo::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSystemCode, bo.getNotin_systemCode());
        }
        // setting_code 开始
        if (bo.getEq_settingCode() != null) {
            queryWrapper.eq(SettingDo::getSettingCode, bo.getEq_settingCode());
        }
        if (bo.getNeq_settingCode() != null) {
            queryWrapper.ne(SettingDo::getSettingCode, bo.getNeq_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingCode())) {
            queryWrapper.like(SettingDo::getSettingCode, bo.getLike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingCode())) {
            queryWrapper.likeRight(SettingDo::getSettingCode, bo.getLiker_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingCode())) {
            queryWrapper.likeLeft(SettingDo::getSettingCode, bo.getLikel_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingCode())) {
            queryWrapper.notLike(SettingDo::getSettingCode, bo.getNotlike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingCode())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingCode, bo.getIn_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingCode())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingCode, bo.getNotin_settingCode());
        }
        // setting_name 开始
        if (bo.getEq_settingName() != null) {
            queryWrapper.eq(SettingDo::getSettingName, bo.getEq_settingName());
        }
        if (bo.getNeq_settingName() != null) {
            queryWrapper.ne(SettingDo::getSettingName, bo.getNeq_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingName())) {
            queryWrapper.like(SettingDo::getSettingName, bo.getLike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingName())) {
            queryWrapper.likeRight(SettingDo::getSettingName, bo.getLiker_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingName())) {
            queryWrapper.likeLeft(SettingDo::getSettingName, bo.getLikel_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingName())) {
            queryWrapper.notLike(SettingDo::getSettingName, bo.getNotlike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingName())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingName, bo.getIn_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingName())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingName, bo.getNotin_settingName());
        }
        // setting_value 开始
        if (bo.getEq_settingValue() != null) {
            queryWrapper.eq(SettingDo::getSettingValue, bo.getEq_settingValue());
        }
        if (bo.getNeq_settingValue() != null) {
            queryWrapper.ne(SettingDo::getSettingValue, bo.getNeq_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingValue())) {
            queryWrapper.like(SettingDo::getSettingValue, bo.getLike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingValue())) {
            queryWrapper.likeRight(SettingDo::getSettingValue, bo.getLiker_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingValue())) {
            queryWrapper.likeLeft(SettingDo::getSettingValue, bo.getLikel_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingValue())) {
            queryWrapper.notLike(SettingDo::getSettingValue, bo.getNotlike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingValue())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingValue, bo.getIn_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingValue())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingValue, bo.getNotin_settingValue());
        }
        // setting_text 开始
        if (bo.getEq_settingText() != null) {
            queryWrapper.eq(SettingDo::getSettingText, bo.getEq_settingText());
        }
        if (bo.getNeq_settingText() != null) {
            queryWrapper.ne(SettingDo::getSettingText, bo.getNeq_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingText())) {
            queryWrapper.like(SettingDo::getSettingText, bo.getLike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingText())) {
            queryWrapper.likeRight(SettingDo::getSettingText, bo.getLiker_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingText())) {
            queryWrapper.likeLeft(SettingDo::getSettingText, bo.getLikel_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingText())) {
            queryWrapper.notLike(SettingDo::getSettingText, bo.getNotlike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingText())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingText, bo.getIn_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingText())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingText, bo.getNotin_settingText());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(SettingDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(SettingDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(SettingDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(SettingDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(SettingDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(SettingDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, SettingDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, SettingDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, SettingDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(SettingDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(SettingDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(SettingDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(SettingDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(SettingDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(SettingDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, SettingDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, SettingDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, SettingDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(SettingDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(SettingDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(SettingDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(SettingDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(SettingDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(SettingDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, SettingDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(SettingDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(SettingDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(SettingDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(SettingDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(SettingDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(SettingDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, SettingDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(SettingDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(SettingDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(SettingDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(SettingDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(SettingDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(SettingDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, SettingDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(SettingDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(SettingDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(SettingDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(SettingDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(SettingDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(SettingDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, SettingDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<SettingDo> getJoinLambdaQueryWrapper(SettingSearchBo bo) {
        JoinLambdaQueryWrapper<SettingDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, SettingDo::getIsDelete, bo.getDataScope());

        // setting_id 开始
        if (bo.getEq_settingId() != null) {
            queryWrapper.eq(SettingDo::getSettingId, bo.getEq_settingId());
        }
        if (bo.getNeq_settingId() != null) {
            queryWrapper.ne(SettingDo::getSettingId, bo.getNeq_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingId())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingId, bo.getIn_settingId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingId())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingId, bo.getNotin_settingId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(SettingDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(SettingDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(SettingDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(SettingDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(SettingDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(SettingDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, SettingDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, SettingDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, SettingDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getState, bo.getNotin_state());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(SettingDo::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(SettingDo::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(SettingDo::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(SettingDo::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(SettingDo::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(SettingDo::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSystemCode, bo.getNotin_systemCode());
        }
        // setting_code 开始
        if (bo.getEq_settingCode() != null) {
            queryWrapper.eq(SettingDo::getSettingCode, bo.getEq_settingCode());
        }
        if (bo.getNeq_settingCode() != null) {
            queryWrapper.ne(SettingDo::getSettingCode, bo.getNeq_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingCode())) {
            queryWrapper.like(SettingDo::getSettingCode, bo.getLike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingCode())) {
            queryWrapper.likeRight(SettingDo::getSettingCode, bo.getLiker_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingCode())) {
            queryWrapper.likeLeft(SettingDo::getSettingCode, bo.getLikel_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingCode())) {
            queryWrapper.notLike(SettingDo::getSettingCode, bo.getNotlike_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingCode())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingCode, bo.getIn_settingCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingCode())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingCode, bo.getNotin_settingCode());
        }
        // setting_name 开始
        if (bo.getEq_settingName() != null) {
            queryWrapper.eq(SettingDo::getSettingName, bo.getEq_settingName());
        }
        if (bo.getNeq_settingName() != null) {
            queryWrapper.ne(SettingDo::getSettingName, bo.getNeq_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingName())) {
            queryWrapper.like(SettingDo::getSettingName, bo.getLike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingName())) {
            queryWrapper.likeRight(SettingDo::getSettingName, bo.getLiker_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingName())) {
            queryWrapper.likeLeft(SettingDo::getSettingName, bo.getLikel_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingName())) {
            queryWrapper.notLike(SettingDo::getSettingName, bo.getNotlike_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingName())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingName, bo.getIn_settingName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingName())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingName, bo.getNotin_settingName());
        }
        // setting_value 开始
        if (bo.getEq_settingValue() != null) {
            queryWrapper.eq(SettingDo::getSettingValue, bo.getEq_settingValue());
        }
        if (bo.getNeq_settingValue() != null) {
            queryWrapper.ne(SettingDo::getSettingValue, bo.getNeq_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingValue())) {
            queryWrapper.like(SettingDo::getSettingValue, bo.getLike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingValue())) {
            queryWrapper.likeRight(SettingDo::getSettingValue, bo.getLiker_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingValue())) {
            queryWrapper.likeLeft(SettingDo::getSettingValue, bo.getLikel_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingValue())) {
            queryWrapper.notLike(SettingDo::getSettingValue, bo.getNotlike_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingValue())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingValue, bo.getIn_settingValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingValue())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingValue, bo.getNotin_settingValue());
        }
        // setting_text 开始
        if (bo.getEq_settingText() != null) {
            queryWrapper.eq(SettingDo::getSettingText, bo.getEq_settingText());
        }
        if (bo.getNeq_settingText() != null) {
            queryWrapper.ne(SettingDo::getSettingText, bo.getNeq_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_settingText())) {
            queryWrapper.like(SettingDo::getSettingText, bo.getLike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_settingText())) {
            queryWrapper.likeRight(SettingDo::getSettingText, bo.getLiker_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_settingText())) {
            queryWrapper.likeLeft(SettingDo::getSettingText, bo.getLikel_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_settingText())) {
            queryWrapper.notLike(SettingDo::getSettingText, bo.getNotlike_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_settingText())) {
            WrapperUtil.in(queryWrapper, SettingDo::getSettingText, bo.getIn_settingText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_settingText())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getSettingText, bo.getNotin_settingText());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(SettingDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(SettingDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(SettingDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(SettingDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(SettingDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(SettingDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, SettingDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, SettingDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, SettingDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(SettingDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(SettingDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(SettingDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(SettingDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(SettingDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(SettingDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, SettingDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, SettingDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, SettingDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(SettingDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(SettingDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(SettingDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(SettingDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(SettingDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(SettingDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, SettingDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(SettingDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(SettingDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(SettingDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(SettingDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(SettingDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(SettingDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, SettingDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(SettingDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(SettingDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(SettingDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(SettingDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(SettingDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(SettingDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, SettingDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(SettingDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(SettingDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(SettingDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(SettingDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(SettingDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(SettingDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, SettingDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, SettingDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}