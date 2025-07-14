package com.wingflare.facade.module.base.biz;


import com.wingflare.facade.module.base.bo.MenuBO;
import com.wingflare.facade.module.base.bo.MenuSearchBO;
import com.wingflare.facade.module.base.bo.PermissionCodesExistBO;
import com.wingflare.facade.module.base.dto.MenuDTO;
import com.wingflare.facade.module.base.dto.SimpleMenuDTO;
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
    PageDto<MenuDTO> list(@Valid MenuSearchBO bo);

    /**
     * 查询系统菜单详情
     */
    MenuDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统菜单详情
     */
    MenuDTO getOnlyOne(@Valid @NotNull MenuSearchBO searchBo);

    /**
     * 删除系统菜单
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统菜单
     */
    @Validated({Default.class, Create.class})
    MenuDTO create(@Valid @NotNull MenuBO bo);

    /**
     * 更新系统菜单
     */
    @Validated({Default.class, Update.class})
    MenuDTO update(@Valid @NotNull MenuBO bo);

    /**
     * 获取树形结构菜单
     *
     * @param searchBo
     * @return
     */
    List<SimpleMenuDTO> tree(@Valid @NotNull MenuSearchBO searchBo);

    /**
     * 判断权限代码是否存在
     *
     * @param existBo
     * @return
     */
    Boolean permissionCodesExist(@Valid @NotNull PermissionCodesExistBO existBo);

}