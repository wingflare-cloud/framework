package com.wingflare.api.core;

import java.util.Map;

/**
 * @author naizui_ycx
 * @date 2023/04/04 04
 * @description 服务上下文来源接口
 */
public interface ContextSource {

    /**
     * 获取全部上下文来源数据
     *
     * @return
     */
    Map<String, String> all();

}
