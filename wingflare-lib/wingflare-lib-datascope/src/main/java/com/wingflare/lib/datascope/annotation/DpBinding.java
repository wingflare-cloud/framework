package com.wingflare.lib.datascope.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限注解子注解
 *
 * @author shaoyuyao
 * @date 2022/8/27
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface DpBinding {
    /**
     * 数据权限标识
     */
    String conditionName() default "";

    /**
     * Mapper全限定方法名
     */
    String mappedStatementId() default "";
}
