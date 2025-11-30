package com.studyplatform.server.controllers;

import com.studyplatform.server.dto.LoginRequest;
import com.studyplatform.server.dto.RegisterRequest;
import com.studyplatform.server.entities.User;
import com.studyplatform.server.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        // Собираем сущность пользователя из DTO
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());

        // В сервис уходит "сырой" пароль, там уже должен происходить BCrypt-хеш
        return authService.register(user, req.getPassword());
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest req) {
        // В AuthService проверяется email + пароль (через BCrypt.matches)
        return authService.login(req.getEmail(), req.getPassword());
    }
}


