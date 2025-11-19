package com.studyplatform.server.services;

import com.studyplatform.server.entities.ActivityLog;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    // Message for everyone in a group (group chat)
    public void notifyGroup(Long groupId, String message) {
        messagingTemplate.convertAndSend("/topic/group/" + groupId, message);
    }

    // Personal channel
    public void notifyUser(Long userId, String message) {
        messagingTemplate.convertAndSend("/topic/user/" + userId, message);
    }

    // Global message for everyone
    public void notifyAll(String message) {
        messagingTemplate.convertAndSend("/topic/updates", message);
    }
}

