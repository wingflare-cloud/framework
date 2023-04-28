package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.business.user.db.JobLevelDo;
import com.wingflare.facade.module.user.bo.JobLevelSearchBo;

/**
 * 职级表 Wrapper
 *
 * @author naizui_ycx
 * @date ${datetime}
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
        // level_classify_id 开始
        if (bo.getEq_levelClassifyId() != null) {
            queryWrapper.eq("level_classify_id", bo.getEq_levelClassifyId());
        }
        if (bo.getNeq_levelClassifyId() != null) {
            queryWrapper.ne("level_classify_id", bo.getNeq_levelClassifyId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_levelClassifyId())) {
            queryWrapper.like("level_classify_id", bo.getLike_levelClassifyId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_levelClassifyId())) {
            queryWrapper.likeRight("level_classify_id", bo.getLiker_levelClassifyId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_levelClassifyId())) {
            queryWrapper.likeLeft("level_classify_id", bo.getLikel_levelClassifyId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_levelClassifyId())) {
            queryWrapper.notLike("level_classify_id", bo.getNotlike_levelClassifyId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_levelClassifyId())) {
            WrapperUtil.in(queryWrapper, "level_classify_id", bo.getIn_levelClassifyId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_levelClassifyId())) {
            WrapperUtil.notIn(queryWrapper, "level_classify_id", bo.getNotin_levelClassifyId());
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
        // root_level 开始
        if (bo.getEq_rootLevel() != null) {
            queryWrapper.eq("root_level", bo.getEq_rootLevel());
        }
        if (bo.getNeq_rootLevel() != null) {
            queryWrapper.ne("root_level", bo.getNeq_rootLevel());
        }
        if (bo.getGt_rootLevel() != null) {
            queryWrapper.gt("root_level", bo.getGt_rootLevel());
        }
        if (bo.getLt_rootLevel() != null) {
            queryWrapper.lt("root_level", bo.getLt_rootLevel());
        }
        if (bo.getEgt_rootLevel() != null) {
            queryWrapper.ge("root_level", bo.getEgt_rootLevel());
        }
        if (bo.getElt_rootLevel() != null) {
            queryWrapper.le("root_level", bo.getElt_rootLevel());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_rootLevel())) {
            WrapperUtil.between(queryWrapper, "root_level", bo.getBetween_rootLevel());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_rootLevel())) {
            WrapperUtil.notBetween(queryWrapper, "root_level", bo.getNotbetween_rootLevel());
        }
        if (StringUtil.isNotEmpty(bo.getIn_rootLevel())) {
            WrapperUtil.in(queryWrapper, "root_level", bo.getIn_rootLevel());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_rootLevel())) {
            WrapperUtil.notIn(queryWrapper, "root_level", bo.getNotin_rootLevel());
        }
        // classify_level 开始
        if (bo.getEq_classifyLevel() != null) {
            queryWrapper.eq("classify_level", bo.getEq_classifyLevel());
        }
        if (bo.getNeq_classifyLevel() != null) {
            queryWrapper.ne("classify_level", bo.getNeq_classifyLevel());
        }
        if (bo.getGt_classifyLevel() != null) {
            queryWrapper.gt("classify_level", bo.getGt_classifyLevel());
        }
        if (bo.getLt_classifyLevel() != null) {
            queryWrapper.lt("classify_level", bo.getLt_classifyLevel());
        }
        if (bo.getEgt_classifyLevel() != null) {
            queryWrapper.ge("classify_level", bo.getEgt_classifyLevel());
        }
        if (bo.getElt_classifyLevel() != null) {
            queryWrapper.le("classify_level", bo.getElt_classifyLevel());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_classifyLevel())) {
            WrapperUtil.between(queryWrapper, "classify_level", bo.getBetween_classifyLevel());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_classifyLevel())) {
            WrapperUtil.notBetween(queryWrapper, "classify_level", bo.getNotbetween_classifyLevel());
        }
        if (StringUtil.isNotEmpty(bo.getIn_classifyLevel())) {
            WrapperUtil.in(queryWrapper, "classify_level", bo.getIn_classifyLevel());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_classifyLevel())) {
            WrapperUtil.notIn(queryWrapper, "classify_level", bo.getNotin_classifyLevel());
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


        return queryWrapper;
    }

}