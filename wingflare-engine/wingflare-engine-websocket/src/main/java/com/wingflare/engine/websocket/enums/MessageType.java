package com.wingflare.engine.websocket.enums;

import com.wingflare.lib.core.enums.ValueEnum;

public enum MessageType implements ValueEnum<String> {

    /**脚本类型消息*/
    SCRIPT("script"),
    /**通知类型消息，特殊的脚本类型消息，专门用于发送一条通知*/
    NOTICE("notice"),
    /**询问类消息，特殊的通知类型消息，发出后需用户响应*/
    ASK("ask"),
    /**应答类消息，即特殊的询问类消息，只回复yes or no*/
    REPLY("reply"),
    ;

    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
