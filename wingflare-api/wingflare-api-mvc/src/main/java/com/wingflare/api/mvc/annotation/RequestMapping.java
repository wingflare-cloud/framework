package com.wingflare.api.mvc.annotation;

import com.wingflare.api.mvc.RequestMethod;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;


@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface RequestMapping {

    String name() default "";

    String[] value() default {};

    String[] path() default {};

    RequestMethod[] method() default {};

}
