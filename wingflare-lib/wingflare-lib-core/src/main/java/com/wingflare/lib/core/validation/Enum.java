package com.wingflare.lib.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举类校验注解
 *
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE})
@ReportAsSingleViolation
@Repeatable(Enum.List.class)
@Constraint(validatedBy = EnumValidator.class)
public @interface Enum {

    String message() default "{wingflare.core.validation.enum.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * the enum's class-type
     *
     * @return Class
     */
    Class<? extends java.lang.Enum<?>> clazz();

    /**
     * the method's name ,which used to validate the enum's value
     * 指定枚举校验具体方法
     * 默认校验 getValue方法
     *
     * @return method's name
     */
    String method() default "";

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
            ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        Enum[] value();
    }

}
