package com.wingflare.engine.task.server.common.util;

import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * @author: opensnail
 * @date : 2024-06-12
 * @since : sj_1.1.0
 */
public final class HttpHeaderUtil {

    public static String getGroupName(HttpHeaders headers) {
        return headers.getAsString(HeadersEnum.GROUP_NAME.getKey());
    }

    public static String getNamespace(HttpHeaders headers) {
        return headers.getAsString(HeadersEnum.NAMESPACE.getKey());
    }

}
