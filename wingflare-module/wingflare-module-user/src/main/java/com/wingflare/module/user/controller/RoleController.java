package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.RoleBiz;
import com.wingflare.facade.module.user.bo.RoleBo;
import com.wingflare.facade.module.user.bo.RoleSearchBo;
import com.wingflare.facade.module.user.dto.RoleDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 系统角色Controller
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
@Controller
@RequestMapping("/role")
public class RoleController
{

	@Resource
    private RoleBiz roleBiz;

    /**
     * 查询系统角色列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<RoleDto> list(RoleSearchBo bo)
    {
		return roleBiz.list(bo);
    }

	/**
     * 查询系统角色详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public RoleDto get(IdBo bo)
	{
		return roleBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统角色详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public RoleDto getOnlyOne(RoleSearchBo searchBo)
	{
		return roleBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统角色
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public RoleDto delete(IdBo bo)
	{
		return roleBiz.delete(bo);
	}

	/**
     * 新增系统角色
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public RoleDto create(@RequestBody RoleBo bo)
	{
		return roleBiz.create(bo);
	}
	
	/**
     * 更新系统角色
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public RoleDto update(@RequestBody RoleBo bo)
	{
		return roleBiz.update(bo);
	}
	
}
