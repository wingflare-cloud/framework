package com.wingflare.module.user.controller;


import com.wingflare.api.security.annotation.BusinessSystem;
import com.wingflare.api.security.annotation.InternalApi;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.api.security.annotation.Secret;
import com.wingflare.facade.module.user.biz.UserBiz;
import com.wingflare.facade.module.user.bo.UpdatePasswdBO;
import com.wingflare.facade.module.user.bo.UserBindRoleBO;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.bo.UserSearchBO;
import com.wingflare.facade.module.user.dto.UserDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.module.user.PermissionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户Controller
 * 
 * @author naizui_ycx
 * @date Sun Mar 05 09:45:12 CST 2023
 */
@Controller
@RequestMapping("/user")
public class UserController
{

    private final UserBiz userBiz;

	public UserController(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	/**
     * 查询系统用户列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.USER_VIEW)
	@BusinessSystem("base")
    public PageDto<UserDTO> list(UserSearchBO bo)
    {
		return userBiz.list(bo);
    }

	/**
     * 查询系统用户详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.USER_VIEW)
	@BusinessSystem("base")
	public UserDTO get(IdBo bo)
	{
		return userBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统用户详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.USER_VIEW)
	@BusinessSystem("base")
	public UserDTO getOnlyOne(UserSearchBO searchBo)
	{
		return userBiz.getOnlyOne(searchBo);
	}

	/**
     * 新增系统用户
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	@Secret
	@RequiresPermissions(PermissionCode.USER_CREATE)
	@BusinessSystem("base")
	public UserDTO create(@Secret @RequestBody UserBO bo)
	{
		return userBiz.create(bo);
	}
	
	/**
     * 更新系统用户
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	@RequiresPermissions(PermissionCode.USER_UPDATE)
	@BusinessSystem("base")
	public UserDTO update(@RequestBody UserBO bo)
	{
		return userBiz.update(bo);
	}

	/**
	 * 删除系统用户
	 */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	@RequiresPermissions(PermissionCode.USER_DELETE)
	@BusinessSystem("base")
	public UserDTO delete(@RequestBody IdBo bo)
	{
		return userBiz.delete(bo);
	}

	/**
	 * 通过登录名获取登录用户
	 */
	@RequestMapping(value = "/getUserByLoginName", method = {RequestMethod.GET})
	@ResponseBody
	@InternalApi
	public UserDTO getUserByLoginName(@RequestParam(value = "loginName") String loginName)
	{
		return userBiz.getUserByLoginName(loginName);
	}

	/**
	 * 更新用户密码
	 */
	@RequestMapping(value = "/updatePasswd", method = {RequestMethod.PUT})
	@ResponseBody
	@Secret
	public void updatePasswd(@Secret @RequestBody UpdatePasswdBO bo)
	{
		userBiz.updatePasswd(bo);
	}

	/**
	 * 用户绑定角色
	 */
	@RequestMapping(value = "/role/bind", method = {RequestMethod.PUT})
	@ResponseBody
	public void userBindRole(@RequestBody UserBindRoleBO bo)
	{
		userBiz.userBindRole(bo);
	}

	/**
	 * 获取登陆用户附加信息
	 */
	@RequestMapping(value = "/getAttribute", method = {RequestMethod.GET})
	@ResponseBody
	@InternalApi
	public Map<String, Object> getAttribute(IdBo bo)
	{
		return new HashMap<>();
	}
	
}
