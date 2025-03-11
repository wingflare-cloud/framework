package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.business.user.db.UserRoleDo;
import com.wingflare.facade.module.user.bo.UserRoleSearchBo;

/**
 * 系统用户角色表 Wrapper
 *
 * @author naizui_ycx
 * @date ${datetime}
 */
public class UserRoleWrapper
{

	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<UserRoleDo> getQueryWrapper(UserRoleSearchBo bo) {
        QueryWrapper<UserRoleDo> queryWrapper = new QueryWrapper();

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
        if (StringUtil.isNotEmpty(bo.getLike_id())) {
            queryWrapper.like("id", bo.getLike_id());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_id())) {
            queryWrapper.likeRight("id", bo.getLiker_id());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_id())) {
            queryWrapper.likeLeft("id", bo.getLikel_id());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_id())) {
            queryWrapper.notLike("id", bo.getNotlike_id());
        }
        if (StringUtil.isNotEmpty(bo.getIn_id())) {
            WrapperUtil.in(queryWrapper, "id", bo.getIn_id());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_id())) {
            WrapperUtil.notIn(queryWrapper, "id", bo.getNotin_id());
        }
        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq("user_id", bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne("user_id", bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userId())) {
            queryWrapper.like("user_id", bo.getLike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userId())) {
            queryWrapper.likeRight("user_id", bo.getLiker_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userId())) {
            queryWrapper.likeLeft("user_id", bo.getLikel_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userId())) {
            queryWrapper.notLike("user_id", bo.getNotlike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, "user_id", bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, "user_id", bo.getNotin_userId());
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
	public static LambdaQueryWrapper<UserRoleDo> getLambdaQueryWrapper(UserRoleSearchBo bo) {
        LambdaQueryWrapper<UserRoleDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, UserRoleDo::getIsDelete, bo.getDataScope());


        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<UserRoleDo> getJoinLambdaQueryWrapper(UserRoleSearchBo bo) {
        JoinLambdaQueryWrapper<UserRoleDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, UserRoleDo::getIsDelete, bo.getDataScope());


        return queryWrapper;
    }

}