package com.studyplatform.server.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    @ManyToOne(optional = false)
    private GroupEntity groupEntity;

    @ManyToOne(optional = false)
    private User uploadedBy;

    @NotBlank
    private String title;

    // napr. "PDF", "LINK", "IMAGE"
    @NotBlank
    private String type;

    // buď URL alebo cesta k súboru
    @NotBlank
    private String url;

    private LocalDateTime uploadedAt = LocalDateTime.now();
}

