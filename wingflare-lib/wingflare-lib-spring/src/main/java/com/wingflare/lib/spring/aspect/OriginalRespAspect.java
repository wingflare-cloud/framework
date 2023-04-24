package com.wingflare.lib.spring.aspect;


import com.wingflare.lib.spring.utils.ApiHelperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @ClassName OriginalRespAspect
 * @Author naizui_ycx
 * @Date 2022/12/12 12
 * @Description 接口数据原样返回切面
 */
@Aspect
@Component
public class OriginalRespAspect implements Ordered {

    @Around("@annotation(com.wingflare.lib.spring.annotation.OriginalResp)")
    public Object innerAround(ProceedingJoinPoint point) throws Throwable {
        ApiHelperUtil.setOriginalResp(true);
        return point.proceed();
    }

    @Override
    public int getOrder() {
        return 10;
    }

}
