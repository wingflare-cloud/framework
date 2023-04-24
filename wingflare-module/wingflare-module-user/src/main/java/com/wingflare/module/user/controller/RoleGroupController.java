package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.RoleGroupBiz;
import com.wingflare.facade.module.user.bo.RoleGroupBo;
import com.wingflare.facade.module.user.bo.RoleGroupSearchBo;
import com.wingflare.facade.module.user.dto.RoleGroupDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 系统角色分组表Controller
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
@Controller
@RequestMapping("/roleGroup")
public class RoleGroupController
{

	@Resource
    private RoleGroupBiz roleGroupBiz;

    /**
     * 查询系统角色分组表列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<RoleGroupDto> list(RoleGroupSearchBo bo) throws Throwable
    {
		return roleGroupBiz.list(bo);
    }

	/**
     * 查询系统角色分组表详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public RoleGroupDto get(IdBo bo)
	{
		return roleGroupBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统角色分组表详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public RoleGroupDto getOnlyOne(RoleGroupSearchBo searchBo) throws Throwable
	{
		return roleGroupBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统角色分组表
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		roleGroupBiz.delete(bo);
	}

	/**
     * 新增系统角色分组表
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public RoleGroupDto create(@RequestBody RoleGroupBo bo)
	{
		return roleGroupBiz.create(bo);
	}
	
	/**
     * 更新系统角色分组表
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public RoleGroupDto update(@RequestBody RoleGroupBo bo)
	{
		return roleGroupBiz.update(bo);
	}
	
}
