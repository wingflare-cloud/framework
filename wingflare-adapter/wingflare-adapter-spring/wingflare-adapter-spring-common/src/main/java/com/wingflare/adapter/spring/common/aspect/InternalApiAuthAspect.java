package com.wingflare.adapter.spring.common.aspect;

import com.wingflare.adapter.spring.common.utils.ApiHelperUtil;
import com.wingflare.lib.core.exceptions.NoPermissionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @ClassName InternalApiAuthAspect
 * @Author naizui_ycx
 * @Date 2022/12/12 12
 * @Description 内外接口认证注解
 */
@Aspect
@Component
public class InternalApiAuthAspect implements Ordered {

    @Around("@annotation(com.wingflare.api.security.annotation.InternalApi)")
    public Object innerAround(ProceedingJoinPoint point) throws Throwable {
        // 内部请求验证
        if (!ApiHelperUtil.checkSecuritySource()) {
            throw new NoPermissionException();
        }

        return point.proceed();
    }

    @Override
    public int getOrder() {
        return  Ordered.HIGHEST_PRECEDENCE + 2;
    }

}
