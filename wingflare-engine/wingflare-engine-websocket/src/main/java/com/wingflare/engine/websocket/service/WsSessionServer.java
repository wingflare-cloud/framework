package com.wingflare.engine.websocket.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.engine.websocket.db.WsSessionDo;
import com.wingflare.engine.websocket.mapper.WsSessionMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * session信息 服务实现类
 * </p>
 *
 * @author naizui_ycx
 * @since 2024-01-06
 */
@Service
public class WsSessionServer extends BaseService<WsSessionMapper, WsSessionDo> {

    /**
     * 获取wsSessionMap
     *
     * wrapper 查询参数
     * @return 结果
     */
    public Map<String, WsSessionDo> getMap(QueryWrapper<WsSessionDo> wrapper)
    {
        List<WsSessionDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
		    return new HashMap<>();
	    }

        Map<String, WsSessionDo> wsSessionDoMap = new HashMap<>(list.size());

        for (WsSessionDo wsSessionDo : list) {
            wsSessionDoMap.put(wsSessionDo.getId(), wsSessionDo);
        }

        return wsSessionDoMap;
    }

}