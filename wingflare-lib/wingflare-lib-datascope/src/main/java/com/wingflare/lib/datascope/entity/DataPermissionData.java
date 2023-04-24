package com.wingflare.lib.datascope.entity;


import java.util.List;
import java.util.Map;

/**
 * 数据权限数据
 *
 * @author shaoyuyao
 * @date 2022/8/17 18:58
 */
public class DataPermissionData {
    /**
     * Mapper全限定方法名和数据权限条件过滤规则，key为Mapper全限定方法名，value为数据权限条件过滤规则
     */
    private Map<String, Condition> conditions;

    /**
     * 白名单
     */
    private Map<String, List<String>> whitelists;

    /**
     * 黑名单
     */
    private Map<String, List<String>> blacklists;

    /**
     * 是否禁用
     */
    private boolean disable = false;

    public Map<String, Condition> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Condition> conditions) {
        this.conditions = conditions;
    }

    public Map<String, List<String>> getWhitelists() {
        return whitelists;
    }

    public void setWhitelists(Map<String, List<String>> whitelists) {
        this.whitelists = whitelists;
    }

    public Map<String, List<String>> getBlacklists() {
        return blacklists;
    }

    public void setBlacklists(Map<String, List<String>> blacklists) {
        this.blacklists = blacklists;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
}
