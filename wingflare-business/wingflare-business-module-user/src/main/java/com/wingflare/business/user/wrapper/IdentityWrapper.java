package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.business.user.db.IdentityDo;
import com.wingflare.facade.module.user.bo.IdentitySearchBo;

/**
 * 岗位身份Wrapper
 *
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
public class IdentityWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<IdentityDo> getQueryWrapper(IdentitySearchBo bo) {
        QueryWrapper<IdentityDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // identity_id 开始
        if (bo.getEq_identityId() != null) {
            queryWrapper.eq("identity_id", bo.getEq_identityId());
        }
        if (bo.getNeq_identityId() != null) {
            queryWrapper.ne("identity_id", bo.getNeq_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityId())) {
            WrapperUtil.in(queryWrapper, "identity_id", bo.getIn_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityId())) {
            WrapperUtil.notIn(queryWrapper, "identity_id", bo.getNotin_identityId());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq("org_id", bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne("org_id", bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like("org_id", bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight("org_id", bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft("org_id", bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike("org_id", bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, "org_id", bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, "org_id", bo.getNotin_orgId());
        }
        // department_id 开始
        if (bo.getEq_departmentId() != null) {
            queryWrapper.eq("department_id", bo.getEq_departmentId());
        }
        if (bo.getNeq_departmentId() != null) {
            queryWrapper.ne("department_id", bo.getNeq_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_departmentId())) {
            queryWrapper.like("department_id", bo.getLike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_departmentId())) {
            queryWrapper.likeRight("department_id", bo.getLiker_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_departmentId())) {
            queryWrapper.likeLeft("department_id", bo.getLikel_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_departmentId())) {
            queryWrapper.notLike("department_id", bo.getNotlike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentId())) {
            WrapperUtil.in(queryWrapper, "department_id", bo.getIn_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentId())) {
            WrapperUtil.notIn(queryWrapper, "department_id", bo.getNotin_departmentId());
        }
        // identity_name 开始
        if (bo.getEq_identityName() != null) {
            queryWrapper.eq("identity_name", bo.getEq_identityName());
        }
        if (bo.getNeq_identityName() != null) {
            queryWrapper.ne("identity_name", bo.getNeq_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_identityName())) {
            queryWrapper.like("identity_name", bo.getLike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_identityName())) {
            queryWrapper.likeRight("identity_name", bo.getLiker_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_identityName())) {
            queryWrapper.likeLeft("identity_name", bo.getLikel_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_identityName())) {
            queryWrapper.notLike("identity_name", bo.getNotlike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityName())) {
            WrapperUtil.in(queryWrapper, "identity_name", bo.getIn_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityName())) {
            WrapperUtil.notIn(queryWrapper, "identity_name", bo.getNotin_identityName());
        }
        // job_level_id 开始
        if (bo.getEq_jobLevelId() != null) {
            queryWrapper.eq("job_level_id", bo.getEq_jobLevelId());
        }
        if (bo.getNeq_jobLevelId() != null) {
            queryWrapper.ne("job_level_id", bo.getNeq_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_jobLevelId())) {
            queryWrapper.like("job_level_id", bo.getLike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_jobLevelId())) {
            queryWrapper.likeRight("job_level_id", bo.getLiker_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_jobLevelId())) {
            queryWrapper.likeLeft("job_level_id", bo.getLikel_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_jobLevelId())) {
            queryWrapper.notLike("job_level_id", bo.getNotlike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_jobLevelId())) {
            WrapperUtil.in(queryWrapper, "job_level_id", bo.getIn_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_jobLevelId())) {
            WrapperUtil.notIn(queryWrapper, "job_level_id", bo.getNotin_jobLevelId());
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
	public static LambdaQueryWrapper<IdentityDo> getLambdaQueryWrapper(IdentitySearchBo bo) {
        LambdaQueryWrapper<IdentityDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, IdentityDo::getIsDelete, bo.getDataScope());

        // identity_id 开始
        if (bo.getEq_identityId() != null) {
            queryWrapper.eq(IdentityDo::getIdentityId, bo.getEq_identityId());
        }
        if (bo.getNeq_identityId() != null) {
            queryWrapper.ne(IdentityDo::getIdentityId, bo.getNeq_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getIdentityId, bo.getIn_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getIdentityId, bo.getNotin_identityId());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(IdentityDo::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(IdentityDo::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like(IdentityDo::getOrgId, bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight(IdentityDo::getOrgId, bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft(IdentityDo::getOrgId, bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike(IdentityDo::getOrgId, bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getOrgId, bo.getNotin_orgId());
        }
        // department_id 开始
        if (bo.getEq_departmentId() != null) {
            queryWrapper.eq(IdentityDo::getDepartmentId, bo.getEq_departmentId());
        }
        if (bo.getNeq_departmentId() != null) {
            queryWrapper.ne(IdentityDo::getDepartmentId, bo.getNeq_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_departmentId())) {
            queryWrapper.like(IdentityDo::getDepartmentId, bo.getLike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_departmentId())) {
            queryWrapper.likeRight(IdentityDo::getDepartmentId, bo.getLiker_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_departmentId())) {
            queryWrapper.likeLeft(IdentityDo::getDepartmentId, bo.getLikel_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_departmentId())) {
            queryWrapper.notLike(IdentityDo::getDepartmentId, bo.getNotlike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getDepartmentId, bo.getIn_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getDepartmentId, bo.getNotin_departmentId());
        }
        // identity_name 开始
        if (bo.getEq_identityName() != null) {
            queryWrapper.eq(IdentityDo::getIdentityName, bo.getEq_identityName());
        }
        if (bo.getNeq_identityName() != null) {
            queryWrapper.ne(IdentityDo::getIdentityName, bo.getNeq_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_identityName())) {
            queryWrapper.like(IdentityDo::getIdentityName, bo.getLike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_identityName())) {
            queryWrapper.likeRight(IdentityDo::getIdentityName, bo.getLiker_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_identityName())) {
            queryWrapper.likeLeft(IdentityDo::getIdentityName, bo.getLikel_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_identityName())) {
            queryWrapper.notLike(IdentityDo::getIdentityName, bo.getNotlike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityName())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getIdentityName, bo.getIn_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityName())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getIdentityName, bo.getNotin_identityName());
        }
        // job_level_id 开始
        if (bo.getEq_jobLevelId() != null) {
            queryWrapper.eq(IdentityDo::getJobLevelId, bo.getEq_jobLevelId());
        }
        if (bo.getNeq_jobLevelId() != null) {
            queryWrapper.ne(IdentityDo::getJobLevelId, bo.getNeq_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_jobLevelId())) {
            queryWrapper.like(IdentityDo::getJobLevelId, bo.getLike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_jobLevelId())) {
            queryWrapper.likeRight(IdentityDo::getJobLevelId, bo.getLiker_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_jobLevelId())) {
            queryWrapper.likeLeft(IdentityDo::getJobLevelId, bo.getLikel_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_jobLevelId())) {
            queryWrapper.notLike(IdentityDo::getJobLevelId, bo.getNotlike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_jobLevelId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getJobLevelId, bo.getIn_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_jobLevelId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getJobLevelId, bo.getNotin_jobLevelId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(IdentityDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(IdentityDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(IdentityDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(IdentityDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(IdentityDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(IdentityDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, IdentityDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, IdentityDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(IdentityDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(IdentityDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(IdentityDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(IdentityDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(IdentityDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(IdentityDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, IdentityDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, IdentityDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(IdentityDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(IdentityDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(IdentityDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(IdentityDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(IdentityDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(IdentityDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(IdentityDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(IdentityDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(IdentityDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(IdentityDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(IdentityDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(IdentityDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(IdentityDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(IdentityDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(IdentityDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(IdentityDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(IdentityDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(IdentityDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(IdentityDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(IdentityDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(IdentityDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(IdentityDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(IdentityDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(IdentityDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<IdentityDo> getJoinLambdaQueryWrapper(IdentitySearchBo bo) {
        JoinLambdaQueryWrapper<IdentityDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, IdentityDo::getIsDelete, bo.getDataScope());

        // identity_id 开始
        if (bo.getEq_identityId() != null) {
            queryWrapper.eq(IdentityDo::getIdentityId, bo.getEq_identityId());
        }
        if (bo.getNeq_identityId() != null) {
            queryWrapper.ne(IdentityDo::getIdentityId, bo.getNeq_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getIdentityId, bo.getIn_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getIdentityId, bo.getNotin_identityId());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(IdentityDo::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(IdentityDo::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like(IdentityDo::getOrgId, bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight(IdentityDo::getOrgId, bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft(IdentityDo::getOrgId, bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike(IdentityDo::getOrgId, bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getOrgId, bo.getNotin_orgId());
        }
        // department_id 开始
        if (bo.getEq_departmentId() != null) {
            queryWrapper.eq(IdentityDo::getDepartmentId, bo.getEq_departmentId());
        }
        if (bo.getNeq_departmentId() != null) {
            queryWrapper.ne(IdentityDo::getDepartmentId, bo.getNeq_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_departmentId())) {
            queryWrapper.like(IdentityDo::getDepartmentId, bo.getLike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_departmentId())) {
            queryWrapper.likeRight(IdentityDo::getDepartmentId, bo.getLiker_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_departmentId())) {
            queryWrapper.likeLeft(IdentityDo::getDepartmentId, bo.getLikel_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_departmentId())) {
            queryWrapper.notLike(IdentityDo::getDepartmentId, bo.getNotlike_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getDepartmentId, bo.getIn_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getDepartmentId, bo.getNotin_departmentId());
        }
        // identity_name 开始
        if (bo.getEq_identityName() != null) {
            queryWrapper.eq(IdentityDo::getIdentityName, bo.getEq_identityName());
        }
        if (bo.getNeq_identityName() != null) {
            queryWrapper.ne(IdentityDo::getIdentityName, bo.getNeq_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_identityName())) {
            queryWrapper.like(IdentityDo::getIdentityName, bo.getLike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_identityName())) {
            queryWrapper.likeRight(IdentityDo::getIdentityName, bo.getLiker_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_identityName())) {
            queryWrapper.likeLeft(IdentityDo::getIdentityName, bo.getLikel_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_identityName())) {
            queryWrapper.notLike(IdentityDo::getIdentityName, bo.getNotlike_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityName())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getIdentityName, bo.getIn_identityName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityName())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getIdentityName, bo.getNotin_identityName());
        }
        // job_level_id 开始
        if (bo.getEq_jobLevelId() != null) {
            queryWrapper.eq(IdentityDo::getJobLevelId, bo.getEq_jobLevelId());
        }
        if (bo.getNeq_jobLevelId() != null) {
            queryWrapper.ne(IdentityDo::getJobLevelId, bo.getNeq_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_jobLevelId())) {
            queryWrapper.like(IdentityDo::getJobLevelId, bo.getLike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_jobLevelId())) {
            queryWrapper.likeRight(IdentityDo::getJobLevelId, bo.getLiker_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_jobLevelId())) {
            queryWrapper.likeLeft(IdentityDo::getJobLevelId, bo.getLikel_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_jobLevelId())) {
            queryWrapper.notLike(IdentityDo::getJobLevelId, bo.getNotlike_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_jobLevelId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getJobLevelId, bo.getIn_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_jobLevelId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getJobLevelId, bo.getNotin_jobLevelId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(IdentityDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(IdentityDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(IdentityDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(IdentityDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(IdentityDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(IdentityDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, IdentityDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, IdentityDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(IdentityDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(IdentityDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(IdentityDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(IdentityDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(IdentityDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(IdentityDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, IdentityDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, IdentityDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(IdentityDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(IdentityDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(IdentityDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(IdentityDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(IdentityDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(IdentityDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(IdentityDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(IdentityDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(IdentityDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(IdentityDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(IdentityDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(IdentityDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(IdentityDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(IdentityDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(IdentityDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(IdentityDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(IdentityDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(IdentityDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(IdentityDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(IdentityDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(IdentityDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(IdentityDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(IdentityDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(IdentityDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, IdentityDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, IdentityDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}