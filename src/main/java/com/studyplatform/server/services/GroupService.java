package com.studyplatform.server.services;

import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final ActivityLogService activityLogService;

    public GroupEntity create(GroupEntity group) {
        GroupEntity saved = groupRepository.save(group);

        activityLogService.log(
                0L, // пока нет создателя, можно считать системным пользователем
                "Created group: " + saved.getName()
        );

        return saved;
    }

    public List<GroupEntity> findAll() {
        return groupRepository.findAll();
    }

    public GroupEntity findById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}


