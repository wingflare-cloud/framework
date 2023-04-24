package com.wingflare.facade.module.base.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统菜单Dto
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
public class MenuDto
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
	
    public MenuDto setMenuId(String menuId)
    {
        this.menuId = menuId;
        return this;
    }

    public String getMenuId()
    {
        return menuId;
    }
	
    public MenuDto setParentMenuId(String parentMenuId)
    {
        this.parentMenuId = parentMenuId;
        return this;
    }

    public String getParentMenuId()
    {
        return parentMenuId;
    }
	
    public MenuDto setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public MenuDto setMenuType(String menuType)
    {
        this.menuType = menuType;
        return this;
    }

    public String getMenuType()
    {
        return menuType;
    }
	
    public MenuDto setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public MenuDto setPermissionCode(String permissionCode)
    {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getPermissionCode()
    {
        return permissionCode;
    }
	
    public MenuDto setMenuName(String menuName)
    {
        this.menuName = menuName;
        return this;
    }

    public String getMenuName()
    {
        return menuName;
    }
	
    public MenuDto setMenuIcon(String menuIcon)
    {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getMenuIcon()
    {
        return menuIcon;
    }
	
    public MenuDto setMenuPath(String menuPath)
    {
        this.menuPath = menuPath;
        return this;
    }

    public String getMenuPath()
    {
        return menuPath;
    }
	
    public MenuDto setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
        return this;
    }

    public String getMenuCode()
    {
        return menuCode;
    }
	
    public MenuDto setHref(String href)
    {
        this.href = href;
        return this;
    }

    public String getHref()
    {
        return href;
    }
	
    public MenuDto setSort(Long sort)
    {
        this.sort = sort;
        return this;
    }

    public Long getSort()
    {
        return sort;
    }
	
    public MenuDto setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public MenuDto setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public MenuDto setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public MenuDto setCreateUserId(String createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public MenuDto setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public MenuDto setUpdateUserId(String updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public MenuDto setVersion(Integer version)
    {
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("menuId", getMenuId())
            .append("parentMenuId", getParentMenuId())
            .append("state", getState())
            .append("menuType", getMenuType())
            .append("systemCode", getSystemCode())
            .append("permissionCode", getPermissionCode())
            .append("menuName", getMenuName())
            .append("menuIcon", getMenuIcon())
            .append("menuPath", getMenuPath())
            .append("menuCode", getMenuCode())
            .append("href", getHref())
            .append("sort", getSort())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .append("updateUser", getUpdateUser())
            .append("updateUserId", getUpdateUserId())
            .append("version", getVersion())
            .toString();
    }
	
}
