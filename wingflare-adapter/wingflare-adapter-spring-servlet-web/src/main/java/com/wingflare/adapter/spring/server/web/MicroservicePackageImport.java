package com.wingflare.adapter.spring.server.web;

import com.wingflare.adapter.spring.server.web.configure.WebMvcConfig;
import com.wingflare.adapter.spring.server.web.filter.HeaderConvertContextFilter;
import com.wingflare.adapter.spring.server.web.handler.ApiResponseAdviceHandler;
import com.wingflare.adapter.spring.server.web.handler.WebApiExceptionHandler;
import com.wingflare.lib.spring.MicroserviceMode;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Import;

/**
 * @author naizui_ycx
 * @className MicroserviceModeImport
 * @email chenxi2511@qq.com
 * @date 2023/04/04/23
 * @description 微服务组件导入
 */
@Import({
        WebMvcConfig.class,
        ApiResponseAdviceHandler.class,
        WebApiExceptionHandler.class,
        HeaderConvertContextFilter.class,
        WebCtxSource.class,

})
@ConditionalOnBean({MicroserviceMode.class})
public class MicroservicePackageImport {
}
