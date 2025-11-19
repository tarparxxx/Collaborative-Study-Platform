package com.studyplatform.server.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
    private Long senderId;

    private String senderName;
    private String content;

    private LocalDateTime timestamp = LocalDateTime.now();
}

