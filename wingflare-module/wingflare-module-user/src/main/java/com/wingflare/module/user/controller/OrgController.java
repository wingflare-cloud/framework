package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.OrgBiz;
import com.wingflare.facade.module.user.bo.OrgBo;
import com.wingflare.facade.module.user.bo.OrgSearchBo;
import com.wingflare.facade.module.user.dto.OrgDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;

/**
 * 组织机构Controller
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
@Controller
@RequestMapping("/org")
public class OrgController
{

	@Resource
    private OrgBiz orgBiz;

    /**
     * 查询组织机构列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<OrgDto> list(OrgSearchBo bo)
    {
		return orgBiz.list(bo);
    }

	/**
     * 查询组织机构详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public OrgDto get(IdBo bo)
	{
		return orgBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个组织机构详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public OrgDto getOnlyOne(OrgSearchBo searchBo)
	{
		return orgBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除组织机构
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		orgBiz.delete(bo);
	}

	/**
     * 新增组织机构
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public OrgDto create(@RequestBody OrgBo bo)
	{
		return orgBiz.create(bo);
	}
	
	/**
     * 更新组织机构
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public OrgDto update(@RequestBody OrgBo bo)
	{
		return orgBiz.update(bo);
	}
	
}
