package com.studyplatform.server.services;

import com.studyplatform.server.entities.User;
import com.studyplatform.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ActivityLogService activityLogService;

    public User register(User user, String rawPassword) {
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        user.setPasswordHash(hashed);
        User saved = userRepository.save(user);

        activityLogService.log(saved.getUserId(), "User registered");
        return saved;
    }

    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) return null;

        if (!BCrypt.checkpw(rawPassword, user.getPasswordHash())) {
            return null;
        }

        activityLogService.log(user.getUserId(), "User logged in");
        return user;
    }
}


