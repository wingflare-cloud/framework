package com.wingflare.module.base.controller;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.security.annotation.BusinessSystem;
import com.wingflare.api.security.annotation.InternalApi;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.facade.module.base.biz.SettingBiz;
import com.wingflare.facade.module.base.bo.SettingBO;
import com.wingflare.facade.module.base.bo.SettingSearchBO;
import com.wingflare.facade.module.base.dto.SettingDTO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.module.base.PermissionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 系统设置Controller
 * 
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
@Controller
@RequestMapping("/setting")
public class SettingController
{

    private final SettingBiz settingBiz;

	public SettingController(SettingBiz settingBiz) {
		this.settingBiz = settingBiz;
	}

	/**
     * 查询系统设置列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.SETTING_VIEW)
	@BusinessSystem("base")
    public PageDto<SettingDTO> list(SettingSearchBO bo)
    {
		return settingBiz.list(bo);
    }

	/**
     * 查询系统设置详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.SETTING_VIEW)
	@BusinessSystem("base")
	public SettingDTO get(IdBo bo)
	{
		return settingBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统设置详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.SETTING_VIEW)
	@BusinessSystem("base")
	public SettingDTO getOnlyOne(SettingSearchBO searchBo)
	{
		return settingBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统设置
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	@RequiresPermissions(PermissionCode.SETTING_DELETE)
	@BusinessSystem("base")
	public void delete(@RequestBody IdBo bo)
	{
		settingBiz.delete(bo);
	}

	/**
     * 新增系统设置
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	@RequiresPermissions(PermissionCode.SETTING_CREATE)
	@BusinessSystem("base")
	public SettingDTO create(@RequestBody SettingBO bo)
	{
		return settingBiz.create(bo);
	}
	
	/**
     * 更新系统设置
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	@RequiresPermissions(PermissionCode.SETTING_UPDATE)
	@BusinessSystem("base")
	public SettingDTO update(@RequestBody SettingBO bo)
	{
		return settingBiz.update(bo);
	}

	/**
	 * 保存系统设置
	 */
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	@ResponseBody
	@InternalApi
	public void save(@RequestBody SettingBO bo)
	{
		settingBiz.save(bo);
	}
	
}
