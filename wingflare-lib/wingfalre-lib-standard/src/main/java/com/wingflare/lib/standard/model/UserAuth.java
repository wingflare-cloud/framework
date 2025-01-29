package com.wingflare.lib.standard.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author naizui_ycx
 * @date {2021/12/22}
 * @description 认证用户类
 */
public class UserAuth implements Serializable
{

    private static final long serialVersionUID = 8618758262923854083L;

    /**
     * 用户名id
     */
    private String userId;

    /**
     * tokenId
     */
    private String tokenId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * token过期时间
     */
    private Long tokenExpireTime;

    /**
     * 客户端类型
     */
    private String clientType;

    /**
     * 登录IP地址
     */
    private String ipAddress;

    /**
     * 是否为超级管理员
     */
    private Boolean isSuperAdmin;

    /**
     * 账号类型
     */
    private List<String> userTypes;

    /**
     * 系统角色id
     */
    private List<String> roles;

    /**
     * 身份id
     */
    private List<String> identities;

    /**
     * 当前身份
     */
    private String identity;

    /**
     * 组织id
     */
    private List<String> org;

    /**
     * 当前组织id
     */
    private String currentOrg;

    /**
     * 刷新id
     */
    private String refreshId;

    /**
     * 登录用户代理
     */
    private String userAgent;

    /**
     * 其他额外属性
     */
    private Map<String, Object> attribute;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Long getTokenExpireTime() {
        return tokenExpireTime;
    }

    public void setTokenExpireTime(Long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public List<String> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<String> userTypes) {
        this.userTypes = userTypes;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getIdentities() {
        return identities;
    }

    public void setIdentities(List<String> identities) {
        this.identities = identities;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public List<String> getOrg() {
        return org;
    }

    public void setOrg(List<String> org) {
        this.org = org;
    }

    public String getCurrentOrg() {
        return currentOrg;
    }

    public void setCurrentOrg(String currentOrg) {
        this.currentOrg = currentOrg;
    }

    public String getRefreshId() {
        return refreshId;
    }

    public void setRefreshId(String refreshId) {
        this.refreshId = refreshId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Map<String, Object> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

}