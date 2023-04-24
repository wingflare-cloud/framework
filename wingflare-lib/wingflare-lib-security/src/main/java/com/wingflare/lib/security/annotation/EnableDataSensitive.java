package com.wingflare.lib.security.annotation;

import com.wingflare.lib.security.aspect.SensitiveDataAspect;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @AnnotationName EnableDataSensitive
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 开启数据脱敏
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        SensitiveDataAspect.class
})
public @interface EnableDataSensitive {
}
