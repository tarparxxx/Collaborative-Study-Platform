package com.studyplatform.server.controllers;

import com.studyplatform.server.entities.User;
import com.studyplatform.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PutMapping("/{id}/name")
    public User updateName(@PathVariable Long id, @RequestParam String name) {
        return userService.updateName(id, name);
    }

    @PutMapping("/{id}/email")
    public User updateEmail(@PathVariable Long id, @RequestParam String email) {
        return userService.updateEmail(id, email);
    }

    @PutMapping("/{id}/password")
    public User updatePassword(@PathVariable Long id, @RequestParam String newPassword) {
        return userService.updatePassword(id, newPassword);
    }
}


