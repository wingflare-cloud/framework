package com.wingflare.module.base.controller;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.mvc.RequestMethod;
import com.wingflare.api.mvc.annotation.Controller;
import com.wingflare.api.mvc.annotation.RequestBody;
import com.wingflare.api.mvc.annotation.RequestMapping;
import com.wingflare.api.mvc.annotation.ResponseBody;
import com.wingflare.api.security.annotation.BusinessSystem;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.facade.module.base.biz.DictBiz;
import com.wingflare.facade.module.base.bo.DictBO;
import com.wingflare.facade.module.base.bo.DictSearchBO;
import com.wingflare.facade.module.base.dto.DictDTO;
import com.wingflare.facade.module.base.dto.SimpleDictDTO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.module.base.PermissionCode;

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

    private final DictBiz dictBiz;

	public DictController(DictBiz dictBiz) {
		this.dictBiz = dictBiz;
	}

	/**
     * 查询系统字典列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_VIEW)
	@BusinessSystem("base")
    public PageDto<DictDTO> list(DictSearchBO bo)
    {
		return dictBiz.list(bo);
    }

	/**
     * 查询系统字典详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_VIEW)
	@BusinessSystem("base")
	public DictDTO get(IdBo bo)
	{
		return dictBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统字典详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_VIEW)
	@BusinessSystem("base")
	public DictDTO getOnlyOne(DictSearchBO searchBo)
	{
		return dictBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统字典
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_DELETE)
	@BusinessSystem("base")
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
	@BusinessSystem("base")
	public DictDTO create(@RequestBody DictBO bo)
	{
		return dictBiz.create(bo);
	}
	
	/**
     * 更新系统字典
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_UPDATE)
	@BusinessSystem("base")
	public DictDTO update(@RequestBody DictBO bo)
	{
		return dictBiz.update(bo);
	}

	/**
	 * 刷新系统字典
	 */
	@RequestMapping(value = "/refresh", method = {RequestMethod.POST})
	@ResponseBody
	@RequiresPermissions(PermissionCode.DICT_CACHE_REFRESH)
	@BusinessSystem("base")
	public void refresh()
	{
		dictBiz.refresh();
	}

	/**
	 * 获取全部字典值
	 */
	@RequestMapping(value = "/getAll", method = {RequestMethod.GET})
	@ResponseBody
	public List<SimpleDictDTO> getAll()
	{
		return dictBiz.getAllDictByCache();
	}
	
}
