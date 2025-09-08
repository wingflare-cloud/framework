package com.wingflare.api.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 * @date 2023/04/16
 * @description 数据解密
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

    String type();

}
