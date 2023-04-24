package com.wingflare.engine.websocket.controller.http;

import com.wingflare.engine.websocket.model.Shout;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send() {
        Shout shout = new Shout();
        shout.setMessage("hello");
        simpMessagingTemplate.convertAndSend("/topic/marco", "alert(123)");
        return "success";
    }

}
