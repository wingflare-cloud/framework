package com.wingflare.adapter.spring.security.config;


import com.wingflare.adapter.spring.common.annotation.ConditionalOnAnnotationPresent;
import com.wingflare.adapter.spring.common.utils.PointUtil;
import com.wingflare.adapter.spring.security.aspect.DataSecretAspect;
import com.wingflare.adapter.spring.security.aspect.SensitiveDataAspect;
import com.wingflare.adapter.spring.security.properties.DataSecretProperties;
import com.wingflare.adapter.spring.security.secret.AESDataSecret;
import com.wingflare.adapter.spring.security.secret.DESDataSecret;
import com.wingflare.adapter.spring.security.secret.RSADataSecret;
import com.wingflare.api.security.annotation.EnableDataSecret;
import com.wingflare.api.security.annotation.EnableDataSensitive;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据加密启用配置
 */
@Configuration
public class EnableSecurityConfig {

    @Bean
    @ConditionalOnAnnotationPresent(EnableDataSecret.class)
    public DataSecretProperties dataSecretProperties() {
        return new DataSecretProperties();
    }

    @Bean
    @ConditionalOnAnnotationPresent(EnableDataSecret.class)
    public DESDataSecret regDESDataSecret(DataSecretProperties dataSecretProperties) {
        return new DESDataSecret(dataSecretProperties);
    }

    @Bean
    @ConditionalOnAnnotationPresent(EnableDataSecret.class)
    public AESDataSecret regAESDataSecret(DataSecretProperties dataSecretProperties) {
        return new AESDataSecret(dataSecretProperties);
    }

    @Bean
    @ConditionalOnAnnotationPresent(EnableDataSecret.class)
    public RSADataSecret regRSADataSecret(DataSecretProperties dataSecretProperties) {
        return new RSADataSecret(dataSecretProperties);
    }

    @Bean
    @ConditionalOnAnnotationPresent(EnableDataSecret.class)
    public DataSecretAspect regDataSecretAspect(PointUtil pointUtil) {
        return new DataSecretAspect(pointUtil);
    }

    @Bean
    @ConditionalOnAnnotationPresent(EnableDataSensitive.class)
    public SensitiveDataAspect regSensitiveDataAspect() {
        return new SensitiveDataAspect();
    }

}
