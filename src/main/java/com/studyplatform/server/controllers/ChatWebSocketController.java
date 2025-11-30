package com.studyplatform.server.controllers;

import com.studyplatform.server.dto.ChatMessageDTO;
import com.studyplatform.server.entities.ChatMessage;
import com.studyplatform.server.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessageDTO dto) {

        ChatMessage saved = chatService.saveMessage(dto);

        // отправляем всем подписчикам группы
        messagingTemplate.convertAndSend(
                "/topic/chat/" + dto.getGroupId(),
                saved
        );
    }
}


