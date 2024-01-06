package com.wingflare.engine.websocket.service;

import com.wingflare.engine.websocket.db.WebhookDo;
import com.wingflare.engine.websocket.mapper.WebhookMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;


/**
 * webhook信息Server
 *
 * @author naizui_ycx
 * @date 2024-01-06
 */
@Service
public class WebhookServer extends BaseService<WebhookMapper, WebhookDo> {
}
