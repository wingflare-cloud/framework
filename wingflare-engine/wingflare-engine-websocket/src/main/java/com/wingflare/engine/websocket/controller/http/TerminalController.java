package com.wingflare.engine.websocket.controller.http;

import com.wingflare.abstraction.engine.websocket.WsAuthServerInterface;
import com.wingflare.facade.engine.websocket.bo.Terminal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author naizui_ycx
 * @className OpenApiController
 * @email chenxi2511@qqq.com
 * @date 2024/01/03
 * @description 终端相关开放接口
 */

@RestController
@RequestMapping("/open/api/terminal")
public class TerminalController {

    @Resource
    private WsAuthServerInterface wsAuthServer;

    /**
     * 注册终端
     *
     * @param terminal
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String regTerminal(Terminal terminal) {
        return "success";
    }

    /**
     * 关闭终端
     *
     * @param terminal
     * @return
     */
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public String closeTerminal(Terminal terminal) {
        return "success";
    }

    /**
     * 关闭终端
     *
     * @return
     */
    @RequestMapping(value = "/close/name/{name}", method = RequestMethod.POST)
    public String closeTerminalByName(@PathVariable("name") String name) {
        return "success";
    }

}
