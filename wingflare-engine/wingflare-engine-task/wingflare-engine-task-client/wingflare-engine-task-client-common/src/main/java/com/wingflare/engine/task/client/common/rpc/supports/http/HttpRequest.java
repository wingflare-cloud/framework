package com.wingflare.engine.task.client.common.rpc.supports.http;


import java.util.Map;

/**
 * @author: opensnail
 * @date : 2024-04-12
 * @since : 3.3.0
 */
public class HttpRequest {
    private final Map<String, String> headers;
    private final String uri;

    public HttpRequest(Map<String, String> headers, String uri) {
        this.headers = headers;
        this.uri = uri;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getUri() {
        return uri;
    }
}
