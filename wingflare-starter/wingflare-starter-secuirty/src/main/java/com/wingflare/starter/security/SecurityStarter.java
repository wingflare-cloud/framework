package com.wingflare.starter.security;

import com.wingflare.abstraction.security.starter.SecurityStarterInterface;
import com.wingflare.lib.security.aspect.PreAuthorizeAspect;
import com.wingflare.lib.security.utils.ApplicationAuthUtil;
import com.wingflare.lib.security.utils.AuthUtil;
import com.wingflare.lib.security.utils.UserAuthUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        SecurityCheckServer.class,
        ApplicationAuthUtil.class,
        UserAuthUtil.class,
        AuthUtil.class,
        PreAuthorizeAspect.class,
})
public class SecurityStarter implements SecurityStarterInterface {

}
