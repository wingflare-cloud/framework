package com.wingflare.business.base;


import com.wingflare.business.base.biz.DictBizImpl;
import com.wingflare.business.base.biz.MenuBizImpl;
import com.wingflare.business.base.biz.SettingBizImpl;
import com.wingflare.business.base.service.DictServer;
import com.wingflare.business.base.service.MenuServer;
import com.wingflare.business.base.service.SettingServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName Bootstrap
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 启动类
 */
@Configuration("baseBootstrap")
@MapperScan("com.wingflare.business.base.mapper")
@Import({
        DictServer.class,
        MenuServer.class,
        SettingServer.class,
        DictBizImpl.class,
        MenuBizImpl.class,
        SettingBizImpl.class,
})
public class Bootstrap {
}
