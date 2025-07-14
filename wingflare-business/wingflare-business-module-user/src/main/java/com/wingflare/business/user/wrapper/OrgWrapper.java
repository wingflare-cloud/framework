package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.OrgDO;
import com.wingflare.facade.module.user.bo.OrgSearchBO;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 组织机构Wrapper
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
public class OrgWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<OrgDO> getQueryWrapper(OrgSearchBO bo) {
        QueryWrapper<OrgDO> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq("org_id", bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne("org_id", bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, "org_id", bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, "org_id", bo.getNotin_orgId());
        }
        // parent_org_id 开始
        if (bo.getEq_parentOrgId() != null) {
            queryWrapper.eq("parent_org_id", bo.getEq_parentOrgId());
        }
        if (bo.getNeq_parentOrgId() != null) {
            queryWrapper.ne("parent_org_id", bo.getNeq_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentOrgId())) {
            queryWrapper.like("parent_org_id", bo.getLike_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentOrgId())) {
            queryWrapper.likeRight("parent_org_id", bo.getLiker_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentOrgId())) {
            queryWrapper.likeLeft("parent_org_id", bo.getLikel_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentOrgId())) {
            queryWrapper.notLike("parent_org_id", bo.getNotlike_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentOrgId())) {
            WrapperUtil.in(queryWrapper, "parent_org_id", bo.getIn_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentOrgId())) {
            WrapperUtil.notIn(queryWrapper, "parent_org_id", bo.getNotin_parentOrgId());
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
        // org_type 开始
        if (bo.getEq_orgType() != null) {
            queryWrapper.eq("org_type", bo.getEq_orgType());
        }
        if (bo.getNeq_orgType() != null) {
            queryWrapper.ne("org_type", bo.getNeq_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgType())) {
            queryWrapper.like("org_type", bo.getLike_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgType())) {
            queryWrapper.likeRight("org_type", bo.getLiker_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgType())) {
            queryWrapper.likeLeft("org_type", bo.getLikel_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgType())) {
            queryWrapper.notLike("org_type", bo.getNotlike_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgType())) {
            WrapperUtil.in(queryWrapper, "org_type", bo.getIn_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgType())) {
            WrapperUtil.notIn(queryWrapper, "org_type", bo.getNotin_orgType());
        }
        // org_name 开始
        if (bo.getEq_orgName() != null) {
            queryWrapper.eq("org_name", bo.getEq_orgName());
        }
        if (bo.getNeq_orgName() != null) {
            queryWrapper.ne("org_name", bo.getNeq_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgName())) {
            queryWrapper.like("org_name", bo.getLike_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgName())) {
            queryWrapper.likeRight("org_name", bo.getLiker_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgName())) {
            queryWrapper.likeLeft("org_name", bo.getLikel_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgName())) {
            queryWrapper.notLike("org_name", bo.getNotlike_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgName())) {
            WrapperUtil.in(queryWrapper, "org_name", bo.getIn_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgName())) {
            WrapperUtil.notIn(queryWrapper, "org_name", bo.getNotin_orgName());
        }
        // org_phone 开始
        if (bo.getEq_orgPhone() != null) {
            queryWrapper.eq("org_phone", bo.getEq_orgPhone());
        }
        if (bo.getNeq_orgPhone() != null) {
            queryWrapper.ne("org_phone", bo.getNeq_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgPhone())) {
            queryWrapper.like("org_phone", bo.getLike_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgPhone())) {
            queryWrapper.likeRight("org_phone", bo.getLiker_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgPhone())) {
            queryWrapper.likeLeft("org_phone", bo.getLikel_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgPhone())) {
            queryWrapper.notLike("org_phone", bo.getNotlike_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgPhone())) {
            WrapperUtil.in(queryWrapper, "org_phone", bo.getIn_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgPhone())) {
            WrapperUtil.notIn(queryWrapper, "org_phone", bo.getNotin_orgPhone());
        }
        // org_address 开始
        if (bo.getEq_orgAddress() != null) {
            queryWrapper.eq("org_address", bo.getEq_orgAddress());
        }
        if (bo.getNeq_orgAddress() != null) {
            queryWrapper.ne("org_address", bo.getNeq_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgAddress())) {
            queryWrapper.like("org_address", bo.getLike_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgAddress())) {
            queryWrapper.likeRight("org_address", bo.getLiker_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgAddress())) {
            queryWrapper.likeLeft("org_address", bo.getLikel_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgAddress())) {
            queryWrapper.notLike("org_address", bo.getNotlike_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgAddress())) {
            WrapperUtil.in(queryWrapper, "org_address", bo.getIn_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgAddress())) {
            WrapperUtil.notIn(queryWrapper, "org_address", bo.getNotin_orgAddress());
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
	public static LambdaQueryWrapper<OrgDO> getLambdaQueryWrapper(OrgSearchBO bo) {
        LambdaQueryWrapper<OrgDO> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, OrgDO::getIsDelete, bo.getDataScope());

        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(OrgDO::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(OrgDO::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgId, bo.getNotin_orgId());
        }
        // parent_org_id 开始
        if (bo.getEq_parentOrgId() != null) {
            queryWrapper.eq(OrgDO::getParentOrgId, bo.getEq_parentOrgId());
        }
        if (bo.getNeq_parentOrgId() != null) {
            queryWrapper.ne(OrgDO::getParentOrgId, bo.getNeq_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentOrgId())) {
            queryWrapper.like(OrgDO::getParentOrgId, bo.getLike_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentOrgId())) {
            queryWrapper.likeRight(OrgDO::getParentOrgId, bo.getLiker_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentOrgId())) {
            queryWrapper.likeLeft(OrgDO::getParentOrgId, bo.getLikel_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentOrgId())) {
            queryWrapper.notLike(OrgDO::getParentOrgId, bo.getNotlike_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentOrgId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getParentOrgId, bo.getIn_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentOrgId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getParentOrgId, bo.getNotin_parentOrgId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(OrgDO::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(OrgDO::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(OrgDO::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(OrgDO::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(OrgDO::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(OrgDO::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, OrgDO::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, OrgDO::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, OrgDO::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getState, bo.getNotin_state());
        }
        // org_type 开始
        if (bo.getEq_orgType() != null) {
            queryWrapper.eq(OrgDO::getOrgType, bo.getEq_orgType());
        }
        if (bo.getNeq_orgType() != null) {
            queryWrapper.ne(OrgDO::getOrgType, bo.getNeq_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgType())) {
            queryWrapper.like(OrgDO::getOrgType, bo.getLike_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgType())) {
            queryWrapper.likeRight(OrgDO::getOrgType, bo.getLiker_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgType())) {
            queryWrapper.likeLeft(OrgDO::getOrgType, bo.getLikel_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgType())) {
            queryWrapper.notLike(OrgDO::getOrgType, bo.getNotlike_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgType())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgType, bo.getIn_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgType())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgType, bo.getNotin_orgType());
        }
        // org_name 开始
        if (bo.getEq_orgName() != null) {
            queryWrapper.eq(OrgDO::getOrgName, bo.getEq_orgName());
        }
        if (bo.getNeq_orgName() != null) {
            queryWrapper.ne(OrgDO::getOrgName, bo.getNeq_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgName())) {
            queryWrapper.like(OrgDO::getOrgName, bo.getLike_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgName())) {
            queryWrapper.likeRight(OrgDO::getOrgName, bo.getLiker_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgName())) {
            queryWrapper.likeLeft(OrgDO::getOrgName, bo.getLikel_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgName())) {
            queryWrapper.notLike(OrgDO::getOrgName, bo.getNotlike_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgName())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgName, bo.getIn_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgName())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgName, bo.getNotin_orgName());
        }
        // org_phone 开始
        if (bo.getEq_orgPhone() != null) {
            queryWrapper.eq(OrgDO::getOrgPhone, bo.getEq_orgPhone());
        }
        if (bo.getNeq_orgPhone() != null) {
            queryWrapper.ne(OrgDO::getOrgPhone, bo.getNeq_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgPhone())) {
            queryWrapper.like(OrgDO::getOrgPhone, bo.getLike_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgPhone())) {
            queryWrapper.likeRight(OrgDO::getOrgPhone, bo.getLiker_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgPhone())) {
            queryWrapper.likeLeft(OrgDO::getOrgPhone, bo.getLikel_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgPhone())) {
            queryWrapper.notLike(OrgDO::getOrgPhone, bo.getNotlike_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgPhone())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgPhone, bo.getIn_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgPhone())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgPhone, bo.getNotin_orgPhone());
        }
        // org_address 开始
        if (bo.getEq_orgAddress() != null) {
            queryWrapper.eq(OrgDO::getOrgAddress, bo.getEq_orgAddress());
        }
        if (bo.getNeq_orgAddress() != null) {
            queryWrapper.ne(OrgDO::getOrgAddress, bo.getNeq_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgAddress())) {
            queryWrapper.like(OrgDO::getOrgAddress, bo.getLike_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgAddress())) {
            queryWrapper.likeRight(OrgDO::getOrgAddress, bo.getLiker_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgAddress())) {
            queryWrapper.likeLeft(OrgDO::getOrgAddress, bo.getLikel_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgAddress())) {
            queryWrapper.notLike(OrgDO::getOrgAddress, bo.getNotlike_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgAddress())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgAddress, bo.getIn_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgAddress())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgAddress, bo.getNotin_orgAddress());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(OrgDO::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(OrgDO::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like(OrgDO::getRoleId, bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight(OrgDO::getRoleId, bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft(OrgDO::getRoleId, bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike(OrgDO::getRoleId, bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getRoleId, bo.getNotin_roleId());
        }
        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(OrgDO::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(OrgDO::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userId())) {
            queryWrapper.like(OrgDO::getUserId, bo.getLike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userId())) {
            queryWrapper.likeRight(OrgDO::getUserId, bo.getLiker_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userId())) {
            queryWrapper.likeLeft(OrgDO::getUserId, bo.getLikel_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userId())) {
            queryWrapper.notLike(OrgDO::getUserId, bo.getNotlike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getUserId, bo.getNotin_userId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(OrgDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(OrgDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(OrgDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(OrgDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(OrgDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(OrgDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, OrgDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, OrgDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, OrgDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(OrgDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(OrgDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(OrgDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(OrgDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(OrgDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(OrgDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, OrgDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, OrgDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, OrgDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(OrgDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(OrgDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(OrgDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(OrgDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(OrgDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(OrgDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, OrgDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(OrgDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(OrgDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(OrgDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(OrgDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(OrgDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(OrgDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(OrgDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(OrgDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(OrgDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(OrgDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(OrgDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(OrgDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, OrgDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(OrgDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(OrgDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(OrgDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(OrgDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(OrgDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(OrgDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<OrgDO> getJoinLambdaQueryWrapper(OrgSearchBO bo) {
        JoinLambdaQueryWrapper<OrgDO> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, OrgDO::getIsDelete, bo.getDataScope());

        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(OrgDO::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(OrgDO::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgId, bo.getNotin_orgId());
        }
        // parent_org_id 开始
        if (bo.getEq_parentOrgId() != null) {
            queryWrapper.eq(OrgDO::getParentOrgId, bo.getEq_parentOrgId());
        }
        if (bo.getNeq_parentOrgId() != null) {
            queryWrapper.ne(OrgDO::getParentOrgId, bo.getNeq_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentOrgId())) {
            queryWrapper.like(OrgDO::getParentOrgId, bo.getLike_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentOrgId())) {
            queryWrapper.likeRight(OrgDO::getParentOrgId, bo.getLiker_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentOrgId())) {
            queryWrapper.likeLeft(OrgDO::getParentOrgId, bo.getLikel_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentOrgId())) {
            queryWrapper.notLike(OrgDO::getParentOrgId, bo.getNotlike_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentOrgId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getParentOrgId, bo.getIn_parentOrgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentOrgId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getParentOrgId, bo.getNotin_parentOrgId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(OrgDO::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(OrgDO::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(OrgDO::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(OrgDO::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(OrgDO::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(OrgDO::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, OrgDO::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, OrgDO::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, OrgDO::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getState, bo.getNotin_state());
        }
        // org_type 开始
        if (bo.getEq_orgType() != null) {
            queryWrapper.eq(OrgDO::getOrgType, bo.getEq_orgType());
        }
        if (bo.getNeq_orgType() != null) {
            queryWrapper.ne(OrgDO::getOrgType, bo.getNeq_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgType())) {
            queryWrapper.like(OrgDO::getOrgType, bo.getLike_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgType())) {
            queryWrapper.likeRight(OrgDO::getOrgType, bo.getLiker_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgType())) {
            queryWrapper.likeLeft(OrgDO::getOrgType, bo.getLikel_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgType())) {
            queryWrapper.notLike(OrgDO::getOrgType, bo.getNotlike_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgType())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgType, bo.getIn_orgType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgType())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgType, bo.getNotin_orgType());
        }
        // org_name 开始
        if (bo.getEq_orgName() != null) {
            queryWrapper.eq(OrgDO::getOrgName, bo.getEq_orgName());
        }
        if (bo.getNeq_orgName() != null) {
            queryWrapper.ne(OrgDO::getOrgName, bo.getNeq_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgName())) {
            queryWrapper.like(OrgDO::getOrgName, bo.getLike_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgName())) {
            queryWrapper.likeRight(OrgDO::getOrgName, bo.getLiker_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgName())) {
            queryWrapper.likeLeft(OrgDO::getOrgName, bo.getLikel_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgName())) {
            queryWrapper.notLike(OrgDO::getOrgName, bo.getNotlike_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgName())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgName, bo.getIn_orgName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgName())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgName, bo.getNotin_orgName());
        }
        // org_phone 开始
        if (bo.getEq_orgPhone() != null) {
            queryWrapper.eq(OrgDO::getOrgPhone, bo.getEq_orgPhone());
        }
        if (bo.getNeq_orgPhone() != null) {
            queryWrapper.ne(OrgDO::getOrgPhone, bo.getNeq_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgPhone())) {
            queryWrapper.like(OrgDO::getOrgPhone, bo.getLike_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgPhone())) {
            queryWrapper.likeRight(OrgDO::getOrgPhone, bo.getLiker_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgPhone())) {
            queryWrapper.likeLeft(OrgDO::getOrgPhone, bo.getLikel_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgPhone())) {
            queryWrapper.notLike(OrgDO::getOrgPhone, bo.getNotlike_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgPhone())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgPhone, bo.getIn_orgPhone());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgPhone())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgPhone, bo.getNotin_orgPhone());
        }
        // org_address 开始
        if (bo.getEq_orgAddress() != null) {
            queryWrapper.eq(OrgDO::getOrgAddress, bo.getEq_orgAddress());
        }
        if (bo.getNeq_orgAddress() != null) {
            queryWrapper.ne(OrgDO::getOrgAddress, bo.getNeq_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgAddress())) {
            queryWrapper.like(OrgDO::getOrgAddress, bo.getLike_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgAddress())) {
            queryWrapper.likeRight(OrgDO::getOrgAddress, bo.getLiker_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgAddress())) {
            queryWrapper.likeLeft(OrgDO::getOrgAddress, bo.getLikel_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgAddress())) {
            queryWrapper.notLike(OrgDO::getOrgAddress, bo.getNotlike_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgAddress())) {
            WrapperUtil.in(queryWrapper, OrgDO::getOrgAddress, bo.getIn_orgAddress());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgAddress())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getOrgAddress, bo.getNotin_orgAddress());
        }
        // role_id 开始
        if (bo.getEq_roleId() != null) {
            queryWrapper.eq(OrgDO::getRoleId, bo.getEq_roleId());
        }
        if (bo.getNeq_roleId() != null) {
            queryWrapper.ne(OrgDO::getRoleId, bo.getNeq_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_roleId())) {
            queryWrapper.like(OrgDO::getRoleId, bo.getLike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_roleId())) {
            queryWrapper.likeRight(OrgDO::getRoleId, bo.getLiker_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_roleId())) {
            queryWrapper.likeLeft(OrgDO::getRoleId, bo.getLikel_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_roleId())) {
            queryWrapper.notLike(OrgDO::getRoleId, bo.getNotlike_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_roleId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getRoleId, bo.getIn_roleId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_roleId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getRoleId, bo.getNotin_roleId());
        }
        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(OrgDO::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(OrgDO::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userId())) {
            queryWrapper.like(OrgDO::getUserId, bo.getLike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userId())) {
            queryWrapper.likeRight(OrgDO::getUserId, bo.getLiker_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userId())) {
            queryWrapper.likeLeft(OrgDO::getUserId, bo.getLikel_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userId())) {
            queryWrapper.notLike(OrgDO::getUserId, bo.getNotlike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getUserId, bo.getNotin_userId());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(OrgDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(OrgDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(OrgDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(OrgDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(OrgDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(OrgDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, OrgDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, OrgDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, OrgDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(OrgDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(OrgDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(OrgDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(OrgDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(OrgDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(OrgDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, OrgDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, OrgDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, OrgDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(OrgDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(OrgDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(OrgDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(OrgDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(OrgDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(OrgDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, OrgDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(OrgDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(OrgDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(OrgDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(OrgDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(OrgDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(OrgDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(OrgDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(OrgDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(OrgDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(OrgDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(OrgDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(OrgDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, OrgDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(OrgDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(OrgDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(OrgDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(OrgDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(OrgDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(OrgDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, OrgDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, OrgDO::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}