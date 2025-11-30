package com.studyplatform.server.services;

import com.studyplatform.server.entities.User;
import com.studyplatform.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ----------- REGISTER -----------
    public User register(User user, String rawPassword) {

        // Проверка: email уже существует?
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already used");
        }

        // Проверка длины пароля
        if (rawPassword.length() < 6) {
            throw new RuntimeException("Password must be at least 6 characters");
        }

        // ХЕШИРУЕМ ПАРОЛЬ
        String hashed = passwordEncoder.encode(rawPassword);
        user.setPasswordHash(hashed);

        return userRepository.save(user);
    }

    // ----------- LOGIN -----------
    public User login(String email, String rawPassword) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // СРАВНИВАЕМ ПАРОЛИ ЧЕРЕЗ BCrypt
        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new RuntimeException("Wrong password");
        }

        return user;
    }
}



