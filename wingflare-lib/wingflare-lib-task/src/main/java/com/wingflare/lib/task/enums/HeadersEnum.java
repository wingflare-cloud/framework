package com.wingflare.lib.task.enums;


/**
 * @author: opensnail
 * @date : 2022-03-11 19:44
 */
public enum HeadersEnum {

    HOST_ID("host-id"),
    HOST_IP("host-ip"),
    HOST_PORT("host-port"),
    GROUP_NAME("group-name"),
    CONTEXT_PATH("context-path"),
    REQUEST_ID("request-id"),
    VERSION("version"),
    HOST("Host"),
    NAMESPACE("namespace"),
    TOKEN("token"),
    LABEL("label"),
    EXECUTOR_TYPE("executor-type"),
    SYSTEM_VERSION("system-version")
    ;

    private final String key;

    HeadersEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
