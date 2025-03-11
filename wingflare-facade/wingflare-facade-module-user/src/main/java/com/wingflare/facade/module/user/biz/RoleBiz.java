package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.RoleBo;
import com.wingflare.facade.module.user.bo.RoleSearchBo;
import com.wingflare.facade.module.user.dto.RoleDto;
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
    PageDto<RoleDto> list(@Valid RoleSearchBo bo);

    /**
     * 查询系统角色详情
     */
    RoleDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统角色详情
     */
    RoleDto getOnlyOne(@Valid @NotNull RoleSearchBo searchBo);

    /**
     * 删除系统角色
     */
    RoleDto delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统角色
     */
    @Validated({Default.class, Create.class})
    RoleDto create(@Valid @NotNull RoleBo bo);

    /**
     * 更新系统角色
     */
    @Validated({Default.class, Update.class})
    RoleDto update(@Valid @NotNull RoleBo bo);

}