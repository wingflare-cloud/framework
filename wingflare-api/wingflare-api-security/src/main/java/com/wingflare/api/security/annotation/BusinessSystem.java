package com.wingflare.api.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author naizui_ycx
 * @date {2025/06/21}
 * @description 仅允许特定系统访问
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface BusinessSystem {

    /**
     * 需要校验的权限码
     */
    String[] value();

}
