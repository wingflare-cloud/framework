package com.wingflare.engine.task.server.common.rpc.server.handler;

import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.request.ConfigRequest;
import com.wingflare.engine.task.server.common.handler.GetHttpRequestHandler;
import com.wingflare.engine.task.datasource.template.access.AccessTemplate;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author: opensnail
 * @date : 2022-03-07 16:29
 * @since 1.0.0
 */
@Component
public class ConfigHttpRequestHandler extends GetHttpRequestHandler {
    private final AccessTemplate accessTemplate;

    public ConfigHttpRequestHandler(AccessTemplate accessTemplate) {
        this.accessTemplate = accessTemplate;
    }

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.SYNC_CONFIG.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery urlQuery, HttpHeaders headers) {
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        String groupName = headers.get(HeadersEnum.GROUP_NAME.getKey());
        String namespace = headers.get(HeadersEnum.NAMESPACE.getKey());
        ConfigRequest configRequest = accessTemplate.getGroupConfigAccess().getConfigInfo(groupName, namespace);
        return new TaskRpcResult(JsonUtil.toJsonString(configRequest), retryRequest.getReqId());
    }
}
