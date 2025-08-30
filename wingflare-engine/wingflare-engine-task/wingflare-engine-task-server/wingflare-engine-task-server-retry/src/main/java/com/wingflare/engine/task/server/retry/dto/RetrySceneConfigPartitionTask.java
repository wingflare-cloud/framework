package com.wingflare.engine.task.server.retry.dto;

import com.wingflare.engine.task.server.common.dto.PartitionTask;

import java.util.Set;

/**
 * @author opensnail
 * @date 2025-01-11
 * @since 1.3.0-beta1.1
 */
public class RetrySceneConfigPartitionTask extends PartitionTask {

    private String namespaceId;

    private String groupName;

    private String sceneName;

    /**
     * 通知告警场景配置id列表
     */
    private Set<Long> notifyIds;

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

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Set<Long> getNotifyIds() {
        return notifyIds;
    }

    public void setNotifyIds(Set<Long> notifyIds) {
        this.notifyIds = notifyIds;
    }
}
