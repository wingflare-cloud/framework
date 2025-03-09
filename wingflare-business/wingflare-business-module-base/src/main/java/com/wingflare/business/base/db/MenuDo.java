package com.wingflare.business.base.db;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import java.util.Date;

/**
 * <p>
 * 系统菜单表 数据对象
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-06
 */
@TableName("sys_menu")
public class MenuDo extends BaseDoAbstract {

    @TableId(value = "menu_id", type = IdType.ASSIGN_ID)
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

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    /**
     * 创建人名称 
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建人id 
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUserId;

    /**
     * 更新人名称 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 更新人id 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    /**
     * 删除状态 
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDelete;

    /**
     * 数据版本号 
     */
    @TableField(fill = FieldFill.INSERT)
    @Version
    private Integer version;

	@Override
    public void setPk(String menuId)
    {
        setMenuId(menuId);
    }

	@Override
    public String getPk()
    {
        return getMenuId();
    }

    public String getMenuId() {
        return menuId;
    }

    public MenuDo setMenuId(String menuId) {
        addNewField("menuId");
        this.menuId = menuId;
        return this;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public MenuDo setParentMenuId(String parentMenuId) {
        addNewField("parentMenuId");
        this.parentMenuId = parentMenuId;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public MenuDo setState(Integer state) {
        addNewField("state");
        this.state = state;
        return this;
    }

    public Integer getHide() {
        return hide;
    }

    public MenuDo setHide(Integer hide) {
        addNewField("hide");
        this.hide = hide;
        return this;
    }

    public Integer getConstant() {
        return constant;
    }

    public MenuDo setConstant(Integer constant) {
        addNewField("constant");
        this.constant = constant;
        return this;
    }

    public Integer getMultiTab() {
        return multiTab;
    }

    public MenuDo setMultiTab(Integer multiTab) {
        addNewField("multiTab");
        this.multiTab = multiTab;
        return this;
    }

    public String getMenuType() {
        return menuType;
    }

    public MenuDo setMenuType(String menuType) {
        addNewField("menuType");
        this.menuType = menuType;
        return this;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public MenuDo setSystemCode(String systemCode) {
        addNewField("systemCode");
        this.systemCode = systemCode;
        return this;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public MenuDo setPermissionCode(String permissionCode) {
        addNewField("permissionCode");
        this.permissionCode = permissionCode;
        return this;
    }

    public String getMenuName() {
        return menuName;
    }

    public MenuDo setMenuName(String menuName) {
        addNewField("menuName");
        this.menuName = menuName;
        return this;
    }

    public String getLangKey() {
        return langKey;
    }

    public MenuDo setLangKey(String langKey) {
        addNewField("langKey");
        this.langKey = langKey;
        return this;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public MenuDo setMenuIcon(String menuIcon) {
        addNewField("menuIcon");
        this.menuIcon = menuIcon;
        return this;
    }

    public String getIconType() {
        return iconType;
    }

    public MenuDo setIconType(String iconType) {
        addNewField("iconType");
        this.iconType = iconType;
        return this;
    }

    public String getRouteName() {
        return routeName;
    }

    public MenuDo setRouteName(String routeName) {
        addNewField("routeName");
        this.routeName = routeName;
        return this;
    }

    public String getRoutePath() {
        return routePath;
    }

    public MenuDo setRoutePath(String routePath) {
        addNewField("routePath");
        this.routePath = routePath;
        return this;
    }

    public String getComponent() {
        return component;
    }

    public MenuDo setComponent(String component) {
        addNewField("component");
        this.component = component;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public MenuDo setQuery(String query) {
        addNewField("query");
        this.query = query;
        return this;
    }

    public String getHref() {
        return href;
    }

    public MenuDo setHref(String href) {
        addNewField("href");
        this.href = href;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public MenuDo setSort(Integer sort) {
        addNewField("sort");
        this.sort = sort;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public MenuDo setCreatedTime(Date createdTime) {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public MenuDo setUpdatedTime(Date updatedTime) {
        addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public MenuDo setCreateUser(String createUser) {
        addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public MenuDo setCreateUserId(String createUserId) {
        addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public MenuDo setUpdateUser(String updateUser) {
        addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public MenuDo setUpdateUserId(String updateUserId) {
        addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public MenuDo setIsDelete(Integer isDelete) {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public MenuDo setVersion(Integer version) {
        addNewField("version");
        this.version = version;
        return this;
    }

	@Override
    public void clearNullNewField() {

	    if (getMenuId() == null) {
            removeNewField("menuId");
        }

	    if (getParentMenuId() == null) {
            removeNewField("parentMenuId");
        }

	    if (getState() == null) {
            removeNewField("state");
        }

	    if (getHide() == null) {
            removeNewField("hide");
        }

	    if (getMultiTab() == null) {
            removeNewField("multiTab");
        }

	    if (getMenuType() == null) {
            removeNewField("menuType");
        }

	    if (getSystemCode() == null) {
            removeNewField("systemCode");
        }

	    if (getPermissionCode() == null) {
            removeNewField("permissionCode");
        }

	    if (getMenuName() == null) {
            removeNewField("menuName");
        }

	    if (getLangKey() == null) {
            removeNewField("langKey");
        }

	    if (getMenuIcon() == null) {
            removeNewField("menuIcon");
        }

	    if (getIconType() == null) {
            removeNewField("iconType");
        }

	    if (getRouteName() == null) {
            removeNewField("routeName");
        }

	    if (getRoutePath() == null) {
            removeNewField("routePath");
        }

	    if (getComponent() == null) {
            removeNewField("component");
        }

	    if (getQuery() == null) {
            removeNewField("query");
        }

	    if (getHref() == null) {
            removeNewField("href");
        }

	    if (getSort() == null) {
            removeNewField("sort");
        }

	    if (getCreatedTime() == null) {
            removeNewField("createdTime");
        }

	    if (getUpdatedTime() == null) {
            removeNewField("updatedTime");
        }

	    if (getCreateUser() == null) {
            removeNewField("createUser");
        }

	    if (getCreateUserId() == null) {
            removeNewField("createUserId");
        }

	    if (getUpdateUser() == null) {
            removeNewField("updateUser");
        }

	    if (getUpdateUserId() == null) {
            removeNewField("updateUserId");
        }

	    if (getIsDelete() == null) {
            removeNewField("isDelete");
        }

	    if (getVersion() == null) {
            removeNewField("version");
        }
    }

    public MenuDo setOnNew(MenuDo newDo)
    {
        MenuDo oldFieldObj = new MenuDo();

        if (newDo.getMenuId() != null && !newDo.getMenuId().equals(getMenuId())) {
            oldFieldObj.setMenuId(getMenuId());
            setMenuId(newDo.getMenuId());
        }

        if (newDo.getParentMenuId() != null && !newDo.getParentMenuId().equals(getParentMenuId())) {
            oldFieldObj.setParentMenuId(getParentMenuId());
            setParentMenuId(newDo.getParentMenuId());
        }

        if (newDo.getState() != null && !newDo.getState().equals(getState())) {
            oldFieldObj.setState(getState());
            setState(newDo.getState());
        }

        if (newDo.getHide() != null && !newDo.getHide().equals(getHide())) {
            oldFieldObj.setHide(getHide());
            setHide(newDo.getHide());
        }

        if (newDo.getMultiTab() != null && !newDo.getMultiTab().equals(getMultiTab())) {
            oldFieldObj.setMultiTab(getMultiTab());
            setMultiTab(newDo.getMultiTab());
        }

        if (newDo.getMenuType() != null && !newDo.getMenuType().equals(getMenuType())) {
            oldFieldObj.setMenuType(getMenuType());
            setMenuType(newDo.getMenuType());
        }

        if (newDo.getSystemCode() != null && !newDo.getSystemCode().equals(getSystemCode())) {
            oldFieldObj.setSystemCode(getSystemCode());
            setSystemCode(newDo.getSystemCode());
        }

        if (newDo.getPermissionCode() != null && !newDo.getPermissionCode().equals(getPermissionCode())) {
            oldFieldObj.setPermissionCode(getPermissionCode());
            setPermissionCode(newDo.getPermissionCode());
        }

        if (newDo.getMenuName() != null && !newDo.getMenuName().equals(getMenuName())) {
            oldFieldObj.setMenuName(getMenuName());
            setMenuName(newDo.getMenuName());
        }

        if (newDo.getLangKey() != null && !newDo.getLangKey().equals(getLangKey())) {
            oldFieldObj.setLangKey(getLangKey());
            setLangKey(newDo.getLangKey());
        }

        if (newDo.getMenuIcon() != null && !newDo.getMenuIcon().equals(getMenuIcon())) {
            oldFieldObj.setMenuIcon(getMenuIcon());
            setMenuIcon(newDo.getMenuIcon());
        }

        if (newDo.getIconType() != null && !newDo.getIconType().equals(getIconType())) {
            oldFieldObj.setIconType(getIconType());
            setIconType(newDo.getIconType());
        }

        if (newDo.getRouteName() != null && !newDo.getRouteName().equals(getRouteName())) {
            oldFieldObj.setRouteName(getRouteName());
            setRouteName(newDo.getRouteName());
        }

        if (newDo.getRoutePath() != null && !newDo.getRoutePath().equals(getRoutePath())) {
            oldFieldObj.setRoutePath(getRoutePath());
            setRoutePath(newDo.getRoutePath());
        }

        if (newDo.getComponent() != null && !newDo.getComponent().equals(getComponent())) {
            oldFieldObj.setComponent(getComponent());
            setComponent(newDo.getComponent());
        }

        if (newDo.getQuery() != null && !newDo.getQuery().equals(getQuery())) {
            oldFieldObj.setQuery(getQuery());
            setQuery(newDo.getQuery());
        }

        if (newDo.getHref() != null && !newDo.getHref().equals(getHref())) {
            oldFieldObj.setHref(getHref());
            setHref(newDo.getHref());
        }

        if (newDo.getSort() != null && !newDo.getSort().equals(getSort())) {
            oldFieldObj.setSort(getSort());
            setSort(newDo.getSort());
        }

        if (newDo.getVersion() != null && !newDo.getVersion().equals(getVersion())) {
            oldFieldObj.setVersion(getVersion());
            setVersion(newDo.getVersion());
        }

        if (!oldFieldObj.hasNewField()) {
            return null;
        }

        return oldFieldObj;
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
        ", createdTime = " + createdTime +
        ", updatedTime = " + updatedTime +
        ", createUser = " + createUser +
        ", createUserId = " + createUserId +
        ", updateUser = " + updateUser +
        ", updateUserId = " + updateUserId +
        ", isDelete = " + isDelete +
        ", version = " + version +
        "}";
    }

}
