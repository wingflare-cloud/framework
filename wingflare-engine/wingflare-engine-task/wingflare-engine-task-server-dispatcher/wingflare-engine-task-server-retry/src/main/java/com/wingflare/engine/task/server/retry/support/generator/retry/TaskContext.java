package com.wingflare.engine.task.server.retry.support.generator.retry;


import java.util.List;

/**
 * 任务生成器上下文
 *
 * @author opensnail
 * @date 2023-07-16 21:26:52
 * @since 2.1.0
 */
public class TaskContext {

    /**
     * namespaceId
     */
    private String namespaceId;

    /**
     * groupName
     */
    private String groupName;

    /**
     * groupId
     */
    private Long groupId;

    /**
     * sceneName
     */
    private String sceneName;

    /**
     * sceneId
     */
    private Long sceneId;

    /**
     * 任务的初始状态
     */
    private Integer initStatus;

    /**
     * 是否初始化场景
     */
    private Integer initScene;

    /**
     * 任务信息
     */
    private List<TaskInfo> taskInfos;

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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Long getSceneId() {
        return sceneId;
    }

    public void setSceneId(Long sceneId) {
        this.sceneId = sceneId;
    }

    public Integer getInitStatus() {
        return initStatus;
    }

    public void setInitStatus(Integer initStatus) {
        this.initStatus = initStatus;
    }

    public Integer getInitScene() {
        return initScene;
    }

    public void setInitScene(Integer initScene) {
        this.initScene = initScene;
    }

    public List<TaskInfo> getTaskInfos() {
        return taskInfos;
    }

    public void setTaskInfos(List<TaskInfo> taskInfos) {
        this.taskInfos = taskInfos;
    }

    public static class TaskInfo {
        /**
         * 业务唯一id
         */
        private String idempotentId;

        /**
         * 执行器名称
         */
        private String executorName;

        /**
         * 业务唯一编号
         */
        private String bizNo;

        /**
         * 客户端上报参数
         */
        private String argsStr;

        /**
         * 额外扩展参数
         */
        private String extAttrs;

        /**
         * 参数序列化器名称
         */
        private String serializerName;

        public String getIdempotentId() {
            return idempotentId;
        }

        public void setIdempotentId(String idempotentId) {
            this.idempotentId = idempotentId;
        }

        public String getExecutorName() {
            return executorName;
        }

        public void setExecutorName(String executorName) {
            this.executorName = executorName;
        }

        public String getBizNo() {
            return bizNo;
        }

        public void setBizNo(String bizNo) {
            this.bizNo = bizNo;
        }

        public String getArgsStr() {
            return argsStr;
        }

        public void setArgsStr(String argsStr) {
            this.argsStr = argsStr;
        }

        public String getExtAttrs() {
            return extAttrs;
        }

        public void setExtAttrs(String extAttrs) {
            this.extAttrs = extAttrs;
        }

        public String getSerializerName() {
            return serializerName;
        }

        public void setSerializerName(String serializerName) {
            this.serializerName = serializerName;
        }
    }
}
