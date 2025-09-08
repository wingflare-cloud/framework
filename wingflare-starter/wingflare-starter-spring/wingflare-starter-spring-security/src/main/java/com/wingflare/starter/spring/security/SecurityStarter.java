package com.wingflare.starter.spring.security;


import com.wingflare.adapter.spring.security.aspect.PreAuthorizeAspect;
import com.wingflare.adapter.spring.security.utils.UserAuthUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        SecurityCheckServer.class,
        UserAuthUtil.class,
        PreAuthorizeAspect.class,
})
public class SecurityStarter {

}
