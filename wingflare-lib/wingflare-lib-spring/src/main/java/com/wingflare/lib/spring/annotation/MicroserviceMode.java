package com.wingflare.lib.spring.annotation;

import com.wingflare.lib.spring.aspect.InternalApiAuthAspect;
import com.wingflare.lib.spring.aspect.OriginalRespAspect;
import com.wingflare.lib.spring.configure.properties.ApiProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author naizui_ycx
 * @date {2023/01/07}
 * @description 开启微服务模式
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({
        com.wingflare.lib.spring.MicroserviceMode.class,
        ApiProperties.class,
        OriginalRespAspect.class,
        InternalApiAuthAspect.class
})
public @interface MicroserviceMode {
}
