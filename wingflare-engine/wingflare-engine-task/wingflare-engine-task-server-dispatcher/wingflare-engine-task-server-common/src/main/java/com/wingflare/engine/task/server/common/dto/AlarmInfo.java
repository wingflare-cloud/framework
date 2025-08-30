package com.wingflare.engine.task.server.common.dto;


/**
 * @author xiaowoniu
 * @date 2023-12-03 10:15:37
 * @since 2.5.0
 */
public class AlarmInfo {

    private String namespaceId;

    private String groupName;

    private Integer count;

    /**
     * 通知配置
     */
    private String notifyIds;

    /**
     * 通知场景
     */
    private Integer notifyScene;

    /**
     * 失败原因
     */
    private String reason;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNotifyIds() {
        return notifyIds;
    }

    public void setNotifyIds(String notifyIds) {
        this.notifyIds = notifyIds;
    }

    public Integer getNotifyScene() {
        return notifyScene;
    }

    public void setNotifyScene(Integer notifyScene) {
        this.notifyScene = notifyScene;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
