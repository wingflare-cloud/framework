package com.wingflare.engine.task.server.common.dto;


/**
 * 服务端节点扩展参数
 *
 * @author: opensnail
 * @date : 2023-06-29 15:54
 */
public class ServerNodeExtAttrs {

    /**
     * web容器port
     */
    private Integer webPort;

    /**
     * 系统版本
     */
    private String systemVersion;

    /**
     * 执行器类型
     */
    private String executorType;

    public Integer getWebPort() {
        return webPort;
    }

    public void setWebPort(Integer webPort) {
        this.webPort = webPort;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getExecutorType() {
        return executorType;
    }

    public void setExecutorType(String executorType) {
        this.executorType = executorType;
    }
}
