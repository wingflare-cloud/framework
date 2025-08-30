package com.wingflare.engine.task.server.web.model.request;


/**
 * @author: xiaowoniu
 * @date : 2023-11-22 09:15
 * @since : 2.5.0
 */
public class UserPermissionRequestVO {

    private String groupName;
    private String namespaceId;

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
}
