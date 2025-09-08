package com.wingflare.api.security.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AnnotationName EnableDataSensitive
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 开启数据脱敏
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableDataSensitive {
}
