package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.RoleBO;
import com.wingflare.facade.module.user.bo.RoleSearchBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;


/**
 * 系统角色Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
@Validated
public interface RoleBiz {

    /**
     * 查询系统角色列表
     */
    PageDto<RoleDTO> list(@Valid RoleSearchBO bo);

    /**
     * 查询系统角色详情
     */
    RoleDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统角色详情
     */
    RoleDTO getOnlyOne(@Valid @NotNull RoleSearchBO searchBo);

    /**
     * 删除系统角色
     */
    RoleDTO delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统角色
     */
    @Validated({Default.class, Create.class})
    RoleDTO create(@Valid @NotNull RoleBO bo);

    /**
     * 更新系统角色
     */
    @Validated({Default.class, Update.class})
    RoleDTO update(@Valid @NotNull RoleBO bo);

}