package com.wingflare.lib.security.standard;


import com.wingflare.lib.standard.model.ApplicationAuth;

public interface SecurityCheckApplication {

    /**
     * 权限判断方法
     *
     * @param businessSystem 业务系统
     * @param key 权限key
     * @param application 当前上下文中的应用对象
     * @param permissionCode 权限代码
     * @param isService 是否为服务商应用
     *
     * @return
     */
    boolean hasPermission(
            String businessSystem,
            String key,
            ApplicationAuth application,
            String permissionCode,
            boolean isService
    );

    ApplicationAuth getApplicationByAppId(String appKey);

}
