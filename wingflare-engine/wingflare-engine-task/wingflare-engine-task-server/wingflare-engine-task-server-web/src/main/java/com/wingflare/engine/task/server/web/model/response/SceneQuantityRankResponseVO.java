package com.wingflare.engine.task.server.web.model.response;


/**
 * @author: opensnail
 * @date : 2022-04-23 10:56
 */
public class SceneQuantityRankResponseVO {

    private String groupName;

    private String sceneName;

    private String total;

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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
