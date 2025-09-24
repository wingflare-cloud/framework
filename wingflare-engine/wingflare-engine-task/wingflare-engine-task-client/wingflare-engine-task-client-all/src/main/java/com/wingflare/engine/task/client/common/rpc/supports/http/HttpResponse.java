package com.wingflare.engine.task.client.common.rpc.supports.http;


import java.util.HashMap;
import java.util.Map;

/**
 * @author: opensnail
 * @date : 2024-04-12
 * @since :3.3.0
 */
public class HttpResponse {

    private Map<String, Object> headers = new HashMap<>();

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeader(String key, Object value) {
        headers.put(key, value);
    }
}
