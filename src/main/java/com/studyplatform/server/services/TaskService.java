package com.studyplatform.server.services;

import com.studyplatform.server.controllers.NotificationController;
import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.entities.TaskEntity;
import com.studyplatform.server.repositories.GroupRepository;
import com.studyplatform.server.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;
    private final ActivityLogService activityLogService;
    private final NotificationController notificationController;

    public TaskEntity create(Long groupId, TaskEntity task) {
        GroupEntity group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return null;

        task.setGroupEntity(group);
        TaskEntity saved = taskRepository.save(task);

        activityLogService.log(0L, "Task created in group " + groupId + ": " + task.getTitle());
        notificationController.sendToGroup(groupId, "New task created: " + saved.getTitle());

        return saved;
    }

    public TaskEntity get(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> getGroupTasks(Long groupId) {
        return taskRepository.findByGroupEntity_GroupId(groupId);
    }

    public TaskEntity update(Long id, TaskEntity data) {
        TaskEntity existing = get(id);
        if (existing == null) return null;

        existing.setTitle(data.getTitle());
        existing.setDescription(data.getDescription());
        existing.setDeadline(data.getDeadline());

        TaskEntity saved = taskRepository.save(existing);

        activityLogService.log(0L, "Task updated: " + id);

        return saved;
    }

    public boolean delete(Long id) {
        TaskEntity existing = get(id);
        if (existing == null) return false;

        taskRepository.delete(existing);

        activityLogService.log(0L, "Task deleted: " + id);

        return true;
    }
}



