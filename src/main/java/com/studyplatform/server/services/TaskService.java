package com.studyplatform.server.services;

import com.studyplatform.server.controllers.NotificationController;
import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.entities.TaskEntity;
import com.studyplatform.server.repositories.GroupRepository;
import com.studyplatform.server.repositories.TaskRepository;
import com.studyplatform.server.services.NotificationService;
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
    private final NotificationService notificationService;

    public TaskEntity create(Long groupId, TaskEntity task) {
        GroupEntity group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return null;

        task.setGroupEntity(group);
        TaskEntity saved = taskRepository.save(task);

        activityLogService.log(0L, "Task created in group " + groupId + ": " + task.getTitle());
        notificationController.sendToGroup(groupId, "New task created: " + saved.getTitle());

        // notification for a group
        notificationService.notifyGroup(
                groupId,
                "New task in group " + group.getName() + ": " + task.getTitle()
        );

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


        notificationService.notifyGroup(
                existing.getGroupEntity().getGroupId(),
                "Task updated: " + existing.getTitle()
        );

        return saved;
    }

    public boolean delete(Long id) {
        TaskEntity existing = get(id);
        if (existing == null) return false;

        Long groupId = existing.getGroupEntity().getGroupId();
        String title = existing.getTitle();

        taskRepository.delete(existing);

        activityLogService.log(0L, "Task deleted: " + id);

        notificationService.notifyGroup(
                groupId,
                "Task deleted: " + title
        );


        return true;
    }
}



