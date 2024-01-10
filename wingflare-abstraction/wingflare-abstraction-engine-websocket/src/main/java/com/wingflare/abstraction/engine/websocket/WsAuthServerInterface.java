package com.wingflare.abstraction.engine.websocket;

import com.wingflare.facade.engine.websocket.bo.Terminal;

import java.util.Map;

/**
 * @author naizui_ycx
 * @interfaceName WsAuthInterface
 * @email chenxi2511@qq.com
 * @date 2024/01/03
 * @description ws安全认证
 */
public interface WsAuthServerInterface {

    /**
     * 通过用sid获取当前终端
     *
     * @param sid
     * @return
     */
    public Terminal getTerminalBySid(String sid);

    /**
     * 获取sn下全部终端
     *
     * @param sn
     * @return
     */
    public Map<String, Terminal> getTerminalBySn(String sn);

    /**
     * 注册终端
     *
     * @param sid
     * @param terminal
     */
    public void regTerminal(String sid, Terminal terminal);

    /**
     * 通过sid关闭终端链接
     *
     * @param sid
     */
    public void closeTerminalBySid(String sid);

    /**
     * 通过终端sn关闭终端全部链接
     *
     * @param terminalSn
     */
    public void closeTerminalBySn(String terminalSn);

}
