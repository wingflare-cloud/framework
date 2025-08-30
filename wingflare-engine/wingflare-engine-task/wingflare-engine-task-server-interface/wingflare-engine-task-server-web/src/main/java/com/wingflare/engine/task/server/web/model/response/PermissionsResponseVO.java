package com.wingflare.engine.task.server.web.model.response;


import java.util.Set;

/**
 * @author: xiaowoniu
 * @date : 2023-11-23 14:01
 * @since : 2.5.0
 */
public class PermissionsResponseVO {

    private String groupName;
    private String namespaceId;
    private String namespaceName;
    private Set<String> groupNames;

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

    public String getNamespaceName() {
        return namespaceName;
    }

    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    public Set<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(Set<String> groupNames) {
        this.groupNames = groupNames;
    }
}
