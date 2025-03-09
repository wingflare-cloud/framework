package com.wingflare.facade.module.base.dto;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 系统菜单表 输出对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-06
 */
public class MenuDto {

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    /**
     * 创建人名称 
     */
    private String createUser;

    /**
     * 创建人id 
     */
    private String createUserId;

    /**
     * 更新人名称 
     */
    private String updateUser;

    /**
     * 更新人id 
     */
    private String updateUserId;

    /**
     * 数据版本号 
     */
    private Integer version;

    public String getMenuId() {
        return menuId;
    }

    public MenuDto setMenuId(String menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public MenuDto setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public MenuDto setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getHide() {
        return hide;
    }

    public MenuDto setHide(Integer hide) {
        this.hide = hide;
        return this;
    }

    public Integer getConstant() {
        return constant;
    }

    public MenuDto setConstant(Integer constant) {
        this.constant = constant;
        return this;
    }

    public Integer getMultiTab() {
        return multiTab;
    }

    public MenuDto setMultiTab(Integer multiTab) {
        this.multiTab = multiTab;
        return this;
    }

    public String getMenuType() {
        return menuType;
    }

    public MenuDto setMenuType(String menuType) {
        this.menuType = menuType;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public MenuDto setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        return this;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public MenuDto setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public MenuDto setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getLangKey() {
        return langKey;
    }

    public MenuDto setLangKey(String langKey) {
        this.langKey = langKey;
        return this;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public MenuDto setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getIconType() {
        return iconType;
    }

    public MenuDto setIconType(String iconType) {
        this.iconType = iconType;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public MenuDto setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public String getRoutePath() {
        return routePath;
    }

    public MenuDto setRoutePath(String routePath) {
        this.routePath = routePath;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public MenuDto setComponent(String component) {
        this.component = component;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public MenuDto setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getHref() {
        return href;
    }

    public MenuDto setHref(String href) {
        this.href = href;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public MenuDto setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public MenuDto setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public MenuDto setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public MenuDto setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public MenuDto setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public MenuDto setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public MenuDto setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public MenuDto setVersion(Integer version) {
        this.version = version;
        return this;
    }

	@Override
	public String toString() {
        return "MenuDo{" +
        "menuId = " + menuId +
        ", parentMenuId = " + parentMenuId +
        ", state = " + state +
        ", hide = " + hide +
                ", constant = " + constant +
        ", multiTab = " + multiTab +
        ", menuType = " + menuType +
        ", systemCode = " + systemCode +
        ", permissionCode = " + permissionCode +
        ", menuName = " + menuName +
        ", langKey = " + langKey +
        ", menuIcon = " + menuIcon +
        ", iconType = " + iconType +
        ", routeName = " + routeName +
        ", routePath = " + routePath +
        ", component = " + component +
        ", query = " + query +
        ", href = " + href +
        ", sort = " + sort +
        ", version = " + version +
        "}";
    }

}