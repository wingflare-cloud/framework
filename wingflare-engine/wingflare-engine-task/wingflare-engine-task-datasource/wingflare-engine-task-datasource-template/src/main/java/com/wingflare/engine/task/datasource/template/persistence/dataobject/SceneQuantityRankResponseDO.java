package com.wingflare.engine.task.datasource.template.persistence.dataobject;


/**
 * @author: opensnail
 * @date : 2022-04-23 10:56
 */
public class SceneQuantityRankResponseDO {

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
