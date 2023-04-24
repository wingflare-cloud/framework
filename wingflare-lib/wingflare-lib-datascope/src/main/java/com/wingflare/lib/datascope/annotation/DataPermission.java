package com.wingflare.lib.datascope.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限注解
 *
 * @author shaoyuyao
 * @date 2022/8/17 15:24
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataPermission {
    /**
     * 数据权限标识和Mapper全限定方法名绑定关系
     */
    DpBinding[] dpBindings() default {};

}