package com.wingflare.business.auth.biz;


import com.wingflare.api.event.EventPublisher;
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
import com.wingflare.lib.config.ConfigUtil;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.enums.SensitiveType;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.DateUtil;
import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.core.validation.MustUserId;
import com.wingflare.lib.jwt.utils.JwtUtil;
import com.wingflare.lib.security.annotation.Desensitize;
import com.wingflare.lib.security.annotation.DesensitizeGroups;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.PageResult;
import com.wingflare.lib.standard.SettingUtil;
import com.wingflare.lib.standard.bo.StringIdBo;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.security.utils.UserAuthUtil;
import com.wingflare.lib.spring.utils.SnowflakeUtil;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.enums.OnOffEnum;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

import java.math.BigInteger;
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
@Validated
public class LoginBizImpl implements LoginBiz {

    private final UserBiz userBiz;

    private final SettingUtil settingUtil;

    private final SnowflakeUtil snowflakeUtil;

    private final JwtUtil jwtUtil;

    private final UserAuthUtil userAuthUtil;

    private final EventPublisher eventPublisher;


    public LoginBizImpl(UserBiz userBiz, SettingUtil settingUtil, SnowflakeUtil snowflakeUtil, JwtUtil jwtUtil,
                        UserAuthUtil userAuthUtil, EventPublisher eventPublisher) {
        this.userBiz = userBiz;
        this.settingUtil = settingUtil;
        this.snowflakeUtil = snowflakeUtil;
        this.jwtUtil = jwtUtil;
        this.userAuthUtil = userAuthUtil;
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
        Assert.isTrue(userAuthUtil.getUser() == null, ErrorCode.AUTH_REPEAT_LOGIN);
        UserDTO userDto = userBiz.getUserByLoginName(bo.getLoginName());
        Assert.isTrue(userDto != null, ErrorCode.USER_LOGIN_ABNORMAL);
        Assert.isTrue(
                UserAuthUtil.matchesPassword(bo.getPasswd(), userDto.getUserPasswd()),
                ErrorCode.USER_LOGIN_ERROR
        );
        Assert.isTrue(OnOffEnum.ON.getValue().equals(userDto.getBanState()), ErrorCode.USER_BAN);

        Date now = new Date();
        Long tokenExpireTime = getTokenExpireTime();
        Long maxRefreshTokenExpireTime = getMaxRefreshTokenExpireTime();

        UserAuth userAuth = Builder.of(UserAuth::new)
                .with(UserAuth::setUserId, userDto.getUserId())
                .with(UserAuth::setUserName, userDto.getUserName())
                .with(UserAuth::setClientId, SecurityUtil.getClientId())
                .with(UserAuth::setSuperAdmin, OnOffEnum.ON.getValue().equals(userDto.getSuperAdministrator()))
                .with(UserAuth::setLoginTime, now.getTime())
                .with(UserAuth::setExpireTime, DateUtil.rollSecond(now, Math.toIntExact(maxRefreshTokenExpireTime)).getTime())
                .with(UserAuth::setTokenExpireTime, DateUtil.rollSecond(now, Math.toIntExact(tokenExpireTime)).getTime())
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

        String tokenId = snowflakeUtil.nextStringId();
        String refreshId = snowflakeUtil.nextStringId();

        userAuth.setRefreshId(refreshId);
        userAuth.setTokenId(tokenId);

        TokenDTO tokenDto = Builder.of(TokenDTO::new)
                .with(TokenDTO::setExpiresIn, tokenExpireTime.intValue())
                .with(TokenDTO::setRefreshExpiresIn, Math.toIntExact(maxRefreshTokenExpireTime))
                .with(TokenDTO::setToken, tokenGen(SecurityUtil.getBusinessSystem(), tokenId, now))
                .with(TokenDTO::setRefreshToken, tokenGen(SecurityUtil.getBusinessSystem(), refreshId, now))
                .build();

        userAuthUtil.setUser(userAuth, (long) DateUtil.getOffsetSeconds(now, new Date(userAuth.getExpireTime())), TimeUnit.SECONDS);

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
        UserAuth userAuth = userAuthUtil.removeToken(tokenId);

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
        return userAuthUtil.getUser(bo.getId());
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
        return userAuthUtil.getLoginUsers(bo.getPageSize(), bo.getStartIndex());
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
        return userAuthUtil.getLoginUsers(bo.getPageSize(), bo.getStartIndex());
    }

    /**
     * 刷新token
     *
     * @param bo
     * @return
     */
    @Override
    public TokenDTO refreshToken(@Valid @NotNull RefreshTokenBO bo) {
        Map<String, Object> oldRefreshTokenMap = parseRefreshToken(bo.getRefreshToken());

        UserAuth userAuth = userAuthUtil.getUser();

        Assert.isTrue(userAuth != null, ErrorCode.LOGIN_INFO_NOTFOUND_OR_EXPIRE);
        Assert.isTrue(StringUtil.equals(userAuth.getRefreshId(), oldRefreshTokenMap.get(Ctx.HEADER_KEY_TOKEN_ID).toString()),
                ErrorCode.REFRESH_TOKEN_DEFEATED);

        String refreshId = snowflakeUtil.nextStringId();
        Long tokenExpireTime = getTokenExpireTime();
        Long maxRefreshTokenExpireTime = getMaxRefreshTokenExpireTime();
        Date now = new Date();
        String systemCode = oldRefreshTokenMap.get(Ctx.HEADER_KEY_BUSINESS_SYSTEM).toString();

        String tokenId = snowflakeUtil.nextStringId();
        String token = tokenGen(systemCode, tokenId, now);

        userAuthUtil.removeToken(userAuth.getTokenId());
        userAuth.setRefreshId(refreshId);
        userAuth.setTokenId(tokenId);
        userAuth.setTokenExpireTime(DateUtil.rollSecond(now, Math.toIntExact(tokenExpireTime)).getTime());
        userAuth.setExpireTime(DateUtil.rollSecond(now, Math.toIntExact(maxRefreshTokenExpireTime)).getTime());
        userAuthUtil.setUser(userAuth, (long) DateUtil.getOffsetSeconds(now, new Date(userAuth.getExpireTime())), TimeUnit.MINUTES);

        return Builder.of(TokenDTO::new)
                .with(TokenDTO::setExpiresIn, tokenExpireTime.intValue())
                .with(TokenDTO::setRefreshExpiresIn, DateUtil.getOffsetSeconds(now, new Date(userAuth.getExpireTime())))
                .with(TokenDTO::setToken, token)
                .with(TokenDTO::setRefreshToken, tokenGen(systemCode, refreshId, now))
                .build();
    }


    private Map<String, Object> parseRefreshToken(String refreshToken) {
        Map<String, Object> claimsMap = jwtUtil.parseToken(refreshToken);

        if (!SecurityUtil.checkTokenClaimsMap(claimsMap, ConfigUtil.getProperty("login.secret"))) {
            throw new BusinessLogicException(ErrorCode.REFRESH_TOKEN_EXCEPTION);
        }

        return claimsMap;
    }


    private String tokenGen(String systemCode, String id, Date date) {
        return jwtUtil.createToken(SecurityUtil.getClaimsMap(systemCode, id, date, ConfigUtil.getProperty("login.secret")));
    }

}
