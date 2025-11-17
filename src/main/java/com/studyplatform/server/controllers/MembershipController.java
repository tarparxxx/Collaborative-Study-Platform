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
    public Membership addUserToGroup(
            @RequestParam Long userId,
            @RequestParam Long groupId
    ) {
        return membershipService.addUserToGroup(userId, groupId);
    }

    @GetMapping
    public List<Membership> getAll() {
        return membershipService.findAll();
    }
}

