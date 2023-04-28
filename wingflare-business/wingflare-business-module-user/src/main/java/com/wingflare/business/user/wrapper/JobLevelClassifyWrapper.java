package com.wingflare.business.user.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.business.user.db.JobLevelClassifyDo;
import com.wingflare.facade.module.user.bo.JobLevelClassifySearchBo;

/**
 * 职级分类表 Wrapper
 *
 * @author naizui_ycx
 * @date ${datetime}
 */
public class JobLevelClassifyWrapper
{

	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<JobLevelClassifyDo> getQueryWrapper(JobLevelClassifySearchBo bo) {
        QueryWrapper<JobLevelClassifyDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

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
        // classify_name 开始
        if (bo.getEq_classifyName() != null) {
            queryWrapper.eq("classify_name", bo.getEq_classifyName());
        }
        if (bo.getNeq_classifyName() != null) {
            queryWrapper.ne("classify_name", bo.getNeq_classifyName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_classifyName())) {
            queryWrapper.like("classify_name", bo.getLike_classifyName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_classifyName())) {
            queryWrapper.likeRight("classify_name", bo.getLiker_classifyName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_classifyName())) {
            queryWrapper.likeLeft("classify_name", bo.getLikel_classifyName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_classifyName())) {
            queryWrapper.notLike("classify_name", bo.getNotlike_classifyName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_classifyName())) {
            WrapperUtil.in(queryWrapper, "classify_name", bo.getIn_classifyName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_classifyName())) {
            WrapperUtil.notIn(queryWrapper, "classify_name", bo.getNotin_classifyName());
        }
        // classify_code 开始
        if (bo.getEq_classifyCode() != null) {
            queryWrapper.eq("classify_code", bo.getEq_classifyCode());
        }
        if (bo.getNeq_classifyCode() != null) {
            queryWrapper.ne("classify_code", bo.getNeq_classifyCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_classifyCode())) {
            queryWrapper.like("classify_code", bo.getLike_classifyCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_classifyCode())) {
            queryWrapper.likeRight("classify_code", bo.getLiker_classifyCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_classifyCode())) {
            queryWrapper.likeLeft("classify_code", bo.getLikel_classifyCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_classifyCode())) {
            queryWrapper.notLike("classify_code", bo.getNotlike_classifyCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_classifyCode())) {
            WrapperUtil.in(queryWrapper, "classify_code", bo.getIn_classifyCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_classifyCode())) {
            WrapperUtil.notIn(queryWrapper, "classify_code", bo.getNotin_classifyCode());
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
	public static LambdaQueryWrapper<JobLevelClassifyDo> getLambdaQueryWrapper(JobLevelClassifySearchBo bo) {
        LambdaQueryWrapper<JobLevelClassifyDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, JobLevelClassifyDo::getIsDelete, bo.getDataScope());


        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<JobLevelClassifyDo> getJoinLambdaQueryWrapper(JobLevelClassifySearchBo bo) {
        JoinLambdaQueryWrapper<JobLevelClassifyDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, JobLevelClassifyDo::getIsDelete, bo.getDataScope());


        return queryWrapper;
    }

}