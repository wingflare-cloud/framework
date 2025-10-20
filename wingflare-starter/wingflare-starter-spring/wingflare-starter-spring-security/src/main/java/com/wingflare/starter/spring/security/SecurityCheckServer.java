package com.wingflare.starter.spring.security;


import com.wingflare.abstraction.security.SecurityCheckUser;
import com.wingflare.api.core.Ctx;
import com.wingflare.api.core.PageResult;
import com.wingflare.api.security.UserAuth;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.standard.CacheService;

import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author naizui_ycx
 * @date {2023/01/11}
 * @description 微服务自动鉴权服务
 */
public class SecurityCheckServer implements SecurityCheckUser {

    private final CacheService cacheService;

    public SecurityCheckServer(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    public boolean hasPermissionAnd(String key, String ... permissionCodes) {
        return cacheService.isMemberAnd(key, (Object[]) permissionCodes);
    }

    public boolean hasPermissionOr(String key, String ... permissionCodes) {
        return cacheService.isMemberOr(key, (Object[]) permissionCodes);
    }

    @Override
    public boolean hasPermissionAnd(String businessSystem, String key, UserAuth user, String ... permissionCodes) {
        String lastKey = String.format(
                "%s:%s:%s",
                businessSystem, key, user.getUserId());
        return hasPermissionAnd(lastKey, permissionCodes);
    }

    @Override
    public boolean hasPermissionOr(String businessSystem, String key, UserAuth user, String ... permissionCodes) {
        String lastKey = String.format(
                "%s:%s:%s",
                businessSystem, key, user.getUserId());
        return hasPermissionOr(lastKey, permissionCodes);
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
    public void setUser(UserAuth userAuth, Duration duration) {
        cacheService.setCacheObject(getTokenMarkKey(userAuth.getUserId(), userAuth.getTokenId()),
                StringUtil.isNotEmpty(userAuth.getClientType()) ? userAuth.getClientType() : "", duration);
        cacheService.setCacheObject(getTokenKey(userAuth.getTokenId()), userAuth, duration);
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
    public PageResult<UserAuth> getUserAllLoginInfo(String userId, long pageSize, long startIndex) {
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
    public static String getTokenMarkKey(String userId, String id) {
        return String.format("%s%s", getTokenMarkKeyPrefix(userId), id);
    }

    public static String getTokenMarkKeyPrefix(String userId) {
        return String.format("%s:%s:%s:", Ctx.PREFIX_ACCESS_TOKEN, Ctx.PREFIX_ACCESS_TOKEN_MARK, userId);
    }

}
