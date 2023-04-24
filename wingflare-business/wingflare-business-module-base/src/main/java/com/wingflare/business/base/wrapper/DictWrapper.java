package com.wingflare.business.base.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.base.db.DictDo;
import com.wingflare.facade.module.base.bo.DictSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 系统字典Wrapper
 *
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
public class DictWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<DictDo> getQueryWrapper(DictSearchBo bo) {
        QueryWrapper<DictDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // dict_id 开始
        if (bo.getEq_dictId() != null) {
            queryWrapper.eq("dict_id", bo.getEq_dictId());
        }
        if (bo.getNeq_dictId() != null) {
            queryWrapper.ne("dict_id", bo.getNeq_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictId())) {
            WrapperUtil.in(queryWrapper, "dict_id", bo.getIn_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictId())) {
            WrapperUtil.notIn(queryWrapper, "dict_id", bo.getNotin_dictId());
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
        // dict_type 开始
        if (bo.getEq_dictType() != null) {
            queryWrapper.eq("dict_type", bo.getEq_dictType());
        }
        if (bo.getNeq_dictType() != null) {
            queryWrapper.ne("dict_type", bo.getNeq_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictType())) {
            queryWrapper.like("dict_type", bo.getLike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictType())) {
            queryWrapper.likeRight("dict_type", bo.getLiker_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictType())) {
            queryWrapper.likeLeft("dict_type", bo.getLikel_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictType())) {
            queryWrapper.notLike("dict_type", bo.getNotlike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictType())) {
            WrapperUtil.in(queryWrapper, "dict_type", bo.getIn_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictType())) {
            WrapperUtil.notIn(queryWrapper, "dict_type", bo.getNotin_dictType());
        }
        // dict_code 开始
        if (bo.getEq_dictCode() != null) {
            queryWrapper.eq("dict_code", bo.getEq_dictCode());
        }
        if (bo.getNeq_dictCode() != null) {
            queryWrapper.ne("dict_code", bo.getNeq_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictCode())) {
            queryWrapper.like("dict_code", bo.getLike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictCode())) {
            queryWrapper.likeRight("dict_code", bo.getLiker_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictCode())) {
            queryWrapper.likeLeft("dict_code", bo.getLikel_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictCode())) {
            queryWrapper.notLike("dict_code", bo.getNotlike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictCode())) {
            WrapperUtil.in(queryWrapper, "dict_code", bo.getIn_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictCode())) {
            WrapperUtil.notIn(queryWrapper, "dict_code", bo.getNotin_dictCode());
        }
        // dict_name 开始
        if (bo.getEq_dictName() != null) {
            queryWrapper.eq("dict_name", bo.getEq_dictName());
        }
        if (bo.getNeq_dictName() != null) {
            queryWrapper.ne("dict_name", bo.getNeq_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictName())) {
            queryWrapper.like("dict_name", bo.getLike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictName())) {
            queryWrapper.likeRight("dict_name", bo.getLiker_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictName())) {
            queryWrapper.likeLeft("dict_name", bo.getLikel_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictName())) {
            queryWrapper.notLike("dict_name", bo.getNotlike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictName())) {
            WrapperUtil.in(queryWrapper, "dict_name", bo.getIn_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictName())) {
            WrapperUtil.notIn(queryWrapper, "dict_name", bo.getNotin_dictName());
        }
        // dict_value 开始
        if (bo.getEq_dictValue() != null) {
            queryWrapper.eq("dict_value", bo.getEq_dictValue());
        }
        if (bo.getNeq_dictValue() != null) {
            queryWrapper.ne("dict_value", bo.getNeq_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictValue())) {
            queryWrapper.like("dict_value", bo.getLike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictValue())) {
            queryWrapper.likeRight("dict_value", bo.getLiker_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictValue())) {
            queryWrapper.likeLeft("dict_value", bo.getLikel_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictValue())) {
            queryWrapper.notLike("dict_value", bo.getNotlike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictValue())) {
            WrapperUtil.in(queryWrapper, "dict_value", bo.getIn_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictValue())) {
            WrapperUtil.notIn(queryWrapper, "dict_value", bo.getNotin_dictValue());
        }
        // dict_text 开始
        if (bo.getEq_dictText() != null) {
            queryWrapper.eq("dict_text", bo.getEq_dictText());
        }
        if (bo.getNeq_dictText() != null) {
            queryWrapper.ne("dict_text", bo.getNeq_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictText())) {
            queryWrapper.like("dict_text", bo.getLike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictText())) {
            queryWrapper.likeRight("dict_text", bo.getLiker_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictText())) {
            queryWrapper.likeLeft("dict_text", bo.getLikel_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictText())) {
            queryWrapper.notLike("dict_text", bo.getNotlike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictText())) {
            WrapperUtil.in(queryWrapper, "dict_text", bo.getIn_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictText())) {
            WrapperUtil.notIn(queryWrapper, "dict_text", bo.getNotin_dictText());
        }
        // sort 开始
        if (bo.getEq_sort() != null) {
            queryWrapper.eq("sort", bo.getEq_sort());
        }
        if (bo.getNeq_sort() != null) {
            queryWrapper.ne("sort", bo.getNeq_sort());
        }
        if (bo.getGt_sort() != null) {
            queryWrapper.gt("sort", bo.getGt_sort());
        }
        if (bo.getLt_sort() != null) {
            queryWrapper.lt("sort", bo.getLt_sort());
        }
        if (bo.getEgt_sort() != null) {
            queryWrapper.ge("sort", bo.getEgt_sort());
        }
        if (bo.getElt_sort() != null) {
            queryWrapper.le("sort", bo.getElt_sort());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sort())) {
            WrapperUtil.between(queryWrapper, "sort", bo.getBetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sort())) {
            WrapperUtil.notBetween(queryWrapper, "sort", bo.getNotbetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sort())) {
            WrapperUtil.in(queryWrapper, "sort", bo.getIn_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sort())) {
            WrapperUtil.notIn(queryWrapper, "sort", bo.getNotin_sort());
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
	public static LambdaQueryWrapper<DictDo> getLambdaQueryWrapper(DictSearchBo bo) {
        LambdaQueryWrapper<DictDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, DictDo::getIsDelete, bo.getDataScope());

        // dict_id 开始
        if (bo.getEq_dictId() != null) {
            queryWrapper.eq(DictDo::getDictId, bo.getEq_dictId());
        }
        if (bo.getNeq_dictId() != null) {
            queryWrapper.ne(DictDo::getDictId, bo.getNeq_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictId())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictId, bo.getIn_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictId())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictId, bo.getNotin_dictId());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(DictDo::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(DictDo::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(DictDo::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(DictDo::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(DictDo::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(DictDo::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, DictDo::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getSystemCode, bo.getNotin_systemCode());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(DictDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(DictDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(DictDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(DictDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(DictDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(DictDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, DictDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, DictDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, DictDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getState, bo.getNotin_state());
        }
        // dict_type 开始
        if (bo.getEq_dictType() != null) {
            queryWrapper.eq(DictDo::getDictType, bo.getEq_dictType());
        }
        if (bo.getNeq_dictType() != null) {
            queryWrapper.ne(DictDo::getDictType, bo.getNeq_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictType())) {
            queryWrapper.like(DictDo::getDictType, bo.getLike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictType())) {
            queryWrapper.likeRight(DictDo::getDictType, bo.getLiker_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictType())) {
            queryWrapper.likeLeft(DictDo::getDictType, bo.getLikel_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictType())) {
            queryWrapper.notLike(DictDo::getDictType, bo.getNotlike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictType())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictType, bo.getIn_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictType())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictType, bo.getNotin_dictType());
        }
        // dict_code 开始
        if (bo.getEq_dictCode() != null) {
            queryWrapper.eq(DictDo::getDictCode, bo.getEq_dictCode());
        }
        if (bo.getNeq_dictCode() != null) {
            queryWrapper.ne(DictDo::getDictCode, bo.getNeq_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictCode())) {
            queryWrapper.like(DictDo::getDictCode, bo.getLike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictCode())) {
            queryWrapper.likeRight(DictDo::getDictCode, bo.getLiker_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictCode())) {
            queryWrapper.likeLeft(DictDo::getDictCode, bo.getLikel_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictCode())) {
            queryWrapper.notLike(DictDo::getDictCode, bo.getNotlike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictCode())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictCode, bo.getIn_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictCode())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictCode, bo.getNotin_dictCode());
        }
        // dict_name 开始
        if (bo.getEq_dictName() != null) {
            queryWrapper.eq(DictDo::getDictName, bo.getEq_dictName());
        }
        if (bo.getNeq_dictName() != null) {
            queryWrapper.ne(DictDo::getDictName, bo.getNeq_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictName())) {
            queryWrapper.like(DictDo::getDictName, bo.getLike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictName())) {
            queryWrapper.likeRight(DictDo::getDictName, bo.getLiker_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictName())) {
            queryWrapper.likeLeft(DictDo::getDictName, bo.getLikel_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictName())) {
            queryWrapper.notLike(DictDo::getDictName, bo.getNotlike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictName())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictName, bo.getIn_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictName())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictName, bo.getNotin_dictName());
        }
        // dict_value 开始
        if (bo.getEq_dictValue() != null) {
            queryWrapper.eq(DictDo::getDictValue, bo.getEq_dictValue());
        }
        if (bo.getNeq_dictValue() != null) {
            queryWrapper.ne(DictDo::getDictValue, bo.getNeq_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictValue())) {
            queryWrapper.like(DictDo::getDictValue, bo.getLike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictValue())) {
            queryWrapper.likeRight(DictDo::getDictValue, bo.getLiker_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictValue())) {
            queryWrapper.likeLeft(DictDo::getDictValue, bo.getLikel_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictValue())) {
            queryWrapper.notLike(DictDo::getDictValue, bo.getNotlike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictValue())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictValue, bo.getIn_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictValue())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictValue, bo.getNotin_dictValue());
        }
        // dict_text 开始
        if (bo.getEq_dictText() != null) {
            queryWrapper.eq(DictDo::getDictText, bo.getEq_dictText());
        }
        if (bo.getNeq_dictText() != null) {
            queryWrapper.ne(DictDo::getDictText, bo.getNeq_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictText())) {
            queryWrapper.like(DictDo::getDictText, bo.getLike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictText())) {
            queryWrapper.likeRight(DictDo::getDictText, bo.getLiker_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictText())) {
            queryWrapper.likeLeft(DictDo::getDictText, bo.getLikel_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictText())) {
            queryWrapper.notLike(DictDo::getDictText, bo.getNotlike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictText())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictText, bo.getIn_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictText())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictText, bo.getNotin_dictText());
        }
        // sort 开始
        if (bo.getEq_sort() != null) {
            queryWrapper.eq(DictDo::getSort, bo.getEq_sort());
        }
        if (bo.getNeq_sort() != null) {
            queryWrapper.ne(DictDo::getSort, bo.getNeq_sort());
        }
        if (bo.getGt_sort() != null) {
            queryWrapper.gt(DictDo::getSort, bo.getGt_sort());
        }
        if (bo.getLt_sort() != null) {
            queryWrapper.lt(DictDo::getSort, bo.getLt_sort());
        }
        if (bo.getEgt_sort() != null) {
            queryWrapper.ge(DictDo::getSort, bo.getEgt_sort());
        }
        if (bo.getElt_sort() != null) {
            queryWrapper.le(DictDo::getSort, bo.getElt_sort());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sort())) {
            WrapperUtil.between(queryWrapper, DictDo::getSort, bo.getBetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sort())) {
            WrapperUtil.notBetween(queryWrapper, DictDo::getSort, bo.getNotbetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sort())) {
            WrapperUtil.in(queryWrapper, DictDo::getSort, bo.getIn_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sort())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getSort, bo.getNotin_sort());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(DictDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(DictDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(DictDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(DictDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(DictDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(DictDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, DictDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, DictDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, DictDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(DictDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(DictDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(DictDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(DictDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(DictDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(DictDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, DictDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, DictDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, DictDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(DictDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(DictDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(DictDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(DictDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(DictDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(DictDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, DictDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(DictDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(DictDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(DictDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(DictDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(DictDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(DictDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, DictDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(DictDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(DictDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(DictDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(DictDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(DictDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(DictDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, DictDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(DictDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(DictDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(DictDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(DictDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(DictDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(DictDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, DictDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<DictDo> getJoinLambdaQueryWrapper(DictSearchBo bo) {
        JoinLambdaQueryWrapper<DictDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, DictDo::getIsDelete, bo.getDataScope());

        // dict_id 开始
        if (bo.getEq_dictId() != null) {
            queryWrapper.eq(DictDo::getDictId, bo.getEq_dictId());
        }
        if (bo.getNeq_dictId() != null) {
            queryWrapper.ne(DictDo::getDictId, bo.getNeq_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictId())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictId, bo.getIn_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictId())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictId, bo.getNotin_dictId());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(DictDo::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(DictDo::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(DictDo::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(DictDo::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(DictDo::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(DictDo::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, DictDo::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getSystemCode, bo.getNotin_systemCode());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(DictDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(DictDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(DictDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(DictDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(DictDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(DictDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, DictDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, DictDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, DictDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getState, bo.getNotin_state());
        }
        // dict_type 开始
        if (bo.getEq_dictType() != null) {
            queryWrapper.eq(DictDo::getDictType, bo.getEq_dictType());
        }
        if (bo.getNeq_dictType() != null) {
            queryWrapper.ne(DictDo::getDictType, bo.getNeq_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictType())) {
            queryWrapper.like(DictDo::getDictType, bo.getLike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictType())) {
            queryWrapper.likeRight(DictDo::getDictType, bo.getLiker_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictType())) {
            queryWrapper.likeLeft(DictDo::getDictType, bo.getLikel_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictType())) {
            queryWrapper.notLike(DictDo::getDictType, bo.getNotlike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictType())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictType, bo.getIn_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictType())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictType, bo.getNotin_dictType());
        }
        // dict_code 开始
        if (bo.getEq_dictCode() != null) {
            queryWrapper.eq(DictDo::getDictCode, bo.getEq_dictCode());
        }
        if (bo.getNeq_dictCode() != null) {
            queryWrapper.ne(DictDo::getDictCode, bo.getNeq_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictCode())) {
            queryWrapper.like(DictDo::getDictCode, bo.getLike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictCode())) {
            queryWrapper.likeRight(DictDo::getDictCode, bo.getLiker_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictCode())) {
            queryWrapper.likeLeft(DictDo::getDictCode, bo.getLikel_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictCode())) {
            queryWrapper.notLike(DictDo::getDictCode, bo.getNotlike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictCode())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictCode, bo.getIn_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictCode())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictCode, bo.getNotin_dictCode());
        }
        // dict_name 开始
        if (bo.getEq_dictName() != null) {
            queryWrapper.eq(DictDo::getDictName, bo.getEq_dictName());
        }
        if (bo.getNeq_dictName() != null) {
            queryWrapper.ne(DictDo::getDictName, bo.getNeq_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictName())) {
            queryWrapper.like(DictDo::getDictName, bo.getLike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictName())) {
            queryWrapper.likeRight(DictDo::getDictName, bo.getLiker_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictName())) {
            queryWrapper.likeLeft(DictDo::getDictName, bo.getLikel_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictName())) {
            queryWrapper.notLike(DictDo::getDictName, bo.getNotlike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictName())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictName, bo.getIn_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictName())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictName, bo.getNotin_dictName());
        }
        // dict_value 开始
        if (bo.getEq_dictValue() != null) {
            queryWrapper.eq(DictDo::getDictValue, bo.getEq_dictValue());
        }
        if (bo.getNeq_dictValue() != null) {
            queryWrapper.ne(DictDo::getDictValue, bo.getNeq_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictValue())) {
            queryWrapper.like(DictDo::getDictValue, bo.getLike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictValue())) {
            queryWrapper.likeRight(DictDo::getDictValue, bo.getLiker_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictValue())) {
            queryWrapper.likeLeft(DictDo::getDictValue, bo.getLikel_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictValue())) {
            queryWrapper.notLike(DictDo::getDictValue, bo.getNotlike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictValue())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictValue, bo.getIn_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictValue())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictValue, bo.getNotin_dictValue());
        }
        // dict_text 开始
        if (bo.getEq_dictText() != null) {
            queryWrapper.eq(DictDo::getDictText, bo.getEq_dictText());
        }
        if (bo.getNeq_dictText() != null) {
            queryWrapper.ne(DictDo::getDictText, bo.getNeq_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictText())) {
            queryWrapper.like(DictDo::getDictText, bo.getLike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictText())) {
            queryWrapper.likeRight(DictDo::getDictText, bo.getLiker_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictText())) {
            queryWrapper.likeLeft(DictDo::getDictText, bo.getLikel_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictText())) {
            queryWrapper.notLike(DictDo::getDictText, bo.getNotlike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictText())) {
            WrapperUtil.in(queryWrapper, DictDo::getDictText, bo.getIn_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictText())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getDictText, bo.getNotin_dictText());
        }
        // sort 开始
        if (bo.getEq_sort() != null) {
            queryWrapper.eq(DictDo::getSort, bo.getEq_sort());
        }
        if (bo.getNeq_sort() != null) {
            queryWrapper.ne(DictDo::getSort, bo.getNeq_sort());
        }
        if (bo.getGt_sort() != null) {
            queryWrapper.gt(DictDo::getSort, bo.getGt_sort());
        }
        if (bo.getLt_sort() != null) {
            queryWrapper.lt(DictDo::getSort, bo.getLt_sort());
        }
        if (bo.getEgt_sort() != null) {
            queryWrapper.ge(DictDo::getSort, bo.getEgt_sort());
        }
        if (bo.getElt_sort() != null) {
            queryWrapper.le(DictDo::getSort, bo.getElt_sort());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sort())) {
            WrapperUtil.between(queryWrapper, DictDo::getSort, bo.getBetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sort())) {
            WrapperUtil.notBetween(queryWrapper, DictDo::getSort, bo.getNotbetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sort())) {
            WrapperUtil.in(queryWrapper, DictDo::getSort, bo.getIn_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sort())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getSort, bo.getNotin_sort());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(DictDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(DictDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(DictDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(DictDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(DictDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(DictDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, DictDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, DictDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, DictDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(DictDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(DictDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(DictDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(DictDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(DictDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(DictDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, DictDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, DictDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, DictDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(DictDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(DictDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(DictDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(DictDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(DictDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(DictDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, DictDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(DictDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(DictDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(DictDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(DictDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(DictDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(DictDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, DictDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(DictDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(DictDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(DictDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(DictDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(DictDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(DictDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, DictDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(DictDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(DictDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(DictDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(DictDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(DictDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(DictDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, DictDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, DictDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}