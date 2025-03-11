package com.wingflare.lib.core.validation;

import com.wingflare.lib.core.utils.StringUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 枚举类校验
 *
 * @author nazui_ycx
 * @date Sat Mar 04 17:58:17 CST 2023
 */
public class EnumValidator implements ConstraintValidator<Enum, Object> {

    private Enum anEnum;

    private final Map<String, List<Object>> enumValues = new HashMap<>();

    @Override
    public void initialize(Enum constraintAnnotation) {
        this.anEnum = constraintAnnotation;
    }

    private List<Object> getEnumValues(Class<? extends java.lang.Enum<?>> clz) throws Throwable {
        String name = clz.getName();

        if (StringUtil.isNotBlank(anEnum.method())) {
            name = name + "_" + anEnum.method();
        }

        if (enumValues.containsKey(name)) {
            return enumValues.get(name);
        }

        java.lang.Enum<?>[] enums = clz.getEnumConstants();
        List<Object> objects = new ArrayList<>(enums.length);

        if (StringUtil.isNotBlank(anEnum.method())) {
            Method method = anEnum.clazz().getMethod(anEnum.method());
            for (java.lang.Enum<?> value : enums) {
                objects.add(method.invoke(value));
            }
        } else {
            for (java.lang.Enum<?> value : enums) {
                objects.add(value.name());
            }
        }

        enumValues.put(name, objects);
        return objects;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if ("".equals(value) || Objects.isNull(value) || Objects.isNull(anEnum)) {
            return true;
        }

        try {
            return getEnumValues(anEnum.clazz())
                    .contains(value);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
