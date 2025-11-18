package com.studyplatform.server.services;

import com.studyplatform.server.entities.ActivityLog;
import com.studyplatform.server.repositories.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public void log(Long userId, String action) {
        ActivityLog log = new ActivityLog();
        log.setUserId(userId);
        log.setAction(action);
        activityLogRepository.save(log);
    }

    public List<ActivityLog> getUserLogs(Long userId) {
        return activityLogRepository.findByUserId(userId);
    }

    public List<ActivityLog> getAllLogs() {
        return activityLogRepository.findAll();
    }
}

