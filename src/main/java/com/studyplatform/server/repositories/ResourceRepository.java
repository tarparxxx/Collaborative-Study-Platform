package com.studyplatform.server.repositories;

import com.studyplatform.server.entities.StudyResource;
import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<StudyResource, Long> {

    List<StudyResource> findByGroupEntity(GroupEntity groupEntity);

    List<StudyResource> findByUploadedBy(User user);
}

