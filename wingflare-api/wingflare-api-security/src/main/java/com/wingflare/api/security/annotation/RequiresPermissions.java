package com.wingflare.api.security.annotation;


import com.wingflare.api.security.enums.Logical;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author naizui_ycx
 * @date {2021/12/18}
 * @description 必须具有指定权限
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface RequiresPermissions {

    /**
     * 需要校验的权限码
     */
    String[] value();

    /**
     * 验证模式：AND | OR，默认AND
     */
    Logical logical() default Logical.AND;

}
