package com.wingflare.module.user.controller;


import com.wingflare.facade.module.user.biz.RolePermissionBiz;
import com.wingflare.facade.module.user.bo.PermissionCodesExistBo;
import com.wingflare.lib.security.annotation.RequiresPermissions;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.module.user.PermissionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

	@Resource
    private RolePermissionBiz rolePermissionBiz;

	/**
	 * 角色权限保存
	 */
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_SAVE_PERMISSION)
	public Boolean save(@RequestBody PermissionCodesExistBo existBo)
	{
		return rolePermissionBiz.savePermission(existBo);
	}

	/**
	 * 获取角色权限
	 */
	@RequestMapping(value = "/all", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.ROLE_PERMISSION_VIEW)
	public List<PermissionCodesExistBo.CodesExist> all(IdBo bo)
	{
		return rolePermissionBiz.permission(bo);
	}
	
}
