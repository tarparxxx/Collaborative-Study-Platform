package com.studyplatform.server.services;

import com.studyplatform.server.dto.ChatMessageDTO;
import com.studyplatform.server.entities.ChatMessage;
import com.studyplatform.server.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatMessage saveMessage(ChatMessageDTO dto) {

        ChatMessage msg = new ChatMessage();
        msg.setGroupId(dto.getGroupId());
        msg.setSenderId(dto.getSenderId());      // ← ВАЖНО
        msg.setSenderName(dto.getSenderName());  // ← ВАЖНО
        msg.setContent(dto.getContent());

        return chatRepository.save(msg);
    }

    public List<ChatMessage> getMessages(Long groupId) {
        return chatRepository.findByGroupId(groupId);
    }
}




