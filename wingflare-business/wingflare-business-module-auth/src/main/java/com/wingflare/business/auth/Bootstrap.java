package com.wingflare.business.auth;

import com.wingflare.business.auth.biz.LoginBizImpl;
import com.wingflare.business.auth.biz.LoginInfoBizImpl;
import com.wingflare.business.auth.biz.LoginTokenBizImpl;
import com.wingflare.business.auth.service.LoginInfoServer;
import com.wingflare.business.auth.service.LoginTokenServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName Bootstrap
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 启动类
 */
@Configuration
@MapperScan("com.wingflare.business.auth.mapper")
@Import({
        LoginInfoServer.class,
        LoginTokenServer.class,
        LoginTokenBizImpl.class,
        LoginInfoBizImpl.class,
        LoginBizImpl.class,
})
public class Bootstrap {
}
