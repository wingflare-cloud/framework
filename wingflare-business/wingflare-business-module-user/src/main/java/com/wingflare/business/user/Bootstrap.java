package com.wingflare.business.user;


import com.wingflare.business.user.biz.*;
import com.wingflare.business.user.service.IdentityServer;
import com.wingflare.business.user.service.JobLevelClassifyServer;
import com.wingflare.business.user.service.JobLevelServer;
import com.wingflare.business.user.service.OrgDepartmentServer;
import com.wingflare.business.user.service.OrgServer;
import com.wingflare.business.user.service.RolePermissionServer;
import com.wingflare.business.user.service.RoleServer;
import com.wingflare.business.user.service.UserRoleServer;
import com.wingflare.business.user.service.UserServer;
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
@MapperScan("com.wingflare.business.user.mapper")
@Import({
        RolePermissionServer.class,
        RoleServer.class,
        UserServer.class,
        IdentityServer.class,
        JobLevelServer.class,
        OrgDepartmentServer.class,
        OrgServer.class,
        UserRoleServer.class,
        JobLevelClassifyServer.class,

        RoleBizImpl.class,
        RolePermissionBizImpl.class,
        UserBizImpl.class,
        UserRoleBizImpl.class,
        IdentityBizImpl.class,
        JobLevelBizImpl.class,
        OrgDepartmentBizImpl.class,
        OrgBizImpl.class,
        JobLevelBizImpl.class,
        JobLevelClassifyBizImpl.class
})
public class Bootstrap {
}
