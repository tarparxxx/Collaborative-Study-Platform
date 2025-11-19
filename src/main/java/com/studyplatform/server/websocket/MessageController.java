package com.studyplatform.server.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/notify")
    @SendTo("/topic/updates")
    public String broadcast(@Payload String message) {
        return message;
    }
}

