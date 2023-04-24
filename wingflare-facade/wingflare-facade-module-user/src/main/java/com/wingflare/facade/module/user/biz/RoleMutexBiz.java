package com.wingflare.facade.module.user.biz;


import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RoleMutexBo;
import com.wingflare.facade.module.user.bo.RoleMutexSearchBo;
import com.wingflare.facade.module.user.dto.RoleMutexDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


/**
 * 系统角色互斥关系Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
@Validated
public interface RoleMutexBiz
{
	
	/**
     * 查询系统角色互斥关系列表
     */
    PageDto<RoleMutexDto> list(@Valid RoleMutexSearchBo bo);
	
	/**
     * 查询系统角色互斥关系详情
     */
	RoleMutexDto get(@Valid @NotNull IdBo bo);
	
	/**
     * 通过条件查询单个系统角色互斥关系详情
     */
	RoleMutexDto getOnlyOne(@Valid @NotNull RoleMutexSearchBo searchBo);
	
	/**
     * 删除系统角色互斥关系
     */
	void delete(@Valid @NotNull IdBo bo);

	/**
     * 新增系统角色互斥关系
     */
	@Validated({Default.class, Create.class})
	RoleMutexDto create(@Valid @NotNull RoleMutexBo bo);
	
	/**
     * 更新系统角色互斥关系
     */
	@Validated({Default.class, Update.class})
	RoleMutexDto update(@Valid @NotNull RoleMutexBo bo);

}