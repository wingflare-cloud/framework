package com.wingflare.engine.websocket.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.engine.websocket.db.WsWebhookDo;
import com.wingflare.engine.websocket.mapper.WsWebHookMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ws webhook信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2024-01-11
 */
@Service
public class WsWebhookServer extends BaseService<WsWebHookMapper, WsWebhookDo> {

    /**
     * 获取wsWebhookMap
     *
     * wrapper 查询参数
     * @return 结果
     */
    public Map<String, WsWebhookDo> getMap(QueryWrapper<WsWebhookDo> wrapper)
    {
        List<WsWebhookDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
		    return new HashMap<>();
	    }

        Map<String, WsWebhookDo> wsWebhookDoMap = new HashMap<>(list.size());

        for (WsWebhookDo wsWebhookDo : list) {
            wsWebhookDoMap.put(wsWebhookDo.getId(), wsWebhookDo);
        }

        return wsWebhookDoMap;
    }

}