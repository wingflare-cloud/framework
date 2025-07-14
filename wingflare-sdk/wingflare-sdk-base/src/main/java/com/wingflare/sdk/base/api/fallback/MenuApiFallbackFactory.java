package com.wingflare.sdk.base.api.fallback;


import com.wingflare.facade.module.base.bo.MenuBO;
import com.wingflare.facade.module.base.bo.MenuSearchBO;
import com.wingflare.facade.module.base.bo.PermissionCodesExistBO;
import com.wingflare.facade.module.base.dto.MenuDTO;
import com.wingflare.facade.module.base.dto.SimpleMenuDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.sdk.base.api.service.MenuApiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * @ClassName UserApiFallbackFactory
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description user api熔断
 */
@Component
public class MenuApiFallbackFactory implements FallbackFactory<MenuApiServer> {

    private static final Logger logger = LoggerFactory.getLogger(MenuApiFallbackFactory.class);

    @Override
    public MenuApiServer create(Throwable throwable) {
        return new MenuApiServer() {

            public PageDto<MenuDTO> list(MenuSearchBO bo) {
                logger.error("查询菜单列表失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            public MenuDTO get(IdBo bo) {
                logger.error("查询单个菜单失败",e(Map.of("message", throwable.getMessage())));
                return null;
            }

            public MenuDTO update(MenuBO bo) {
                logger.error("更新菜单失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            public MenuDTO getOnlyOne(MenuSearchBO searchBo) {
                logger.error("根据条件查询单个菜单失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            public void delete(IdBo bo) {
                logger.error("删除菜单失败", e(Map.of("message", throwable.getMessage())));
            }

            public MenuDTO create(MenuBO bo) {
                logger.error("创建菜单失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            public List<SimpleMenuDTO> tree(MenuSearchBO searchBo) {
                logger.error("获取树形菜单失败", e(Map.of("message", throwable.getMessage())));
                return null;
            }

            public Boolean permissionCodesExist(PermissionCodesExistBO existBo) {
                logger.error("判断权限代码是否存在", e(Map.of("message", throwable.getMessage())));
                return null;
            }
        };
    }
}
