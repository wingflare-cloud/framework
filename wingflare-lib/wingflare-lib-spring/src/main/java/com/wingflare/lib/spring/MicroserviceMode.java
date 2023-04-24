package com.wingflare.lib.spring;

import com.wingflare.lib.spring.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName MicroserviceMode
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 微服务bean，存在该bean表示开启了微服务模块，可以用于注入一些特殊的bean判断
 */
@Import({
        ResponseConverter.Default.class,
        FeignAutoConfiguration.class,
})
public class MicroserviceMode {
}
