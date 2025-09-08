package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.PermissionCodesExistBO;
import com.wingflare.facade.module.user.bo.RolePermissionBO;
import com.wingflare.facade.module.user.dto.RolePermissionDTO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RolePermissionSearchBO;
import com.wingflare.lib.standard.PageDto;

import java.util.List;


/**
 * 系统角色权限Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
public interface RolePermissionBiz
{
	
	/**
     * 查询系统角色权限列表
     */
    PageDto<RolePermissionDTO> list(RolePermissionSearchBO bo);
	
	/**
     * 查询系统角色权限详情
     */
	RolePermissionDTO get(IdBo bo);
	
	/**
     * 通过条件查询单个系统角色权限详情
     */
	RolePermissionDTO getOnlyOne(RolePermissionSearchBO searchBo);
	
	/**
     * 删除系统角色权限
     */
	void delete(IdBo bo);

	/**
     * 新增系统角色权限
     */
	RolePermissionDTO create(RolePermissionBO bo);
	
	/**
     * 更新系统角色权限
     */
	RolePermissionDTO update(RolePermissionBO bo);

	/**
	 * 保存角色权限
	 * @param existBo
	 * @return
	 */
	Boolean savePermission(PermissionCodesExistBO existBo);

	/**
	 * 获取角色权限
	 *
	 * @param bo
	 * @return
	 */
	List<PermissionCodesExistBO.CodesExist> permission(IdBo bo);

}