package com.wingflare.business.auth.biz;


import com.wingflare.business.auth.ErrorCode;
import com.wingflare.business.auth.SettingCode;
import com.wingflare.facade.module.auth.biz.LoginBiz;
import com.wingflare.facade.module.auth.bo.GetLoginUsersBo;
import com.wingflare.facade.module.auth.bo.LoginBo;
import com.wingflare.facade.module.auth.bo.RefreshTokenBo;
import com.wingflare.facade.module.auth.constants.AuthEventName;
import com.wingflare.facade.module.auth.dto.TokenDto;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.bo.UserBo;
import com.wingflare.facade.module.user.dto.UserDto;
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
import com.wingflare.lib.standard.*;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.security.utils.UserAuthUtil;
import com.wingflare.lib.spring.utils.SnowflakeUtil;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.enums.OnOffEnum;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
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
    private UserAuthUtil userAuthUtil;

    @Resource
    private EventUtil eventUtil;

    /**
     * 获取token过期时间
     *
     * @return
     */
    public Long getTokenExpireTime() {
        return settingUtil.get(SettingCode.TOKEN_EXPIRE_TIME, 15L, Long.class);
    }

    public Long getMaxRefreshTokenExpireTime() {
        return settingUtil.get(SettingCode.MAX_REFRESH_TOKEN_EXPIRE_TIME, 0L, Long.class);
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
        Long maxRefreshTokenExpireTime = getMaxRefreshTokenExpireTime();

        if (maxRefreshTokenExpireTime > 0L) {
            Assert.isFalse(bo.getExpireTime() > maxRefreshTokenExpireTime, ErrorCode.USER_LOGIN_EXPIRE_TIME_OVER_LIMIT);
        }

        UserAuth userAuth = Builder.of(UserAuth::new)
                .with(UserAuth::setUserId, userDto.getUserId())
                .with(UserAuth::setUserName, userDto.getUserName())
                .with(UserAuth::setSuperAdmin, OnOffEnum.ON.getValue().equals(userDto.getSuperAdministrator()))
                .with(UserAuth::setLoginTime, now.getTime())
                .with(UserAuth::setExpireTime, DateUtil.rollSecond(now, bo.getExpireTime().intValue()).getTime())
                .with(UserAuth::setTokenExpireTime, DateUtil.rollSecond(now, Math.toIntExact(tokenExpireTime)).getTime())
                .with(UserAuth::setCurrentOrg, bo.getOrgId())
                .with(UserAuth::setIpAddress, bo.getIpaddr())
                .with(UserAuth::setClientType, bo.getClientType())
                .build();

        Map<String, Object> userAttr = userBiz.getAttribute(new IdBo().setId(userDto.getUserId()));
        List<String> orgList = null;
        List<String> identities = null;

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

        userAuth.setIdentity(bo.getIdentityId());
        userAuth.setUserAgent(bo.getUserAgent());

        String tokenId = snowflakeUtil.nextStringId();
        String refreshId = snowflakeUtil.nextStringId();

        userAuth.setRefreshId(refreshId);
        userAuth.setTokenId(tokenId);

        TokenDto tokenDto = Builder.of(TokenDto::new)
                .with(TokenDto::setExpiresIn, tokenExpireTime.intValue())
                .with(TokenDto::setRefreshExpiresIn, bo.getExpireTime().intValue())
                .with(TokenDto::setToken, tokenGen(tokenId, now))
                .with(TokenDto::setRefreshToken, tokenGen(refreshId, now))
                .build();

        userAuthUtil.setUser(userAuth, (long) DateUtil.getOffsetSeconds(now, new Date(userAuth.getExpireTime())), TimeUnit.SECONDS);

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
    public UserAuth getUserLoginInfo(@Valid @NotNull IdBo bo) {
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
    public PageResult<UserAuth> getLoginUsers(@Valid @NotNull GetLoginUsersBo bo) {
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
    public PageResult<UserAuth> getUserLoginInfos(@Valid @NotNull GetLoginUsersBo bo) {
        return userAuthUtil.getLoginUsers(bo.getPageSize(), bo.getStartIndex());
    }

    /**
     * 刷新token
     *
     * @param bo
     * @return
     */
    @Override
    public TokenDto refreshToken(@Valid @NotNull RefreshTokenBo bo) {
        String oldRefreshId = getRefreshIdByRefreshToken(bo.getRefreshToken());

        UserAuth userAuth = userAuthUtil.getUser();

        Assert.isTrue(userAuth != null, ErrorCode.LOGIN_INFO_NOTFOUND_OR_EXPIRE);
        Assert.isTrue(StringUtil.equals(userAuth.getRefreshId(), oldRefreshId), ErrorCode.REFRESH_TOKEN_DEFEATED);

        String refreshId = snowflakeUtil.nextStringId();
        Long tokenExpireTime = getTokenExpireTime();
        Date now = new Date();

        String tokenId = snowflakeUtil.nextStringId();
        String token = tokenGen(tokenId, now);

        userAuthUtil.removeToken(userAuth.getTokenId());
        userAuth.setRefreshId(refreshId);
        userAuth.setTokenId(tokenId);
        userAuth.setTokenExpireTime(DateUtil.rollSecond(now, Math.toIntExact(tokenExpireTime)).getTime());
        userAuthUtil.setUser(userAuth, (long) DateUtil.getOffsetSeconds(now, new Date(userAuth.getExpireTime())), TimeUnit.MINUTES);

        return Builder.of(TokenDto::new)
                .with(TokenDto::setExpiresIn, tokenExpireTime.intValue())
                .with(TokenDto::setRefreshExpiresIn, DateUtil.getOffsetSeconds(now, new Date(userAuth.getExpireTime())))
                .with(TokenDto::setToken, token)
                .with(TokenDto::setRefreshToken, tokenGen(refreshId, now))
                .build();
    }


    private String getRefreshIdByRefreshToken(String refreshToken) {
        Map<String, Object> claimsMap = jwtUtil.parseToken(refreshToken);

        if (!SecurityUtil.checkTokenClaimsMap(claimsMap, secret)) {
            throw new BusinessLogicException(ErrorCode.REFRESH_TOKEN_EXCEPTION);
        }

        return claimsMap.get(Ctx.HEADER_KEY_TOKEN_ID)
                .toString();
    }


    private String tokenGen(String id, Date date) {
        return jwtUtil.createToken(SecurityUtil.getClaimsMap(id, date, secret));
    }

}
