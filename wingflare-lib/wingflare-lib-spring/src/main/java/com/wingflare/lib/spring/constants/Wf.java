package com.wingflare.lib.spring.constants;

import org.springframework.core.Ordered;

/**
 * @InterfaceName Wf
 * @Author naizui_ycx
 * @Date 2022/12/12 12
 * @Description 框架常量文件
 */
public interface Wf {

    /**
     * 内网接口认证切面权重
     */
    int INTERNAL_API_AUTH_ORDER = Ordered.HIGHEST_PRECEDENCE + 2;

    /**
     * 内网接口认证头
     */
    String INTERNAL_API_AUTH_CONTEXT_KEY = "sys:ctx:wfInner";

    /**
     * 功能权限认证上下文结果key
     */
    String PERMISSION_RESULT_CONTEXT_KEY = "wf:perm:result";

    /**
     * 流量来源上下文结果KEY
     */
    String FROM_SOURCE_RESULT_CONTEXT_KEY = "wf:fromSource:result";

    /**
     * 内网api默认认证签名key
     */
    String INTERNAL_API_DEFAULT_SECRET_STR = "wf-framework-inner-secret";

    /**
     * 流量来源标记key
     */
    String SYS_FROM_KEY = "from-sys";

    /**
     * 接口强制返回文本key
     */
    String RESPONSE_FORCE_ORIGINAL_CONTEXT_KEY = "wf:resp:forceOriginal";

    /**
     * 请求自动装载头部
     */
    String REQUEST_AUTO_HEADER_CONTEXT_KEY = "wf:req:autoHeader";

    /**
     * 请求头上下文自动穿透
     */
    String REQUEST_HEADER_PENETRATION_CONTEXT_KEY = "wf:req:headerPenetration";

    /**
     * 服务异常自动抛出
     */
    String REQUEST_AUTO_THROW_ERR_CONTEXT_KEY = "wf:req:autoThrowErr";

    /**
     * 功能标识上下文
     */
    String FUNC_MARK_CONTEXT_KEY = "wf:func:mark";

}
