package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.JobLevelDo;
import com.wingflare.facade.module.user.bo.JobLevelSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 职级Wrapper
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
public class JobLevelWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<JobLevelDo> getQueryWrapper(JobLevelSearchBo bo) {
        QueryWrapper<JobLevelDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // job_level_id 开始
        if (bo.getEq_jobLevelId() != null) {
            queryWrapper.eq("job_level_id", bo.getEq_jobLevelId());
        }
        if (bo.getNeq_jobLevelId() != null) {
            queryWrapper.ne("job_level_id", bo.getNeq_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_jobLevelId())) {
            WrapperUtil.in(queryWrapper, "job_level_id", bo.getIn_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_jobLevelId())) {
            WrapperUtil.notIn(queryWrapper, "job_level_id", bo.getNotin_jobLevelId());
        }
        // level_name 开始
        if (bo.getEq_levelName() != null) {
            queryWrapper.eq("level_name", bo.getEq_levelName());
        }
        if (bo.getNeq_levelName() != null) {
            queryWrapper.ne("level_name", bo.getNeq_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_levelName())) {
            queryWrapper.like("level_name", bo.getLike_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_levelName())) {
            queryWrapper.likeRight("level_name", bo.getLiker_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_levelName())) {
            queryWrapper.likeLeft("level_name", bo.getLikel_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_levelName())) {
            queryWrapper.notLike("level_name", bo.getNotlike_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_levelName())) {
            WrapperUtil.in(queryWrapper, "level_name", bo.getIn_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_levelName())) {
            WrapperUtil.notIn(queryWrapper, "level_name", bo.getNotin_levelName());
        }
        // level 开始
        if (bo.getEq_level() != null) {
            queryWrapper.eq("level", bo.getEq_level());
        }
        if (bo.getNeq_level() != null) {
            queryWrapper.ne("level", bo.getNeq_level());
        }
        if (bo.getGt_level() != null) {
            queryWrapper.gt("level", bo.getGt_level());
        }
        if (bo.getLt_level() != null) {
            queryWrapper.lt("level", bo.getLt_level());
        }
        if (bo.getEgt_level() != null) {
            queryWrapper.ge("level", bo.getEgt_level());
        }
        if (bo.getElt_level() != null) {
            queryWrapper.le("level", bo.getElt_level());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_level())) {
            WrapperUtil.between(queryWrapper, "level", bo.getBetween_level());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_level())) {
            WrapperUtil.notBetween(queryWrapper, "level", bo.getNotbetween_level());
        }
        if (StringUtil.isNotEmpty(bo.getIn_level())) {
            WrapperUtil.in(queryWrapper, "level", bo.getIn_level());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_level())) {
            WrapperUtil.notIn(queryWrapper, "level", bo.getNotin_level());
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
	public static LambdaQueryWrapper<JobLevelDo> getLambdaQueryWrapper(JobLevelSearchBo bo) {
        LambdaQueryWrapper<JobLevelDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, JobLevelDo::getIsDelete, bo.getDataScope());

        // job_level_id 开始
        if (bo.getEq_jobLevelId() != null) {
            queryWrapper.eq(JobLevelDo::getJobLevelId, bo.getEq_jobLevelId());
        }
        if (bo.getNeq_jobLevelId() != null) {
            queryWrapper.ne(JobLevelDo::getJobLevelId, bo.getNeq_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_jobLevelId())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getJobLevelId, bo.getIn_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_jobLevelId())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getJobLevelId, bo.getNotin_jobLevelId());
        }
        // level_name 开始
        if (bo.getEq_levelName() != null) {
            queryWrapper.eq(JobLevelDo::getLevelName, bo.getEq_levelName());
        }
        if (bo.getNeq_levelName() != null) {
            queryWrapper.ne(JobLevelDo::getLevelName, bo.getNeq_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_levelName())) {
            queryWrapper.like(JobLevelDo::getLevelName, bo.getLike_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_levelName())) {
            queryWrapper.likeRight(JobLevelDo::getLevelName, bo.getLiker_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_levelName())) {
            queryWrapper.likeLeft(JobLevelDo::getLevelName, bo.getLikel_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_levelName())) {
            queryWrapper.notLike(JobLevelDo::getLevelName, bo.getNotlike_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_levelName())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getLevelName, bo.getIn_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_levelName())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getLevelName, bo.getNotin_levelName());
        }
        // level 开始
        if (bo.getEq_level() != null) {
            queryWrapper.eq(JobLevelDo::getLevel, bo.getEq_level());
        }
        if (bo.getNeq_level() != null) {
            queryWrapper.ne(JobLevelDo::getLevel, bo.getNeq_level());
        }
        if (bo.getGt_level() != null) {
            queryWrapper.gt(JobLevelDo::getLevel, bo.getGt_level());
        }
        if (bo.getLt_level() != null) {
            queryWrapper.lt(JobLevelDo::getLevel, bo.getLt_level());
        }
        if (bo.getEgt_level() != null) {
            queryWrapper.ge(JobLevelDo::getLevel, bo.getEgt_level());
        }
        if (bo.getElt_level() != null) {
            queryWrapper.le(JobLevelDo::getLevel, bo.getElt_level());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_level())) {
            WrapperUtil.between(queryWrapper, JobLevelDo::getLevel, bo.getBetween_level());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_level())) {
            WrapperUtil.notBetween(queryWrapper, JobLevelDo::getLevel, bo.getNotbetween_level());
        }
        if (StringUtil.isNotEmpty(bo.getIn_level())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getLevel, bo.getIn_level());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_level())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getLevel, bo.getNotin_level());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(JobLevelDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(JobLevelDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(JobLevelDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(JobLevelDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(JobLevelDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(JobLevelDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, JobLevelDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, JobLevelDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(JobLevelDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(JobLevelDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(JobLevelDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(JobLevelDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(JobLevelDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(JobLevelDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, JobLevelDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, JobLevelDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(JobLevelDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(JobLevelDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(JobLevelDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(JobLevelDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(JobLevelDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(JobLevelDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(JobLevelDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(JobLevelDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(JobLevelDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(JobLevelDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(JobLevelDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(JobLevelDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(JobLevelDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(JobLevelDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(JobLevelDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(JobLevelDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(JobLevelDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(JobLevelDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(JobLevelDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(JobLevelDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(JobLevelDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(JobLevelDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(JobLevelDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(JobLevelDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<JobLevelDo> getJoinLambdaQueryWrapper(JobLevelSearchBo bo) {
        JoinLambdaQueryWrapper<JobLevelDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, JobLevelDo::getIsDelete, bo.getDataScope());

        // job_level_id 开始
        if (bo.getEq_jobLevelId() != null) {
            queryWrapper.eq(JobLevelDo::getJobLevelId, bo.getEq_jobLevelId());
        }
        if (bo.getNeq_jobLevelId() != null) {
            queryWrapper.ne(JobLevelDo::getJobLevelId, bo.getNeq_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_jobLevelId())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getJobLevelId, bo.getIn_jobLevelId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_jobLevelId())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getJobLevelId, bo.getNotin_jobLevelId());
        }
        // level_name 开始
        if (bo.getEq_levelName() != null) {
            queryWrapper.eq(JobLevelDo::getLevelName, bo.getEq_levelName());
        }
        if (bo.getNeq_levelName() != null) {
            queryWrapper.ne(JobLevelDo::getLevelName, bo.getNeq_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_levelName())) {
            queryWrapper.like(JobLevelDo::getLevelName, bo.getLike_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_levelName())) {
            queryWrapper.likeRight(JobLevelDo::getLevelName, bo.getLiker_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_levelName())) {
            queryWrapper.likeLeft(JobLevelDo::getLevelName, bo.getLikel_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_levelName())) {
            queryWrapper.notLike(JobLevelDo::getLevelName, bo.getNotlike_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_levelName())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getLevelName, bo.getIn_levelName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_levelName())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getLevelName, bo.getNotin_levelName());
        }
        // level 开始
        if (bo.getEq_level() != null) {
            queryWrapper.eq(JobLevelDo::getLevel, bo.getEq_level());
        }
        if (bo.getNeq_level() != null) {
            queryWrapper.ne(JobLevelDo::getLevel, bo.getNeq_level());
        }
        if (bo.getGt_level() != null) {
            queryWrapper.gt(JobLevelDo::getLevel, bo.getGt_level());
        }
        if (bo.getLt_level() != null) {
            queryWrapper.lt(JobLevelDo::getLevel, bo.getLt_level());
        }
        if (bo.getEgt_level() != null) {
            queryWrapper.ge(JobLevelDo::getLevel, bo.getEgt_level());
        }
        if (bo.getElt_level() != null) {
            queryWrapper.le(JobLevelDo::getLevel, bo.getElt_level());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_level())) {
            WrapperUtil.between(queryWrapper, JobLevelDo::getLevel, bo.getBetween_level());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_level())) {
            WrapperUtil.notBetween(queryWrapper, JobLevelDo::getLevel, bo.getNotbetween_level());
        }
        if (StringUtil.isNotEmpty(bo.getIn_level())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getLevel, bo.getIn_level());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_level())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getLevel, bo.getNotin_level());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(JobLevelDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(JobLevelDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(JobLevelDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(JobLevelDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(JobLevelDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(JobLevelDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, JobLevelDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, JobLevelDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(JobLevelDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(JobLevelDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(JobLevelDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(JobLevelDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(JobLevelDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(JobLevelDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, JobLevelDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, JobLevelDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(JobLevelDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(JobLevelDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(JobLevelDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(JobLevelDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(JobLevelDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(JobLevelDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(JobLevelDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(JobLevelDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(JobLevelDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(JobLevelDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(JobLevelDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(JobLevelDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(JobLevelDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(JobLevelDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(JobLevelDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(JobLevelDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(JobLevelDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(JobLevelDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(JobLevelDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(JobLevelDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(JobLevelDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(JobLevelDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(JobLevelDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(JobLevelDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, JobLevelDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, JobLevelDo::getUpdateUserId, bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }

}