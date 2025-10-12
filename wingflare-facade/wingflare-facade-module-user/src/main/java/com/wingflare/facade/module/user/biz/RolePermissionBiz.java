package com.wingflare.facade.module.user.biz;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.core.validate.Create;
import com.wingflare.api.core.validate.Update;
import com.wingflare.api.validation.annotation.Validated;
import com.wingflare.facade.module.user.bo.PermissionCodesExistBO;
import com.wingflare.facade.module.user.bo.RolePermissionBO;
import com.wingflare.facade.module.user.dto.RolePermissionDTO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RolePermissionSearchBO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import java.util.List;


/**
 * 系统角色权限Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
@Validated
public interface RolePermissionBiz
{

    /**
     * 查询系统角色权限列表
     */
    PageDto<RolePermissionDTO> list(@Valid RolePermissionSearchBO bo);

    /**
     * 查询系统角色权限详情
     */
    RolePermissionDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个系统角色权限详情
     */
    RolePermissionDTO getOnlyOne(@Valid @NotNull RolePermissionSearchBO searchBo);

    /**
     * 删除系统角色权限
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增系统角色权限
     */
    @Validated({Default.class, Create.class})
    RolePermissionDTO create(@Valid @NotNull RolePermissionBO bo);

    /**
     * 更新系统角色权限
     */
    @Validated({Default.class, Update.class})
    RolePermissionDTO update(@Valid @NotNull RolePermissionBO bo);

    /**
     * 保存角色权限
     * @param existBo
     * @return
     */
    Boolean savePermission(@Valid @NotNull PermissionCodesExistBO existBo);

    /**
     * 获取角色权限
     *
     * @param bo
     * @return
     */
    List<PermissionCodesExistBO.CodesExist> permission(@Valid @NotNull IdBo bo);

}
