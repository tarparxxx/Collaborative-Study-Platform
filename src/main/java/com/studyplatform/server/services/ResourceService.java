package com.studyplatform.server.services;

import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.entities.StudyResource;
import com.studyplatform.server.entities.User;
import com.studyplatform.server.repositories.GroupRepository;
import com.studyplatform.server.repositories.ResourceRepository;
import com.studyplatform.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public StudyResource create(Long groupId, Long userId, StudyResource data) {
        GroupEntity group = groupRepository.findById(groupId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (group == null || user == null) {
            return null; // можно кинуть RuntimeException, если хочешь жестче
        }

        StudyResource resource = new StudyResource();
        resource.setGroupEntity(group);
        resource.setUploadedBy(user);
        resource.setTitle(data.getTitle());
        resource.setType(data.getType());
        resource.setUrl(data.getUrl());

        return resourceRepository.save(resource);
    }

    public List<StudyResource> getByGroup(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return Collections.emptyList();
        return resourceRepository.findByGroupEntity(group);
    }

    public List<StudyResource> getByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return Collections.emptyList();
        return resourceRepository.findByUploadedBy(user);
    }

    public void delete(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }
}
