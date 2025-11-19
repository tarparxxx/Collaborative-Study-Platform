package com.studyplatform.server.controllers;

import com.studyplatform.server.entities.Membership;
import com.studyplatform.server.services.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping("/add")
    public Membership add(
            @RequestParam Long userId,
            @RequestParam Long groupId) {
        return membershipService.addToGroup(userId, groupId);
    }

    @GetMapping("/user/{userId}")
    public List<Membership> getUserMemberships(@PathVariable Long userId) {
        return membershipService.getUserMemberships(userId);
    }

    @GetMapping("/group/{groupId}")
    public List<Membership> getGroupMemberships(@PathVariable Long groupId) {
        return membershipService.getGroupMemberships(groupId);
    }
}


