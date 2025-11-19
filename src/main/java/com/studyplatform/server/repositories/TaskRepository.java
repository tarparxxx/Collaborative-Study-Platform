package com.studyplatform.server.repositories;

import com.studyplatform.server.entities.TaskEntity;
import com.studyplatform.server.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findByGroupEntity(GroupEntity groupEntity);

    List<TaskEntity> findByGroupEntity_GroupId(Long groupId);
}


