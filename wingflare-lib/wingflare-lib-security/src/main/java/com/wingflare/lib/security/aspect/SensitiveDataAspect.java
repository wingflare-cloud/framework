package com.wingflare.lib.security.aspect;


import com.wingflare.lib.core.enums.SensitiveType;
import com.wingflare.lib.core.utils.ConvertUtil;
import com.wingflare.lib.core.utils.DesensitizedUtils;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.security.annotation.Desensitize;
import com.wingflare.lib.security.annotation.DesensitizeGroups;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.noear.snack.ONode;
import org.noear.snack.OValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author naizui_ycx
 * @date {2022/2/8}
 * @description 数据脱敏
 */
@Aspect
@Component
public class SensitiveDataAspect implements Ordered {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveDataAspect.class);

    private final Map<String, List<Desensitize>> desensitizeCache = new HashMap<>();

    @Pointcut("@annotation(com.wingflare.lib.security.annotation.Desensitize)" +
            "|| @annotation(com.wingflare.lib.security.annotation.DesensitizeGroups)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String cacheName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        List<Desensitize> desensitizeList = new ArrayList<>();

        if (desensitizeCache.containsKey(cacheName)) {
            desensitizeList = desensitizeCache.get(cacheName);
        } else {
            DesensitizeGroups desensitizeGroups = method.getAnnotation(DesensitizeGroups.class);

            if (desensitizeGroups != null) {
                desensitizeList = Arrays.asList(desensitizeGroups.desensitizes());
            }

            Desensitize sensitiveData = method.getAnnotation(Desensitize.class);

            if (sensitiveData != null) {
                desensitizeList.add(sensitiveData);
            }

            desensitizeCache.put(cacheName, desensitizeList);
        }

        Object result = joinPoint.proceed(joinPoint.getArgs());

        if (result != null && isNotGeneralType(result.getClass())) {
            ONode oNode = ONode.load(result);
            AtomicBoolean hit = new AtomicBoolean(false);
            for (Desensitize desensitize : desensitizeList) {
                try {
                    ONode values = oNode.select(desensitize.jsonPath());

                    if (values.isNull()) {
                        continue;
                    }

                    if (values.isArray()) {
                        values.forEach(item -> {
                            if (item.isValue() && !item.isNull()) {
                                OValue oVal = item.val();
                                oVal.set(trySensitive(desensitize.sensitiveType(), oVal.getRaw()));
                                hit.set(true);
                            }
                        });
                    } else if (values.isValue()) {
                        OValue oVal = values.val();
                        oVal.set(trySensitive(desensitize.sensitiveType(), oVal.getRaw()));
                        hit.set(true);
                    } else {
                        logger.warn("脱敏匹配数据异常: {}", desensitize.jsonPath());
                    }
                } catch (Throwable e) {
                    logger.error("数据脱敏失败: {}, {}", e.getMessage(), desensitize.jsonPath());
                }
            }

            if (hit.get()) {
                result = oNode.toObject(result.getClass());
            }
        }

        return result;
    }

    private Object trySensitive(SensitiveType type, Object val) {
        String strVal = ConvertUtil.toStr(val);

        if (StringUtil.isBlank(strVal)) {
            return val;
        }

        switch (type) {
            case CHINESE_NAME: {
                return DesensitizedUtils.chineseName(strVal);
            }
            case ID_CARD: {
                return DesensitizedUtils.idCardNum(strVal);
            }
            case MOBILE_PHONE: {
                return DesensitizedUtils.mobilePhone(strVal);
            }
            case ADDRESS: {
                return DesensitizedUtils.address(strVal);
            }
            case EMAIL: {
                return DesensitizedUtils.email(strVal);
            }
            case BANK_CARD: {
                return DesensitizedUtils.bankCard(strVal);
            }
            case PASSWD: {
                return DesensitizedUtils.passwd(strVal);
            }
            case NONE:
            default:
                return DesensitizedUtils.none(strVal);
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

    private static boolean isNotGeneralType(Class<?> clazz) {
        return !clazz.isPrimitive()
                && !clazz.isEnum()
                && Objects.nonNull(clazz.getPackage())
                && !StringUtil.startsWith(clazz.getPackage().getName(), "javax.")
                && !StringUtil.startsWith(clazz.getPackage().getName(), "java.lang")
                && !StringUtil.startsWith(clazz.getPackage().getName(), "java.time");
    }

}
