package com.wingflare.lib.core.utils;


import com.wingflare.lib.core.exceptions.ParameterException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author naizui_ycx
 * @date {2023/02/25}
 * @description bean验证工具类
 */
public class ValidationUtil {

    private static final Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 获取验证器对象
     *
     * @return
     */
    public static Validator getValidator() {
        return validator;
    }

    /**
     * 业务参数验证方法
     *
     * @param object
     * @param noNull
     * @param groups
     * @throws IllegalArgumentException
     */
    public static void validateBo(Object object, boolean noNull, Class<?>... groups) throws ParameterException {

        if (noNull && object == null) {
            throw new ParameterException("biz.business.paramNull");
        }

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            List<String> errMsgList = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList());

            if (errMsgList.size() == 1) {
                throw new ParameterException(errMsgList.get(0));
            } else {
                throw new ParameterException("biz.business.paramErr", errMsgList);
            }
        }
    }

    /**
     * 业务参数验证方法
     *
     * @param object
     * @param groups
     * @throws IllegalArgumentException
     */
    public static void validateBo(Object object, Class<?>... groups) throws ParameterException {
        validateBo(object, true, groups);
    }
}
