package com.wingflare.lib.task.enums;


import org.springframework.http.MediaType;

/**
 * @author: xiaowoniu
 * @date : 2024-01-03
 * @since : 2.6.0
 */
public enum ContentTypeEnum {

    JSON(1, MediaType.APPLICATION_JSON),
    FORM(2, MediaType.APPLICATION_FORM_URLENCODED);

    private final Integer type;
    private final MediaType mediaType;

    ContentTypeEnum(Integer type, MediaType mediaType) {
        this.type = type;
        this.mediaType = mediaType;
    }

    public Integer getType() {
        return type;
    }

    public MediaType getMediaType() {
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
