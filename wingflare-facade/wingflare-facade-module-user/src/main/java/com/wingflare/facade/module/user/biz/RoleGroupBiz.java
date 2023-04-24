package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.RoleGroupBo;
import com.wingflare.facade.module.user.bo.RoleGroupSearchBo;
import com.wingflare.facade.module.user.dto.RoleGroupDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


/**
 * 系统角色分组表Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
@Validated
public interface RoleGroupBiz {

    /**
     * 查询系统角色分组表列表
     */
    PageDto<RoleGroupDto> list(@Valid RoleGroupSearchBo bo);

    /**
     * 查询系统角色分组表详情
     */
    RoleGroupDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统角色分组表详情
     */
    RoleGroupDto getOnlyOne(@Valid @NotNull RoleGroupSearchBo searchBo);

    /**
     * 删除系统角色分组表
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统角色分组表
     */
    @Validated({Default.class, Create.class})
    RoleGroupDto create(@Valid @NotNull RoleGroupBo bo);

    /**
     * 更新系统角色分组表
     */
    @Validated({Default.class, Update.class})
    RoleGroupDto update(@Valid @NotNull RoleGroupBo bo);

}