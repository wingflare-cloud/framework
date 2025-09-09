package com.wingflare.adapter.spring.common.annotation;


import com.wingflare.adapter.spring.common.OnAnnotationPresentCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通用条件注解，当指定注解存在于启动类上时生效
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnAnnotationPresentCondition.class)
public @interface ConditionalOnAnnotationPresent {
    /**
     * 需要检查的注解类型
     */
    Class<? extends Annotation> value();
}