package com.wingflare.adapter.spring.common.interceptor;


import com.wingflare.api.core.annotation.Validated;
import jakarta.validation.ConstraintViolationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.lang.reflect.Method;
import java.util.Set;


@Aspect
public class ValiInterceptor {

    private final Validator validator;

    // 注入JSR-303校验器（Hibernate Validator实现）
    public ValiInterceptor(Validator validator) {
        this.validator = validator;
    }

    // 拦截所有标注@Vali的方法或类中的方法
    @Around("@annotation(com.wingflare.api.core.annotation.Validated) || @within(com.wingflare.api.core.annotation.Validated)")
    public Object validate(ProceedingJoinPoint joinPoint, Validated validated) throws Throwable {
        // 获取方法信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();

        // 处理类级别注解的情况
        if (validated == null) {
            validated = AnnotationUtils.findAnnotation(method.getDeclaringClass(), Validated.class);
        }

        // 执行参数校验
        Set<ConstraintViolation<Object>> violations = validateParameters(
                joinPoint.getTarget(), method, args, validated.value()
        );

        // 校验失败则抛出异常
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("参数校验失败", violations);
        }

        // 校验通过，执行原方法
        return joinPoint.proceed();
    }

    // 调用校验器执行参数校验
    private <T> Set<ConstraintViolation<T>> validateParameters(
            T target, Method method, Object[] parameterValues, Class<?>[] groups) {
        return validator.forExecutables()
                .validateParameters(target, method, parameterValues, groups);
    }

}
