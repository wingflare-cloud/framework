package com.wingflare.business.auth.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.auth.db.LoginInfoDo;
import com.wingflare.facade.module.auth.bo.LoginInfoSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 登陆信息Wrapper
 *
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
public class LoginInfoWrapper
{
	/**
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<LoginInfoDo> getQueryWrapper(LoginInfoSearchBo bo) {
        QueryWrapper<LoginInfoDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // login_id 开始
        if (bo.getEq_loginId() != null) {
            queryWrapper.eq("login_id", bo.getEq_loginId());
        }
        if (bo.getNeq_loginId() != null) {
            queryWrapper.ne("login_id", bo.getNeq_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_loginId())) {
            WrapperUtil.in(queryWrapper, "login_id", bo.getIn_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_loginId())) {
            WrapperUtil.notIn(queryWrapper, "login_id", bo.getNotin_loginId());
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
        // identity_id 开始
        if (bo.getEq_identityId() != null) {
            queryWrapper.eq("identity_id", bo.getEq_identityId());
        }
        if (bo.getNeq_identityId() != null) {
            queryWrapper.ne("identity_id", bo.getNeq_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_identityId())) {
            queryWrapper.like("identity_id", bo.getLike_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_identityId())) {
            queryWrapper.likeRight("identity_id", bo.getLiker_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_identityId())) {
            queryWrapper.likeLeft("identity_id", bo.getLikel_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_identityId())) {
            queryWrapper.notLike("identity_id", bo.getNotlike_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityId())) {
            WrapperUtil.in(queryWrapper, "identity_id", bo.getIn_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityId())) {
            WrapperUtil.notIn(queryWrapper, "identity_id", bo.getNotin_identityId());
        }
        // refresh_token 开始
        if (bo.getEq_refreshToken() != null) {
            queryWrapper.eq("refresh_token", bo.getEq_refreshToken());
        }
        if (bo.getNeq_refreshToken() != null) {
            queryWrapper.ne("refresh_token", bo.getNeq_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLike_refreshToken())) {
            queryWrapper.like("refresh_token", bo.getLike_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_refreshToken())) {
            queryWrapper.likeRight("refresh_token", bo.getLiker_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_refreshToken())) {
            queryWrapper.likeLeft("refresh_token", bo.getLikel_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_refreshToken())) {
            queryWrapper.notLike("refresh_token", bo.getNotlike_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getIn_refreshToken())) {
            WrapperUtil.in(queryWrapper, "refresh_token", bo.getIn_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_refreshToken())) {
            WrapperUtil.notIn(queryWrapper, "refresh_token", bo.getNotin_refreshToken());
        }
        // user_agent 开始
        if (bo.getEq_userAgent() != null) {
            queryWrapper.eq("user_agent", bo.getEq_userAgent());
        }
        if (bo.getNeq_userAgent() != null) {
            queryWrapper.ne("user_agent", bo.getNeq_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userAgent())) {
            queryWrapper.like("user_agent", bo.getLike_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userAgent())) {
            queryWrapper.likeRight("user_agent", bo.getLiker_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userAgent())) {
            queryWrapper.likeLeft("user_agent", bo.getLikel_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userAgent())) {
            queryWrapper.notLike("user_agent", bo.getNotlike_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userAgent())) {
            WrapperUtil.in(queryWrapper, "user_agent", bo.getIn_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userAgent())) {
            WrapperUtil.notIn(queryWrapper, "user_agent", bo.getNotin_userAgent());
        }
        // ipaddr 开始
        if (bo.getEq_ipaddr() != null) {
            queryWrapper.eq("ipaddr", bo.getEq_ipaddr());
        }
        if (bo.getNeq_ipaddr() != null) {
            queryWrapper.ne("ipaddr", bo.getNeq_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLike_ipaddr())) {
            queryWrapper.like("ipaddr", bo.getLike_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_ipaddr())) {
            queryWrapper.likeRight("ipaddr", bo.getLiker_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_ipaddr())) {
            queryWrapper.likeLeft("ipaddr", bo.getLikel_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_ipaddr())) {
            queryWrapper.notLike("ipaddr", bo.getNotlike_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getIn_ipaddr())) {
            WrapperUtil.in(queryWrapper, "ipaddr", bo.getIn_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_ipaddr())) {
            WrapperUtil.notIn(queryWrapper, "ipaddr", bo.getNotin_ipaddr());
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
	public static LambdaQueryWrapper<LoginInfoDo> getLambdaQueryWrapper(LoginInfoSearchBo bo) {
        LambdaQueryWrapper<LoginInfoDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }


        // login_id 开始
        if (bo.getEq_loginId() != null) {
            queryWrapper.eq(LoginInfoDo::getLoginId, bo.getEq_loginId());
        }
        if (bo.getNeq_loginId() != null) {
            queryWrapper.ne(LoginInfoDo::getLoginId, bo.getNeq_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_loginId())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getLoginId, bo.getIn_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_loginId())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getLoginId, bo.getNotin_loginId());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(LoginInfoDo::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(LoginInfoDo::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(LoginInfoDo::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(LoginInfoDo::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(LoginInfoDo::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(LoginInfoDo::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getSystemCode, bo.getNotin_systemCode());
        }
        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(LoginInfoDo::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(LoginInfoDo::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userId())) {
            queryWrapper.like(LoginInfoDo::getUserId, bo.getLike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userId())) {
            queryWrapper.likeRight(LoginInfoDo::getUserId, bo.getLiker_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userId())) {
            queryWrapper.likeLeft(LoginInfoDo::getUserId, bo.getLikel_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userId())) {
            queryWrapper.notLike(LoginInfoDo::getUserId, bo.getNotlike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getUserId, bo.getNotin_userId());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(LoginInfoDo::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(LoginInfoDo::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like(LoginInfoDo::getOrgId, bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight(LoginInfoDo::getOrgId, bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft(LoginInfoDo::getOrgId, bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike(LoginInfoDo::getOrgId, bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getOrgId, bo.getNotin_orgId());
        }
        // identity_id 开始
        if (bo.getEq_identityId() != null) {
            queryWrapper.eq(LoginInfoDo::getIdentityId, bo.getEq_identityId());
        }
        if (bo.getNeq_identityId() != null) {
            queryWrapper.ne(LoginInfoDo::getIdentityId, bo.getNeq_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_identityId())) {
            queryWrapper.like(LoginInfoDo::getIdentityId, bo.getLike_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_identityId())) {
            queryWrapper.likeRight(LoginInfoDo::getIdentityId, bo.getLiker_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_identityId())) {
            queryWrapper.likeLeft(LoginInfoDo::getIdentityId, bo.getLikel_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_identityId())) {
            queryWrapper.notLike(LoginInfoDo::getIdentityId, bo.getNotlike_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityId())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getIdentityId, bo.getIn_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityId())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getIdentityId, bo.getNotin_identityId());
        }
        // refresh_token 开始
        if (bo.getEq_refreshToken() != null) {
            queryWrapper.eq(LoginInfoDo::getRefreshToken, bo.getEq_refreshToken());
        }
        if (bo.getNeq_refreshToken() != null) {
            queryWrapper.ne(LoginInfoDo::getRefreshToken, bo.getNeq_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLike_refreshToken())) {
            queryWrapper.like(LoginInfoDo::getRefreshToken, bo.getLike_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_refreshToken())) {
            queryWrapper.likeRight(LoginInfoDo::getRefreshToken, bo.getLiker_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_refreshToken())) {
            queryWrapper.likeLeft(LoginInfoDo::getRefreshToken, bo.getLikel_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_refreshToken())) {
            queryWrapper.notLike(LoginInfoDo::getRefreshToken, bo.getNotlike_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getIn_refreshToken())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getRefreshToken, bo.getIn_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_refreshToken())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getRefreshToken, bo.getNotin_refreshToken());
        }
        // user_agent 开始
        if (bo.getEq_userAgent() != null) {
            queryWrapper.eq(LoginInfoDo::getUserAgent, bo.getEq_userAgent());
        }
        if (bo.getNeq_userAgent() != null) {
            queryWrapper.ne(LoginInfoDo::getUserAgent, bo.getNeq_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userAgent())) {
            queryWrapper.like(LoginInfoDo::getUserAgent, bo.getLike_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userAgent())) {
            queryWrapper.likeRight(LoginInfoDo::getUserAgent, bo.getLiker_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userAgent())) {
            queryWrapper.likeLeft(LoginInfoDo::getUserAgent, bo.getLikel_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userAgent())) {
            queryWrapper.notLike(LoginInfoDo::getUserAgent, bo.getNotlike_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userAgent())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getUserAgent, bo.getIn_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userAgent())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getUserAgent, bo.getNotin_userAgent());
        }
        // ipaddr 开始
        if (bo.getEq_ipaddr() != null) {
            queryWrapper.eq(LoginInfoDo::getIpaddr, bo.getEq_ipaddr());
        }
        if (bo.getNeq_ipaddr() != null) {
            queryWrapper.ne(LoginInfoDo::getIpaddr, bo.getNeq_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLike_ipaddr())) {
            queryWrapper.like(LoginInfoDo::getIpaddr, bo.getLike_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_ipaddr())) {
            queryWrapper.likeRight(LoginInfoDo::getIpaddr, bo.getLiker_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_ipaddr())) {
            queryWrapper.likeLeft(LoginInfoDo::getIpaddr, bo.getLikel_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_ipaddr())) {
            queryWrapper.notLike(LoginInfoDo::getIpaddr, bo.getNotlike_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getIn_ipaddr())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getIpaddr, bo.getIn_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_ipaddr())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getIpaddr, bo.getNotin_ipaddr());
        }
        // expire_time 开始
        if (bo.getEq_expireTime() != null) {
            queryWrapper.eq(LoginInfoDo::getExpireTime, bo.getEq_expireTime());
        }
        if (bo.getNeq_expireTime() != null) {
            queryWrapper.ne(LoginInfoDo::getExpireTime, bo.getNeq_expireTime());
        }
        if (bo.getGt_expireTime() != null) {
            queryWrapper.gt(LoginInfoDo::getExpireTime, bo.getGt_expireTime());
        }
        if (bo.getLt_expireTime() != null) {
            queryWrapper.lt(LoginInfoDo::getExpireTime, bo.getLt_expireTime());
        }
        if (bo.getEgt_expireTime() != null) {
            queryWrapper.ge(LoginInfoDo::getExpireTime, bo.getEgt_expireTime());
        }
        if (bo.getElt_expireTime() != null) {
            queryWrapper.le(LoginInfoDo::getExpireTime, bo.getElt_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_expireTime())) {
            WrapperUtil.between(queryWrapper, LoginInfoDo::getExpireTime, bo.getBetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_expireTime())) {
            WrapperUtil.notBetween(queryWrapper, LoginInfoDo::getExpireTime, bo.getNotbetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_expireTime())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getExpireTime, bo.getIn_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_expireTime())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getExpireTime, bo.getNotin_expireTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(LoginInfoDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(LoginInfoDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(LoginInfoDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(LoginInfoDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(LoginInfoDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(LoginInfoDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, LoginInfoDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, LoginInfoDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getCreatedTime, bo.getNotin_createdTime());
        }

        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<LoginInfoDo> getJoinLambdaQueryWrapper(LoginInfoSearchBo bo) {
        JoinLambdaQueryWrapper<LoginInfoDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }


        // login_id 开始
        if (bo.getEq_loginId() != null) {
            queryWrapper.eq(LoginInfoDo::getLoginId, bo.getEq_loginId());
        }
        if (bo.getNeq_loginId() != null) {
            queryWrapper.ne(LoginInfoDo::getLoginId, bo.getNeq_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_loginId())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getLoginId, bo.getIn_loginId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_loginId())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getLoginId, bo.getNotin_loginId());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(LoginInfoDo::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(LoginInfoDo::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(LoginInfoDo::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(LoginInfoDo::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(LoginInfoDo::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(LoginInfoDo::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getSystemCode, bo.getNotin_systemCode());
        }
        // user_id 开始
        if (bo.getEq_userId() != null) {
            queryWrapper.eq(LoginInfoDo::getUserId, bo.getEq_userId());
        }
        if (bo.getNeq_userId() != null) {
            queryWrapper.ne(LoginInfoDo::getUserId, bo.getNeq_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userId())) {
            queryWrapper.like(LoginInfoDo::getUserId, bo.getLike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userId())) {
            queryWrapper.likeRight(LoginInfoDo::getUserId, bo.getLiker_userId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userId())) {
            queryWrapper.likeLeft(LoginInfoDo::getUserId, bo.getLikel_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userId())) {
            queryWrapper.notLike(LoginInfoDo::getUserId, bo.getNotlike_userId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userId())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getUserId, bo.getIn_userId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userId())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getUserId, bo.getNotin_userId());
        }
        // org_id 开始
        if (bo.getEq_orgId() != null) {
            queryWrapper.eq(LoginInfoDo::getOrgId, bo.getEq_orgId());
        }
        if (bo.getNeq_orgId() != null) {
            queryWrapper.ne(LoginInfoDo::getOrgId, bo.getNeq_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_orgId())) {
            queryWrapper.like(LoginInfoDo::getOrgId, bo.getLike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_orgId())) {
            queryWrapper.likeRight(LoginInfoDo::getOrgId, bo.getLiker_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_orgId())) {
            queryWrapper.likeLeft(LoginInfoDo::getOrgId, bo.getLikel_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_orgId())) {
            queryWrapper.notLike(LoginInfoDo::getOrgId, bo.getNotlike_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_orgId())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getOrgId, bo.getIn_orgId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_orgId())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getOrgId, bo.getNotin_orgId());
        }
        // identity_id 开始
        if (bo.getEq_identityId() != null) {
            queryWrapper.eq(LoginInfoDo::getIdentityId, bo.getEq_identityId());
        }
        if (bo.getNeq_identityId() != null) {
            queryWrapper.ne(LoginInfoDo::getIdentityId, bo.getNeq_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_identityId())) {
            queryWrapper.like(LoginInfoDo::getIdentityId, bo.getLike_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_identityId())) {
            queryWrapper.likeRight(LoginInfoDo::getIdentityId, bo.getLiker_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_identityId())) {
            queryWrapper.likeLeft(LoginInfoDo::getIdentityId, bo.getLikel_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_identityId())) {
            queryWrapper.notLike(LoginInfoDo::getIdentityId, bo.getNotlike_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_identityId())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getIdentityId, bo.getIn_identityId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_identityId())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getIdentityId, bo.getNotin_identityId());
        }
        // refresh_token 开始
        if (bo.getEq_refreshToken() != null) {
            queryWrapper.eq(LoginInfoDo::getRefreshToken, bo.getEq_refreshToken());
        }
        if (bo.getNeq_refreshToken() != null) {
            queryWrapper.ne(LoginInfoDo::getRefreshToken, bo.getNeq_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLike_refreshToken())) {
            queryWrapper.like(LoginInfoDo::getRefreshToken, bo.getLike_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_refreshToken())) {
            queryWrapper.likeRight(LoginInfoDo::getRefreshToken, bo.getLiker_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_refreshToken())) {
            queryWrapper.likeLeft(LoginInfoDo::getRefreshToken, bo.getLikel_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_refreshToken())) {
            queryWrapper.notLike(LoginInfoDo::getRefreshToken, bo.getNotlike_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getIn_refreshToken())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getRefreshToken, bo.getIn_refreshToken());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_refreshToken())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getRefreshToken, bo.getNotin_refreshToken());
        }
        // user_agent 开始
        if (bo.getEq_userAgent() != null) {
            queryWrapper.eq(LoginInfoDo::getUserAgent, bo.getEq_userAgent());
        }
        if (bo.getNeq_userAgent() != null) {
            queryWrapper.ne(LoginInfoDo::getUserAgent, bo.getNeq_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLike_userAgent())) {
            queryWrapper.like(LoginInfoDo::getUserAgent, bo.getLike_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_userAgent())) {
            queryWrapper.likeRight(LoginInfoDo::getUserAgent, bo.getLiker_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_userAgent())) {
            queryWrapper.likeLeft(LoginInfoDo::getUserAgent, bo.getLikel_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_userAgent())) {
            queryWrapper.notLike(LoginInfoDo::getUserAgent, bo.getNotlike_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getIn_userAgent())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getUserAgent, bo.getIn_userAgent());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_userAgent())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getUserAgent, bo.getNotin_userAgent());
        }
        // ipaddr 开始
        if (bo.getEq_ipaddr() != null) {
            queryWrapper.eq(LoginInfoDo::getIpaddr, bo.getEq_ipaddr());
        }
        if (bo.getNeq_ipaddr() != null) {
            queryWrapper.ne(LoginInfoDo::getIpaddr, bo.getNeq_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLike_ipaddr())) {
            queryWrapper.like(LoginInfoDo::getIpaddr, bo.getLike_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_ipaddr())) {
            queryWrapper.likeRight(LoginInfoDo::getIpaddr, bo.getLiker_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_ipaddr())) {
            queryWrapper.likeLeft(LoginInfoDo::getIpaddr, bo.getLikel_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_ipaddr())) {
            queryWrapper.notLike(LoginInfoDo::getIpaddr, bo.getNotlike_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getIn_ipaddr())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getIpaddr, bo.getIn_ipaddr());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_ipaddr())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getIpaddr, bo.getNotin_ipaddr());
        }
        // expire_time 开始
        if (bo.getEq_expireTime() != null) {
            queryWrapper.eq(LoginInfoDo::getExpireTime, bo.getEq_expireTime());
        }
        if (bo.getNeq_expireTime() != null) {
            queryWrapper.ne(LoginInfoDo::getExpireTime, bo.getNeq_expireTime());
        }
        if (bo.getGt_expireTime() != null) {
            queryWrapper.gt(LoginInfoDo::getExpireTime, bo.getGt_expireTime());
        }
        if (bo.getLt_expireTime() != null) {
            queryWrapper.lt(LoginInfoDo::getExpireTime, bo.getLt_expireTime());
        }
        if (bo.getEgt_expireTime() != null) {
            queryWrapper.ge(LoginInfoDo::getExpireTime, bo.getEgt_expireTime());
        }
        if (bo.getElt_expireTime() != null) {
            queryWrapper.le(LoginInfoDo::getExpireTime, bo.getElt_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_expireTime())) {
            WrapperUtil.between(queryWrapper, LoginInfoDo::getExpireTime, bo.getBetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_expireTime())) {
            WrapperUtil.notBetween(queryWrapper, LoginInfoDo::getExpireTime, bo.getNotbetween_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_expireTime())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getExpireTime, bo.getIn_expireTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_expireTime())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getExpireTime, bo.getNotin_expireTime());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(LoginInfoDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(LoginInfoDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(LoginInfoDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(LoginInfoDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(LoginInfoDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(LoginInfoDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, LoginInfoDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, LoginInfoDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, LoginInfoDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, LoginInfoDo::getCreatedTime, bo.getNotin_createdTime());
        }

        return queryWrapper;
    }

}