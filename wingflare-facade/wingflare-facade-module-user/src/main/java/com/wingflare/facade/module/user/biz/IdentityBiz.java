package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.IdentityBO;
import com.wingflare.facade.module.user.bo.IdentitySearchBO;
import com.wingflare.facade.module.user.dto.IdentityDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;


/**
 * 岗位身份Biz
 *
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
public interface IdentityBiz
{
	
	/**
     * 查询岗位身份列表
     */
    PageDto<IdentityDTO> list(IdentitySearchBO bo);
	
	/**
     * 查询岗位身份详情
     */
	IdentityDTO get(IdBo bo);
	
	/**
     * 通过条件查询单个岗位身份详情
     */
	IdentityDTO getOnlyOne(IdentitySearchBO searchBo);
	
	/**
     * 删除岗位身份
     */
	IdentityDTO delete(IdBo bo);

	/**
     * 新增岗位身份
     */
	IdentityDTO create(IdentityBO bo);
	
	/**
     * 更新岗位身份
     */
	IdentityDTO update(IdentityBO bo);

}