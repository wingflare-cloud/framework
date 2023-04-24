package com.wingflare.sdk.user.api;

import com.wingflare.lib.spring.annotation.WingFlareFeignClient;
import com.wingflare.sdk.user.api.fallback.UserApiFallbackFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName UserApiStarter
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 载入用户服务对外api
 */
@Configuration
@WingFlareFeignClient(basePackages = "com.wingflare.sdk.user.api.service")
@Import({
        UserApiFallbackFactory.class
})
public class UserApiStarter {
}
