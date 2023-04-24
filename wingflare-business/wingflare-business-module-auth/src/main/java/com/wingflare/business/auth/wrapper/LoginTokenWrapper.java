package com.wingflare.business.auth.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.auth.db.LoginTokenDo;
import com.wingflare.facade.module.auth.bo.LoginTokenSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 登陆tokenWrapper
 *
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
public class LoginTokenWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<LoginTokenDo> getQueryWrapper(LoginTokenSearchBo bo) {
        QueryWrapper<LoginTokenDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }


        // token_id 开始
        if (bo.getEq_tokenId() != null) {
            queryWrapper.eq("token_id", bo.getEq_tokenId());
        }
        if (bo.getNeq_tokenId() != null) {
            queryWrapper.ne("token_id", bo.getNeq_tokenId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_tokenId())) {
            WrapperUtil.in(queryWrapper, "token_id", bo.getIn_tokenId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_tokenId())) {
            WrapperUtil.notIn(queryWrapper, "token_id", bo.getNotin_tokenId());
        }
        // login_id 开始
        if (bo.getEq_loginId() != null) {
            queryWrapper.eq("login_id", bo.getEq_loginId());
        }
        if (bo.getNeq_loginId() != null) {
            queryWrapper.ne("login_id", bo.getNeq_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_loginId())) {
            queryWrapper.like("login_id", bo.getLike_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_loginId())) {
            queryWrapper.likeRight("login_id", bo.getLiker_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_loginId())) {
            queryWrapper.likeLeft("login_id", bo.getLikel_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_loginId())) {
            queryWrapper.notLike("login_id", bo.getNotlike_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_loginId())) {
            WrapperUtil.in(queryWrapper, "login_id", bo.getIn_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_loginId())) {
            WrapperUtil.notIn(queryWrapper, "login_id", bo.getNotin_loginId());
        }
        // token_key 开始
        if (bo.getEq_tokenKey() != null) {
            queryWrapper.eq("token_key", bo.getEq_tokenKey());
        }
        if (bo.getNeq_tokenKey() != null) {
            queryWrapper.ne("token_key", bo.getNeq_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLike_tokenKey())) {
            queryWrapper.like("token_key", bo.getLike_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_tokenKey())) {
            queryWrapper.likeRight("token_key", bo.getLiker_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_tokenKey())) {
            queryWrapper.likeLeft("token_key", bo.getLikel_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_tokenKey())) {
            queryWrapper.notLike("token_key", bo.getNotlike_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getIn_tokenKey())) {
            WrapperUtil.in(queryWrapper, "token_key", bo.getIn_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_tokenKey())) {
            WrapperUtil.notIn(queryWrapper, "token_key", bo.getNotin_tokenKey());
        }
        // expire_time 开始
        if (bo.getEq_expireTime() != null) {
            queryWrapper.eq("expire_time", bo.getEq_expireTime());
        }
        if (bo.getNeq_expireTime() != null) {
            queryWrapper.ne("expire_time", bo.getNeq_expireTime());
        }
        if (bo.getGt_expireTime() != null) {
            queryWrapper.gt("expire_time", bo.getGt_expireTime());
        }
        if (bo.getLt_expireTime() != null) {
            queryWrapper.lt("expire_time", bo.getLt_expireTime());
        }
        if (bo.getEgt_expireTime() != null) {
            queryWrapper.ge("expire_time", bo.getEgt_expireTime());
        }
        if (bo.getElt_expireTime() != null) {
            queryWrapper.le("expire_time", bo.getElt_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_expireTime())) {
            WrapperUtil.between(queryWrapper, "expire_time", bo.getBetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_expireTime())) {
            WrapperUtil.notBetween(queryWrapper, "expire_time", bo.getNotbetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_expireTime())) {
            WrapperUtil.in(queryWrapper, "expire_time", bo.getIn_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_expireTime())) {
            WrapperUtil.notIn(queryWrapper, "expire_time", bo.getNotin_expireTime());
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

        return queryWrapper;
    }
	
	/**
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<LoginTokenDo> getLambdaQueryWrapper(LoginTokenSearchBo bo) {
        LambdaQueryWrapper<LoginTokenDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }


        // token_id 开始
        if (bo.getEq_tokenId() != null) {
            queryWrapper.eq(LoginTokenDo::getTokenId, bo.getEq_tokenId());
        }
        if (bo.getNeq_tokenId() != null) {
            queryWrapper.ne(LoginTokenDo::getTokenId, bo.getNeq_tokenId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_tokenId())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getTokenId, bo.getIn_tokenId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_tokenId())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getTokenId, bo.getNotin_tokenId());
        }
        // login_id 开始
        if (bo.getEq_loginId() != null) {
            queryWrapper.eq(LoginTokenDo::getLoginId, bo.getEq_loginId());
        }
        if (bo.getNeq_loginId() != null) {
            queryWrapper.ne(LoginTokenDo::getLoginId, bo.getNeq_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_loginId())) {
            queryWrapper.like(LoginTokenDo::getLoginId, bo.getLike_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_loginId())) {
            queryWrapper.likeRight(LoginTokenDo::getLoginId, bo.getLiker_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_loginId())) {
            queryWrapper.likeLeft(LoginTokenDo::getLoginId, bo.getLikel_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_loginId())) {
            queryWrapper.notLike(LoginTokenDo::getLoginId, bo.getNotlike_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_loginId())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getLoginId, bo.getIn_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_loginId())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getLoginId, bo.getNotin_loginId());
        }
        // token_key 开始
        if (bo.getEq_tokenKey() != null) {
            queryWrapper.eq(LoginTokenDo::getTokenKey, bo.getEq_tokenKey());
        }
        if (bo.getNeq_tokenKey() != null) {
            queryWrapper.ne(LoginTokenDo::getTokenKey, bo.getNeq_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLike_tokenKey())) {
            queryWrapper.like(LoginTokenDo::getTokenKey, bo.getLike_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_tokenKey())) {
            queryWrapper.likeRight(LoginTokenDo::getTokenKey, bo.getLiker_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_tokenKey())) {
            queryWrapper.likeLeft(LoginTokenDo::getTokenKey, bo.getLikel_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_tokenKey())) {
            queryWrapper.notLike(LoginTokenDo::getTokenKey, bo.getNotlike_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getIn_tokenKey())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getTokenKey, bo.getIn_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_tokenKey())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getTokenKey, bo.getNotin_tokenKey());
        }
        // expire_time 开始
        if (bo.getEq_expireTime() != null) {
            queryWrapper.eq(LoginTokenDo::getExpireTime, bo.getEq_expireTime());
        }
        if (bo.getNeq_expireTime() != null) {
            queryWrapper.ne(LoginTokenDo::getExpireTime, bo.getNeq_expireTime());
        }
        if (bo.getGt_expireTime() != null) {
            queryWrapper.gt(LoginTokenDo::getExpireTime, bo.getGt_expireTime());
        }
        if (bo.getLt_expireTime() != null) {
            queryWrapper.lt(LoginTokenDo::getExpireTime, bo.getLt_expireTime());
        }
        if (bo.getEgt_expireTime() != null) {
            queryWrapper.ge(LoginTokenDo::getExpireTime, bo.getEgt_expireTime());
        }
        if (bo.getElt_expireTime() != null) {
            queryWrapper.le(LoginTokenDo::getExpireTime, bo.getElt_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_expireTime())) {
            WrapperUtil.between(queryWrapper, LoginTokenDo::getExpireTime, bo.getBetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_expireTime())) {
            WrapperUtil.notBetween(queryWrapper, LoginTokenDo::getExpireTime, bo.getNotbetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_expireTime())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getExpireTime, bo.getIn_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_expireTime())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getExpireTime, bo.getNotin_expireTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(LoginTokenDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(LoginTokenDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(LoginTokenDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(LoginTokenDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(LoginTokenDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(LoginTokenDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, LoginTokenDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, LoginTokenDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getCreatedTime, bo.getNotin_createdTime());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<LoginTokenDo> getJoinLambdaQueryWrapper(LoginTokenSearchBo bo) {
        JoinLambdaQueryWrapper<LoginTokenDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }


        // token_id 开始
        if (bo.getEq_tokenId() != null) {
            queryWrapper.eq(LoginTokenDo::getTokenId, bo.getEq_tokenId());
        }
        if (bo.getNeq_tokenId() != null) {
            queryWrapper.ne(LoginTokenDo::getTokenId, bo.getNeq_tokenId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_tokenId())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getTokenId, bo.getIn_tokenId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_tokenId())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getTokenId, bo.getNotin_tokenId());
        }
        // login_id 开始
        if (bo.getEq_loginId() != null) {
            queryWrapper.eq(LoginTokenDo::getLoginId, bo.getEq_loginId());
        }
        if (bo.getNeq_loginId() != null) {
            queryWrapper.ne(LoginTokenDo::getLoginId, bo.getNeq_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_loginId())) {
            queryWrapper.like(LoginTokenDo::getLoginId, bo.getLike_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_loginId())) {
            queryWrapper.likeRight(LoginTokenDo::getLoginId, bo.getLiker_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_loginId())) {
            queryWrapper.likeLeft(LoginTokenDo::getLoginId, bo.getLikel_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_loginId())) {
            queryWrapper.notLike(LoginTokenDo::getLoginId, bo.getNotlike_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_loginId())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getLoginId, bo.getIn_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_loginId())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getLoginId, bo.getNotin_loginId());
        }
        // token_key 开始
        if (bo.getEq_tokenKey() != null) {
            queryWrapper.eq(LoginTokenDo::getTokenKey, bo.getEq_tokenKey());
        }
        if (bo.getNeq_tokenKey() != null) {
            queryWrapper.ne(LoginTokenDo::getTokenKey, bo.getNeq_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLike_tokenKey())) {
            queryWrapper.like(LoginTokenDo::getTokenKey, bo.getLike_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_tokenKey())) {
            queryWrapper.likeRight(LoginTokenDo::getTokenKey, bo.getLiker_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_tokenKey())) {
            queryWrapper.likeLeft(LoginTokenDo::getTokenKey, bo.getLikel_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_tokenKey())) {
            queryWrapper.notLike(LoginTokenDo::getTokenKey, bo.getNotlike_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getIn_tokenKey())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getTokenKey, bo.getIn_tokenKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_tokenKey())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getTokenKey, bo.getNotin_tokenKey());
        }
        // expire_time 开始
        if (bo.getEq_expireTime() != null) {
            queryWrapper.eq(LoginTokenDo::getExpireTime, bo.getEq_expireTime());
        }
        if (bo.getNeq_expireTime() != null) {
            queryWrapper.ne(LoginTokenDo::getExpireTime, bo.getNeq_expireTime());
        }
        if (bo.getGt_expireTime() != null) {
            queryWrapper.gt(LoginTokenDo::getExpireTime, bo.getGt_expireTime());
        }
        if (bo.getLt_expireTime() != null) {
            queryWrapper.lt(LoginTokenDo::getExpireTime, bo.getLt_expireTime());
        }
        if (bo.getEgt_expireTime() != null) {
            queryWrapper.ge(LoginTokenDo::getExpireTime, bo.getEgt_expireTime());
        }
        if (bo.getElt_expireTime() != null) {
            queryWrapper.le(LoginTokenDo::getExpireTime, bo.getElt_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_expireTime())) {
            WrapperUtil.between(queryWrapper, LoginTokenDo::getExpireTime, bo.getBetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_expireTime())) {
            WrapperUtil.notBetween(queryWrapper, LoginTokenDo::getExpireTime, bo.getNotbetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_expireTime())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getExpireTime, bo.getIn_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_expireTime())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getExpireTime, bo.getNotin_expireTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(LoginTokenDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(LoginTokenDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(LoginTokenDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(LoginTokenDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(LoginTokenDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(LoginTokenDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, LoginTokenDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, LoginTokenDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, LoginTokenDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, LoginTokenDo::getCreatedTime, bo.getNotin_createdTime());
        }

        return queryWrapper;
    }

}