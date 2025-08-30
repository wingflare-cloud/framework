package com.wingflare.engine.task.server.openapi.interceptor;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.enums.HeadersEnum;
import com.wingflare.engine.task.server.common.cache.CacheToken;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
@Configuration
public class OpenApiAuthenticationInterceptor implements HandlerInterceptor {
    public static final String NAMESPACE_ID = HeadersEnum.NAMESPACE.getKey();
    public static final String TOKEN = HeadersEnum.TOKEN.getKey();
    public static final String GROUP_NAME = HeadersEnum.GROUP_NAME.getKey();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String namespaceId = request.getHeader(NAMESPACE_ID);
        String groupName = request.getHeader(GROUP_NAME);
        String token = request.getHeader(TOKEN);
        request.setAttribute(NAMESPACE_ID, namespaceId);
        request.setAttribute(GROUP_NAME, groupName);
        
        if (StrUtil.isBlank(token) || !CacheToken.get(groupName, namespaceId).equals(token)) {
            throw new TaskServerException("Token authentication failed. [namespace:{} groupName:{} token:{}]",
                    namespaceId, groupName, token);
        }

        return true;
    }
}
