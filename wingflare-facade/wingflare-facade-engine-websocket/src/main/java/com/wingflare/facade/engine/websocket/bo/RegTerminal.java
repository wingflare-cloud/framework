package com.wingflare.facade.engine.websocket.bo;

import java.util.List;

/**
 * 终端注册对象
 * @author naizui
 */
public class RegTerminal {

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
     * 终端
     */
    private Terminal terminal;


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

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
