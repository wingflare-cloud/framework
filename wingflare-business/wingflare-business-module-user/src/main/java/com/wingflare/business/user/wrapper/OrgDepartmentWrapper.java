package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.OrgDepartmentDo;
import com.wingflare.facade.module.user.bo.OrgDepartmentSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 机构部门Wrapper
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
public class OrgDepartmentWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<OrgDepartmentDo> getQueryWrapper(OrgDepartmentSearchBo bo) {
        QueryWrapper<OrgDepartmentDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // department_id 开始
        if (bo.getEq_departmentId() != null) {
            queryWrapper.eq("department_id", bo.getEq_departmentId());
        }
        if (bo.getNeq_departmentId() != null) {
            queryWrapper.ne("department_id", bo.getNeq_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentId())) {
            WrapperUtil.in(queryWrapper, "department_id", bo.getIn_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentId())) {
            WrapperUtil.notIn(queryWrapper, "department_id", bo.getNotin_departmentId());
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
        // parent_department_id 开始
        if (bo.getEq_parentDepartmentId() != null) {
            queryWrapper.eq("parent_department_id", bo.getEq_parentDepartmentId());
        }
        if (bo.getNeq_parentDepartmentId() != null) {
            queryWrapper.ne("parent_department_id", bo.getNeq_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentDepartmentId())) {
            queryWrapper.like("parent_department_id", bo.getLike_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentDepartmentId())) {
            queryWrapper.likeRight("parent_department_id", bo.getLiker_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentDepartmentId())) {
            queryWrapper.likeLeft("parent_department_id", bo.getLikel_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentDepartmentId())) {
            queryWrapper.notLike("parent_department_id", bo.getNotlike_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentDepartmentId())) {
            WrapperUtil.in(queryWrapper, "parent_department_id", bo.getIn_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentDepartmentId())) {
            WrapperUtil.notIn(queryWrapper, "parent_department_id", bo.getNotin_parentDepartmentId());
        }
        // department_name 开始
        if (bo.getEq_departmentName() != null) {
            queryWrapper.eq("department_name", bo.getEq_departmentName());
        }
        if (bo.getNeq_departmentName() != null) {
            queryWrapper.ne("department_name", bo.getNeq_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_departmentName())) {
            queryWrapper.like("department_name", bo.getLike_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_departmentName())) {
            queryWrapper.likeRight("department_name", bo.getLiker_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_departmentName())) {
            queryWrapper.likeLeft("department_name", bo.getLikel_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_departmentName())) {
            queryWrapper.notLike("department_name", bo.getNotlike_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentName())) {
            WrapperUtil.in(queryWrapper, "department_name", bo.getIn_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentName())) {
            WrapperUtil.notIn(queryWrapper, "department_name", bo.getNotin_departmentName());
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
	public static LambdaQueryWrapper<OrgDepartmentDo> getLambdaQueryWrapper(OrgDepartmentSearchBo bo) {
        LambdaQueryWrapper<OrgDepartmentDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, OrgDepartmentDo::getIsDelete, bo.getDataScope());

        // department_id 开始
        if (bo.getEq_departmentId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getDepartmentId, bo.getEq_departmentId());
        }
        if (bo.getNeq_departmentId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getDepartmentId, bo.getNeq_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getDepartmentId, bo.getIn_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getDepartmentId, bo.getNotin_departmentId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(OrgDepartmentDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(OrgDepartmentDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(OrgDepartmentDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(OrgDepartmentDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(OrgDepartmentDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(OrgDepartmentDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, OrgDepartmentDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, OrgDepartmentDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getState, bo.getNotin_state());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like(OrgDepartmentDo::getOrgId, bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getOrgId, bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getOrgId, bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike(OrgDepartmentDo::getOrgId, bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getOrgId, bo.getNotin_orgId());
        }
        // parent_department_id 开始
        if (bo.getEq_parentDepartmentId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getParentDepartmentId, bo.getEq_parentDepartmentId());
        }
        if (bo.getNeq_parentDepartmentId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getParentDepartmentId, bo.getNeq_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentDepartmentId())) {
            queryWrapper.like(OrgDepartmentDo::getParentDepartmentId, bo.getLike_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentDepartmentId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getParentDepartmentId, bo.getLiker_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentDepartmentId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getParentDepartmentId, bo.getLikel_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentDepartmentId())) {
            queryWrapper.notLike(OrgDepartmentDo::getParentDepartmentId, bo.getNotlike_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentDepartmentId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getParentDepartmentId, bo.getIn_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentDepartmentId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getParentDepartmentId, bo.getNotin_parentDepartmentId());
        }
        // department_name 开始
        if (bo.getEq_departmentName() != null) {
            queryWrapper.eq(OrgDepartmentDo::getDepartmentName, bo.getEq_departmentName());
        }
        if (bo.getNeq_departmentName() != null) {
            queryWrapper.ne(OrgDepartmentDo::getDepartmentName, bo.getNeq_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_departmentName())) {
            queryWrapper.like(OrgDepartmentDo::getDepartmentName, bo.getLike_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_departmentName())) {
            queryWrapper.likeRight(OrgDepartmentDo::getDepartmentName, bo.getLiker_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_departmentName())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getDepartmentName, bo.getLikel_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_departmentName())) {
            queryWrapper.notLike(OrgDepartmentDo::getDepartmentName, bo.getNotlike_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentName())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getDepartmentName, bo.getIn_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentName())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getDepartmentName, bo.getNotin_departmentName());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like(OrgDepartmentDo::getRoleId, bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getRoleId, bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getRoleId, bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike(OrgDepartmentDo::getRoleId, bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getRoleId, bo.getNotin_roleId());
        }
        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userId())) {
            queryWrapper.like(OrgDepartmentDo::getUserId, bo.getLike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getUserId, bo.getLiker_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getUserId, bo.getLikel_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userId())) {
            queryWrapper.notLike(OrgDepartmentDo::getUserId, bo.getNotlike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getUserId, bo.getNotin_userId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(OrgDepartmentDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(OrgDepartmentDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(OrgDepartmentDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(OrgDepartmentDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(OrgDepartmentDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(OrgDepartmentDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, OrgDepartmentDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, OrgDepartmentDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(OrgDepartmentDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(OrgDepartmentDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(OrgDepartmentDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(OrgDepartmentDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(OrgDepartmentDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(OrgDepartmentDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, OrgDepartmentDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, OrgDepartmentDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(OrgDepartmentDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(OrgDepartmentDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(OrgDepartmentDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(OrgDepartmentDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(OrgDepartmentDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(OrgDepartmentDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(OrgDepartmentDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(OrgDepartmentDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(OrgDepartmentDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(OrgDepartmentDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(OrgDepartmentDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(OrgDepartmentDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(OrgDepartmentDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(OrgDepartmentDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<OrgDepartmentDo> getJoinLambdaQueryWrapper(OrgDepartmentSearchBo bo) {
        JoinLambdaQueryWrapper<OrgDepartmentDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, OrgDepartmentDo::getIsDelete, bo.getDataScope());

        // department_id 开始
        if (bo.getEq_departmentId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getDepartmentId, bo.getEq_departmentId());
        }
        if (bo.getNeq_departmentId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getDepartmentId, bo.getNeq_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getDepartmentId, bo.getIn_departmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getDepartmentId, bo.getNotin_departmentId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(OrgDepartmentDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(OrgDepartmentDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(OrgDepartmentDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(OrgDepartmentDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(OrgDepartmentDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(OrgDepartmentDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, OrgDepartmentDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, OrgDepartmentDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getState, bo.getNotin_state());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like(OrgDepartmentDo::getOrgId, bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getOrgId, bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getOrgId, bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike(OrgDepartmentDo::getOrgId, bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getOrgId, bo.getNotin_orgId());
        }
        // parent_department_id 开始
        if (bo.getEq_parentDepartmentId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getParentDepartmentId, bo.getEq_parentDepartmentId());
        }
        if (bo.getNeq_parentDepartmentId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getParentDepartmentId, bo.getNeq_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentDepartmentId())) {
            queryWrapper.like(OrgDepartmentDo::getParentDepartmentId, bo.getLike_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentDepartmentId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getParentDepartmentId, bo.getLiker_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentDepartmentId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getParentDepartmentId, bo.getLikel_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentDepartmentId())) {
            queryWrapper.notLike(OrgDepartmentDo::getParentDepartmentId, bo.getNotlike_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentDepartmentId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getParentDepartmentId, bo.getIn_parentDepartmentId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentDepartmentId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getParentDepartmentId, bo.getNotin_parentDepartmentId());
        }
        // department_name 开始
        if (bo.getEq_departmentName() != null) {
            queryWrapper.eq(OrgDepartmentDo::getDepartmentName, bo.getEq_departmentName());
        }
        if (bo.getNeq_departmentName() != null) {
            queryWrapper.ne(OrgDepartmentDo::getDepartmentName, bo.getNeq_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_departmentName())) {
            queryWrapper.like(OrgDepartmentDo::getDepartmentName, bo.getLike_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_departmentName())) {
            queryWrapper.likeRight(OrgDepartmentDo::getDepartmentName, bo.getLiker_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_departmentName())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getDepartmentName, bo.getLikel_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_departmentName())) {
            queryWrapper.notLike(OrgDepartmentDo::getDepartmentName, bo.getNotlike_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_departmentName())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getDepartmentName, bo.getIn_departmentName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_departmentName())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getDepartmentName, bo.getNotin_departmentName());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like(OrgDepartmentDo::getRoleId, bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getRoleId, bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getRoleId, bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike(OrgDepartmentDo::getRoleId, bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getRoleId, bo.getNotin_roleId());
        }
        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userId())) {
            queryWrapper.like(OrgDepartmentDo::getUserId, bo.getLike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getUserId, bo.getLiker_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getUserId, bo.getLikel_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userId())) {
            queryWrapper.notLike(OrgDepartmentDo::getUserId, bo.getNotlike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getUserId, bo.getNotin_userId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(OrgDepartmentDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(OrgDepartmentDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(OrgDepartmentDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(OrgDepartmentDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(OrgDepartmentDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(OrgDepartmentDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, OrgDepartmentDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, OrgDepartmentDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(OrgDepartmentDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(OrgDepartmentDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(OrgDepartmentDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(OrgDepartmentDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(OrgDepartmentDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(OrgDepartmentDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, OrgDepartmentDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, OrgDepartmentDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(OrgDepartmentDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(OrgDepartmentDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(OrgDepartmentDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(OrgDepartmentDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(OrgDepartmentDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(OrgDepartmentDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(OrgDepartmentDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(OrgDepartmentDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(OrgDepartmentDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(OrgDepartmentDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(OrgDepartmentDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(OrgDepartmentDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(OrgDepartmentDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(OrgDepartmentDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(OrgDepartmentDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(OrgDepartmentDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(OrgDepartmentDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(OrgDepartmentDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, OrgDepartmentDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, OrgDepartmentDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}