package com.studyplatform.server.controllers;

import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public GroupEntity create(@RequestBody GroupEntity group) {
        return groupService.create(group);
    }

    @GetMapping
    public List<GroupEntity> getAll() {
        return groupService.findAll();
    }

    @GetMapping("/{id}")
    public GroupEntity getById(@PathVariable Long id) {
        return groupService.findById(id);
    }
}

