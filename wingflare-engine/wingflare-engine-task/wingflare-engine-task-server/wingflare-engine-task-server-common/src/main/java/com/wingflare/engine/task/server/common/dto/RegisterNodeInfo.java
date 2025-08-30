package com.wingflare.engine.task.server.common.dto;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.util.JsonUtil;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 注册的节点信息
 *
 * @author: opensnail
 * @date : 2023-06-09 11:02
 */
public class RegisterNodeInfo implements Comparable<RegisterNodeInfo> {

    private String namespaceId;

    private String groupName;

    private String hostId;

    private String hostIp;

    private Integer hostPort;

    private LocalDateTime expireAt;

    private Integer nodeType;

    private String contextPath;

    private Map<String, String> labelMap;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Integer getHostPort() {
        return hostPort;
    }

    public void setHostPort(Integer hostPort) {
        this.hostPort = hostPort;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Map<String, String> getLabelMap() {
        return labelMap;
    }

    public void setLabelMap(Map<String, String> labelMap) {
        this.labelMap = labelMap;
    }

    public String address() {
        return MessageFormat.format("{0}:{1}", hostIp, hostPort.toString());
    }

    public void setLabels(String labels) {
        if (StrUtil.isBlank(labels)) {
            return;
        }
        this.labelMap = JsonUtil.parseHashMap(labels);
    }

    @Override
    public int compareTo(RegisterNodeInfo info) {
        return hostId.compareTo(info.hostId);
    }
}
