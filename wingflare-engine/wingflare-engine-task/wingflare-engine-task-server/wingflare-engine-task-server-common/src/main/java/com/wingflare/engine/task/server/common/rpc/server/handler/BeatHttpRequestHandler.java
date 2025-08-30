package com.wingflare.engine.task.server.common.rpc.server.handler;

import cn.hutool.core.net.url.UrlQuery;
import com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH;
import com.wingflare.engine.task.common.core.context.SnailSpringContext;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.common.core.model.TaskRequest;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.Register;
import com.wingflare.engine.task.server.common.dto.ServerNodeExtAttrs;
import com.wingflare.engine.task.server.common.handler.GetHttpRequestHandler;
import com.wingflare.engine.task.server.common.register.ClientRegister;
import com.wingflare.engine.task.server.common.register.RegisterContext;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.stereotype.Component;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.BEAT.PONG;

/**
 * 接收心跳请求
 *
 * @author: opensnail
 * @date : 2022-03-07 16:26
 * @since 1.0.0
 */
@Component
public class BeatHttpRequestHandler extends GetHttpRequestHandler {

    @Override
    public boolean supports(String path) {
        return HTTP_PATH.BEAT.equals(path);
    }

    @Override
    public HttpMethod method() {
        return HttpMethod.POST;
    }

    @Override
    public TaskRpcResult doHandler(String content, UrlQuery query, HttpHeaders headers) {
        SnailJobLog.LOCAL.debug("Beat check content:[{}]", content);
        Register register = SnailSpringContext.getBean(ClientRegister.BEAN_NAME, Register.class);
        RegisterContext registerContext = new RegisterContext();
        registerContext.setGroupName(headers.get(HeadersEnum.GROUP_NAME.getKey()));
        registerContext.setHostPort(Integer.valueOf(headers.get(HeadersEnum.HOST_PORT.getKey())));
        registerContext.setHostIp(headers.get(HeadersEnum.HOST_IP.getKey()));
        registerContext.setHostId(headers.get(HeadersEnum.HOST_ID.getKey()));
        registerContext.setUri(HTTP_PATH.BEAT);
        registerContext.setNamespaceId(headers.get(HeadersEnum.NAMESPACE.getKey()));
        registerContext.setLabels(headers.get(HeadersEnum.LABEL.getKey()));

        ServerNodeExtAttrs serverNodeExtAttrs = new ServerNodeExtAttrs();
        serverNodeExtAttrs.setExecutorType(headers.get(HeadersEnum.EXECUTOR_TYPE.getKey()));
        serverNodeExtAttrs.setSystemVersion(headers.get(HeadersEnum.SYSTEM_VERSION.getKey()));
        registerContext.setExtAttrs(JsonUtil.toJsonString(serverNodeExtAttrs));

        boolean result = register.register(registerContext);
        if (!result) {
            SnailJobLog.LOCAL.warn("client register error. groupName:[{}]", headers.get(HeadersEnum.GROUP_NAME.getKey()));
        }
        TaskRequest retryRequest = JsonUtil.parseObject(content, TaskRequest.class);
        return new TaskRpcResult(PONG, retryRequest.getReqId());
    }
}
