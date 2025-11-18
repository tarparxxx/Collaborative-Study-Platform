package com.studyplatform.server.controllers;

import com.studyplatform.server.entities.ActivityLog;
import com.studyplatform.server.services.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @GetMapping("/user/{id}")
    public List<ActivityLog> getUserLogs(@PathVariable Long id) {
        return activityLogService.getUserLogs(id);
    }

    @GetMapping("/all")
    public List<ActivityLog> getAllLogs() {
        return activityLogService.getAllLogs();
    }
}

