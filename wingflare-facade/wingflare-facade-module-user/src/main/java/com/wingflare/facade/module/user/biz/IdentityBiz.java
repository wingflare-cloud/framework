package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.IdentityBo;
import com.wingflare.facade.module.user.bo.IdentitySearchBo;
import com.wingflare.facade.module.user.dto.IdentityDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * 岗位身份Biz
 *
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
@Validated
public interface IdentityBiz
{
	
	/**
     * 查询岗位身份列表
     */
    PageDto<IdentityDto> list(@Valid IdentitySearchBo bo);
	
	/**
     * 查询岗位身份详情
     */
	IdentityDto get(@Valid @NotNull IdBo bo);
	
	/**
     * 通过条件查询单个岗位身份详情
     */
	IdentityDto getOnlyOne(@Valid @NotNull IdentitySearchBo searchBo);
	
	/**
     * 删除岗位身份
     */
	IdentityDto delete(@Valid @NotNull IdBo bo);

	/**
     * 新增岗位身份
     */
	IdentityDto create(@Valid @NotNull IdentityBo bo);
	
	/**
     * 更新岗位身份
     */
	IdentityDto update(@Valid @NotNull IdentityBo bo);

}