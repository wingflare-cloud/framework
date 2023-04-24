package com.wingflare.business.auth.biz;


import com.alibaba.fastjson.JSONObject;
import com.wingflare.business.auth.ErrorCode;
import com.wingflare.business.auth.db.LoginInfoDo;
import com.wingflare.business.auth.db.LoginTokenDo;
import com.wingflare.business.auth.service.LoginInfoServer;
import com.wingflare.business.auth.service.LoginTokenServer;
import com.wingflare.facade.module.auth.biz.LoginBiz;
import com.wingflare.facade.module.auth.bo.LoginBo;
import com.wingflare.facade.module.auth.bo.LoginTokenSearchBo;
import com.wingflare.facade.module.auth.bo.RefreshTokenBo;
import com.wingflare.facade.module.auth.constants.AuthEventName;
import com.wingflare.facade.module.auth.dto.TokenDto;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.bo.UserBo;
import com.wingflare.facade.module.user.dto.UserDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.DateUtil;
import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.jwt.utils.JwtUtil;
import com.wingflare.lib.standard.EventUtil;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.security.utils.UserAuthUtil;
import com.wingflare.lib.spring.utils.SnowflakeUtil;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.SettingUtil;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.enums.OnOffEnum;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author naizui_ycx
 * @date 2023/03/10
 * @email chenxi2511@qq.com
 * @description 登陆业务
 */
@Component
@Validated
public class LoginBizImpl implements LoginBiz {

    @Resource
    private UserBiz userBiz;

    @Resource
    private SettingUtil settingUtil;

    @Resource
    private SnowflakeUtil snowflakeUtil;

    @Resource
    private JwtUtil jwtUtil;

    @Value("${login.secret:" + Ctx.LOGIN_DEFAULT_SECRET + "}")
    private String secret;

    @Resource
    private LoginInfoServer loginInfoServer;

    @Resource
    private LoginTokenServer loginTokenServer;

    @Resource
    private UserAuthUtil userAuthUtil;

    @Resource
    private EventUtil eventUtil;

    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 获取token过期时间
     *
     * @return
     */
    public Long getTokenExpireTime() {
        return settingUtil.get("TOKEN_EXPIRE_TIME", 15L, Long.class);
    }

    public Long getRefreshTokenExpireTime() {
        return settingUtil.get("REFRESH_TOKEN_EXPIRE_TIME", 1440L, Long.class);
    }

    /**
     * 登入
     *
     * @param bo
     * @return
     */
    @Override
    public TokenDto login(@Valid @NotNull LoginBo bo) {
        Assert.isTrue(userAuthUtil.getUser() == null, ErrorCode.AUTH_REPEAT_LOGIN);
        UserDto userDto = userBiz.getUserByLoginName(bo.getLoginName());
        Assert.isTrue(userDto != null, ErrorCode.USER_LOGIN_ABNORMAL);
        Assert.isTrue(
                UserAuthUtil.matchesPassword(bo.getPasswd(), userDto.getUserPasswd()),
                ErrorCode.USER_LOGIN_ERROR
        );
        Assert.isTrue(OnOffEnum.ON.getValue().equals(userDto.getBanState()), ErrorCode.USER_BAN);

        Date now = new Date();
        Long tokenExpireTime = getTokenExpireTime();
        Long refreshTokenExpireTime = getRefreshTokenExpireTime();

        UserAuth userAuth = Builder.of(UserAuth::new)
                .with(UserAuth::setUserId, userDto.getUserId())
                .with(UserAuth::setUserName, userDto.getUserName())
                .with(UserAuth::setSuperAdmin, OnOffEnum.ON.getValue().equals(userDto.getSuperAdministrator()))
                .with(UserAuth::setLoginTime, now.getTime())
                .with(UserAuth::setExpireTime, tokenExpireTime)
                .with(UserAuth::setCurrentOrg, bo.getOrgId())
                .with(UserAuth::setIpAddress, bo.getIpaddr())
                .build();

        Map<String, Object> userAttr = userBiz.getAttribute(new IdBo().setId(userDto.getUserId()));
        List<String> orgList = null;
        List<String> identities = null;

        if (CollectionUtil.isNotEmpty(userAttr)) {
            userAuth.setAttribute(userAttr);

            if (userAttr.containsKey("roles") && userAttr.get("roles") instanceof List) {
                userAuth.setRoles(ObjectUtil.cast(userAttr.get("roles")));
                userAttr.remove("roles");
            }

            if (userAttr.containsKey("org") && userAttr.get("org") instanceof List) {
                orgList = ObjectUtil.cast(userAttr.get("org"));
                userAuth.setOrg(orgList);
                userAttr.remove("org");
            }

            if (userAttr.containsKey("identities") && userAttr.get("identities") instanceof List) {
                identities = ObjectUtil.cast(userAttr.get("identities"));
                userAuth.setIdentities(identities);
                userAttr.remove("identities");
            }
        }

        if ((CollectionUtil.isEmpty(orgList) && StringUtil.isNotEmpty(bo.getOrgId()))
                || (CollectionUtil.isNotEmpty(orgList) && StringUtil.isNotEmpty(bo.getOrgId())
                && !orgList.contains(bo.getOrgId()))) {
            throw new BusinessLogicException(ErrorCode.ORG_NO_PERMISSION);
        }

        if ((CollectionUtil.isEmpty(identities) && StringUtil.isNotEmpty(bo.getIdentityId()))
                || (CollectionUtil.isNotEmpty(identities) && StringUtil.isNotEmpty(bo.getIdentityId())
                && !identities.contains(bo.getIdentityId()))) {
            throw new BusinessLogicException(ErrorCode.ORG_NO_IDENTITY);
        }

        TokenDto tokenDto = transactionTemplate.execute(status -> {
            String loginId = snowflakeUtil.nextStringId();
            String tokenId = snowflakeUtil.nextStringId();
            LoginInfoDo loginInfoDo = Builder.of(LoginInfoDo::new)
                    .with(LoginInfoDo::setLoginId, loginId)
                    .with(LoginInfoDo::setSystemCode, bo.getSystemCode())
                    .with(LoginInfoDo::setUserId, userDto.getUserId())
                    .with(LoginInfoDo::setIdentityId, bo.getIdentityId())
                    .with(LoginInfoDo::setOrgId, bo.getOrgId())
                    .with(LoginInfoDo::setUserAgent, bo.getUserAgent())
                    .with(LoginInfoDo::setIpaddr, bo.getIpaddr())
                    .with(LoginInfoDo::setRefreshToken, refreshTokenGen(loginId, now))
                    .with(LoginInfoDo::setCreatedTime, now)
                    .with(LoginInfoDo::setExpireTime, DateUtil.rollMinute(now, refreshTokenExpireTime.intValue()))
                    .build();

            Assert.isTrue(loginInfoServer.save(loginInfoDo), ErrorCode.TOKEN_GENERATE_ERR);

            LoginTokenDo loginTokenDo = Builder.of(LoginTokenDo::new)
                    .with(LoginTokenDo::setTokenId, tokenId)
                    .with(LoginTokenDo::setLoginId, loginInfoDo.getLoginId())
                    .with(LoginTokenDo::setExpireTime, DateUtil.rollMinute(now, tokenExpireTime.intValue()))
                    .with(LoginTokenDo::setTokenKey, tokenGen(tokenId, now))
                    .build();

            Assert.isTrue(loginTokenServer.save(loginTokenDo), ErrorCode.TOKEN_GENERATE_WRONG);
            userAuthUtil.setUser(loginTokenDo.getTokenId(), userAuth, tokenExpireTime, TimeUnit.MINUTES);

            return Builder.of(TokenDto::new)
                    .with(TokenDto::setExpiresIn, tokenExpireTime.intValue())
                    .with(TokenDto::setRefreshExpiresIn, refreshTokenExpireTime.intValue())
                    .with(TokenDto::setToken, loginTokenDo.getTokenKey())
                    .with(TokenDto::setRefreshToken, loginInfoDo.getRefreshToken())
                    .build();
        });

        userBiz.update(new UserBo()
                .setUserId(userDto.getUserId())
                .setLastLoginTime(now)
                .setLastLoginIp(bo.getIpaddr()));

        eventUtil.publishEvent(AuthEventName.USER_LOGIN, false, userAuth);

        return tokenDto;
    }

    /**
     * 登出
     */
    @Override
    public UserAuth logout(@Valid @NotNull IdBo bo) {
        String tokenId = bo.getId();
        UserAuth userAuth = userAuthUtil.removeToken(tokenId);

        if (userAuth != null) {
            transactionTemplate.execute(status -> {
                LoginTokenDo tokenDo = loginTokenServer.getById(tokenId);

                if (tokenDo != null) {
                    loginInfoServer.removeById(tokenDo.getLoginId());
                }

                return loginTokenServer.removeById(tokenId);
            });

            eventUtil.publishEvent(AuthEventName.USER_LOGOUT, false, userAuth);
        }

        return userAuth;
    }

    /**
     * 获取登陆用户
     *
     * @param bo token id bo
     * @return
     */
    @Override
    public UserAuth getLoginUser(@Valid @NotNull IdBo bo) {
        return userAuthUtil.getUser(bo.getId());
    }

    /**
     * 刷新token
     *
     * @param bo
     * @return
     */
    @Override
    public TokenDto refreshToken(@Valid @NotNull RefreshTokenBo bo) {
        String loginId = getLoginIdByRefreshToken(bo.getRefreshToken());
        Long tokenExpireTime = getTokenExpireTime();
        Long refreshTokenExpireTime = getRefreshTokenExpireTime();
        Date now = new Date();
        String tokenId = snowflakeUtil.nextStringId();
        String token = tokenGen(tokenId, now);

        String oldTokenId = transactionTemplate.execute(status -> {
            LoginInfoDo loginInfoDo = loginInfoServer.getById(loginId);

            if (loginInfoDo == null || loginInfoDo.getExpireTime().before(now)) {
                throw new BusinessLogicException(ErrorCode.LOGIN_INFO_NOTFOUND_OR_EXPIRE);
            }

            if (!loginInfoDo.getUserId().equals(bo.getUserId())) {
                throw new BusinessLogicException(ErrorCode.REFRESH_TOKEN_DEFEATED);
            }

            LoginTokenDo oldLoginTokenDo = loginTokenServer.getLoginTokenOnlyOne(
                    new LoginTokenSearchBo().setEq_loginId(loginId));

            if (oldLoginTokenDo == null) {
                throw new BusinessLogicException(ErrorCode.REFRESH_TOKEN_DEFEATED);
            }

            LoginTokenDo loginTokenDo = Builder.of(LoginTokenDo::new)
                    .with(LoginTokenDo::setTokenId, tokenId)
                    .with(LoginTokenDo::setLoginId, loginInfoDo.getLoginId())
                    .with(LoginTokenDo::setExpireTime, DateUtil.rollMinute(now, tokenExpireTime.intValue()))
                    .with(LoginTokenDo::setTokenKey, token)
                    .build();

            Assert.isTrue(loginTokenServer.save(loginTokenDo), ErrorCode.TOKEN_GENERATE_WRONG);
            return oldLoginTokenDo.getTokenId();
        });

        UserAuth userAuth = userAuthUtil.getUser(oldTokenId);
        userAuthUtil.removeToken(oldTokenId);
        userAuthUtil.setUser(tokenId, userAuth, tokenExpireTime, TimeUnit.MINUTES);

        return Builder.of(TokenDto::new)
                .with(TokenDto::setExpiresIn, tokenExpireTime.intValue())
                .with(TokenDto::setRefreshExpiresIn, refreshTokenExpireTime.intValue())
                .with(TokenDto::setToken, token)
                .with(TokenDto::setRefreshToken, bo.getRefreshToken())
                .build();
    }


    private String refreshTokenGen(String id, Date date) {
        Map<String, Object> claimsMap = SecurityUtil.getClaimsMap(id, date, secret);
        String sign = SecurityUtil.claimsMapSign(claimsMap, secret);
        claimsMap.put(Ctx.AUTH_JSON_SIGN_KEY, sign);
        return Base64.encodeBase64String(JSONObject.toJSONBytes(claimsMap));
    }


    private String getLoginIdByRefreshToken(String refreshToken) {
        Map<String, Object> claimsMap = JSONObject.parseObject(Base64.decodeBase64(refreshToken), Map.class);

        if (!SecurityUtil.checkClaimsMapSign(claimsMap, secret)) {
            throw new BusinessLogicException(ErrorCode.REFRESH_TOKEN_EXCEPTION);
        }

        return claimsMap.get(Ctx.HEADER_KEY_TOKEN_ID)
                .toString();
    }

    private String tokenGen(String id, Date date) {
        return jwtUtil.createToken(SecurityUtil.getClaimsMap(id, date, secret));
    }

}
