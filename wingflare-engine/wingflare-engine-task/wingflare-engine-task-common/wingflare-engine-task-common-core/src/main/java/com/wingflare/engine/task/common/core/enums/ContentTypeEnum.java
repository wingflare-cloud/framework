package com.wingflare.engine.task.common.core.enums;


/**
 * @author: xiaowoniu
 * @date : 2024-01-03
 * @since : 2.6.0
 */
public enum ContentTypeEnum {

    JSON(1, "application/json"),
    FORM(2, "application/x-www-form-urlencoded");

    private final Integer type;
    private final String mediaType;

    ContentTypeEnum(Integer type, String mediaType) {
        this.type = type;
        this.mediaType = mediaType;
    }

    public Integer getType() {
        return type;
    }

    public String getMediaType() {
        return mediaType;
    }

    public static ContentTypeEnum valueOf(Integer type) {
        for (ContentTypeEnum contentTypeEnum : values()) {
            if (contentTypeEnum.getType().equals(type)) {
                return contentTypeEnum;
            }
        }

        return ContentTypeEnum.JSON;
    }
}
