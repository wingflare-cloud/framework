package com.wingflare.api.mvc.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@Target({ElementType.PARAMETER})
@Documented
public @interface RequestPart {

    String value() default "";

    String name() default "";

    boolean required() default true;

}
