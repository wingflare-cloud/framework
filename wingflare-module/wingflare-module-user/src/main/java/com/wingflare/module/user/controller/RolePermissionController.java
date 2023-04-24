package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.RolePermissionBiz;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.RolePermissionBo;
import com.wingflare.facade.module.user.bo.RolePermissionSearchBo;
import com.wingflare.facade.module.user.dto.RolePermissionDto;
import com.wingflare.lib.standard.PageDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 系统角色权限Controller
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
@Controller
@RequestMapping("/rolePermission")
public class RolePermissionController
{

	@Resource
    private RolePermissionBiz rolePermissionBiz;

    /**
     * 查询系统角色权限列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<RolePermissionDto> list(RolePermissionSearchBo bo) throws Throwable
    {
		return rolePermissionBiz.list(bo);
    }

	/**
     * 查询系统角色权限详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public RolePermissionDto get(IdBo bo)
	{
		return rolePermissionBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统角色权限详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public RolePermissionDto getOnlyOne(RolePermissionSearchBo searchBo) throws Throwable
	{
		return rolePermissionBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统角色权限
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(IdBo bo)
	{
		rolePermissionBiz.delete(bo);
	}

	/**
     * 新增系统角色权限
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public RolePermissionDto create(@RequestBody RolePermissionBo bo)
	{
		return rolePermissionBiz.create(bo);
	}
	
	/**
     * 更新系统角色权限
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public RolePermissionDto update(@RequestBody RolePermissionBo bo)
	{
		return rolePermissionBiz.update(bo);
	}
	
}
