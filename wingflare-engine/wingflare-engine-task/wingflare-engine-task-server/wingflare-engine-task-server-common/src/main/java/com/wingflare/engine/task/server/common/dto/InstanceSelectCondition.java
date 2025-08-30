package com.wingflare.engine.task.server.common.dto;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-05-24
 */
public class InstanceSelectCondition {
    private String allocKey;
    private String groupName;
    private String namespaceId;
    private Integer routeKey;
    private String targetLabels;

    public String getAllocKey() {
        return allocKey;
    }

    public void setAllocKey(String allocKey) {
        this.allocKey = allocKey;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public Integer getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(Integer routeKey) {
        this.routeKey = routeKey;
    }

    public void setTargetLabels(String targetLabels) {
        this.targetLabels = targetLabels;
    }

    public Map<String, String> getTargetLabels() {
        return StrUtil.isNotBlank(targetLabels) ? JsonUtil.parseHashMap(targetLabels) : Maps.newHashMap();
    }
}
