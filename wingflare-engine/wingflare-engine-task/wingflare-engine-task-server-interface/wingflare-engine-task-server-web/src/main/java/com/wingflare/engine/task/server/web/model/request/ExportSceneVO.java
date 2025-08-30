package com.wingflare.engine.task.server.web.model.request;


import java.util.Set;

/**
 * @author: opensnail
 * @date : 2024-05-29
 * @since : sj_1.0.0
 */
public class ExportSceneVO {

    private String groupName;

    private Integer sceneStatus;

    private String sceneName;

    private Set<Long> sceneIds;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getSceneStatus() {
        return sceneStatus;
    }

    public void setSceneStatus(Integer sceneStatus) {
        this.sceneStatus = sceneStatus;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Set<Long> getSceneIds() {
        return sceneIds;
    }

    public void setSceneIds(Set<Long> sceneIds) {
        this.sceneIds = sceneIds;
    }
}
