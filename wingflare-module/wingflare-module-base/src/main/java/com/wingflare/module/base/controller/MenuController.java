package com.wingflare.module.base.controller;


import com.wingflare.facade.module.base.biz.MenuBiz;
import com.wingflare.facade.module.base.bo.MenuBo;
import com.wingflare.facade.module.base.bo.MenuSearchBo;
import com.wingflare.facade.module.base.bo.PermissionCodesExistBo;
import com.wingflare.facade.module.base.dto.MenuDto;
import com.wingflare.facade.module.base.dto.MenuPermissionDto;
import com.wingflare.facade.module.base.dto.SimpleMenuDto;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.security.annotation.BusinessSystem;
import com.wingflare.lib.security.annotation.RequiresPermissions;
import com.wingflare.lib.spring.annotation.InternalApi;
import com.wingflare.lib.spring.configure.properties.BusinessSystemProperties;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.enums.OnOffEnum;
import com.wingflare.module.base.PermissionCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
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
	@RequiresPermissions(PermissionCode.MENU_VIEW)
	@BusinessSystem("base")
    public PageDto<MenuDto> list(MenuSearchBo bo) throws Throwable
    {
		return menuBiz.list(bo);
    }

	/**
     * 查询系统菜单详情
     */
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.MENU_VIEW)
	@BusinessSystem("base")
	public MenuDto get(IdBo bo)
	{
		return menuBiz.get(bo);
	}
	
	/**
     * 通过条件查询单个系统菜单详情
     */
	@RequestMapping(value = "/getOnlyOne", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.MENU_VIEW)
	@BusinessSystem("base")
	public MenuDto getOnlyOne(MenuSearchBo searchBo) throws Throwable
	{
		return menuBiz.getOnlyOne(searchBo);
	}

	/**
     * 删除系统菜单
     */
	@RequestMapping(value = "/delete", method = {RequestMethod.DELETE})
	@ResponseBody
	@RequiresPermissions(PermissionCode.MENU_DELETE)
	@BusinessSystem("base")
	public void delete(@RequestBody IdBo bo)
	{
		menuBiz.delete(bo);
	}

	/**
     * 新增系统菜单
     */
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	@RequiresPermissions(PermissionCode.MENU_CREATE)
	@BusinessSystem("base")
	public MenuDto create(@RequestBody MenuBo bo)
	{
		return menuBiz.create(bo);
	}
	
	/**
     * 更新系统菜单
     */
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ResponseBody
	@RequiresPermissions(PermissionCode.MENU_UPDATE)
	@BusinessSystem("base")
	public MenuDto update(@RequestBody MenuBo bo)
	{
		return menuBiz.update(bo);
	}

	/**
	 * 获取系统树形菜单
	 */
	@RequestMapping(value = "/tree", method = {RequestMethod.GET})
	@ResponseBody
	public List<SimpleMenuDto> tree(@RequestParam(value = "systemCode") String systemCode) {
		return menuBiz.tree(new MenuSearchBo()
				.setEq_systemCode(systemCode)
				.setEq_state(OnOffEnum.ON.getValue())
		);
	}

	/**
	 * 获取系统树形菜单
	 */
	@RequestMapping(value = "/allTree", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.MENU_VIEW)
	@BusinessSystem("base")
	public List<SimpleMenuDto> allTree() {
		Set<String> names = properties.getNames();
		List<SimpleMenuDto> list = new ArrayList<>();

		for (String name : names) {
			SimpleMenuDto dto = new SimpleMenuDto();
			dto.setMenuType("system");
			dto.setMenuName(name);
			dto.setMenuId(name);
			dto.setState(1);
			dto.setChildren(menuBiz.tree(new MenuSearchBo()
					.setEq_systemCode(name)
					.setEq_state(OnOffEnum.ON.getValue()))
			);
			list.add(dto);
		}

		return list;
	}

	/**
	 * 获取全部系统菜单权限
	 */
	@RequestMapping(value = "/menuPermission", method = {RequestMethod.GET})
	@ResponseBody
	@RequiresPermissions(PermissionCode.MENU_PERM_VIEW)
	@BusinessSystem("base")
	public List<MenuPermissionDto> menuPermission() {
		Set<String> names = properties.getNames();
		List<SimpleMenuDto> list = new ArrayList<>();

		for (String name : names) {
			SimpleMenuDto dto = new SimpleMenuDto();
			dto.setMenuType("system");
			dto.setMenuName(name);
			dto.setMenuId(name);
			dto.setState(1);
			dto.setChildren(menuBiz.tree(new MenuSearchBo()
							.setEq_systemCode(name)
							.setEq_state(OnOffEnum.ON.getValue())
							.setEq_constant(OnOffEnum.OFF.getValue())
					)
			);
			list.add(dto);
		}

		return convertTreeToPerm(list);
	}

	/**
	 * 判断权限代码是否存在
	 *
	 * @param existBo
	 *
	 * @return
	 */
	@RequestMapping(value = "/permissionCodesExist", method = {RequestMethod.GET})
	@ResponseBody
	@InternalApi
	public Boolean permissionCodesExist(PermissionCodesExistBo existBo) {
		return menuBiz.permissionCodesExist(existBo);
	}

	/**
	 * 转换树形菜单为权限映射列表
	 *
	 * @param list
	 * @return
	 */
	private List<MenuPermissionDto> convertTreeToPerm(List<SimpleMenuDto> list) {
		if (CollectionUtil.isEmpty(list)) {
			return new ArrayList<>();
		}

		List<MenuPermissionDto> permList = new ArrayList<>();

		list.forEach(dto -> {
			MenuPermissionDto perm = new MenuPermissionDto();
			perm.setKey(dto.getMenuId());
			perm.setName(dto.getMenuName());
			perm.setLangKey(dto.getLangKey());
			perm.setSystemCode(dto.getSystemCode());
			perm.setPermissionCode(dto.getPermissionCode());
			perm.setMenuType(dto.getMenuType());
			perm.setChildren(convertTreeToPerm(dto.getChildren()));
			permList.add(perm);
		});

		return permList;
	}
	
}
