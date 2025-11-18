package com.studyplatform.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendToGroup(Long groupId, String message) {
        messagingTemplate.convertAndSend("/topic/group/" + groupId, message);
    }
}
