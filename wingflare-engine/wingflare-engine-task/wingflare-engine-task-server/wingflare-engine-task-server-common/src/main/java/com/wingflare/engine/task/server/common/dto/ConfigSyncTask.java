package com.wingflare.engine.task.server.common.dto;


/**
 * @author: xiaowoniu
 * @date : 2023-11-23 17:01
 * @since : 2.5.0
 */
public class ConfigSyncTask {

    private String groupName;
    private String namespaceId;
    private Integer currentVersion;

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

    public Integer getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }
}
