package com.studyplatform.server.services;

import com.studyplatform.server.entities.ChatMessage;
import com.studyplatform.server.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ActivityLogService activityLogService;

    public ChatMessage saveMessage(ChatMessage message) {
        ChatMessage saved = chatRepository.save(message);

        activityLogService.log(
                message.getSenderId(),
                "Sent message in group " + message.getGroupId()
        );

        return saved;
    }

    public List<ChatMessage> getGroupHistory(Long groupId) {
        return chatRepository.findByGroupId(groupId);
    }
}

