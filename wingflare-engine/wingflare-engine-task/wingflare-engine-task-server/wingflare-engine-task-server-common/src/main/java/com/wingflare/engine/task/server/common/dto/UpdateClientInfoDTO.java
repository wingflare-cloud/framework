package com.wingflare.engine.task.server.common.dto;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-06-13
 */
public class UpdateClientInfoDTO {

    private String groupName;
    private String namespaceId;
    private String hostId;
    private String hostIp;
    private String labels;

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

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
