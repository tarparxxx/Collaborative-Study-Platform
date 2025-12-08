package com.studyplatform.server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @ManyToOne(optional = false)
    private GroupEntity groupEntity;

    @NotBlank
    private String title;

    private String description;

    private LocalDateTime deadline;

    private LocalDateTime createdAt = LocalDateTime.now();

    private boolean completed = false;
}


