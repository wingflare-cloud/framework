package com.wingflare.sdk.base.api.fallback;


import com.wingflare.facade.module.base.bo.MenuBo;
import com.wingflare.facade.module.base.bo.MenuSearchBo;
import com.wingflare.facade.module.base.bo.PermissionCodesExistBo;
import com.wingflare.facade.module.base.dto.MenuDto;
import com.wingflare.facade.module.base.dto.SimpleMenuDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.sdk.base.api.service.MenuApiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

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

            public PageDto<MenuDto> list(MenuSearchBo bo) {
                logger.error("查询菜单列表失败: {}", throwable.getMessage());
                return null;
            }

            public MenuDto get(IdBo bo) {
                logger.error("查询单个菜单失败: {}", throwable.getMessage());
                return null;
            }

            public MenuDto update(MenuBo bo) {
                logger.error("更新菜单失败: {}", throwable.getMessage());
                return null;
            }

            public MenuDto getOnlyOne(MenuSearchBo searchBo) {
                logger.error("根据条件查询单个菜单失败: {}", throwable.getMessage());
                return null;
            }

            public void delete(IdBo bo) {
                logger.error("删除菜单失败: {}", throwable.getMessage());
            }

            public MenuDto create(MenuBo bo) {
                logger.error("创建菜单失败: {}", throwable.getMessage());
                return null;
            }

            public List<SimpleMenuDto> tree(MenuSearchBo searchBo) {
                logger.error("获取树形菜单失败: {}", throwable.getMessage());
                return null;
            }

            public Boolean permissionCodesExist(PermissionCodesExistBo existBo) {
                logger.error("判断权限代码是否存在: {}", throwable.getMessage());
                return null;
            }
        };
    }
}
