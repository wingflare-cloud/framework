package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.IdentityBiz;
import com.wingflare.facade.module.user.bo.IdentityBO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.facade.module.user.bo.IdentitySearchBO;
import com.wingflare.facade.module.user.dto.IdentityDTO;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;

/**
 * 岗位身份Controller
 * 
 * @author naizui_ycx
 * @date Sun Apr 02 10:04:38 CST 2023
 */
@Controller
@RequestMapping("/identity")
public class IdentityController
{

	@Resource
    private IdentityBiz identityBiz;

    /**
     * 查询岗位身份列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<IdentityDTO> list(IdentitySearchBO bo)
    {
		return identityBiz.list(bo);
    }

	/**
     * 查询岗位身份详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public IdentityDTO get(IdBo bo)
	{
		return identityBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个岗位身份详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public IdentityDTO getOnlyOne(IdentitySearchBO searchBo)
	{
		return identityBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除岗位身份
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		identityBiz.delete(bo);
	}

	/**
     * 新增岗位身份
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public IdentityDTO create(@RequestBody IdentityBO bo)
	{
		return identityBiz.create(bo);
	}
	
	/**
     * 更新岗位身份
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public IdentityDTO update(@RequestBody IdentityBO bo)
	{
		return identityBiz.update(bo);
	}
	
}
