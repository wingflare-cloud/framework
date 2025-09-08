package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.RoleBO;
import com.wingflare.facade.module.user.bo.RoleSearchBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;


/**
 * 系统角色Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
public interface RoleBiz {

    /**
     * 查询系统角色列表
     */
    PageDto<RoleDTO> list(RoleSearchBO bo);

    /**
     * 查询系统角色详情
     */
    RoleDTO get(IdBo bo);

    /**
     * 通过条件查询单个系统角色详情
     */
    RoleDTO getOnlyOne(RoleSearchBO searchBo);

    /**
     * 删除系统角色
     */
    RoleDTO delete(IdBo bo);

    /**
     * 新增系统角色
     */
    RoleDTO create(RoleBO bo);

    /**
     * 更新系统角色
     */
    RoleDTO update(RoleBO bo);

}