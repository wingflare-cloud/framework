package com.wingflare.business.auth;

import com.wingflare.business.auth.biz.LoginBizImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName Bootstrap
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 启动类
 */
@Configuration
@Import({
        LoginBizImpl.class,
})
public class Bootstrap {
}
