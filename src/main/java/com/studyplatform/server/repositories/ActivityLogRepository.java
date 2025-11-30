package com.studyplatform.server.repositories;

import com.studyplatform.server.entities.ActivityLog;
import com.studyplatform.server.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByUser(User user);
}


