package com.wingflare.lib.security.aspect;


import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.exceptions.NoPermissionException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.security.annotation.*;
import com.wingflare.lib.security.constants.SecurityErrorCode;
import com.wingflare.lib.security.utils.ApplicationAuthUtil;
import com.wingflare.lib.security.utils.AuthUtil;
import com.wingflare.lib.security.utils.UserAuthUtil;
import com.wingflare.lib.spring.annotation.InternalApi;
import com.wingflare.lib.spring.constants.Wf;
import com.wingflare.lib.spring.utils.ApiHelperUtil;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.standard.enums.AuthType;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * @author naizui_ycx
 * @date {2021/12/18}
 * @description 基于 Spring Aop 的注解鉴权
 */
@Aspect
@Component
@ConditionalOnBean({AuthUtil.class})
public class PreAuthorizeAspect implements Ordered {

    @Resource
    private AuthUtil authUtil;

    @Resource
    private ApplicationAuthUtil applicationAuthUtil;

    @Resource
    private UserAuthUtil userAuthUtil;

    private final Map<String, AuthMode> authModeCache = new HashMap<>();

    private final Map<String, InternalApi> internalApiCache = new HashMap<>();

    private final Map<String, RequiresLogin> requiresLoginCache = new HashMap<>();

    private final Map<String, RequiresPermissions> requiresPermissionsCache = new HashMap<>();

    private final Map<String, PermissionGroups> PermissionGroupsCache = new HashMap<>();

    private final Map<String, BusinessSystem> BusinessSystemCache = new HashMap<>();

    private final Logger logger = LoggerFactory.getLogger(PreAuthorizeAspect.class);

    /**
     * 定义AOP签名 (切入所有使用鉴权注解的方法)
     */
    public static final String POINTCUT_SIGN = "@annotation(com.wingflare.lib.security.annotation.RequiresLogin) || "
            + "@annotation(com.wingflare.lib.security.annotation.RequiresPermissions) || "
            + "@annotation(com.wingflare.lib.security.annotation.AuthMode) || "
            + "@annotation(com.wingflare.lib.security.annotation.BusinessSystem) || "
            + "@annotation(com.wingflare.lib.security.annotation.PermissionGroups)";

    /**
     * 声明AOP签名
     */
    @Pointcut(POINTCUT_SIGN)
    public void pointcut() {
    }

    /**
     * 环绕切入
     *
     * @param joinPoint 切面对象
     * @return 底层方法执行后的返回值
     * @throws Throwable 底层方法抛出的异常
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 注解鉴权
        checkMethodAnnotation(joinPoint.getSignature());
        try {
            // 执行原有逻辑
            Object obj = joinPoint.proceed();
            return obj;
        } catch (Throwable e) {
            throw e;
        }
    }

    /**
     * Method对象进行注解检查
     */
    public void checkMethodAnnotation(Signature signature) {
        // 获取认证模式
        AuthMode authMode = getAuthMode(signature);
        // 获取当前上下文中的功能标记代码
        String funcMark = ApiHelperUtil.getFuncMark();
        // 如果方法注解上指定了认证模式则使用方法上的鉴权模式，否则默认为用户鉴权模式
        if (authMode != null) {
            ContextHolder.set(Ctx.AUTH_MODE_CONTEXT, authMode.value());
        } else {
            ContextHolder.set(Ctx.AUTH_MODE_CONTEXT, AuthType.USER);
        }

        boolean ignore = false;
        InternalApi innerAuth = getInternalApi(signature);

        if (innerAuth != null) {
            // 内部请求验证
            if (ApiHelperUtil.checkSecuritySource()) {
                ignore = true;
            }
        }

        if (!ignore) {
            // 校验 @RequiresLogin 注解
            BusinessSystem businessSystem = getBusinessSystem(signature);

            if (businessSystem != null && CollectionUtil.isNotEmpty(businessSystem.value())) {
                if (!ArrayUtils.contains(businessSystem.value(), SecurityUtil.getBusinessSystem())) {
                    throw new NoPermissionException(SecurityErrorCode.AUTH_NOT_PERMISSION_SYSTEM);
                }
            }

            // 校验 @RequiresLogin 注解
            RequiresLogin requiresLogin = getRequiresLogin(signature);

            if (requiresLogin != null) {
                if (AuthType.USER.equals(SecurityUtil.getAuthMode())) {
                    userAuthUtil.checkUser();
                } else {
                    applicationAuthUtil.checkApp();
                }
            }

            RequiresPermissions requiresPermissions = null;
            PermissionGroups permissionGroups = getPermissionGroups(signature);

            if (permissionGroups != null) {
                List<PG> pgList = Arrays.asList(permissionGroups.value());
                Map<String, RequiresPermissions> permissionsMap = new HashMap<>();
                if (!pgList.isEmpty()) {
                    pgList.forEach(item -> permissionsMap.put(item.func(), item.permissions()));
                    requiresPermissions = permissionsMap.get(funcMark);
                }
            }

            if (requiresPermissions == null) {
                // 校验 @RequiresPermissions 注解
                requiresPermissions = getRequiresPermissions(signature);
            }

            if (requiresPermissions != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("接口权限", e(Map.of(
                            "permissionCode", requiresPermissions.value()
                    )));
                }
                authUtil.checkPermissions(requiresPermissions);
            }

            ContextHolder.set(Wf.PERMISSION_RESULT_CONTEXT_KEY, true);
        }
    }

    private AuthMode getAuthMode(Signature signature) {
        if (authModeCache.containsKey(signature.getName())) {
            return authModeCache.get(signature.getName());
        }

        AuthMode authMode;

        synchronized (authModeCache) {
            Method method = ((MethodSignature) signature).getMethod();
            authMode = method.getAnnotation(AuthMode.class);
            authModeCache.put(signature.getName(), authMode);
        }

        return authMode;
    }

    private InternalApi getInternalApi(Signature signature) {
        if (internalApiCache.containsKey(signature.getName())) {
            return internalApiCache.get(signature.getName());
        }

        InternalApi internalApi;

        synchronized (internalApiCache) {
            Method method = ((MethodSignature) signature).getMethod();
            internalApi = method.getAnnotation(InternalApi.class);
            internalApiCache.put(signature.getName(), internalApi);
        }

        return internalApi;
    }

    private BusinessSystem getBusinessSystem(Signature signature) {
        if (BusinessSystemCache.containsKey(signature.getName())) {
            return BusinessSystemCache.get(signature.getName());
        }

        BusinessSystem businessSystem;

        synchronized (BusinessSystemCache) {
            Method method = ((MethodSignature) signature).getMethod();
            businessSystem = method.getAnnotation(BusinessSystem.class);
            BusinessSystemCache.put(signature.getName(), businessSystem);
        }

        return businessSystem;
    }

    private RequiresLogin getRequiresLogin(Signature signature) {
        if (requiresLoginCache.containsKey(signature.getName())) {
            return requiresLoginCache.get(signature.getName());
        }

        RequiresLogin requiresLogin;

        synchronized (requiresLoginCache) {
            Method method = ((MethodSignature) signature).getMethod();
            requiresLogin = method.getAnnotation(RequiresLogin.class);
            requiresLoginCache.put(signature.getName(), requiresLogin);
        }

        return requiresLogin;
    }

    private RequiresPermissions getRequiresPermissions(Signature signature) {
        if (requiresPermissionsCache.containsKey(signature.getName())) {
            return requiresPermissionsCache.get(signature.getName());
        }

        RequiresPermissions requiresPermissions;

        synchronized (requiresPermissionsCache) {
            Method method = ((MethodSignature) signature).getMethod();
            requiresPermissions = method.getAnnotation(RequiresPermissions.class);
            requiresPermissionsCache.put(signature.getName(), requiresPermissions);
        }

        return requiresPermissions;
    }

    private PermissionGroups getPermissionGroups(Signature signature) {
        if (PermissionGroupsCache.containsKey(signature.getName())) {
            return PermissionGroupsCache.get(signature.getName());
        }

        PermissionGroups permissionGroups;

        synchronized (PermissionGroupsCache) {
            Method method = ((MethodSignature) signature).getMethod();
            permissionGroups = method.getAnnotation(PermissionGroups.class);
            PermissionGroupsCache.put(signature.getName(), permissionGroups);
        }

        return permissionGroups;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }
}
