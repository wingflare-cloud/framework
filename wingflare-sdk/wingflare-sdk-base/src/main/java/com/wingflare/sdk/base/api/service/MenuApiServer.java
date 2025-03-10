package com.wingflare.sdk.base.api.service;

import com.wingflare.facade.module.base.biz.MenuBiz;
import com.wingflare.facade.module.base.bo.MenuBo;
import com.wingflare.facade.module.base.bo.MenuSearchBo;
import com.wingflare.facade.module.base.bo.PermissionCodesExistBo;
import com.wingflare.facade.module.base.dto.MenuDto;
import com.wingflare.facade.module.base.dto.SimpleMenuDto;
import com.wingflare.lib.spring.annotation.ApiClient;
import com.wingflare.lib.spring.annotation.RequestAutoHeader;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.sdk.base.Info;
import com.wingflare.sdk.base.api.fallback.MenuApiFallbackFactory;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * @InterfaceName MenuApiServer
 * @Author naizui_ycx
 * @Date 2025/03/10
 * @Description 菜单开放接口服务
 */
@ApiClient(
        contextId = "menuApiService",
        name = Info.SERVER_NAME,
        fallbackFactory = MenuApiFallbackFactory.class
)
@RequestAutoHeader
public interface MenuApiServer extends MenuBiz {

    /**
     * 查询系统菜单列表
     */
    @Override
    @RequestMapping(value = "/menu/list", method = {RequestMethod.GET})
    PageDto<MenuDto> list(@SpringQueryMap MenuSearchBo bo);

    /**
     * 查询系统菜单详情
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/get", method = {RequestMethod.GET})
    MenuDto get(@SpringQueryMap IdBo bo);


    /**
     * 更新系统菜单
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/update", method = {RequestMethod.PUT})
    MenuDto update(@RequestBody MenuBo bo);


    /**
     * 通过条件查询单个系统菜单详情
     *
     * @param searchBo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/getOnlyOne", method = {RequestMethod.GET})
    MenuDto getOnlyOne(@SpringQueryMap MenuSearchBo searchBo);

    /**
     * 删除系统菜单
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/delete", method = {RequestMethod.DELETE})
    void delete(@RequestBody IdBo bo);

    /**
     * 新增系统菜单
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/create", method = {RequestMethod.POST})
    MenuDto create(@RequestBody MenuBo bo);

    /**
     * 获取树形结构菜单
     *
     * @param searchBo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/tree", method = {RequestMethod.GET})
    List<SimpleMenuDto> tree(@SpringQueryMap MenuSearchBo searchBo);

    /**
     * 判断权限代码是否存在
     *
     * @param existBo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/permissionCodesExist", method = {RequestMethod.GET})
    Boolean permissionCodesExist(@SpringQueryMap PermissionCodesExistBo existBo);

}
