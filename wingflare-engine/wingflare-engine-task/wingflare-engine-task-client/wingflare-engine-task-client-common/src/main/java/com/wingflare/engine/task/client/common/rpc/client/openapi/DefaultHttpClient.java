package com.wingflare.engine.task.client.common.rpc.client.openapi;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.wingflare.api.core.Charset;
import com.wingflare.api.core.MimeType;
import com.wingflare.api.http.HttpMethod;
import com.wingflare.api.http.HttpRequest;
import com.wingflare.api.http.HttpResponse;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.engine.task.common.core.model.TaskOpenApiResult;
import com.wingflare.lib.container.Container;

import java.text.MessageFormat;

/**
 * <p>
 * 默认用DefaultHttpClient 支持扩展其他HttpClient
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
public class DefaultHttpClient implements TaskHttpClient {
    private final static String URL = "{0}://{1}:{2,number,#}/{3}/{4}";
    private final static String HTTPS = "https";
    private final static String HTTP = "http";
    private final TaskProperties.TaskOpenApiConfig config;

    public DefaultHttpClient(TaskProperties.TaskOpenApiConfig config) {
        this.config = config;
    }

    @Override
    public TaskOpenApiResult execute(Request request) {
        String path = request.getPath();
        if (path.startsWith(StrUtil.SLASH)) {
            path = path.substring(1);
        }

        String prefix = config.getPrefix();
        if (prefix.startsWith(StrUtil.SLASH)) {
            prefix = prefix.substring(1);
        }

        String host = config.getHost();

        String url = MessageFormat.format(URL, config.isHttps() ? HTTPS : HTTP, host, config.getPort(), prefix, path);
        if (StrUtil.isNotBlank(request.getParams())){
            url += request.getParams();
        }

        HttpRequest execute = Container.get(HttpRequest.class);
        HttpResponse response = execute
                .setUrl(url)
                .setBody(request.getBody())
                .setContentType(MimeType.JSON.getContentType(Charset.UTF_8))
                .setMethod(HttpMethod.valueOf(request.getMethod()))
                .execute();
        return JSONObject.parseObject(response.getBody(), TaskOpenApiResult.class);
    }
}
