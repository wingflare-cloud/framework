package com.wingflare.starter.security;


import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.standard.CacheService;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.PageResult;
import com.wingflare.lib.standard.model.ApplicationAuth;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.security.standard.SecurityCheckApplication;
import com.wingflare.lib.security.standard.SecurityCheckUser;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author naizui_ycx
 * @date {2023/01/11}
 * @description 微服务自动鉴权服务
 */
@Component
public class SecurityCheckServer implements SecurityCheckUser, SecurityCheckApplication {

    @Resource
    private CacheService cacheService;

    @Override
    public boolean hasPermission(
            String businessSystem,
            String key,
            ApplicationAuth app,
            String permissionCode,
            boolean isService
    ) {
        String lastKey;

        if (isService) {
            lastKey = String.format(
                    "%s:%s:ser:%s",
                    businessSystem, key, app.getAppId());
        } else {
            lastKey = String.format(
                    "%s:%s:%s",
                    businessSystem, key, app.getAppId());
        }

        return hasPermission(lastKey, permissionCode);
    }

    @Override
    public ApplicationAuth getApplicationByAppId(String appKey) {
        return cacheService.getCacheObject(appKey);
    }


    public boolean hasPermission(String key, String permissionCode) {
        return cacheService.isMember(key, permissionCode);
    }

    @Override
    public boolean hasPermission(String businessSystem, String key, UserAuth user, String permissionCode) {
        String lastKey = String.format(
                "%s:%s:%s",
                businessSystem, key, user.getUserId());
        return hasPermission(lastKey, permissionCode);
    }

    @Override
    public UserAuth getUserByTokenId(String tokenId) {
        return cacheService.getCacheObject(getTokenKey(tokenId));
    }

    @Override
    public void removeUser(UserAuth userAuth) {
        cacheService.deleteObject(getTokenMarkKey(userAuth.getUserId(), userAuth.getTokenId()));
        cacheService.deleteObject(getTokenKey(userAuth.getTokenId()));
    }

    @Override
    public void setUser(UserAuth userAuth, final Long timeout, final TimeUnit timeUnit) {
        cacheService.setCacheObject(getTokenMarkKey(userAuth.getUserId(), userAuth.getTokenId()),
                StringUtil.isNotEmpty(userAuth.getClientType()) ? userAuth.getClientType() : "", timeout, timeUnit);
        cacheService.setCacheObject(getTokenKey(userAuth.getTokenId()), userAuth, timeout, timeUnit);
    }

    @Override
    public PageResult<UserAuth> getLoginUsers(long pageSize, long startIndex) {
        PageResult<String> keysPageResult = cacheService.scanKey(getTokenKeyPrefix(), pageSize, startIndex);
        PageResult<UserAuth> pageResult = null;

        if (!CollectionUtil.isEmpty(keysPageResult.getList())) {
            List<String> newKeys = keysPageResult.getList()
                    .stream()
                    .filter(StringUtil::isNotEmpty)
                    .collect(Collectors.toList());
            List<UserAuth> userAuths = cacheService.getCacheListObject(newKeys);
            pageResult = new PageResult<UserAuth>(keysPageResult.getNextCursorId(), userAuths.stream()
                    .filter(Objects::nonNull).collect(Collectors.toList()), keysPageResult.isHasNext());
        } else {
            pageResult = new PageResult<UserAuth>("", new ArrayList<>(), false);
        }

        return pageResult;
    }

    @Override
    public PageResult<UserAuth> getUserAllLoginInfo(BigInteger userId, long pageSize, long startIndex) {
        String markKeyPrefix = getTokenMarkKeyPrefix(userId);
        PageResult<String> keysPageResult = cacheService.scanKey(markKeyPrefix, pageSize, startIndex);
        PageResult<UserAuth> pageResult = null;

        if (!CollectionUtil.isEmpty(keysPageResult.getList())) {
            List<String> newKeys = keysPageResult.getList()
                    .stream()
                    .filter(StringUtil::isNotEmpty)
                    .map(s -> s.startsWith(markKeyPrefix) ? getTokenKey(s.substring(markKeyPrefix.length())) : s)
                    .collect(Collectors.toList());
            List<UserAuth> userAuths = cacheService.getCacheListObject(newKeys);
            pageResult = new PageResult<UserAuth>(keysPageResult.getNextCursorId(), userAuths.stream()
                    .filter(Objects::nonNull).collect(Collectors.toList()), keysPageResult.isHasNext());
        } else {
            pageResult = new PageResult<UserAuth>("", new ArrayList<>(), false);
        }

        return pageResult;
    }

    /**
     * 获取用户登录token存储key
     *
     * @param id
     * @return
     */
    public static String getTokenKey(String id) {
        return String.format("%s%s", getTokenKeyPrefix(), id);
    }

    public static String getTokenKeyPrefix() {
        return String.format("%s:%s:", Ctx.PREFIX_ACCESS_TOKEN, Ctx.PREFIX_ACCESS_TOKEN_ID);
    }

    /**
     * 获取用户登录token标识存储key
     *
     * @param id
     * @return
     */
    public static String getTokenMarkKey(BigInteger userId, String id) {
        return String.format("%s%s", getTokenMarkKeyPrefix(userId), id);
    }

    public static String getTokenMarkKeyPrefix(BigInteger userId) {
        return String.format("%s:%s:%s:", Ctx.PREFIX_ACCESS_TOKEN, Ctx.PREFIX_ACCESS_TOKEN_MARK, userId);
    }

}
