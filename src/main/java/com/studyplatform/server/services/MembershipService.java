package com.studyplatform.server.services;

import com.studyplatform.server.controllers.NotificationController;
import com.studyplatform.server.entities.GroupEntity;
import com.studyplatform.server.entities.Membership;
import com.studyplatform.server.entities.User;
import com.studyplatform.server.repositories.GroupRepository;
import com.studyplatform.server.repositories.MembershipRepository;
import com.studyplatform.server.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ActivityLogService activityLogService;
    private final NotificationController notificationController;

    public Membership addUserToGroup(Long userId, Long groupId) {
        User user = userRepository.findById(userId).orElse(null);
        GroupEntity group = groupRepository.findById(groupId).orElse(null);

        if (user == null || group == null) {
            return null;
        }

        Membership membership = new Membership();
        membership.setUser(user);
        membership.setGroupEntity(group);

        Membership saved = membershipRepository.save(membership);

        activityLogService.log(
                userId,
                "User joined group " + groupId
        );

        notificationController.sendToGroup(
                groupId,
                "User " + userId + " joined group " + groupId
        );

        return saved;
    }

    public List<Membership> findAll() {
        return membershipRepository.findAll();
    }
}


