package com.wingflare.module.base.controller;


import com.wingflare.facade.module.base.biz.SettingBiz;
import com.wingflare.facade.module.base.bo.SettingBo;
import com.wingflare.facade.module.base.bo.SettingSearchBo;
import com.wingflare.facade.module.base.dto.SettingDto;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

	@Resource
    private SettingBiz settingBiz;

    /**
     * 查询系统设置列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<SettingDto> list(SettingSearchBo bo)
    {
		return settingBiz.list(bo);
    }

	/**
     * 查询系统设置详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public SettingDto get(IdBo bo)
	{
		return settingBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统设置详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public SettingDto getOnlyOne(SettingSearchBo searchBo)
	{
		return settingBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统设置
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(@RequestBody IdBo bo)
	{
		settingBiz.delete(bo);
	}

	/**
     * 新增系统设置
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public SettingDto create(@RequestBody SettingBo bo)
	{
		return settingBiz.create(bo);
	}
	
	/**
     * 更新系统设置
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public SettingDto update(@RequestBody SettingBo bo)
	{
		return settingBiz.update(bo);
	}

	/**
	 * 保存系统设置
	 */
	@RequestMapping(value = "/save", method = {RequestMethod.POST})
	@ResponseBody
	public void save(@RequestBody SettingBo bo)
	{
		settingBiz.save(bo);
	}
	
}
