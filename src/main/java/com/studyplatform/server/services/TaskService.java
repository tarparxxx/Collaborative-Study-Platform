package com.studyplatform.server.services;

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

    public TaskEntity create(TaskEntity task, Long groupId) {
        GroupEntity group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return null;

        task.setGroupEntity(group);
        return taskRepository.save(task);
    }

    public List<TaskEntity> getGroupTasks(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return null;

        return taskRepository.findByGroupEntity(group);
    }
}

