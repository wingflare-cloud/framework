package com.wingflare.business.base.db;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 系统菜单Do
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 18:51:27 CST 2023
 */
@TableName("sys_menu")
public class MenuDo extends BaseDoAbstract
{

	@TableId(type = IdType.ASSIGN_ID)
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
	@TableLogic
	@TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

	/**
     * 数据版本号
     */
	@Version
	@TableField(fill = FieldFill.INSERT)
    private Integer version;

	@Override
	public MenuDo setPk(String menuId)
	{
		setMenuId(menuId);
		return this;
	}

	@Override
	public String getPk()
	{
		return getMenuId();
	}

	
    public MenuDo setMenuId(String menuId)
    {
		addNewField("menuId");
        this.menuId = menuId;
        return this;
    }

    public String getMenuId()
    {
        return menuId;
    }
	
    public MenuDo setParentMenuId(String parentMenuId)
    {
		addNewField("parentMenuId");
        this.parentMenuId = parentMenuId;
        return this;
    }

    public String getParentMenuId()
    {
        return parentMenuId;
    }
	
    public MenuDo setState(Integer state)
    {
		addNewField("state");
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public MenuDo setMenuType(String menuType)
    {
		addNewField("menuType");
        this.menuType = menuType;
        return this;
    }

    public String getMenuType()
    {
        return menuType;
    }
	
    public MenuDo setSystemCode(String systemCode)
    {
		addNewField("systemCode");
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public MenuDo setPermissionCode(String permissionCode)
    {
		addNewField("permissionCode");
        this.permissionCode = permissionCode;
        return this;
    }

    public String getPermissionCode()
    {
        return permissionCode;
    }
	
    public MenuDo setMenuName(String menuName)
    {
		addNewField("menuName");
        this.menuName = menuName;
        return this;
    }

    public String getMenuName()
    {
        return menuName;
    }
	
    public MenuDo setMenuIcon(String menuIcon)
    {
		addNewField("menuIcon");
        this.menuIcon = menuIcon;
        return this;
    }

    public String getMenuIcon()
    {
        return menuIcon;
    }
	
    public MenuDo setMenuPath(String menuPath)
    {
		addNewField("menuPath");
        this.menuPath = menuPath;
        return this;
    }

    public String getMenuPath()
    {
        return menuPath;
    }
	
    public MenuDo setMenuCode(String menuCode)
    {
		addNewField("menuCode");
        this.menuCode = menuCode;
        return this;
    }

    public String getMenuCode()
    {
        return menuCode;
    }
	
    public MenuDo setHref(String href)
    {
		addNewField("href");
        this.href = href;
        return this;
    }

    public String getHref()
    {
        return href;
    }
	
    public MenuDo setSort(Long sort)
    {
		addNewField("sort");
        this.sort = sort;
        return this;
    }

    public Long getSort()
    {
        return sort;
    }
	
    public MenuDo setCreatedTime(Date createdTime)
    {
		addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public MenuDo setUpdatedTime(Date updatedTime)
    {
		addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public MenuDo setCreateUser(String createUser)
    {
		addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public MenuDo setCreateUserId(String createUserId)
    {
		addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateUserId()
    {
        return createUserId;
    }
	
    public MenuDo setUpdateUser(String updateUser)
    {
		addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public MenuDo setUpdateUserId(String updateUserId)
    {
		addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public String getUpdateUserId()
    {
        return updateUserId;
    }
	
    public MenuDo setIsDelete(Integer isDelete)
    {
		addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }
	
    public MenuDo setVersion(Integer version)
    {
		addNewField("version");
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }

	@Override
	public MenuDo clearNullNewField()
	{

		if (getMenuId() == null) {
			removeNewField("menuId");
		}

		if (getParentMenuId() == null) {
			removeNewField("parentMenuId");
		}

		if (getState() == null) {
			removeNewField("state");
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

		if (getMenuIcon() == null) {
			removeNewField("menuIcon");
		}

		if (getMenuPath() == null) {
			removeNewField("menuPath");
		}

		if (getMenuCode() == null) {
			removeNewField("menuCode");
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

		return this;
	}

	public MenuDo setOnNew(MenuDo newDo)
	{
	    MenuDo oldFieldObj = new MenuDo();

		if (newDo.getParentMenuId() != null && !newDo.getParentMenuId().equals(getParentMenuId())) {
			oldFieldObj.setParentMenuId(getParentMenuId());
			setParentMenuId(newDo.getParentMenuId());
		}

		if (newDo.getState() != null && !newDo.getState().equals(getState())) {
			oldFieldObj.setState(getState());
			setState(newDo.getState());
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

		if (newDo.getMenuIcon() != null && !newDo.getMenuIcon().equals(getMenuIcon())) {
			oldFieldObj.setMenuIcon(getMenuIcon());
			setMenuIcon(newDo.getMenuIcon());
		}

		if (newDo.getMenuPath() != null && !newDo.getMenuPath().equals(getMenuPath())) {
			oldFieldObj.setMenuPath(getMenuPath());
			setMenuPath(newDo.getMenuPath());
		}

		if (newDo.getMenuCode() != null && !newDo.getMenuCode().equals(getMenuCode())) {
			oldFieldObj.setMenuCode(getMenuCode());
			setMenuCode(newDo.getMenuCode());
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
            .append("isDelete", getIsDelete())
            .append("version", getVersion())
            .toString();
    }
	
}
