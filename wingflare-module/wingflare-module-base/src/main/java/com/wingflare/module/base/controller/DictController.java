package com.wingflare.module.base.controller;


import com.wingflare.facade.module.base.biz.DictBiz;
import com.wingflare.facade.module.base.bo.DictBo;
import com.wingflare.facade.module.base.bo.DictSearchBo;
import com.wingflare.facade.module.base.dto.DictDto;
import com.wingflare.facade.module.base.dto.SimpleDictDto;
import com.wingflare.lib.security.annotation.RequiresPermissions;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.module.base.PermissionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统字典Controller
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
@Controller
@RequestMapping("/dict")
public class DictController
{

	@Resource
    private DictBiz dictBiz;

    /**
     * 查询系统字典列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_VIEW)
    public PageDto<DictDto> list(DictSearchBo bo)
    {
		return dictBiz.list(bo);
    }

	/**
     * 查询系统字典详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_VIEW)
	public DictDto get(IdBo bo)
	{
		return dictBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统字典详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_VIEW)
	public DictDto getOnlyOne(DictSearchBo searchBo)
	{
		return dictBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统字典
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_DELETE)
	public void delete(@RequestBody IdBo bo)
	{
		dictBiz.delete(bo);
	}

	/**
     * 新增系统字典
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_CREATE)
	public DictDto create(@RequestBody DictBo bo)
	{
		return dictBiz.create(bo);
	}
	
	/**
     * 更新系统字典
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_UPDATE)
	public DictDto update(@RequestBody DictBo bo)
	{
		return dictBiz.update(bo);
	}

	/**
	 * 刷新系统字典
	 */
	@RequestMapping(value = "/refresh", method = {RequestMethod.POST})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_CACHE_REFRESH)
	public void refresh()
	{
		dictBiz.refresh();
	}

	/**
	 * 获取全部字典值
	 */
	@RequestMapping(value = "/getAll", method = {RequestMethod.GET})
	@ResponseBody
	public List<SimpleDictDto> getAll()
	{
		return dictBiz.getAllDictByCache();
	}
	
}
