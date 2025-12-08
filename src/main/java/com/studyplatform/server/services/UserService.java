package com.studyplatform.server.services;

import com.studyplatform.server.entities.User;
import com.studyplatform.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User update(Long id, User data) {
        User existing = get(id);
        if (existing == null) return null;

        existing.setName(data.getName());
        existing.setEmail(data.getEmail());
        existing.setPasswordHash(data.getPasswordHash());

        return userRepository.save(existing);
    }

    public boolean delete(Long id) {
        User existing = get(id);
        if (existing == null) return false;

        userRepository.delete(existing);
        return true;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateName(Long id, String name) {
        User user = get(id);
        user.setName(name);
        return userRepository.save(user);
    }

    public User updateEmail(Long id, String email) {
        User user = get(id);
        user.setEmail(email);
        return userRepository.save(user);
    }


    public User updatePassword(Long id, String newPassword) {
        User user = get(id);
        String hashed = passwordEncoder.encode(newPassword);
        user.setPasswordHash(hashed);
        return userRepository.save(user);
    }
}


