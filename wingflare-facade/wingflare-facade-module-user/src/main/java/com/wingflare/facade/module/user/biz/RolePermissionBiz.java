package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.PermissionCodesExistBo;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RolePermissionBo;
import com.wingflare.facade.module.user.bo.RolePermissionSearchBo;
import com.wingflare.facade.module.user.dto.RolePermissionDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
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
    PageDto<RolePermissionDto> list(@Valid RolePermissionSearchBo bo);
	
	/**
     * 查询系统角色权限详情
     */
	RolePermissionDto get(@Valid @NotNull IdBo bo);
	
	/**
     * 通过条件查询单个系统角色权限详情
     */
	RolePermissionDto getOnlyOne(@Valid @NotNull RolePermissionSearchBo searchBo);
	
	/**
     * 删除系统角色权限
     */
	void delete(@Valid @NotNull IdBo bo);

	/**
     * 新增系统角色权限
     */
	@Validated({Default.class, Create.class})
	RolePermissionDto create(@Valid @NotNull RolePermissionBo bo);
	
	/**
     * 更新系统角色权限
     */
	@Validated({Default.class, Update.class})
	RolePermissionDto update(@Valid @NotNull RolePermissionBo bo);

	/**
	 * 保存角色权限
	 * @param existBo
	 * @return
	 */
	Boolean savePermission(@Valid @NotNull PermissionCodesExistBo existBo);

	/**
	 * 获取角色权限
	 *
	 * @param bo
	 * @return
	 */
	List<PermissionCodesExistBo.CodesExist> permission(@Valid @NotNull IdBo bo);

}