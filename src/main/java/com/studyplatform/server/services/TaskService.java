package com.studyplatform.server.services;

import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.entities.TaskEntity;
import com.studyplatform.server.entities.TaskStatus;
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

    public TaskEntity create(TaskEntity task, Long groupId) {
        GroupEntity group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return null;

        task.setGroupEntity(group);
        task.setStatus(TaskStatus.OPEN);

        return taskRepository.save(task);
    }

    public TaskEntity get(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> getGroupTasks(Long groupId) {
        return taskRepository.findByGroupEntity_GroupId(groupId);
    }

    public TaskEntity update(Long id, TaskEntity data) {
        TaskEntity task = get(id);
        if (task == null) return null;

        task.setTitle(data.getTitle());
        task.setDescription(data.getDescription());
        task.setDeadline(data.getDeadline());

        return taskRepository.save(task);
    }

    public void delete(Long id) {
        TaskEntity task = get(id);
        if (task != null) {
            taskRepository.delete(task);
        }
    }

    // ---------- STATUS LOGIC ---------- //

    public TaskEntity setStatus(Long id, TaskStatus status) {
        TaskEntity task = get(id);
        if (task == null) return null;

        task.setStatus(status);
        return taskRepository.save(task);
    }

    public TaskEntity start(Long id) {
        return setStatus(id, TaskStatus.IN_PROGRESS);
    }

    public TaskEntity complete(Long id) {
        return setStatus(id, TaskStatus.DONE);
    }

    public TaskEntity reopen(Long id) {
        return setStatus(id, TaskStatus.OPEN);
    }
}




