package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.RolePermissionDO;
import com.wingflare.facade.module.user.bo.RolePermissionSearchBO;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 系统角色权限Wrapper
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
public class RolePermissionWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<RolePermissionDO> getQueryWrapper(RolePermissionSearchBO bo) {
        QueryWrapper<RolePermissionDO> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }


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
        // permission_code 开始
        if (bo.getEq_permissionCode() != null) {
            queryWrapper.eq("permission_code", bo.getEq_permissionCode());
        }
        if (bo.getNeq_permissionCode() != null) {
            queryWrapper.ne("permission_code", bo.getNeq_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_permissionCode())) {
            queryWrapper.like("permission_code", bo.getLike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_permissionCode())) {
            queryWrapper.likeRight("permission_code", bo.getLiker_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_permissionCode())) {
            queryWrapper.likeLeft("permission_code", bo.getLikel_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_permissionCode())) {
            queryWrapper.notLike("permission_code", bo.getNotlike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_permissionCode())) {
            WrapperUtil.in(queryWrapper, "permission_code", bo.getIn_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_permissionCode())) {
            WrapperUtil.notIn(queryWrapper, "permission_code", bo.getNotin_permissionCode());
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
	public static LambdaQueryWrapper<RolePermissionDO> getLambdaQueryWrapper(RolePermissionSearchBO bo) {
        LambdaQueryWrapper<RolePermissionDO> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }


        // id 开始
        if (bo.getEq_id() != null) {
            queryWrapper.eq(RolePermissionDO::getId, bo.getEq_id());
        }
        if (bo.getNeq_id() != null) {
            queryWrapper.ne(RolePermissionDO::getId, bo.getNeq_id());
        }
        if (StringUtil.isNotEmpty(bo.getIn_id())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getId, bo.getIn_id());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_id())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getId, bo.getNotin_id());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(RolePermissionDO::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(RolePermissionDO::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like(RolePermissionDO::getRoleId, bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight(RolePermissionDO::getRoleId, bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft(RolePermissionDO::getRoleId, bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike(RolePermissionDO::getRoleId, bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getRoleId, bo.getNotin_roleId());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(RolePermissionDO::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(RolePermissionDO::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(RolePermissionDO::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(RolePermissionDO::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(RolePermissionDO::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(RolePermissionDO::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getSystemCode, bo.getNotin_systemCode());
        }
        // permission_code 开始
        if (bo.getEq_permissionCode() != null) {
            queryWrapper.eq(RolePermissionDO::getPermissionCode, bo.getEq_permissionCode());
        }
        if (bo.getNeq_permissionCode() != null) {
            queryWrapper.ne(RolePermissionDO::getPermissionCode, bo.getNeq_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_permissionCode())) {
            queryWrapper.like(RolePermissionDO::getPermissionCode, bo.getLike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_permissionCode())) {
            queryWrapper.likeRight(RolePermissionDO::getPermissionCode, bo.getLiker_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_permissionCode())) {
            queryWrapper.likeLeft(RolePermissionDO::getPermissionCode, bo.getLikel_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_permissionCode())) {
            queryWrapper.notLike(RolePermissionDO::getPermissionCode, bo.getNotlike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_permissionCode())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getPermissionCode, bo.getIn_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_permissionCode())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getPermissionCode, bo.getNotin_permissionCode());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(RolePermissionDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(RolePermissionDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(RolePermissionDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(RolePermissionDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(RolePermissionDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(RolePermissionDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, RolePermissionDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, RolePermissionDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(RolePermissionDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(RolePermissionDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(RolePermissionDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(RolePermissionDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(RolePermissionDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(RolePermissionDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(RolePermissionDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(RolePermissionDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(RolePermissionDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(RolePermissionDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(RolePermissionDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(RolePermissionDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getCreateUserId, bo.getNotin_createUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<RolePermissionDO> getJoinLambdaQueryWrapper(RolePermissionSearchBO bo) {
        JoinLambdaQueryWrapper<RolePermissionDO> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }


        // id 开始
        if (bo.getEq_id() != null) {
            queryWrapper.eq(RolePermissionDO::getId, bo.getEq_id());
        }
        if (bo.getNeq_id() != null) {
            queryWrapper.ne(RolePermissionDO::getId, bo.getNeq_id());
        }
        if (StringUtil.isNotEmpty(bo.getIn_id())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getId, bo.getIn_id());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_id())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getId, bo.getNotin_id());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(RolePermissionDO::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(RolePermissionDO::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like(RolePermissionDO::getRoleId, bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight(RolePermissionDO::getRoleId, bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft(RolePermissionDO::getRoleId, bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike(RolePermissionDO::getRoleId, bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getRoleId, bo.getNotin_roleId());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(RolePermissionDO::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(RolePermissionDO::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(RolePermissionDO::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(RolePermissionDO::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(RolePermissionDO::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(RolePermissionDO::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getSystemCode, bo.getNotin_systemCode());
        }
        // permission_code 开始
        if (bo.getEq_permissionCode() != null) {
            queryWrapper.eq(RolePermissionDO::getPermissionCode, bo.getEq_permissionCode());
        }
        if (bo.getNeq_permissionCode() != null) {
            queryWrapper.ne(RolePermissionDO::getPermissionCode, bo.getNeq_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_permissionCode())) {
            queryWrapper.like(RolePermissionDO::getPermissionCode, bo.getLike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_permissionCode())) {
            queryWrapper.likeRight(RolePermissionDO::getPermissionCode, bo.getLiker_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_permissionCode())) {
            queryWrapper.likeLeft(RolePermissionDO::getPermissionCode, bo.getLikel_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_permissionCode())) {
            queryWrapper.notLike(RolePermissionDO::getPermissionCode, bo.getNotlike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_permissionCode())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getPermissionCode, bo.getIn_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_permissionCode())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getPermissionCode, bo.getNotin_permissionCode());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(RolePermissionDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(RolePermissionDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(RolePermissionDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(RolePermissionDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(RolePermissionDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(RolePermissionDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, RolePermissionDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, RolePermissionDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(RolePermissionDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(RolePermissionDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(RolePermissionDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(RolePermissionDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(RolePermissionDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(RolePermissionDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(RolePermissionDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(RolePermissionDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(RolePermissionDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(RolePermissionDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(RolePermissionDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(RolePermissionDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, RolePermissionDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, RolePermissionDO::getCreateUserId, bo.getNotin_createUserId());
        }

        return queryWrapper;
    }

}