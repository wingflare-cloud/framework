package com.wingflare.engine.task.client.common.rpc.client.openapi;


import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
public class Request {

    /**
     * {@link RequestMethod} 请求类型
     */
    private String method;

    /**
     * 路径
     */
    private String path;

    /**
     * 参数
     */
    private String body;

    /**
     * url参数
     */
    private String params;

    /**
     * 上下文
     */
    private Map<String, String> headers;

    /**
     * 超时时间
     */
    private long timeout;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
