package com.wingflare.facade.module.base.dto;


import java.math.BigInteger;
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
     * 隐藏状态
     */
    private Integer hide;

    /**
     * 常量路由
     */
    private Integer constant;

    /**
     * 多标签页
     */
    private Integer multiTab;

    /**
     * 菜单类型
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
     * 国际化Key
     */
    private String langKey;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 图标类型(iconify local)
     */
    private String iconType;

    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 路由路径
     */
    private String routePath;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 查询参数
     */
    private String query;

    /**
     * 外部链接
     */
    private String href;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 子菜单
     */
    private List<SimpleMenuDto> children;

    public String getMenuId() {
        return menuId;
    }

    public SimpleMenuDto setMenuId(String menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public SimpleMenuDto setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public SimpleMenuDto setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getHide() {
        return hide;
    }

    public SimpleMenuDto setHide(Integer hide) {
        this.hide = hide;
        return this;
    }

    public Integer getConstant() {
        return constant;
    }

    public SimpleMenuDto setConstant(Integer constant) {
        this.constant = constant;
        return this;
    }

    public Integer getMultiTab() {
        return multiTab;
    }

    public SimpleMenuDto setMultiTab(Integer multiTab) {
        this.multiTab = multiTab;
        return this;
    }

    public String getMenuType() {
        return menuType;
    }

    public SimpleMenuDto setMenuType(String menuType) {
        this.menuType = menuType;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public SimpleMenuDto setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        return this;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public SimpleMenuDto setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public SimpleMenuDto setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getLangKey() {
        return langKey;
    }

    public SimpleMenuDto setLangKey(String langKey) {
        this.langKey = langKey;
        return this;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public SimpleMenuDto setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getIconType() {
        return iconType;
    }

    public SimpleMenuDto setIconType(String iconType) {
        this.iconType = iconType;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public SimpleMenuDto setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public String getRoutePath() {
        return routePath;
    }

    public SimpleMenuDto setRoutePath(String routePath) {
        this.routePath = routePath;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public SimpleMenuDto setComponent(String component) {
        this.component = component;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public SimpleMenuDto setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getHref() {
        return href;
    }

    public SimpleMenuDto setHref(String href) {
        this.href = href;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public SimpleMenuDto setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public List<SimpleMenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<SimpleMenuDto> children) {
        this.children = children;
    }
}
