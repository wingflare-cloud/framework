package com.wingflare.lib.sleuth.perfect;


import brave.baggage.BaggageFields;
import brave.baggage.CorrelationScopeConfig;
import brave.baggage.CorrelationScopeCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 解决Sleuth日志默认不携带parentId问题
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
@Configuration
public class SleuthConfiguration {

    @Bean
    public CorrelationScopeCustomizer createCorrelationScopeCustomizer(){
        return builder -> builder.add(CorrelationScopeConfig.SingleCorrelationField.create(BaggageFields.PARENT_ID));
    }

}
