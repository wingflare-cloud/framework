package com.wingflare.lib.spring.utils;

import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.spring.configure.properties.ApiProperties;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import jakarta.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * 判断切面对象是否为api bean，还是普通bean
 *
 */
@ConditionalOnBean({
        ApiProperties.class
})
public class PointUtil {

    private static final Logger logger = LoggerFactory.getLogger(PointUtil.class);

    @Resource
    private ApiProperties apiProperties;

    private List<Class<? extends Annotation>> apiAnnotationClassList = null;

    private List<Class<?>> apiBaseClassList = null;

    private final Map<String, Boolean> apiTargetCache = new HashMap<>();


    /**
     * 判断是否为api客户端调用
     *
     * @param point
     * @return
     */
    public boolean isApiClient(JoinPoint point) {
        String name = point.getSignature().getDeclaringTypeName();

        if (apiTargetCache.containsKey(name)) {
            return apiTargetCache.get(name);
        }

        synchronized (apiTargetCache) {
            if (!apiTargetCache.containsKey(name)) {
                List<Class<? extends Annotation>> annClasses = getApiAnnotationClass();
                List<Class<?>> classes = getApiBaseClass();
                Annotation annotation = null;
                boolean ret = false;

                for (Class<?> clz : classes) {
                    if (point.getTarget().getClass().isAssignableFrom(clz)) {
                        ret = true;
                        break;
                    }
                }

                if (!ret) {
                    for (Class<? extends Annotation> annClass : annClasses) {
                        annotation = point.getSignature().getDeclaringType().getAnnotation(annClass);
                        if (annotation != null) {
                            ret = true;
                            break;
                        }
                    }
                }

                apiTargetCache.put(name, ret);
            }

            return apiTargetCache.get(name);
        }
    }

    private List<Class<? extends Annotation>> getApiAnnotationClass() {
        if (apiAnnotationClassList == null) {
            synchronized (this) {
                if (apiAnnotationClassList == null) {
                    List<String> annNames = apiProperties.getAnnotationClasses();
                    apiAnnotationClassList = new ArrayList<>();
                    if (!CollectionUtil.isEmpty(annNames)) {
                        annNames.forEach(className -> {
                            try {
                                apiAnnotationClassList.add((Class<? extends Annotation>) Class.forName(className));
                            } catch (Throwable e) {
                                logger.warn("class notfound or not api annotation.", e(Map.of(
                                        "class", className
                                )));
                            }
                        });
                    }
                }
            }
        }

        return apiAnnotationClassList;
    }

    private List<Class<?>> getApiBaseClass() {
        if (apiBaseClassList == null) {
            synchronized (this) {
                if (apiBaseClassList == null) {
                    List<String> classNames = apiProperties.getBaseClasses();
                    apiBaseClassList = new ArrayList<>();
                    if (!CollectionUtil.isEmpty(classNames)) {
                        classNames.forEach(className -> {
                            try {
                                apiBaseClassList.add(Class.forName(className));
                            } catch (Throwable e) {
                                logger.warn("api base class notfound.", e(Map.of(
                                        "class", className
                                )));
                            }
                        });
                    }
                }
            }
        }

        return apiBaseClassList;
    }

}
