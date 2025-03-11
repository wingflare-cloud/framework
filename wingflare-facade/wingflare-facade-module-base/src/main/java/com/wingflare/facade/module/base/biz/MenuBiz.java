package com.wingflare.facade.module.base.biz;


import com.wingflare.facade.module.base.bo.MenuBo;
import com.wingflare.facade.module.base.bo.MenuSearchBo;
import com.wingflare.facade.module.base.bo.PermissionCodesExistBo;
import com.wingflare.facade.module.base.dto.MenuDto;
import com.wingflare.facade.module.base.dto.SimpleMenuDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import java.util.List;


/**
 * 系统菜单Biz
 *
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
@Validated
public interface MenuBiz {

    /**
     * 查询系统菜单列表
     */
    PageDto<MenuDto> list(@Valid MenuSearchBo bo);

    /**
     * 查询系统菜单详情
     */
    MenuDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统菜单详情
     */
    MenuDto getOnlyOne(@Valid @NotNull MenuSearchBo searchBo);

    /**
     * 删除系统菜单
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统菜单
     */
    @Validated({Default.class, Create.class})
    MenuDto create(@Valid @NotNull MenuBo bo);

    /**
     * 更新系统菜单
     */
    @Validated({Default.class, Update.class})
    MenuDto update(@Valid @NotNull MenuBo bo);

    /**
     * 获取树形结构菜单
     *
     * @param searchBo
     * @return
     */
    List<SimpleMenuDto> tree(@Valid @NotNull MenuSearchBo searchBo);

    /**
     * 判断权限代码是否存在
     *
     * @param existBo
     * @return
     */
    Boolean permissionCodesExist(@Valid @NotNull PermissionCodesExistBo existBo);

}