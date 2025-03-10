package com.wingflare.sdk.base.api;

import com.wingflare.lib.spring.annotation.WingFlareFeignClient;
import com.wingflare.sdk.base.api.fallback.MenuApiFallbackFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName ApiStarter
 * @Author naizui_ycx
 * @Date 2025/03/10
 * @Description 载入菜单服务对外api
 */
@Configuration
@WingFlareFeignClient(basePackages = "com.wingflare.sdk.base.api.service")
@Import({
        MenuApiFallbackFactory.class
})
public class ApiStarter {
}
