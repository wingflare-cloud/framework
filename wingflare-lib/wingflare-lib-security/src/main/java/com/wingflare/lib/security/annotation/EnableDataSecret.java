package com.wingflare.lib.security.annotation;

import com.wingflare.lib.security.aspect.DataSecretAspect;
import com.wingflare.lib.spring.configure.properties.ApiProperties;
import com.wingflare.lib.security.properties.DataSecretProperties;
import com.wingflare.lib.security.secret.AESDataSecret;
import com.wingflare.lib.security.secret.DESDataSecret;
import com.wingflare.lib.security.secret.RSADataSecret;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启保密数据自动加密解密功能
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({
        DataSecretProperties.class,
        ApiProperties.class,
        DESDataSecret.class,
        AESDataSecret.class,
        RSADataSecret.class,
        DataSecretAspect.class,
})
public @interface EnableDataSecret {
}
