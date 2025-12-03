package com.studyplatform.server.controllers;

import com.studyplatform.server.dto.UserProfileDTO;
import com.studyplatform.server.entities.User;
import com.studyplatform.server.services.ActivityLogService;
import com.studyplatform.server.services.MembershipService;
import com.studyplatform.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final MembershipService membershipService;
    private final ActivityLogService activityLogService;

    @GetMapping("/{id}")
    public UserProfileDTO getProfile(@PathVariable Long id) {

        User user = userService.get(id);

        UserProfileDTO dto = new UserProfileDTO();
        dto.setId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        dto.setGroups(membershipService.getUserGroups(id));
        dto.setActivities(activityLogService.getUserLogs(id));

        return dto;
    }
}

