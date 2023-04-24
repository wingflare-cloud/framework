package com.wingflare.facade.module.auth.biz;


import com.wingflare.facade.module.auth.bo.LoginInfoBo;
import com.wingflare.facade.module.auth.bo.LoginInfoSearchBo;
import com.wingflare.facade.module.auth.dto.LoginInfoDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * 登陆信息Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
@Validated
public interface LoginInfoBiz
{
	
	/**
     * 查询登陆信息列表
     */
    PageDto<LoginInfoDto> list(@Valid LoginInfoSearchBo bo);
	
	/**
     * 查询登陆信息详情
     */
	LoginInfoDto get(@Valid @NotNull IdBo bo);
	
	/**
     * 通过条件查询单个登陆信息详情
     */
	LoginInfoDto getOnlyOne(@Valid @NotNull LoginInfoSearchBo searchBo);
	
	/**
     * 删除登陆信息
     */
	void delete(@Valid @NotNull IdBo bo);

	/**
     * 新增登陆信息
     */
	LoginInfoDto create(@Validated(Create.class) @NotNull LoginInfoBo bo);
	
	/**
     * 更新登陆信息
     */
	LoginInfoDto update(@Validated(Update.class) @NotNull LoginInfoBo bo);

}