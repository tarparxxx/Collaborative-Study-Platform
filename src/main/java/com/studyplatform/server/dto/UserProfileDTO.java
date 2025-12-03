package com.studyplatform.server.dto;

import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.entities.ActivityLog;
import lombok.Data;
import java.util.List;

@Data
public class UserProfileDTO {
    private Long id;
    private String name;
    private String email;

    private List<GroupEntity> groups;
    private List<ActivityLog> activities;
}

