package com.wingflare.engine.task.client.common.annotation;

import com.wingflare.engine.task.common.core.enums.HeadersEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求头信息
 *
 * @author: opensnail
 * @date : 2023-06-19 16:02
 * @since 2.0.0
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Header {

    HeadersEnum name();
}
