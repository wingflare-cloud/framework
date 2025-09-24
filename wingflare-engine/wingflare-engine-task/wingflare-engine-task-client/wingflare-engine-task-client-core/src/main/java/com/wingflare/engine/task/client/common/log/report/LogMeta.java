package com.wingflare.engine.task.client.common.log.report;


/**
 * @author: xiaowoniu
 * @date : 2024-03-21
 * @since : 3.2.0
 */

public class LogMeta {
    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名称
     */
    private String groupName;

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
}
