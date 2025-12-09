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
    public TaskEntity create(@RequestParam Long groupId, @RequestBody TaskEntity task) {
        return taskService.create(task, groupId);
    }

    @GetMapping("/{id}")
    public TaskEntity getById(@PathVariable Long id) {
        return taskService.get(id);
    }

    @GetMapping("/group/{groupId}")
    public List<TaskEntity> getGroupTasks(@PathVariable Long groupId) {
        return taskService.getGroupTasks(groupId);
    }

    @PutMapping("/{id}")
    public TaskEntity update(@PathVariable Long id, @RequestBody TaskEntity task) {
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    // ---------- STATUS ENDPOINTS ---------- //

    @PutMapping("/{id}/start")
    public TaskEntity start(@PathVariable Long id) {
        return taskService.start(id);
    }

    @PutMapping("/{id}/complete")
    public TaskEntity complete(@PathVariable Long id) {
        return taskService.complete(id);
    }

    @PutMapping("/{id}/reopen")
    public TaskEntity reopen(@PathVariable Long id) {
        return taskService.reopen(id);
    }
}



