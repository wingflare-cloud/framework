package com.wingflare.starter.datascope;

import com.wingflare.lib.datascope.DataPermissionWhereHandler;
import com.wingflare.lib.datascope.aspect.DataPermissionAspect;
import com.wingflare.lib.datascope.utils.DataScopeUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        DataPermissionAspect.class,
        DataScopeUtil.class,
        DataPermissionWhereHandler.class,
        DataScopeHandle.class
})
public class DataScopeStarter {
}
