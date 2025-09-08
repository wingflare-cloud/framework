package com.wingflare.module.user.controller;


import com.wingflare.api.security.annotation.BusinessSystem;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.facade.module.user.biz.RoleBiz;
import com.wingflare.facade.module.user.bo.RoleBO;
import com.wingflare.facade.module.user.bo.RoleSearchBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.module.user.PermissionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


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

    private final RoleBiz roleBiz;

	public RoleController(RoleBiz roleBiz) {
		this.roleBiz = roleBiz;
	}

	/**
     * 查询系统角色列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_VIEW)
	@BusinessSystem("base")
    public PageDto<RoleDTO> list(RoleSearchBO bo)
    {
		return roleBiz.list(bo);
    }

	/**
     * 查询系统角色详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_VIEW)
	@BusinessSystem("base")
	public RoleDTO get(IdBo bo)
	{
		return roleBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统角色详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_VIEW)
	@BusinessSystem("base")
	public RoleDTO getOnlyOne(RoleSearchBO searchBo)
	{
		return roleBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统角色
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_DELETE)
	@BusinessSystem("base")
	public RoleDTO delete(@RequestBody IdBo bo)
	{
		return roleBiz.delete(bo);
	}

	/**
     * 新增系统角色
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_CREATE)
	@BusinessSystem("base")
	public RoleDTO create(@RequestBody RoleBO bo)
	{
		return roleBiz.create(bo);
	}
	
	/**
     * 更新系统角色
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_UPDATE)
	@BusinessSystem("base")
	public RoleDTO update(@RequestBody RoleBO bo)
	{
		return roleBiz.update(bo);
	}
	
}
