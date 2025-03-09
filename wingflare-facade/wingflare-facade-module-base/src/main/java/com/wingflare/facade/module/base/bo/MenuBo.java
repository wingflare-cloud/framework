package com.wingflare.facade.module.base.bo;

import java.util.Date;

import com.wingflare.facade.module.base.dict.IconTypes;
import com.wingflare.facade.module.base.dict.MenuLayout;
import com.wingflare.facade.module.base.dict.MenuTypes;
import com.wingflare.lib.core.Regexp;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Enum;
import com.wingflare.lib.core.validation.Update;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wingflare.lib.standard.enums.OnOffEnum;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


/**
 * <p>
 * 系统菜单表 业务对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-06
 */
public class MenuBo {

    @NotBlank(message = "sys.menuId.notBlank", groups = Update.class)
    @Pattern(regexp = Regexp.SNOWFLAKE_ID, message = "sys.menuId.formatError", groups = Update.class)
    private String menuId;

    /**
     * 父级菜单id 
     */
    @Pattern(regexp = Regexp.SNOWFLAKE_ID, message = "sys.parentMenuId.formatError", groups = Create.class)
    private String parentMenuId;

    /**
     * 启禁用状态 
     */
    @Enum(clazz = OnOffEnum.class, method = "getValue", message = "sys.menu.stateFormatError",
            groups = {Create.class, Update.class})
    private Integer state;

    /**
     * 隐藏状态 
     */
    @Enum(clazz = OnOffEnum.class, method = "getValue", message = "sys.menu.hideFormatError",
            groups = {Create.class, Update.class})
    private Integer hide;

    /**
     * 是否常量路由
     */
    @Enum(clazz = OnOffEnum.class, method = "getValue", message = "sys.menu.constantFormatError",
            groups = {Create.class, Update.class})
    private Integer constant;

    /**
     * 多标签页 
     */
    @Enum(clazz = OnOffEnum.class, method = "getValue", message = "sys.menu.multiTabFormatError",
            groups = {Create.class, Update.class})
    private Integer multiTab;

    /**
     * 菜单类型 
     */
    @Enum(clazz = MenuTypes.class, method = "getValue", message = "sys.menu.typeError", groups = Create.class)
    private String menuType;

    /**
     * 系统代码 
     */
    @NotBlank(message = "sys.systemCode.notBlank", groups = Create.class)
    @Pattern(regexp = "^[a-zA-Z]{2,32}$", message = "sys.systemCode.formatError", groups = Create.class)
    private String systemCode;

    /**
     * 权限code 
     */
    @Length(max = 256, message = "sys.permissionCode.lengthError", groups = {Create.class, Update.class})
    private String permissionCode;

    /**
     * 菜单名称 
     */
    @Length(max = 64, message = "sys.menu.nameLengthError")
    @NotBlank(message = "sys.menu.nameNotBlank", groups = Create.class)
    private String menuName;

    /**
     * 国际化Key 
     */
    @Length(max = 256, message = "sys.menu.langKeyError", groups = {Create.class, Update.class})
    private String langKey;

    /**
     * 菜单图标 
     */
    @Length(max = 256, message = "sys.menu.iconLengthError", groups = {Create.class, Update.class})
    private String menuIcon;

    /**
     * 图标类型(iconify local) 
     */
    @Enum(clazz = IconTypes.class, method = "getValue", message = "sys.menu.iconTypeError", groups = {Create.class, Update.class})
    private String iconType;

    /**
     * 路由名称 
     */
    @Length(max = 256, message = "sys.menu.routeNameLengthError", groups = {Create.class, Update.class})
    private String routeName;

    /**
     * 路由路径 
     */
    @Length(max = 256, message = "sys.menu.routePathLengthError", groups = {Create.class, Update.class})
    private String routePath;

    /**
     * 组件路径 
     */
    @Length(max = 256, message = "sys.menu.componentLengthError", groups = {Create.class, Update.class})
    private String component;

    /**
     * 查询参数 
     */
    @Length(max = 512, message = "sys.menu.queryLengthError", groups = {Create.class, Update.class})
    private String query;

    /**
     * 外部链接 
     */
    @Length(max = 512, message = "sys.menu.hrefLengthError", groups = {Create.class, Update.class})
    private String href;

    /**
     * 排序 
     */
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "sys.menu.sortFormatError",
            groups = {Create.class, Update.class})
    private Integer sort;

    /**
     * 数据版本号 
     */
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "sys.menu.versionFormatError",
            groups = {Create.class, Update.class})
    private Integer version;

    public String getMenuId() {
        return menuId;
    }

    public MenuBo setMenuId(String menuId) {
        this.menuId = menuId;
        return this;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public MenuBo setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public MenuBo setState(Integer state) {
        this.state = state;
        return this;
    }

    public Integer getHide() {
        return hide;
    }

    public MenuBo setHide(Integer hide) {
        this.hide = hide;
        return this;
    }

    public Integer getConstant() {
        return constant;
    }

    public MenuBo setConstant(Integer constant) {
        this.constant = constant;
        return this;
    }

    public Integer getMultiTab() {
        return multiTab;
    }

    public MenuBo setMultiTab(Integer multiTab) {
        this.multiTab = multiTab;
        return this;
    }

    public String getMenuType() {
        return menuType;
    }

    public MenuBo setMenuType(String menuType) {
        this.menuType = menuType;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public MenuBo setSystemCode(String systemCode) {
        this.systemCode = systemCode;
        return this;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public MenuBo setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public MenuBo setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public String getLangKey() {
        return langKey;
    }

    public MenuBo setLangKey(String langKey) {
        this.langKey = langKey;
        return this;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public MenuBo setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getIconType() {
        return iconType;
    }

    public MenuBo setIconType(String iconType) {
        this.iconType = iconType;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public MenuBo setRouteName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    public String getRoutePath() {
        return routePath;
    }

    public MenuBo setRoutePath(String routePath) {
        this.routePath = routePath;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public MenuBo setComponent(String component) {
        this.component = component;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public MenuBo setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getHref() {
        return href;
    }

    public MenuBo setHref(String href) {
        this.href = href;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public MenuBo setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public MenuBo setVersion(Integer version) {
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