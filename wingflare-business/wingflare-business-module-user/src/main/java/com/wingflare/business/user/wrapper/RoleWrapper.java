package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.business.user.db.RoleDo;
import com.wingflare.facade.module.user.bo.RoleSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 系统角色Wrapper
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
public class RoleWrapper
{
	
	/**
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<RoleDo> getLambdaQueryWrapper(RoleSearchBo bo) {
        LambdaQueryWrapper<RoleDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, RoleDo::getIsDelete, bo.getDataScope());

        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(RoleDo::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(RoleDo::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, RoleDo::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getRoleId, bo.getNotin_roleId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(RoleDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(RoleDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(RoleDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(RoleDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(RoleDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(RoleDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, RoleDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, RoleDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, RoleDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getState, bo.getNotin_state());
        }
        // role_name 开始
        if (bo.getEq_roleName() != null) {
            queryWrapper.eq(RoleDo::getRoleName, bo.getEq_roleName());
        }
        if (bo.getNeq_roleName() != null) {
            queryWrapper.ne(RoleDo::getRoleName, bo.getNeq_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleName())) {
            queryWrapper.like(RoleDo::getRoleName, bo.getLike_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleName())) {
            queryWrapper.likeRight(RoleDo::getRoleName, bo.getLiker_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleName())) {
            queryWrapper.likeLeft(RoleDo::getRoleName, bo.getLikel_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleName())) {
            queryWrapper.notLike(RoleDo::getRoleName, bo.getNotlike_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleName())) {
            WrapperUtil.in(queryWrapper, RoleDo::getRoleName, bo.getIn_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleName())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getRoleName, bo.getNotin_roleName());
        }
        // role_remark 开始
        if (bo.getEq_roleRemark() != null) {
            queryWrapper.eq(RoleDo::getRoleRemark, bo.getEq_roleRemark());
        }
        if (bo.getNeq_roleRemark() != null) {
            queryWrapper.ne(RoleDo::getRoleRemark, bo.getNeq_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleRemark())) {
            queryWrapper.like(RoleDo::getRoleRemark, bo.getLike_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleRemark())) {
            queryWrapper.likeRight(RoleDo::getRoleRemark, bo.getLiker_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleRemark())) {
            queryWrapper.likeLeft(RoleDo::getRoleRemark, bo.getLikel_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleRemark())) {
            queryWrapper.notLike(RoleDo::getRoleRemark, bo.getNotlike_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleRemark())) {
            WrapperUtil.in(queryWrapper, RoleDo::getRoleRemark, bo.getIn_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleRemark())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getRoleRemark, bo.getNotin_roleRemark());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(RoleDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(RoleDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(RoleDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(RoleDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(RoleDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(RoleDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, RoleDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, RoleDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(RoleDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(RoleDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(RoleDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(RoleDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(RoleDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(RoleDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, RoleDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, RoleDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(RoleDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(RoleDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(RoleDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(RoleDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(RoleDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(RoleDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, RoleDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(RoleDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(RoleDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(RoleDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(RoleDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(RoleDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(RoleDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, RoleDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(RoleDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(RoleDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(RoleDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(RoleDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(RoleDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(RoleDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, RoleDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(RoleDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(RoleDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(RoleDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(RoleDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(RoleDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(RoleDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, RoleDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<RoleDo> getJoinLambdaQueryWrapper(RoleSearchBo bo) {
        JoinLambdaQueryWrapper<RoleDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, RoleDo::getIsDelete, bo.getDataScope());

        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(RoleDo::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(RoleDo::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, RoleDo::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getRoleId, bo.getNotin_roleId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(RoleDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(RoleDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(RoleDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(RoleDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(RoleDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(RoleDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, RoleDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, RoleDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, RoleDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getState, bo.getNotin_state());
        }
        // role_name 开始
        if (bo.getEq_roleName() != null) {
            queryWrapper.eq(RoleDo::getRoleName, bo.getEq_roleName());
        }
        if (bo.getNeq_roleName() != null) {
            queryWrapper.ne(RoleDo::getRoleName, bo.getNeq_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleName())) {
            queryWrapper.like(RoleDo::getRoleName, bo.getLike_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleName())) {
            queryWrapper.likeRight(RoleDo::getRoleName, bo.getLiker_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleName())) {
            queryWrapper.likeLeft(RoleDo::getRoleName, bo.getLikel_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleName())) {
            queryWrapper.notLike(RoleDo::getRoleName, bo.getNotlike_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleName())) {
            WrapperUtil.in(queryWrapper, RoleDo::getRoleName, bo.getIn_roleName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleName())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getRoleName, bo.getNotin_roleName());
        }
        // role_remark 开始
        if (bo.getEq_roleRemark() != null) {
            queryWrapper.eq(RoleDo::getRoleRemark, bo.getEq_roleRemark());
        }
        if (bo.getNeq_roleRemark() != null) {
            queryWrapper.ne(RoleDo::getRoleRemark, bo.getNeq_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleRemark())) {
            queryWrapper.like(RoleDo::getRoleRemark, bo.getLike_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleRemark())) {
            queryWrapper.likeRight(RoleDo::getRoleRemark, bo.getLiker_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleRemark())) {
            queryWrapper.likeLeft(RoleDo::getRoleRemark, bo.getLikel_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleRemark())) {
            queryWrapper.notLike(RoleDo::getRoleRemark, bo.getNotlike_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleRemark())) {
            WrapperUtil.in(queryWrapper, RoleDo::getRoleRemark, bo.getIn_roleRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleRemark())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getRoleRemark, bo.getNotin_roleRemark());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(RoleDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(RoleDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(RoleDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(RoleDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(RoleDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(RoleDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, RoleDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, RoleDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(RoleDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(RoleDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(RoleDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(RoleDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(RoleDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(RoleDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, RoleDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, RoleDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(RoleDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(RoleDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(RoleDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(RoleDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(RoleDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(RoleDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, RoleDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(RoleDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(RoleDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(RoleDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(RoleDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(RoleDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(RoleDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, RoleDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(RoleDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(RoleDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(RoleDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(RoleDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(RoleDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(RoleDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, RoleDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(RoleDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(RoleDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(RoleDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(RoleDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(RoleDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(RoleDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, RoleDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}