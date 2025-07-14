package com.wingflare.facade.module.base.dto;


import java.util.List;

/**
 * 系统菜单Dto
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
public class SimpleMenuDTO
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
    private List<SimpleMenuDTO> children;

    public String getMenuId() {
        return menuId;
    }

    public SimpleMenuDTO setMenuId(String menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public SimpleMenuDTO setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public SimpleMenuDTO setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getHide() {
        return hide;
    }

    public SimpleMenuDTO setHide(Integer hide) {
        this.hide = hide;
        return this;
    }

    public Integer getConstant() {
        return constant;
    }

    public SimpleMenuDTO setConstant(Integer constant) {
        this.constant = constant;
        return this;
    }

    public Integer getMultiTab() {
        return multiTab;
    }

    public SimpleMenuDTO setMultiTab(Integer multiTab) {
        this.multiTab = multiTab;
        return this;
    }

    public String getMenuType() {
        return menuType;
    }

    public SimpleMenuDTO setMenuType(String menuType) {
        this.menuType = menuType;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public SimpleMenuDTO setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        return this;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public SimpleMenuDTO setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public SimpleMenuDTO setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getLangKey() {
        return langKey;
    }

    public SimpleMenuDTO setLangKey(String langKey) {
        this.langKey = langKey;
        return this;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public SimpleMenuDTO setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getIconType() {
        return iconType;
    }

    public SimpleMenuDTO setIconType(String iconType) {
        this.iconType = iconType;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public SimpleMenuDTO setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public String getRoutePath() {
        return routePath;
    }

    public SimpleMenuDTO setRoutePath(String routePath) {
        this.routePath = routePath;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public SimpleMenuDTO setComponent(String component) {
        this.component = component;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public SimpleMenuDTO setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getHref() {
        return href;
    }

    public SimpleMenuDTO setHref(String href) {
        this.href = href;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public SimpleMenuDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public List<SimpleMenuDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SimpleMenuDTO> children) {
        this.children = children;
    }
}
