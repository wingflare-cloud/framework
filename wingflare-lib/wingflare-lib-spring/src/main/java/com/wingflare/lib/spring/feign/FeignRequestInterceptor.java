package com.wingflare.lib.spring.feign;


import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.annotation.HeaderPenetration;
import com.wingflare.lib.spring.annotation.RequestAutoHeader;
import com.wingflare.lib.spring.configure.properties.SystemInternalProperties;
import com.wingflare.lib.spring.constants.Wf;
import com.wingflare.lib.spring.utils.ApiHelperUtil;
import com.wingflare.lib.standard.ContextSource;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author naizui_ycx
 * @date {2021/12/17}
 * @description feign请求头自动补齐
 */
@Component
@ConditionalOnBean({FeignAutoConfiguration.class})
public class FeignRequestInterceptor implements RequestInterceptor {

    @Resource
    private SystemInternalProperties systemInternalProperties;

    @Autowired(required = false)
    private ContextSource ctxSource;

    @Value("${spring.application.name:}")
    private String systemCode;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Target<?> target = requestTemplate.feignTarget();

        if (target.type().isAnnotationPresent(RequestAutoHeader.class)) {
            ApiHelperUtil.setRequestAutoHeader(true);
        }

        if (target.type().isAnnotationPresent(HeaderPenetration.class)) {
            ApiHelperUtil.setRequestHeaderPenetration(true);
        }

        if (ApiHelperUtil.checkRequestHeaderPenetration()) {
            if (ctxSource != null) {
                try {
                    Map<String, String> ctxAll = ctxSource.all();
                    for (String key : systemInternalProperties.getCtxWhitelist()) {
                        String value = ctxAll.get(key);
                        if (StringUtil.isNotEmpty(value)) {
                            requestTemplate.header(key, value);
                        }
                    }
                } catch (Throwable e) {
                }
            }
        }

        if (ApiHelperUtil.checkRequestAutoHeader()) {
            for (Map.Entry<String, String> key : systemInternalProperties.getCtx().entrySet()) {
                String name;
                if (StringUtil.isNotBlank(key.getValue())) {
                    name = key.getValue();
                } else {
                    name = StringUtil.toCamelCase(key.getKey(), '-');
                }

                Object value = ContextHolder.get(name, Object.class);

                if (ObjectUtils.isNotEmpty(value)) {
                    if (value instanceof CharSequence) {
                        requestTemplate.header(key.getKey(), StringUtil.urlEncode((String) value));
                    } else {
                        String str = String.format(
                                "@S[%s]",
                                Base64.encodeBase64URLSafeString(
                                        SerializationUtils.serialize(value)
                                )
                        );
                        requestTemplate.header(key.getKey(), str);
                    }
                }
            }

            if (!systemInternalProperties.getContexts().isEmpty()) {
                systemInternalProperties.getContexts().forEach(requestTemplate::header);
            }

            requestTemplate.header(Wf.SYS_FROM_KEY, systemCode);
            ApiHelperUtil.setRequestAutoHeader(false);
        }
    }

}
