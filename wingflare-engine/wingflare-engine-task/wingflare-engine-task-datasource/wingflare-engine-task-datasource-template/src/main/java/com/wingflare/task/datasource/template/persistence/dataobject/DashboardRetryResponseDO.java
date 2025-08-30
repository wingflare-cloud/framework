package com.wingflare.task.datasource.template.persistence.dataobject;


/**
 * @author zhengweilin
 * @version 2.6.0
 * @date 2023/11/28
 */
public class DashboardRetryResponseDO {

    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 场景名称
     */
    private String sceneName;

    /**
     * 运行数量
     */
    private Integer runningNum;

    /**
     * 完成数量
     */
    private Integer finishNum;

    /**
     * 最大重试数量
     */
    private Integer maxCountNum;

    /**
     * 暂停
     */
    private Integer suspendNum;

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

    public Integer getRunningNum() {
        return runningNum;
    }

    public void setRunningNum(Integer runningNum) {
        this.runningNum = runningNum;
    }

    public Integer getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(Integer finishNum) {
        this.finishNum = finishNum;
    }

    public Integer getMaxCountNum() {
        return maxCountNum;
    }

    public void setMaxCountNum(Integer maxCountNum) {
        this.maxCountNum = maxCountNum;
    }

    public Integer getSuspendNum() {
        return suspendNum;
    }

    public void setSuspendNum(Integer suspendNum) {
        this.suspendNum = suspendNum;
    }
}
