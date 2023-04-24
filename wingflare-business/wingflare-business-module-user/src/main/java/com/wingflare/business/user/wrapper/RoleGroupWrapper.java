package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.RoleGroupDo;
import com.wingflare.facade.module.user.bo.RoleGroupSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 系统角色分组表Wrapper
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
public class RoleGroupWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<RoleGroupDo> getQueryWrapper(RoleGroupSearchBo bo) {
        QueryWrapper<RoleGroupDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // role_group_id 开始
        if (bo.getEq_roleGroupId() != null) {
            queryWrapper.eq("role_group_id", bo.getEq_roleGroupId());
        }
        if (bo.getNeq_roleGroupId() != null) {
            queryWrapper.ne("role_group_id", bo.getNeq_roleGroupId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleGroupId())) {
            WrapperUtil.in(queryWrapper, "role_group_id", bo.getIn_roleGroupId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleGroupId())) {
            WrapperUtil.notIn(queryWrapper, "role_group_id", bo.getNotin_roleGroupId());
        }
        // group_name 开始
        if (bo.getEq_groupName() != null) {
            queryWrapper.eq("group_name", bo.getEq_groupName());
        }
        if (bo.getNeq_groupName() != null) {
            queryWrapper.ne("group_name", bo.getNeq_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_groupName())) {
            queryWrapper.like("group_name", bo.getLike_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_groupName())) {
            queryWrapper.likeRight("group_name", bo.getLiker_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_groupName())) {
            queryWrapper.likeLeft("group_name", bo.getLikel_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_groupName())) {
            queryWrapper.notLike("group_name", bo.getNotlike_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_groupName())) {
            WrapperUtil.in(queryWrapper, "group_name", bo.getIn_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_groupName())) {
            WrapperUtil.notIn(queryWrapper, "group_name", bo.getNotin_groupName());
        }
        // group_remark 开始
        if (bo.getEq_groupRemark() != null) {
            queryWrapper.eq("group_remark", bo.getEq_groupRemark());
        }
        if (bo.getNeq_groupRemark() != null) {
            queryWrapper.ne("group_remark", bo.getNeq_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLike_groupRemark())) {
            queryWrapper.like("group_remark", bo.getLike_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_groupRemark())) {
            queryWrapper.likeRight("group_remark", bo.getLiker_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_groupRemark())) {
            queryWrapper.likeLeft("group_remark", bo.getLikel_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_groupRemark())) {
            queryWrapper.notLike("group_remark", bo.getNotlike_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getIn_groupRemark())) {
            WrapperUtil.in(queryWrapper, "group_remark", bo.getIn_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_groupRemark())) {
            WrapperUtil.notIn(queryWrapper, "group_remark", bo.getNotin_groupRemark());
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
	public static LambdaQueryWrapper<RoleGroupDo> getLambdaQueryWrapper(RoleGroupSearchBo bo) {
        LambdaQueryWrapper<RoleGroupDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, RoleGroupDo::getIsDelete, bo.getDataScope());

        // role_group_id 开始
        if (bo.getEq_roleGroupId() != null) {
            queryWrapper.eq(RoleGroupDo::getRoleGroupId, bo.getEq_roleGroupId());
        }
        if (bo.getNeq_roleGroupId() != null) {
            queryWrapper.ne(RoleGroupDo::getRoleGroupId, bo.getNeq_roleGroupId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleGroupId())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getRoleGroupId, bo.getIn_roleGroupId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleGroupId())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getRoleGroupId, bo.getNotin_roleGroupId());
        }
        // group_name 开始
        if (bo.getEq_groupName() != null) {
            queryWrapper.eq(RoleGroupDo::getGroupName, bo.getEq_groupName());
        }
        if (bo.getNeq_groupName() != null) {
            queryWrapper.ne(RoleGroupDo::getGroupName, bo.getNeq_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_groupName())) {
            queryWrapper.like(RoleGroupDo::getGroupName, bo.getLike_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_groupName())) {
            queryWrapper.likeRight(RoleGroupDo::getGroupName, bo.getLiker_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_groupName())) {
            queryWrapper.likeLeft(RoleGroupDo::getGroupName, bo.getLikel_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_groupName())) {
            queryWrapper.notLike(RoleGroupDo::getGroupName, bo.getNotlike_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_groupName())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getGroupName, bo.getIn_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_groupName())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getGroupName, bo.getNotin_groupName());
        }
        // group_remark 开始
        if (bo.getEq_groupRemark() != null) {
            queryWrapper.eq(RoleGroupDo::getGroupRemark, bo.getEq_groupRemark());
        }
        if (bo.getNeq_groupRemark() != null) {
            queryWrapper.ne(RoleGroupDo::getGroupRemark, bo.getNeq_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLike_groupRemark())) {
            queryWrapper.like(RoleGroupDo::getGroupRemark, bo.getLike_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_groupRemark())) {
            queryWrapper.likeRight(RoleGroupDo::getGroupRemark, bo.getLiker_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_groupRemark())) {
            queryWrapper.likeLeft(RoleGroupDo::getGroupRemark, bo.getLikel_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_groupRemark())) {
            queryWrapper.notLike(RoleGroupDo::getGroupRemark, bo.getNotlike_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getIn_groupRemark())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getGroupRemark, bo.getIn_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_groupRemark())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getGroupRemark, bo.getNotin_groupRemark());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(RoleGroupDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(RoleGroupDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(RoleGroupDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(RoleGroupDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(RoleGroupDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(RoleGroupDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, RoleGroupDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleGroupDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(RoleGroupDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(RoleGroupDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(RoleGroupDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(RoleGroupDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(RoleGroupDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(RoleGroupDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, RoleGroupDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleGroupDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(RoleGroupDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(RoleGroupDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(RoleGroupDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(RoleGroupDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(RoleGroupDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(RoleGroupDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(RoleGroupDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(RoleGroupDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(RoleGroupDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(RoleGroupDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(RoleGroupDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(RoleGroupDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(RoleGroupDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(RoleGroupDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(RoleGroupDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(RoleGroupDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(RoleGroupDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(RoleGroupDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(RoleGroupDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(RoleGroupDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(RoleGroupDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(RoleGroupDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(RoleGroupDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(RoleGroupDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<RoleGroupDo> getJoinLambdaQueryWrapper(RoleGroupSearchBo bo) {
        JoinLambdaQueryWrapper<RoleGroupDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, RoleGroupDo::getIsDelete, bo.getDataScope());

        // role_group_id 开始
        if (bo.getEq_roleGroupId() != null) {
            queryWrapper.eq(RoleGroupDo::getRoleGroupId, bo.getEq_roleGroupId());
        }
        if (bo.getNeq_roleGroupId() != null) {
            queryWrapper.ne(RoleGroupDo::getRoleGroupId, bo.getNeq_roleGroupId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleGroupId())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getRoleGroupId, bo.getIn_roleGroupId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleGroupId())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getRoleGroupId, bo.getNotin_roleGroupId());
        }
        // group_name 开始
        if (bo.getEq_groupName() != null) {
            queryWrapper.eq(RoleGroupDo::getGroupName, bo.getEq_groupName());
        }
        if (bo.getNeq_groupName() != null) {
            queryWrapper.ne(RoleGroupDo::getGroupName, bo.getNeq_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_groupName())) {
            queryWrapper.like(RoleGroupDo::getGroupName, bo.getLike_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_groupName())) {
            queryWrapper.likeRight(RoleGroupDo::getGroupName, bo.getLiker_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_groupName())) {
            queryWrapper.likeLeft(RoleGroupDo::getGroupName, bo.getLikel_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_groupName())) {
            queryWrapper.notLike(RoleGroupDo::getGroupName, bo.getNotlike_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_groupName())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getGroupName, bo.getIn_groupName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_groupName())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getGroupName, bo.getNotin_groupName());
        }
        // group_remark 开始
        if (bo.getEq_groupRemark() != null) {
            queryWrapper.eq(RoleGroupDo::getGroupRemark, bo.getEq_groupRemark());
        }
        if (bo.getNeq_groupRemark() != null) {
            queryWrapper.ne(RoleGroupDo::getGroupRemark, bo.getNeq_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLike_groupRemark())) {
            queryWrapper.like(RoleGroupDo::getGroupRemark, bo.getLike_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_groupRemark())) {
            queryWrapper.likeRight(RoleGroupDo::getGroupRemark, bo.getLiker_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_groupRemark())) {
            queryWrapper.likeLeft(RoleGroupDo::getGroupRemark, bo.getLikel_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_groupRemark())) {
            queryWrapper.notLike(RoleGroupDo::getGroupRemark, bo.getNotlike_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getIn_groupRemark())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getGroupRemark, bo.getIn_groupRemark());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_groupRemark())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getGroupRemark, bo.getNotin_groupRemark());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(RoleGroupDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(RoleGroupDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(RoleGroupDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(RoleGroupDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(RoleGroupDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(RoleGroupDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, RoleGroupDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleGroupDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(RoleGroupDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(RoleGroupDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(RoleGroupDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(RoleGroupDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(RoleGroupDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(RoleGroupDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, RoleGroupDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, RoleGroupDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(RoleGroupDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(RoleGroupDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(RoleGroupDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(RoleGroupDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(RoleGroupDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(RoleGroupDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(RoleGroupDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(RoleGroupDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(RoleGroupDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(RoleGroupDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(RoleGroupDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(RoleGroupDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(RoleGroupDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(RoleGroupDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(RoleGroupDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(RoleGroupDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(RoleGroupDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(RoleGroupDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(RoleGroupDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(RoleGroupDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(RoleGroupDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(RoleGroupDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(RoleGroupDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(RoleGroupDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, RoleGroupDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, RoleGroupDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}