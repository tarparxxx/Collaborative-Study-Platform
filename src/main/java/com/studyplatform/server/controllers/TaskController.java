package com.studyplatform.server.controllers;

import com.studyplatform.server.entities.TaskEntity;
import com.studyplatform.server.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public TaskEntity create(
            @RequestParam Long groupId,
            @RequestBody TaskEntity task
    ) {
        return taskService.create(groupId, task);
    }

    @GetMapping("/{id}")
    public TaskEntity get(@PathVariable Long id) {
        return taskService.get(id);
    }

    @GetMapping("/group/{groupId}")
    public List<TaskEntity> getGroupTasks(@PathVariable Long groupId) {
        return taskService.getGroupTasks(groupId);
    }

    @PutMapping("/{id}")
    public TaskEntity update(
            @PathVariable Long id,
            @RequestBody TaskEntity data
    ) {
        return taskService.update(id, data);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return taskService.delete(id);
    }
}


