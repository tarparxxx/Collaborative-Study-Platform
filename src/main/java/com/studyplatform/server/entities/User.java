package com.studyplatform.server.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    private String name;

    @Column(unique = true)
    @NotBlank
    private String email;

    @NotBlank
    private String passwordHash;
}


