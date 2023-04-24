package com.wingflare.lib.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author naizui_ycx
 * @annotationName HeaderPenetration
 * @email chenxi2511@qqq.com
 * @date 2023/04/23
 * @description 请求头穿透
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HeaderPenetration {
}
