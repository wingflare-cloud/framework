package com.wingflare.api.security.annotation;


import com.wingflare.api.security.enums.SensitiveType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AnnotationName Desensitize
 * @Author naizui_ycx
 * @Date 2023/02/02 02
 * @Description 数据脱敏
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Desensitize {

    String jsonPath();

    SensitiveType sensitiveType() default SensitiveType.NONE;

}
