package com.wingflare.starter.security;


import com.wingflare.lib.standard.CacheService;
import com.wingflare.lib.standard.model.ApplicationAuth;
import com.wingflare.lib.standard.model.UserAuth;
import com.wingflare.lib.security.standard.SecurityCheckApplication;
import com.wingflare.lib.security.standard.SecurityCheckUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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
    public UserAuth getUserByTokenKey(String tokenKey) {
        return cacheService.getCacheObject(tokenKey);
    }

    @Override
    public void removeUserByTokenKey(String tokenKey) {
        cacheService.deleteObject(tokenKey);
    }

    @Override
    public void setUser(String tokenKey, UserAuth userAuth, final Long timeout, final TimeUnit timeUnit) {
        cacheService.setCacheObject(tokenKey, userAuth, timeout, timeUnit);
    }

}
