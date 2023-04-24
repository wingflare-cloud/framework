package com.wingflare.lib.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AnnotationName DesensitizeGroups
 * @Author naizui_ycx
 * @Date 2023/02/02 02
 * @Description 数据脱敏分组
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DesensitizeGroups {

    Desensitize[] desensitizes();

}
