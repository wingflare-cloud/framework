package com.wingflare.engine.task.server.common.dto;


/**
 * <p>
 * 客户端标签
 * </p>
 *
 * @author opensnail
 * @date 2025-05-24
 */
public class ClientTagDTO {

    private String tagName;
    private String tagValue;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
