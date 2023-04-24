package com.wingflare.lib.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AnnotationName PG
 * @Author naizui_ycx
 * @Date 2023/02/02 02
 * @Description 权限分组
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface PG {

    /**
     * 功能代码
     *
     * @return
     */
    String func();

    /**
     * 权限代码
     *
     * @return
     */
    RequiresPermissions permissions();

}
