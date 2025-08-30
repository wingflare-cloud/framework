package com.wingflare.engine.task.server.common.dto;


import java.time.LocalDateTime;
import java.util.List;

/**
 * @author opensnail
 * @date 2023-09-25 22:48:36
 * @since 2.4.0
 */
public class FilterStrategyContext {

    private Long id;

    private RegisterNodeInfo registerNodeInfo;

    private String groupName;

    private LocalDateTime nextTriggerAt;


    private String uniqueId;

    private List<String> sceneBlacklist;

    private String sceneName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegisterNodeInfo getRegisterNodeInfo() {
        return registerNodeInfo;
    }

    public void setRegisterNodeInfo(RegisterNodeInfo registerNodeInfo) {
        this.registerNodeInfo = registerNodeInfo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public LocalDateTime getNextTriggerAt() {
        return nextTriggerAt;
    }

    public void setNextTriggerAt(LocalDateTime nextTriggerAt) {
        this.nextTriggerAt = nextTriggerAt;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public List<String> getSceneBlacklist() {
        return sceneBlacklist;
    }

    public void setSceneBlacklist(List<String> sceneBlacklist) {
        this.sceneBlacklist = sceneBlacklist;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
}
