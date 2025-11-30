package com.studyplatform.server.services;

import com.studyplatform.server.entities.ActivityLog;
import com.studyplatform.server.entities.User;
import com.studyplatform.server.repositories.ActivityLogRepository;
import com.studyplatform.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;
    private final UserRepository userRepository;

    public void log(Long userId, String action) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ActivityLog log = new ActivityLog();
        log.setUser(user);      // ← ИСПРАВЛЕНО
        log.setAction(action);

        activityLogRepository.save(log);
    }

    public List<ActivityLog> getUserLogs(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return activityLogRepository.findByUser(user);
    }

    public List<ActivityLog> getAllLogs() {
        return activityLogRepository.findAll();
    }
}


