package com.wingflare.lib.security.annotation;


import com.wingflare.lib.standard.enums.AuthType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author naizui_ycx
 * @date {2023/01/14}
 * @description 认证模式 APP | USER
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface AuthMode {

    AuthType value() default AuthType.USER;

}
