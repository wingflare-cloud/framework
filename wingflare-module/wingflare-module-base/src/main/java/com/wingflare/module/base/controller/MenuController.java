package com.wingflare.module.base.controller;


import com.wingflare.facade.module.base.biz.MenuBiz;
import com.wingflare.facade.module.base.bo.MenuBo;
import com.wingflare.facade.module.base.bo.MenuSearchBo;
import com.wingflare.facade.module.base.dto.MenuDto;
import com.wingflare.facade.module.base.dto.SimpleMenuDto;
import com.wingflare.lib.spring.configure.properties.BusinessSystemProperties;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 系统菜单Controller
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
@Controller
@RequestMapping("/menu")
public class MenuController
{

	@Resource
    private MenuBiz menuBiz;

	@Resource
	private BusinessSystemProperties properties;

    /**
     * 查询系统菜单列表
     */
	@RequestMapping(value="/list", method={RequestMethod.GET})
	@ResponseBody
    public PageDto<MenuDto> list(MenuSearchBo bo) throws Throwable
    {
		return menuBiz.list(bo);
    }

	/**
     * 查询系统菜单详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public MenuDto get(IdBo bo)
	{
		return menuBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统菜单详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	public MenuDto getOnlyOne(MenuSearchBo searchBo) throws Throwable
	{
		return menuBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统菜单
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	public void delete(@RequestBody IdBo bo)
	{
		menuBiz.delete(bo);
	}

	/**
     * 新增系统菜单
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public MenuDto create(@RequestBody MenuBo bo)
	{
		return menuBiz.create(bo);
	}
	
	/**
     * 更新系统菜单
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	public MenuDto update(@RequestBody MenuBo bo)
	{
		return menuBiz.update(bo);
	}

	/**
	 * 获取系统树形菜单
	 */
	@RequestMapping(value = "/tree", method = {RequestMethod.GET})
	@ResponseBody
	public List<SimpleMenuDto> tree(String systemCode) {
		return menuBiz.tree(systemCode);
	}

	/**
	 * 获取系统树形菜单
	 */
	@RequestMapping(value = "/allTree", method = {RequestMethod.GET})
	@ResponseBody
	public List<SimpleMenuDto> allTree() {
		Set<String> names = properties.getNames();
		List<SimpleMenuDto> list = new ArrayList<>();

		for (String name : names) {
			SimpleMenuDto dto = new SimpleMenuDto();
			dto.setMenuType("system");
			dto.setMenuName(name);
			dto.setMenuId(name);
			dto.setState(1);
			dto.setChildren(menuBiz.tree(name));
			list.add(dto);
		}

		return list;
	}
	
}
