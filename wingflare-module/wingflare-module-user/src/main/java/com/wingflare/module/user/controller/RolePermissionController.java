package com.wingflare.module.user.controller;


import com.wingflare.api.mvc.RequestMethod;
import com.wingflare.api.mvc.annotation.Controller;
import com.wingflare.api.mvc.annotation.RequestBody;
import com.wingflare.api.mvc.annotation.RequestMapping;
import com.wingflare.api.mvc.annotation.ResponseBody;
import com.wingflare.api.security.annotation.BusinessSystem;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.facade.module.user.biz.RolePermissionBiz;
import com.wingflare.facade.module.user.bo.PermissionCodesExistBO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.module.user.PermissionCode;

import java.util.List;

/**
 * 系统角色权限Controller
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
@Controller
@RequestMapping("/role/permission")
public class RolePermissionController
{

    private final RolePermissionBiz rolePermissionBiz;

	public RolePermissionController(RolePermissionBiz rolePermissionBiz) {
		this.rolePermissionBiz = rolePermissionBiz;
	}

	/**
	 * 角色权限保存
	 */
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_SAVE_PERMISSION)
	@BusinessSystem("base")
	public Boolean save(@RequestBody PermissionCodesExistBO existBo)
	{
		return rolePermissionBiz.savePermission(existBo);
	}

	/**
	 * 获取角色权限
	 */
	@RequestMapping(value = "/all", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_PERMISSION_VIEW)
	@BusinessSystem("base")
	public List<PermissionCodesExistBO.CodesExist> all(IdBo bo)
	{
		return rolePermissionBiz.permission(bo);
	}
	
}
