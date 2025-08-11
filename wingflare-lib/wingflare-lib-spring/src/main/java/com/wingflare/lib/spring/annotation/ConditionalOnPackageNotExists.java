package com.wingflare.lib.spring.annotation;

import com.wingflare.lib.spring.PackageNotExistsCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(PackageNotExistsCondition.class)
public @interface ConditionalOnPackageNotExists {

    // 需要检查的包名
    String value();

}