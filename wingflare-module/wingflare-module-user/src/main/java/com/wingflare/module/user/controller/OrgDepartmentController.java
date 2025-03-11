package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.OrgDepartmentBiz;
import com.wingflare.facade.module.user.bo.OrgDepartmentBo;
import com.wingflare.facade.module.user.bo.OrgDepartmentSearchBo;
import com.wingflare.facade.module.user.dto.OrgDepartmentDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;

/**
 * 机构部门Controller
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
@Controller
@RequestMapping("/orgDepartment")
public class OrgDepartmentController
{

	@Resource
    private OrgDepartmentBiz orgDepartmentBiz;

    /**
     * 查询机构部门列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<OrgDepartmentDto> list(OrgDepartmentSearchBo bo)
    {
		return orgDepartmentBiz.list(bo);
    }

	/**
     * 查询机构部门详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public OrgDepartmentDto get(IdBo bo)
	{
		return orgDepartmentBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个机构部门详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public OrgDepartmentDto getOnlyOne(OrgDepartmentSearchBo searchBo)
	{
		return orgDepartmentBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除机构部门
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		orgDepartmentBiz.delete(bo);
	}

	/**
     * 新增机构部门
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public OrgDepartmentDto create(@RequestBody OrgDepartmentBo bo)
	{
		return orgDepartmentBiz.create(bo);
	}
	
	/**
     * 更新机构部门
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public OrgDepartmentDto update(@RequestBody OrgDepartmentBo bo)
	{
		return orgDepartmentBiz.update(bo);
	}
	
}
