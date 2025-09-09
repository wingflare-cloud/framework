package com.wingflare.sdk.base.api.service;

import com.wingflare.adapter.spring.common.annotation.ApiClient;
import com.wingflare.api.core.PageDto;
import com.wingflare.api.core.annotation.RequestAutoHeader;
import com.wingflare.facade.module.base.biz.MenuBiz;
import com.wingflare.facade.module.base.bo.MenuBO;
import com.wingflare.facade.module.base.bo.MenuSearchBO;
import com.wingflare.facade.module.base.bo.PermissionCodesExistBO;
import com.wingflare.facade.module.base.dto.MenuDTO;
import com.wingflare.facade.module.base.dto.SimpleMenuDTO;
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
    PageDto<MenuDTO> list(@SpringQueryMap MenuSearchBO bo);

    /**
     * 查询系统菜单详情
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/get", method = {RequestMethod.GET})
    MenuDTO get(@SpringQueryMap IdBo bo);


    /**
     * 更新系统菜单
     *
     * @param bo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/update", method = {RequestMethod.PUT})
    MenuDTO update(@RequestBody MenuBO bo);


    /**
     * 通过条件查询单个系统菜单详情
     *
     * @param searchBo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/getOnlyOne", method = {RequestMethod.GET})
    MenuDTO getOnlyOne(@SpringQueryMap MenuSearchBO searchBo);

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
    MenuDTO create(@RequestBody MenuBO bo);

    /**
     * 获取树形结构菜单
     *
     * @param searchBo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/tree", method = {RequestMethod.GET})
    List<SimpleMenuDTO> tree(@SpringQueryMap MenuSearchBO searchBo);

    /**
     * 判断权限代码是否存在
     *
     * @param existBo
     * @return
     */
    @Override
    @RequestMapping(value = "/menu/permissionCodesExist", method = {RequestMethod.GET})
    Boolean permissionCodesExist(@SpringQueryMap PermissionCodesExistBO existBo);

}
