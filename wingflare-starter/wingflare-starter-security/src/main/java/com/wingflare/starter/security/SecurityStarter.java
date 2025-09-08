package com.wingflare.starter.security;


import com.wingflare.lib.security.aspect.PreAuthorizeAspect;
import com.wingflare.lib.security.utils.UserAuthUtil;
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
