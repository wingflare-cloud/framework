package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.RoleMutexDo;
import com.wingflare.facade.module.user.bo.RoleMutexSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 系统角色互斥关系Wrapper
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
public class RoleMutexWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<RoleMutexDo> getQueryWrapper(RoleMutexSearchBo bo) {
        QueryWrapper<RoleMutexDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // id 开始
        if (bo.getEq_id() != null) {
            queryWrapper.eq("id", bo.getEq_id());
        }
        if (bo.getNeq_id() != null) {
            queryWrapper.ne("id", bo.getNeq_id());
        }
        if (StringUtil.isNotEmpty(bo.getIn_id())) {
            WrapperUtil.in(queryWrapper, "id", bo.getIn_id());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_id())) {
            WrapperUtil.notIn(queryWrapper, "id", bo.getNotin_id());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq("role_id", bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne("role_id", bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like("role_id", bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight("role_id", bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft("role_id", bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike("role_id", bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, "role_id", bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, "role_id", bo.getNotin_roleId());
        }
        // mutex_role_id 开始
        if (bo.getEq_mutexRoleId() != null) {
            queryWrapper.eq("mutex_role_id", bo.getEq_mutexRoleId());
        }
        if (bo.getNeq_mutexRoleId() != null) {
            queryWrapper.ne("mutex_role_id", bo.getNeq_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_mutexRoleId())) {
            queryWrapper.like("mutex_role_id", bo.getLike_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_mutexRoleId())) {
            queryWrapper.likeRight("mutex_role_id", bo.getLiker_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_mutexRoleId())) {
            queryWrapper.likeLeft("mutex_role_id", bo.getLikel_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_mutexRoleId())) {
            queryWrapper.notLike("mutex_role_id", bo.getNotlike_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_mutexRoleId())) {
            WrapperUtil.in(queryWrapper, "mutex_role_id", bo.getIn_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_mutexRoleId())) {
            WrapperUtil.notIn(queryWrapper, "mutex_role_id", bo.getNotin_mutexRoleId());
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

        return queryWrapper;
    }
	
	/**
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<RoleMutexDo> getLambdaQueryWrapper(RoleMutexSearchBo bo) {
        LambdaQueryWrapper<RoleMutexDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, RoleMutexDo::getIsDelete, bo.getDataScope());

        // id 开始
        if (bo.getEq_id() != null) {
            queryWrapper.eq(RoleMutexDo::getId, bo.getEq_id());
        }
        if (bo.getNeq_id() != null) {
            queryWrapper.ne(RoleMutexDo::getId, bo.getNeq_id());
        }
        if (StringUtil.isNotEmpty(bo.getIn_id())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getId, bo.getIn_id());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_id())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getId, bo.getNotin_id());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(RoleMutexDo::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(RoleMutexDo::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like(RoleMutexDo::getRoleId, bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight(RoleMutexDo::getRoleId, bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft(RoleMutexDo::getRoleId, bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike(RoleMutexDo::getRoleId, bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getRoleId, bo.getNotin_roleId());
        }
        // mutex_role_id 开始
        if (bo.getEq_mutexRoleId() != null) {
            queryWrapper.eq(RoleMutexDo::getMutexRoleId, bo.getEq_mutexRoleId());
        }
        if (bo.getNeq_mutexRoleId() != null) {
            queryWrapper.ne(RoleMutexDo::getMutexRoleId, bo.getNeq_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_mutexRoleId())) {
            queryWrapper.like(RoleMutexDo::getMutexRoleId, bo.getLike_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_mutexRoleId())) {
            queryWrapper.likeRight(RoleMutexDo::getMutexRoleId, bo.getLiker_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_mutexRoleId())) {
            queryWrapper.likeLeft(RoleMutexDo::getMutexRoleId, bo.getLikel_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_mutexRoleId())) {
            queryWrapper.notLike(RoleMutexDo::getMutexRoleId, bo.getNotlike_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_mutexRoleId())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getMutexRoleId, bo.getIn_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_mutexRoleId())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getMutexRoleId, bo.getNotin_mutexRoleId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(RoleMutexDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(RoleMutexDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(RoleMutexDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(RoleMutexDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(RoleMutexDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(RoleMutexDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, RoleMutexDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleMutexDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(RoleMutexDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(RoleMutexDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(RoleMutexDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(RoleMutexDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(RoleMutexDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(RoleMutexDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(RoleMutexDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(RoleMutexDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(RoleMutexDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(RoleMutexDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(RoleMutexDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(RoleMutexDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getCreateUserId, bo.getNotin_createUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<RoleMutexDo> getJoinLambdaQueryWrapper(RoleMutexSearchBo bo) {
        JoinLambdaQueryWrapper<RoleMutexDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, RoleMutexDo::getIsDelete, bo.getDataScope());

        // id 开始
        if (bo.getEq_id() != null) {
            queryWrapper.eq(RoleMutexDo::getId, bo.getEq_id());
        }
        if (bo.getNeq_id() != null) {
            queryWrapper.ne(RoleMutexDo::getId, bo.getNeq_id());
        }
        if (StringUtil.isNotEmpty(bo.getIn_id())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getId, bo.getIn_id());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_id())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getId, bo.getNotin_id());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(RoleMutexDo::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(RoleMutexDo::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like(RoleMutexDo::getRoleId, bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight(RoleMutexDo::getRoleId, bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft(RoleMutexDo::getRoleId, bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike(RoleMutexDo::getRoleId, bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getRoleId, bo.getNotin_roleId());
        }
        // mutex_role_id 开始
        if (bo.getEq_mutexRoleId() != null) {
            queryWrapper.eq(RoleMutexDo::getMutexRoleId, bo.getEq_mutexRoleId());
        }
        if (bo.getNeq_mutexRoleId() != null) {
            queryWrapper.ne(RoleMutexDo::getMutexRoleId, bo.getNeq_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_mutexRoleId())) {
            queryWrapper.like(RoleMutexDo::getMutexRoleId, bo.getLike_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_mutexRoleId())) {
            queryWrapper.likeRight(RoleMutexDo::getMutexRoleId, bo.getLiker_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_mutexRoleId())) {
            queryWrapper.likeLeft(RoleMutexDo::getMutexRoleId, bo.getLikel_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_mutexRoleId())) {
            queryWrapper.notLike(RoleMutexDo::getMutexRoleId, bo.getNotlike_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_mutexRoleId())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getMutexRoleId, bo.getIn_mutexRoleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_mutexRoleId())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getMutexRoleId, bo.getNotin_mutexRoleId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(RoleMutexDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(RoleMutexDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(RoleMutexDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(RoleMutexDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(RoleMutexDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(RoleMutexDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, RoleMutexDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleMutexDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(RoleMutexDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(RoleMutexDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(RoleMutexDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(RoleMutexDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(RoleMutexDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(RoleMutexDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(RoleMutexDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(RoleMutexDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(RoleMutexDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(RoleMutexDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(RoleMutexDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(RoleMutexDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, RoleMutexDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleMutexDo::getCreateUserId, bo.getNotin_createUserId());
        }

        return queryWrapper;
    }

}