package com.wingflare.engine.websocket.utils;


import com.wingflare.lib.core.utils.MapUtil;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import com.wingflare.facade.engine.websocket.bo.Terminal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WsUtil {

    static {
        sessions = new HashMap<>();
        sessionMapping = new HashMap<>();
        mapping = new HashMap<>();
    }

    /**
     * key - 链接唯一标识, value - WebSocketSession
     */
    private static final Map<String, WebSocketSession> sessions;

    /**
     * session映射信息
     */
    private static final  Map<String, String> sessionMapping;

    /**
     * 一级映射信息
     */
    private static final Map<String, String> mapping;

    /**
     * 保存session关系数据
     *
     * @param session
     */
    public static void addSession(WebSocketSession session) {
        synchronized (sessions) {
            if (!sessions.containsKey(session.getId())) {
                Terminal terminal = WsCtxUtil.getTerminal();
                sessions.put(session.getId(), session);
                sessionMapping.put(terminal.getPoint(), session.getId());
                mapping.put(terminal.getPoint(), terminal.getSn());
            }
        }
    }

    /**
     * 关闭当前session
     *
     * @param sid
     * @throws IOException
     */
    public static void closeSession(String sid) throws IOException {
        closeSession(sid, CloseStatus.POLICY_VIOLATION);
    }

    /**
     * 关闭当前session
     *
     * @param sid
     * @throws IOException
     */
    public static void closeSession(String sid, CloseStatus closeStatus) throws IOException {
        synchronized (sessions) {
            WebSocketSession session = sessions.get(sid);
            if (session != null) {
                String mappingKey = MapUtil.findFirstKeyByValue(sessionMapping, session.getId());
                String mappingMappingKey = MapUtil.findFirstKeyByValue(mapping, mappingKey);
                session.close(closeStatus);
                sessions.remove(sid);
                mapping.remove(mappingMappingKey);
                sessionMapping.remove(mappingKey);
            }
        }
    }

}
