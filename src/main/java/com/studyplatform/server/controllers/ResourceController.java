package com.studyplatform.server.controllers;

import com.studyplatform.server.entities.StudyResource;
import com.studyplatform.server.services.ResourceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Resources")
@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    // Создать ресурс в группе от конкретного пользователя
    @PostMapping("/create")
    public StudyResource create(
            @RequestParam Long groupId,
            @RequestParam Long userId,
            @RequestBody StudyResource body
    ) {
        return resourceService.create(groupId, userId, body);
    }

    // Все ресурсы группы
    @GetMapping("/group/{groupId}")
    public List<StudyResource> getByGroup(@PathVariable Long groupId) {
        return resourceService.getByGroup(groupId);
    }

    // Все ресурсы, загруженные пользователем
    @GetMapping("/user/{userId}")
    public List<StudyResource> getByUser(@PathVariable Long userId) {
        return resourceService.getByUser(userId);
    }

    // Удалить ресурс
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        resourceService.delete(id);
    }
}

