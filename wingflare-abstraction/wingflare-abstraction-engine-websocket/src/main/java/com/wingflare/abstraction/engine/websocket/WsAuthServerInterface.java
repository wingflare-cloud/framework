package com.wingflare.abstraction.engine.websocket;

import com.wingflare.facade.engine.websocket.bo.Terminal;

/**
 * @author naizui_ycx
 * @interfaceName WsAuthInterface
 * @email chenxi2511@qqq.com
 * @date 2024/01/03
 * @description ws安全认证
 */
public interface WsAuthServerInterface {

    /**
     * 通过用户名获取当前终端
     *
     * @param username
     * @return
     */
    public Terminal getTerminalByUserName(String username);

    /**
     * 注册终端
     *
     * @param username
     * @param terminal
     */
    public void regTerminal(String username, Terminal terminal);

    /**
     * 关闭终端
     *
     * @param terminal
     */
    public void closeTerminal(Terminal terminal);

}
