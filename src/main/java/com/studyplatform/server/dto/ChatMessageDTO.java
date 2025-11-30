package com.studyplatform.server.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private Long groupId;
    private Long senderId;
    private String senderName;
    private String content;
}


