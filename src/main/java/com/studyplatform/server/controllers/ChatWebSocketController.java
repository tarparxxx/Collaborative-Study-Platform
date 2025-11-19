package com.studyplatform.server.controllers;

import com.studyplatform.server.entities.ChatMessage;
import com.studyplatform.server.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat.send")
    public void sendMessage(@Payload ChatMessage message) {

        // Save to Database and log
        ChatMessage saved = chatService.saveMessage(message);

        // Send it to group subscribers
        messagingTemplate.convertAndSend(
                "/topic/group/" + saved.getGroupId() + "/chat",
                saved
        );
    }
}

