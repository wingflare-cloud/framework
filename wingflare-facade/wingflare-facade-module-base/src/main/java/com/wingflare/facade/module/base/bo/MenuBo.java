package com.wingflare.facade.module.base.bo;


import com.wingflare.facade.module.base.dict.MenuTypes;
import com.wingflare.lib.core.Regexp;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Enum;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.enums.OnOffEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 系统菜单Bo
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
public class MenuBo
{

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
    @Length(max = 256, message = "sys.permissionCode.lengthError")
    private String permissionCode;

	/**
     * 菜单名称
     */
    @Length(max = 64, message = "sys.menu.nameLengthError")
    @NotBlank(message = "sys.menu.nameNotBlank", groups = Create.class)
    private String menuName;

	/**
     * 菜单图标
     */
    @Length(max = 256, message = "sys.menu.iconLengthError")
    private String menuIcon;

	/**
     * 菜单路径
     */
    @Length(max = 256, message = "sys.menu.pathLengthError")
    private String menuPath;

	/**
     * 菜单代码
     */
    @Length(max = 256, message = "sys.menu.codeLengthError")
    @NotBlank(message = "sys.menu.codeNotBlank", groups = Create.class)
    private String menuCode;

	/**
     * 外部链接
     */
    @Length(max = 512, message = "sys.v.hrefLengthError")
    private String href;

	/**
     * 排序
     */
    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = "sys.menu.sortFormatError",
            groups = {Create.class, Update.class})
    private Long sort;

	/**
     * 数据版本号
     */
    private Integer version;
    
	public MenuBo setMenuId(String menuId)
    {
        this.menuId = menuId;
        return this;
    }

    public String getMenuId()
    {
        return menuId;
    }
    
	public MenuBo setParentMenuId(String parentMenuId)
    {
        this.parentMenuId = parentMenuId;
        return this;
    }

    public String getParentMenuId()
    {
        return parentMenuId;
    }
    
	public MenuBo setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
    
	public MenuBo setMenuType(String menuType)
    {
        this.menuType = menuType;
        return this;
    }

    public String getMenuType()
    {
        return menuType;
    }
    
	public MenuBo setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
    
	public MenuBo setPermissionCode(String permissionCode)
    {
        this.permissionCode = permissionCode;
        return this;
    }

    public String getPermissionCode()
    {
        return permissionCode;
    }
    
	public MenuBo setMenuName(String menuName)
    {
        this.menuName = menuName;
        return this;
    }

    public String getMenuName()
    {
        return menuName;
    }
    
	public MenuBo setMenuIcon(String menuIcon)
    {
        this.menuIcon = menuIcon;
        return this;
    }

    public String getMenuIcon()
    {
        return menuIcon;
    }
    
	public MenuBo setMenuPath(String menuPath)
    {
        this.menuPath = menuPath;
        return this;
    }

    public String getMenuPath()
    {
        return menuPath;
    }
    
	public MenuBo setMenuCode(String menuCode)
    {
        this.menuCode = menuCode;
        return this;
    }

    public String getMenuCode()
    {
        return menuCode;
    }
    
	public MenuBo setHref(String href)
    {
        this.href = href;
        return this;
    }

    public String getHref()
    {
        return href;
    }
    
	public MenuBo setSort(Long sort)
    {
        this.sort = sort;
        return this;
    }

    public Long getSort()
    {
        return sort;
    }
    
	public MenuBo setVersion(Integer version)
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
            .append("version", getVersion())
            .toString();
    }
	
}
