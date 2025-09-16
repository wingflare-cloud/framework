package com.wingflare.business.auth.biz;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wingflare.api.core.PageResult;
import com.wingflare.api.validation.annotation.Validated;
import com.wingflare.api.core.enums.OnOffEnum;
import com.wingflare.api.core.validate.MustUserId;
import com.wingflare.api.event.EventPublisher;
import com.wingflare.api.idgenerate.IdGenerate;
import com.wingflare.api.security.UserAuth;
import com.wingflare.api.security.UserAuthServer;
import com.wingflare.api.security.annotation.Desensitize;
import com.wingflare.api.security.annotation.DesensitizeGroups;
import com.wingflare.api.security.enums.SensitiveType;
import com.wingflare.business.auth.ErrorCode;
import com.wingflare.business.auth.SettingCode;
import com.wingflare.facade.module.auth.biz.LoginBiz;
import com.wingflare.facade.module.auth.bo.GetLoginUsersBO;
import com.wingflare.facade.module.auth.bo.LoginBO;
import com.wingflare.facade.module.auth.bo.RefreshTokenBO;
import com.wingflare.facade.module.auth.dto.TokenDTO;
import com.wingflare.facade.module.auth.event.UserLoginEvent;
import com.wingflare.facade.module.auth.event.UserLogoutEvent;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.DateUtil;
import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.jwt.AuthTool;
import com.wingflare.lib.standard.SettingUtil;
import com.wingflare.lib.standard.bo.StringIdBo;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.utils.SecurityUtil;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

import java.math.BigInteger;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author naizui_ycx
 * @date 2023/03/10
 * @email chenxi2511@qq.com
 * @description 登陆业务
 */
@Validated
public class LoginBizImpl implements LoginBiz {

    private final UserBiz userBiz;

    private final SettingUtil settingUtil;

    private final IdGenerate idGenerate;

    private final AuthTool authTool;

    private final UserAuthServer userAuthServer;

    private final EventPublisher eventPublisher;


    public LoginBizImpl(UserBiz userBiz, SettingUtil settingUtil, IdGenerate idGenerate, AuthTool authTool,
                        UserAuthServer userAuthServer, EventPublisher eventPublisher) {
        this.userBiz = userBiz;
        this.settingUtil = settingUtil;
        this.idGenerate = idGenerate;
        this.authTool = authTool;
        this.userAuthServer = userAuthServer;
        this.eventPublisher = eventPublisher;
    }

    /**
     * 获取token过期时间
     *
     * @return
     */
    public Long getTokenExpireTime() {
        return settingUtil.get(SettingCode.TOKEN_EXPIRE_TIME, 300L, Long.class);
    }

    public Long getMaxRefreshTokenExpireTime() {
        return settingUtil.get(SettingCode.MAX_REFRESH_TOKEN_EXPIRE_TIME, 1800L, Long.class);
    }

    /**
     * 登入
     *
     * @param bo
     * @return
     */
    @Override
    public TokenDTO login(@Valid @NotNull LoginBO bo) {
        Assert.isTrue(userAuthServer.getUser() == null, ErrorCode.AUTH_REPEAT_LOGIN);
        UserDTO userDto = userBiz.getUserByLoginName(bo.getLoginName());
        Assert.isTrue(userDto != null, ErrorCode.USER_LOGIN_ABNORMAL);
        Assert.isTrue(
                userAuthServer.matchesPassword(bo.getPasswd(), userDto.getUserPasswd()),
                ErrorCode.USER_LOGIN_ERROR
        );
        Assert.isTrue(OnOffEnum.ON.getValue().equals(userDto.getBanState()), ErrorCode.USER_BAN);

        Date now = new Date();

        UserAuth userAuth = Builder.of(UserAuth::new)
                .with(UserAuth::setUserId, userDto.getUserId())
                .with(UserAuth::setUserName, userDto.getUserName())
                .with(UserAuth::setClientId, SecurityUtil.getClientId())
                .with(UserAuth::setSuperAdmin, OnOffEnum.ON.getValue().equals(userDto.getSuperAdministrator()))
                .with(UserAuth::setLoginTime, now.getTime())
                .with(UserAuth::setCurrentOrg, bo.getOrgId())
                .with(UserAuth::setIpAddress, bo.getIpaddr())
                .with(UserAuth::setClientType, bo.getClientType())
                .build();

        Map<String, Object> userAttr = userBiz.getAttribute(new IdBo().setId(userDto.getUserId()));
        List<BigInteger> orgList = null;
        List<BigInteger> identities = null;

        if (CollectionUtil.isNotEmpty(userAttr)) {
            userAuth.setAttribute(userAttr);

            if (userAttr.containsKey("roles") && userAttr.get("roles") instanceof List) {
                userAuth.setRoles(ObjectUtil.cast(userAttr.get("roles")));
            }

            if (userAttr.containsKey("org") && userAttr.get("org") instanceof List) {
                orgList = ObjectUtil.cast(userAttr.get("org"));
                userAuth.setOrg(orgList);
            }

            if (userAttr.containsKey("identities") && userAttr.get("identities") instanceof List) {
                identities = ObjectUtil.cast(userAttr.get("identities"));
                userAuth.setIdentities(identities);
            }
        }

        if ((CollectionUtil.isEmpty(orgList) && bo.getOrgId() != null)
                || (CollectionUtil.isNotEmpty(orgList) && bo.getOrgId() != null
                && !orgList.contains(bo.getOrgId()))) {
            throw new BusinessLogicException(ErrorCode.ORG_NO_PERMISSION);
        }

        if ((CollectionUtil.isEmpty(identities) && bo.getIdentityId() != null)
                || (CollectionUtil.isNotEmpty(identities) && bo.getIdentityId() != null
                && !identities.contains(bo.getIdentityId()))) {
            throw new BusinessLogicException(ErrorCode.ORG_NO_IDENTITY);
        }

        userAuth.setIdentity(bo.getIdentityId());
        userAuth.setUserAgent(bo.getUserAgent());

        String tokenId = String.valueOf(idGenerate.nextId());
        String refreshId = String.valueOf(idGenerate.nextId());

        userAuth.setRefreshId(refreshId);
        userAuth.setTokenId(tokenId);

        Long tokenExpireTime = getTokenExpireTime();
        Long maxRefreshTokenExpireTime = getMaxRefreshTokenExpireTime();
        Date tokenExpireDate = DateUtil.rollSecond(now, Math.toIntExact(tokenExpireTime));
        Date refreshTokenExpireDate = DateUtil.rollSecond(now, Math.toIntExact(maxRefreshTokenExpireTime));

        TokenDTO tokenDto = Builder.of(TokenDTO::new)
                .with(TokenDTO::setExpiresIn, tokenExpireTime.intValue())
                .with(TokenDTO::setRefreshExpiresIn, Math.toIntExact(maxRefreshTokenExpireTime))
                .with(TokenDTO::setToken, authTool.createLoginToken(tokenId, String.valueOf(userAuth.getUserId()),
                        tokenExpireDate, SecurityUtil.getBusinessSystem()))
                .with(TokenDTO::setRefreshToken, authTool.createRefreshToken(refreshId, tokenId, refreshTokenExpireDate))
                .build();

        userAuthServer.setUser(userAuth, Duration.ofSeconds(DateUtil.getOffsetSeconds(now, refreshTokenExpireDate)));

        userBiz.update(new UserBO()
                .setUserId(userDto.getUserId())
                .setLastLoginTime(now)
                .setLastLoginIp(bo.getIpaddr()));

        eventPublisher.publishEvent(new UserLoginEvent(userAuth));

        return tokenDto;
    }

    /**
     * 登出
     */
    @Override
    public UserAuth logout(@Valid @NotNull StringIdBo bo) {
        String tokenId = bo.getId();
        UserAuth userAuth = userAuthServer.removeToken(tokenId);

        if (userAuth != null) {
            eventPublisher.publishEvent(new UserLogoutEvent(userAuth));
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
    public UserAuth getUserLoginInfo(@Valid @NotNull StringIdBo bo) {
        return userAuthServer.getUser(bo.getId());
    }

    /**
     * 获取登录用户列表
     *
     * @param bo
     * @return
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.list[*].tokenId",
                            sensitiveType = SensitiveType.SECRET_RSA
                    ),
                    @Desensitize(
                            jsonPath = "$.list[*].refreshId"
                    )
            }
    )
    public PageResult<UserAuth> getLoginUsers(@Valid @NotNull GetLoginUsersBO bo) {
        return userAuthServer.getLoginUsers(bo.getPageSize(), bo.getStartIndex());
    }

    /**
     * 获取指定用户登录信息
     *
     * @param bo
     * @return
     */
    @Override
    @DesensitizeGroups(
            desensitizes = {
                    @Desensitize(
                            jsonPath = "$.list[*].tokenId",
                            sensitiveType = SensitiveType.SECRET_RSA
                    ),
                    @Desensitize(
                            jsonPath = "$.list[*].refreshId"
                    )
            }
    )
    @Validated({Default.class, MustUserId.class})
    public PageResult<UserAuth> getUserLoginInfos(@Valid @NotNull GetLoginUsersBO bo) {
        return userAuthServer.getLoginUsers(bo.getPageSize(), bo.getStartIndex());
    }

    /**
     * 刷新token
     *
     * @param bo
     * @return
     */
    @Override
    public TokenDTO refreshToken(@Valid @NotNull RefreshTokenBO bo) {
        UserAuth userAuth = userAuthServer.getUser();

        Assert.isTrue(userAuth != null, ErrorCode.LOGIN_INFO_NOTFOUND_OR_EXPIRE);

        try {
            authTool.verifyToken(bo.getRefreshToken(), userAuth.getRefreshId(), userAuth.getTokenId());
        } catch (JWTVerificationException e) {
            throw new BusinessLogicException(ErrorCode.REFRESH_TOKEN_EXCEPTION);
        }

        String refreshId = String.valueOf(idGenerate.nextId());
        Date now = new Date();
        Long tokenExpireTime = getTokenExpireTime();
        Long maxRefreshTokenExpireTime = getMaxRefreshTokenExpireTime();
        Date tokenExpireDate = DateUtil.rollSecond(now, Math.toIntExact(tokenExpireTime));
        Date refreshTokenExpireDate = DateUtil.rollSecond(now, Math.toIntExact(maxRefreshTokenExpireTime));

        String tokenId = String.valueOf(idGenerate.nextId());
        String token = authTool.createLoginToken(tokenId, String.valueOf(userAuth.getUserId()),
                tokenExpireDate, SecurityUtil.getBusinessSystem());

        userAuthServer.removeToken(userAuth.getTokenId());
        userAuth.setRefreshId(refreshId);
        userAuth.setTokenId(tokenId);
        userAuthServer.setUser(userAuth, Duration.ofSeconds(DateUtil.getOffsetSeconds(now, refreshTokenExpireDate)));

        return Builder.of(TokenDTO::new)
                .with(TokenDTO::setExpiresIn, tokenExpireTime.intValue())
                .with(TokenDTO::setRefreshExpiresIn, DateUtil.getOffsetSeconds(now, refreshTokenExpireDate))
                .with(TokenDTO::setToken, token)
                .with(TokenDTO::setRefreshToken, authTool.createRefreshToken(refreshId, tokenId, refreshTokenExpireDate))
                .build();
    }

}
