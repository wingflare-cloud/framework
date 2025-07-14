package com.wingflare.business.base.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.business.base.db.DictDO;
import com.wingflare.facade.module.base.bo.DictSearchBO;
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
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<DictDO> getLambdaQueryWrapper(DictSearchBO bo) {
        LambdaQueryWrapper<DictDO> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, DictDO::getIsDelete, bo.getDataScope());

        // dict_id 开始
        if (bo.getEq_dictId() != null) {
            queryWrapper.eq(DictDO::getDictId, bo.getEq_dictId());
        }
        if (bo.getNeq_dictId() != null) {
            queryWrapper.ne(DictDO::getDictId, bo.getNeq_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictId())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictId, bo.getIn_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictId())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictId, bo.getNotin_dictId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(DictDO::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(DictDO::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(DictDO::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(DictDO::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(DictDO::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(DictDO::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, DictDO::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, DictDO::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, DictDO::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getState, bo.getNotin_state());
        }
        // dict_type 开始
        if (bo.getEq_dictType() != null) {
            queryWrapper.eq(DictDO::getDictType, bo.getEq_dictType());
        }
        if (bo.getNeq_dictType() != null) {
            queryWrapper.ne(DictDO::getDictType, bo.getNeq_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictType())) {
            queryWrapper.like(DictDO::getDictType, bo.getLike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictType())) {
            queryWrapper.likeRight(DictDO::getDictType, bo.getLiker_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictType())) {
            queryWrapper.likeLeft(DictDO::getDictType, bo.getLikel_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictType())) {
            queryWrapper.notLike(DictDO::getDictType, bo.getNotlike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictType())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictType, bo.getIn_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictType())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictType, bo.getNotin_dictType());
        }
        // dict_code 开始
        if (bo.getEq_dictCode() != null) {
            queryWrapper.eq(DictDO::getDictCode, bo.getEq_dictCode());
        }
        if (bo.getNeq_dictCode() != null) {
            queryWrapper.ne(DictDO::getDictCode, bo.getNeq_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictCode())) {
            queryWrapper.like(DictDO::getDictCode, bo.getLike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictCode())) {
            queryWrapper.likeRight(DictDO::getDictCode, bo.getLiker_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictCode())) {
            queryWrapper.likeLeft(DictDO::getDictCode, bo.getLikel_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictCode())) {
            queryWrapper.notLike(DictDO::getDictCode, bo.getNotlike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictCode())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictCode, bo.getIn_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictCode())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictCode, bo.getNotin_dictCode());
        }
        // dict_name 开始
        if (bo.getEq_dictName() != null) {
            queryWrapper.eq(DictDO::getDictName, bo.getEq_dictName());
        }
        if (bo.getNeq_dictName() != null) {
            queryWrapper.ne(DictDO::getDictName, bo.getNeq_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictName())) {
            queryWrapper.like(DictDO::getDictName, bo.getLike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictName())) {
            queryWrapper.likeRight(DictDO::getDictName, bo.getLiker_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictName())) {
            queryWrapper.likeLeft(DictDO::getDictName, bo.getLikel_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictName())) {
            queryWrapper.notLike(DictDO::getDictName, bo.getNotlike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictName())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictName, bo.getIn_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictName())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictName, bo.getNotin_dictName());
        }
        // dict_value 开始
        if (bo.getEq_dictValue() != null) {
            queryWrapper.eq(DictDO::getDictValue, bo.getEq_dictValue());
        }
        if (bo.getNeq_dictValue() != null) {
            queryWrapper.ne(DictDO::getDictValue, bo.getNeq_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictValue())) {
            queryWrapper.like(DictDO::getDictValue, bo.getLike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictValue())) {
            queryWrapper.likeRight(DictDO::getDictValue, bo.getLiker_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictValue())) {
            queryWrapper.likeLeft(DictDO::getDictValue, bo.getLikel_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictValue())) {
            queryWrapper.notLike(DictDO::getDictValue, bo.getNotlike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictValue())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictValue, bo.getIn_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictValue())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictValue, bo.getNotin_dictValue());
        }
        // dict_text 开始
        if (bo.getEq_dictText() != null) {
            queryWrapper.eq(DictDO::getDictText, bo.getEq_dictText());
        }
        if (bo.getNeq_dictText() != null) {
            queryWrapper.ne(DictDO::getDictText, bo.getNeq_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictText())) {
            queryWrapper.like(DictDO::getDictText, bo.getLike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictText())) {
            queryWrapper.likeRight(DictDO::getDictText, bo.getLiker_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictText())) {
            queryWrapper.likeLeft(DictDO::getDictText, bo.getLikel_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictText())) {
            queryWrapper.notLike(DictDO::getDictText, bo.getNotlike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictText())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictText, bo.getIn_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictText())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictText, bo.getNotin_dictText());
        }
        // sort 开始
        if (bo.getEq_sort() != null) {
            queryWrapper.eq(DictDO::getSort, bo.getEq_sort());
        }
        if (bo.getNeq_sort() != null) {
            queryWrapper.ne(DictDO::getSort, bo.getNeq_sort());
        }
        if (bo.getGt_sort() != null) {
            queryWrapper.gt(DictDO::getSort, bo.getGt_sort());
        }
        if (bo.getLt_sort() != null) {
            queryWrapper.lt(DictDO::getSort, bo.getLt_sort());
        }
        if (bo.getEgt_sort() != null) {
            queryWrapper.ge(DictDO::getSort, bo.getEgt_sort());
        }
        if (bo.getElt_sort() != null) {
            queryWrapper.le(DictDO::getSort, bo.getElt_sort());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sort())) {
            WrapperUtil.between(queryWrapper, DictDO::getSort, bo.getBetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sort())) {
            WrapperUtil.notBetween(queryWrapper, DictDO::getSort, bo.getNotbetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sort())) {
            WrapperUtil.in(queryWrapper, DictDO::getSort, bo.getIn_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sort())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getSort, bo.getNotin_sort());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(DictDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(DictDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(DictDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(DictDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(DictDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(DictDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, DictDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, DictDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, DictDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(DictDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(DictDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(DictDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(DictDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(DictDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(DictDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, DictDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, DictDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, DictDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(DictDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(DictDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(DictDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(DictDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(DictDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(DictDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, DictDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(DictDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(DictDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(DictDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(DictDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(DictDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(DictDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, DictDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(DictDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(DictDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(DictDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(DictDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(DictDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(DictDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, DictDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(DictDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(DictDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(DictDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(DictDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(DictDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(DictDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, DictDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<DictDO> getJoinLambdaQueryWrapper(DictSearchBO bo) {
        JoinLambdaQueryWrapper<DictDO> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, DictDO::getIsDelete, bo.getDataScope());

        // dict_id 开始
        if (bo.getEq_dictId() != null) {
            queryWrapper.eq(DictDO::getDictId, bo.getEq_dictId());
        }
        if (bo.getNeq_dictId() != null) {
            queryWrapper.ne(DictDO::getDictId, bo.getNeq_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictId())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictId, bo.getIn_dictId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictId())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictId, bo.getNotin_dictId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(DictDO::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(DictDO::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(DictDO::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(DictDO::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(DictDO::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(DictDO::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, DictDO::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, DictDO::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, DictDO::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getState, bo.getNotin_state());
        }
        // dict_type 开始
        if (bo.getEq_dictType() != null) {
            queryWrapper.eq(DictDO::getDictType, bo.getEq_dictType());
        }
        if (bo.getNeq_dictType() != null) {
            queryWrapper.ne(DictDO::getDictType, bo.getNeq_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictType())) {
            queryWrapper.like(DictDO::getDictType, bo.getLike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictType())) {
            queryWrapper.likeRight(DictDO::getDictType, bo.getLiker_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictType())) {
            queryWrapper.likeLeft(DictDO::getDictType, bo.getLikel_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictType())) {
            queryWrapper.notLike(DictDO::getDictType, bo.getNotlike_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictType())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictType, bo.getIn_dictType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictType())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictType, bo.getNotin_dictType());
        }
        // dict_code 开始
        if (bo.getEq_dictCode() != null) {
            queryWrapper.eq(DictDO::getDictCode, bo.getEq_dictCode());
        }
        if (bo.getNeq_dictCode() != null) {
            queryWrapper.ne(DictDO::getDictCode, bo.getNeq_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictCode())) {
            queryWrapper.like(DictDO::getDictCode, bo.getLike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictCode())) {
            queryWrapper.likeRight(DictDO::getDictCode, bo.getLiker_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictCode())) {
            queryWrapper.likeLeft(DictDO::getDictCode, bo.getLikel_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictCode())) {
            queryWrapper.notLike(DictDO::getDictCode, bo.getNotlike_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictCode())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictCode, bo.getIn_dictCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictCode())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictCode, bo.getNotin_dictCode());
        }
        // dict_name 开始
        if (bo.getEq_dictName() != null) {
            queryWrapper.eq(DictDO::getDictName, bo.getEq_dictName());
        }
        if (bo.getNeq_dictName() != null) {
            queryWrapper.ne(DictDO::getDictName, bo.getNeq_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictName())) {
            queryWrapper.like(DictDO::getDictName, bo.getLike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictName())) {
            queryWrapper.likeRight(DictDO::getDictName, bo.getLiker_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictName())) {
            queryWrapper.likeLeft(DictDO::getDictName, bo.getLikel_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictName())) {
            queryWrapper.notLike(DictDO::getDictName, bo.getNotlike_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictName())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictName, bo.getIn_dictName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictName())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictName, bo.getNotin_dictName());
        }
        // dict_value 开始
        if (bo.getEq_dictValue() != null) {
            queryWrapper.eq(DictDO::getDictValue, bo.getEq_dictValue());
        }
        if (bo.getNeq_dictValue() != null) {
            queryWrapper.ne(DictDO::getDictValue, bo.getNeq_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictValue())) {
            queryWrapper.like(DictDO::getDictValue, bo.getLike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictValue())) {
            queryWrapper.likeRight(DictDO::getDictValue, bo.getLiker_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictValue())) {
            queryWrapper.likeLeft(DictDO::getDictValue, bo.getLikel_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictValue())) {
            queryWrapper.notLike(DictDO::getDictValue, bo.getNotlike_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictValue())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictValue, bo.getIn_dictValue());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictValue())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictValue, bo.getNotin_dictValue());
        }
        // dict_text 开始
        if (bo.getEq_dictText() != null) {
            queryWrapper.eq(DictDO::getDictText, bo.getEq_dictText());
        }
        if (bo.getNeq_dictText() != null) {
            queryWrapper.ne(DictDO::getDictText, bo.getNeq_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLike_dictText())) {
            queryWrapper.like(DictDO::getDictText, bo.getLike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_dictText())) {
            queryWrapper.likeRight(DictDO::getDictText, bo.getLiker_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_dictText())) {
            queryWrapper.likeLeft(DictDO::getDictText, bo.getLikel_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_dictText())) {
            queryWrapper.notLike(DictDO::getDictText, bo.getNotlike_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getIn_dictText())) {
            WrapperUtil.in(queryWrapper, DictDO::getDictText, bo.getIn_dictText());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_dictText())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getDictText, bo.getNotin_dictText());
        }
        // sort 开始
        if (bo.getEq_sort() != null) {
            queryWrapper.eq(DictDO::getSort, bo.getEq_sort());
        }
        if (bo.getNeq_sort() != null) {
            queryWrapper.ne(DictDO::getSort, bo.getNeq_sort());
        }
        if (bo.getGt_sort() != null) {
            queryWrapper.gt(DictDO::getSort, bo.getGt_sort());
        }
        if (bo.getLt_sort() != null) {
            queryWrapper.lt(DictDO::getSort, bo.getLt_sort());
        }
        if (bo.getEgt_sort() != null) {
            queryWrapper.ge(DictDO::getSort, bo.getEgt_sort());
        }
        if (bo.getElt_sort() != null) {
            queryWrapper.le(DictDO::getSort, bo.getElt_sort());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sort())) {
            WrapperUtil.between(queryWrapper, DictDO::getSort, bo.getBetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sort())) {
            WrapperUtil.notBetween(queryWrapper, DictDO::getSort, bo.getNotbetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sort())) {
            WrapperUtil.in(queryWrapper, DictDO::getSort, bo.getIn_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sort())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getSort, bo.getNotin_sort());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(DictDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(DictDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(DictDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(DictDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(DictDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(DictDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, DictDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, DictDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, DictDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(DictDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(DictDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(DictDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(DictDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(DictDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(DictDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, DictDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, DictDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, DictDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(DictDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(DictDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(DictDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(DictDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(DictDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(DictDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, DictDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(DictDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(DictDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(DictDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(DictDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(DictDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(DictDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, DictDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(DictDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(DictDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(DictDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(DictDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(DictDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(DictDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, DictDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(DictDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(DictDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(DictDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(DictDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(DictDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(DictDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, DictDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, DictDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}