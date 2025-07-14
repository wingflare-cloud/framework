package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.business.user.db.IdentityDO;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.facade.module.user.bo.IdentitySearchBO;

/**
 * 岗位身份Wrapper
 *
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
public class IdentityWrapper
{
	
	/**
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<IdentityDO> getLambdaQueryWrapper(IdentitySearchBO bo) {
        LambdaQueryWrapper<IdentityDO> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, IdentityDO::getIsDelete, bo.getDataScope());

        // identity_id 开始
        if (bo.getEq_identityId() != null) {
            queryWrapper.eq(IdentityDO::getIdentityId, bo.getEq_identityId());
        }
        if (bo.getNeq_identityId() != null) {
            queryWrapper.ne(IdentityDO::getIdentityId, bo.getNeq_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getIdentityId, bo.getIn_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getIdentityId, bo.getNotin_identityId());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(IdentityDO::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(IdentityDO::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like(IdentityDO::getOrgId, bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight(IdentityDO::getOrgId, bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft(IdentityDO::getOrgId, bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike(IdentityDO::getOrgId, bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getOrgId, bo.getNotin_orgId());
        }
        // department_id 开始
        if (bo.getEq_departmentId() != null) {
            queryWrapper.eq(IdentityDO::getDepartmentId, bo.getEq_departmentId());
        }
        if (bo.getNeq_departmentId() != null) {
            queryWrapper.ne(IdentityDO::getDepartmentId, bo.getNeq_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_departmentId())) {
            queryWrapper.like(IdentityDO::getDepartmentId, bo.getLike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_departmentId())) {
            queryWrapper.likeRight(IdentityDO::getDepartmentId, bo.getLiker_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_departmentId())) {
            queryWrapper.likeLeft(IdentityDO::getDepartmentId, bo.getLikel_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_departmentId())) {
            queryWrapper.notLike(IdentityDO::getDepartmentId, bo.getNotlike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getDepartmentId, bo.getIn_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getDepartmentId, bo.getNotin_departmentId());
        }
        // identity_name 开始
        if (bo.getEq_identityName() != null) {
            queryWrapper.eq(IdentityDO::getIdentityName, bo.getEq_identityName());
        }
        if (bo.getNeq_identityName() != null) {
            queryWrapper.ne(IdentityDO::getIdentityName, bo.getNeq_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_identityName())) {
            queryWrapper.like(IdentityDO::getIdentityName, bo.getLike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_identityName())) {
            queryWrapper.likeRight(IdentityDO::getIdentityName, bo.getLiker_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_identityName())) {
            queryWrapper.likeLeft(IdentityDO::getIdentityName, bo.getLikel_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_identityName())) {
            queryWrapper.notLike(IdentityDO::getIdentityName, bo.getNotlike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityName())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getIdentityName, bo.getIn_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityName())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getIdentityName, bo.getNotin_identityName());
        }
        // job_level_id 开始
        if (bo.getEq_jobLevelId() != null) {
            queryWrapper.eq(IdentityDO::getJobLevelId, bo.getEq_jobLevelId());
        }
        if (bo.getNeq_jobLevelId() != null) {
            queryWrapper.ne(IdentityDO::getJobLevelId, bo.getNeq_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_jobLevelId())) {
            queryWrapper.like(IdentityDO::getJobLevelId, bo.getLike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_jobLevelId())) {
            queryWrapper.likeRight(IdentityDO::getJobLevelId, bo.getLiker_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_jobLevelId())) {
            queryWrapper.likeLeft(IdentityDO::getJobLevelId, bo.getLikel_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_jobLevelId())) {
            queryWrapper.notLike(IdentityDO::getJobLevelId, bo.getNotlike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_jobLevelId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getJobLevelId, bo.getIn_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_jobLevelId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getJobLevelId, bo.getNotin_jobLevelId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(IdentityDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(IdentityDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(IdentityDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(IdentityDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(IdentityDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(IdentityDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, IdentityDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, IdentityDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(IdentityDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(IdentityDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(IdentityDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(IdentityDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(IdentityDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(IdentityDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, IdentityDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, IdentityDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(IdentityDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(IdentityDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(IdentityDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(IdentityDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(IdentityDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(IdentityDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(IdentityDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(IdentityDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(IdentityDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(IdentityDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(IdentityDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(IdentityDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(IdentityDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(IdentityDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(IdentityDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(IdentityDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(IdentityDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(IdentityDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(IdentityDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(IdentityDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(IdentityDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(IdentityDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(IdentityDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(IdentityDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<IdentityDO> getJoinLambdaQueryWrapper(IdentitySearchBO bo) {
        JoinLambdaQueryWrapper<IdentityDO> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, IdentityDO::getIsDelete, bo.getDataScope());

        // identity_id 开始
        if (bo.getEq_identityId() != null) {
            queryWrapper.eq(IdentityDO::getIdentityId, bo.getEq_identityId());
        }
        if (bo.getNeq_identityId() != null) {
            queryWrapper.ne(IdentityDO::getIdentityId, bo.getNeq_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getIdentityId, bo.getIn_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getIdentityId, bo.getNotin_identityId());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(IdentityDO::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(IdentityDO::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like(IdentityDO::getOrgId, bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight(IdentityDO::getOrgId, bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft(IdentityDO::getOrgId, bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike(IdentityDO::getOrgId, bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getOrgId, bo.getNotin_orgId());
        }
        // department_id 开始
        if (bo.getEq_departmentId() != null) {
            queryWrapper.eq(IdentityDO::getDepartmentId, bo.getEq_departmentId());
        }
        if (bo.getNeq_departmentId() != null) {
            queryWrapper.ne(IdentityDO::getDepartmentId, bo.getNeq_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_departmentId())) {
            queryWrapper.like(IdentityDO::getDepartmentId, bo.getLike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_departmentId())) {
            queryWrapper.likeRight(IdentityDO::getDepartmentId, bo.getLiker_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_departmentId())) {
            queryWrapper.likeLeft(IdentityDO::getDepartmentId, bo.getLikel_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_departmentId())) {
            queryWrapper.notLike(IdentityDO::getDepartmentId, bo.getNotlike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getDepartmentId, bo.getIn_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getDepartmentId, bo.getNotin_departmentId());
        }
        // identity_name 开始
        if (bo.getEq_identityName() != null) {
            queryWrapper.eq(IdentityDO::getIdentityName, bo.getEq_identityName());
        }
        if (bo.getNeq_identityName() != null) {
            queryWrapper.ne(IdentityDO::getIdentityName, bo.getNeq_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_identityName())) {
            queryWrapper.like(IdentityDO::getIdentityName, bo.getLike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_identityName())) {
            queryWrapper.likeRight(IdentityDO::getIdentityName, bo.getLiker_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_identityName())) {
            queryWrapper.likeLeft(IdentityDO::getIdentityName, bo.getLikel_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_identityName())) {
            queryWrapper.notLike(IdentityDO::getIdentityName, bo.getNotlike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityName())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getIdentityName, bo.getIn_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityName())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getIdentityName, bo.getNotin_identityName());
        }
        // job_level_id 开始
        if (bo.getEq_jobLevelId() != null) {
            queryWrapper.eq(IdentityDO::getJobLevelId, bo.getEq_jobLevelId());
        }
        if (bo.getNeq_jobLevelId() != null) {
            queryWrapper.ne(IdentityDO::getJobLevelId, bo.getNeq_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_jobLevelId())) {
            queryWrapper.like(IdentityDO::getJobLevelId, bo.getLike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_jobLevelId())) {
            queryWrapper.likeRight(IdentityDO::getJobLevelId, bo.getLiker_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_jobLevelId())) {
            queryWrapper.likeLeft(IdentityDO::getJobLevelId, bo.getLikel_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_jobLevelId())) {
            queryWrapper.notLike(IdentityDO::getJobLevelId, bo.getNotlike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_jobLevelId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getJobLevelId, bo.getIn_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_jobLevelId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getJobLevelId, bo.getNotin_jobLevelId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(IdentityDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(IdentityDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(IdentityDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(IdentityDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(IdentityDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(IdentityDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, IdentityDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, IdentityDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(IdentityDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(IdentityDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(IdentityDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(IdentityDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(IdentityDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(IdentityDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, IdentityDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, IdentityDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(IdentityDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(IdentityDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(IdentityDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(IdentityDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(IdentityDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(IdentityDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(IdentityDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(IdentityDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(IdentityDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(IdentityDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(IdentityDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(IdentityDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(IdentityDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(IdentityDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(IdentityDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(IdentityDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(IdentityDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(IdentityDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(IdentityDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(IdentityDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(IdentityDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(IdentityDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(IdentityDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(IdentityDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, IdentityDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}