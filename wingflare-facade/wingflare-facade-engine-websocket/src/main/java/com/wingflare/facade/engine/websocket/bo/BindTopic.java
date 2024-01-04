package com.wingflare.facade.engine.websocket.bo;

import java.util.List;

/**
 * 频道注册对象
 * @author naizui
 */
public class BindTopic {

    /**
     * 只读频道
     */
    private List<String> readOnlyTopics;

    /**
     * 只写频道
     */
    private List<String> writeOnlyTopics;

    /**
     * 普通频道
     */
    private List<String> topics;

    /**
     * 设备编号
     */
    private List<String> terminalSnList;

    /**
     * 设备sid
     */
    private List<String> terminalSidList;

    public List<String> getReadOnlyTopics() {
        return readOnlyTopics;
    }

    public void setReadOnlyTopics(List<String> readOnlyTopics) {
        this.readOnlyTopics = readOnlyTopics;
    }

    public List<String> getWriteOnlyTopics() {
        return writeOnlyTopics;
    }

    public void setWriteOnlyTopics(List<String> writeOnlyTopics) {
        this.writeOnlyTopics = writeOnlyTopics;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTerminalSnList() {
        return terminalSnList;
    }

    public void setTerminalSnList(List<String> terminalSnList) {
        this.terminalSnList = terminalSnList;
    }

    public List<String> getTerminalSidList() {
        return terminalSidList;
    }

    public void setTerminalSidList(List<String> terminalSidList) {
        this.terminalSidList = terminalSidList;
    }
}
