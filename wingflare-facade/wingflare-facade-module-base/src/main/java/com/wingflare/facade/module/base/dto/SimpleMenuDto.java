package com.wingflare.facade.module.base.dto;


import java.util.List;

/**
 * 系统菜单Dto
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
public class SimpleMenuDto
{
	
    private String menuId;
	
	/**
     * 父级菜单id
     */
    private String parentMenuId;
	
	/**
     * 启禁用状态
     */
    private Integer state;
	
	/**
     * 菜单状态
     */
    private String menuType;
	
	/**
     * 系统代码
     */
    private String systemCode;
	
	/**
     * 权限code
     */
    private String permissionCode;
	
	/**
     * 菜单名称
     */
    private String menuName;
	
	/**
     * 菜单图标
     */
    private String menuIcon;
	
	/**
     * 菜单路径
     */
    private String menuPath;
	
	/**
     * 菜单代码
     */
    private String menuCode;
	
	/**
     * 外部链接
     */
    private String href;
	
	/**
     * 排序
     */
    private Long sort;

    /**
     * 子菜单
     */
    private List<SimpleMenuDto> children;
	
    public SimpleMenuDto setMenuId(String menuId)
    {
        this.menuId = menuId;
        return this;
    }

    public String getMenuId()
    {
        return menuId;
    }
	
    public SimpleMenuDto setParentMenuId(String parentMenuId)
    {
        this.parentMenuId = parentMenuId;
        return this;
    }

    public String getParentMenuId()
    {
        return parentMenuId;
    }
	
    public SimpleMenuDto setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public SimpleMenuDto setMenuType(String menuType)
    {
        this.menuType = menuType;
        return this;
    }

    public String getMenuType()
    {
        return menuType;
    }
	
    public SimpleMenuDto setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public SimpleMenuDto setPermissionCode(String permissionCode)
    {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getPermissionCode()
    {
        return permissionCode;
    }
	
    public SimpleMenuDto setMenuName(String menuName)
    {
        this.menuName = menuName;
        return this;
    }

    public String getMenuName()
    {
        return menuName;
    }
	
    public SimpleMenuDto setMenuIcon(String menuIcon)
    {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getMenuIcon()
    {
        return menuIcon;
    }
	
    public SimpleMenuDto setMenuPath(String menuPath)
    {
        this.menuPath = menuPath;
        return this;
    }

    public String getMenuPath()
    {
        return menuPath;
    }
	
    public SimpleMenuDto setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
        return this;
    }

    public String getMenuCode()
    {
        return menuCode;
    }
	
    public SimpleMenuDto setHref(String href)
    {
        this.href = href;
        return this;
    }

    public String getHref()
    {
        return href;
    }
	
    public SimpleMenuDto setSort(Long sort)
    {
        this.sort = sort;
        return this;
    }

    public Long getSort()
    {
        return sort;
    }

    public List<SimpleMenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<SimpleMenuDto> children) {
        this.children = children;
    }
}
