package com.wingflare.api.security.annotation;


import com.wingflare.api.security.enums.SecretType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 保密数据接口
 * @author naizui_ycx
 * @email chenxi@qq.com
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Secret {

    SecretType type() default SecretType.ALL;

}
