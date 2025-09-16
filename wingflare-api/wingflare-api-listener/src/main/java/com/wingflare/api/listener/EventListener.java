package com.wingflare.api.listener;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface EventListener {

    Class<?>[] value() default {};

}
