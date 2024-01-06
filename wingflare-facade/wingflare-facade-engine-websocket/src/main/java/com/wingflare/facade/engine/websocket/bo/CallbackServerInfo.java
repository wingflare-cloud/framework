package com.wingflare.facade.engine.websocket.bo;

/**
 * @author naizui_ycx
 * @className CallbackServerInfo
 * @email chenxi2511@qq.com
 * @date 2024/01/03
 * @description 回调服务信息
 */
public class CallbackServerInfo {

    /**
     * 服务名，内外服务时必填
     */
    private String serverName;

    /**
     * 回调路径
     */
    private String path;

    /**
     * 监听的频道
     */
    private String topics;

    /**
     * 是否开启ssl证书
     */
    private boolean enableSSL = false;


    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public boolean isEnableSSL() {
        return enableSSL;
    }

    public void setEnableSSL(boolean enableSSL) {
        this.enableSSL = enableSSL;
    }
}
