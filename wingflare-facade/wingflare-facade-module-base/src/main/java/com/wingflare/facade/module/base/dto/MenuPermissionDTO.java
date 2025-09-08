package com.wingflare.facade.module.base.dto;


import java.util.List;

/**
 * 菜单权限数据类
 */
public class MenuPermissionDTO {

    private String name;

    private String langKey;

    private String permissionCode;

    private String systemCode;

    private String key;

    private String menuType;

    private List<MenuPermissionDTO> children;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public List<MenuPermissionDTO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuPermissionDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuPermissionDTO{" +
                "name='" + name + '\'' +
                ", langKey='" + langKey + '\'' +
                ", permissionCode='" + permissionCode + '\'' +
                ", systemCode='" + systemCode + '\'' +
                ", key='" + key + '\'' +
                ", menuType='" + menuType + '\'' +
                ", children=" + children +
                '}';
    }
}
