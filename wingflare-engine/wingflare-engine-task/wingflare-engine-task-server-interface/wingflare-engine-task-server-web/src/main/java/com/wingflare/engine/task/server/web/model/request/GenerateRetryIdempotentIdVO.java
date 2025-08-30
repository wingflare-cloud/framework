package com.wingflare.engine.task.server.web.model.request;


import org.hibernate.validator.constraints.NotBlank;

/**
 * 生成idempotentId模型
 *
 * @auther opensnail
 * @date 2022/03/25 10:06
 */
public class GenerateRetryIdempotentIdVO {
    /**
     * 组名称
     */
    @NotBlank(message = "Group name cannot be null")
    private String groupName;

    /**
     * 场景名称
     */
    @NotBlank(message = "Scene name cannot be null")
    private String sceneName;

    /**
     * 执行参数
     */
    @NotBlank(message = "Parameters cannot be null")
    private String argsStr;

    /**
     * 执行器名称
     */
    @NotBlank(message = "Executor cannot be null")
    private String executorName;

    /**
     * 参数序列化器名称
     */
    @NotBlank(message = "serializerName cannot be null")
    private String serializerName;

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

    public String getArgsStr() {
        return argsStr;
    }

    public void setArgsStr(String argsStr) {
        this.argsStr = argsStr;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getSerializerName() {
        return serializerName;
    }

    public void setSerializerName(String serializerName) {
        this.serializerName = serializerName;
    }
}
