package com.wingflare.engine.websocket.service;


import com.wingflare.engine.websocket.db.WsTopicBindInfoDo;
import com.wingflare.engine.websocket.mapper.WsTopicBindInfoMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 频道终端绑定信息 Mapper 接口
 * </p>
 *
 * @author naizui_ycx
 * @since 2024-01-06
 */
@Service
public class WsTopicBindInfoServer extends BaseService<WsTopicBindInfoMapper, WsTopicBindInfoDo> {
}

