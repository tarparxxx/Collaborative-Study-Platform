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

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ActivityLogService activityLogService;
    private final NotificationController notificationController;
    private final NotificationService notificationService;

    public Membership addToGroup(Long userId, Long groupId) {

        User user = userRepository.findById(userId).orElse(null);
        GroupEntity group = groupRepository.findById(groupId).orElse(null);

        if (user == null || group == null)
            return null;

        // create membership
        Membership membership = new Membership();
        membership.setUser(user);
        membership.setGroupEntity(group);

        Membership saved = membershipRepository.save(membership);

        // log
        activityLogService.log(
                userId,
                "User " + userId + " joined group " + groupId
        );

        // notification for group members
        notificationController.sendToGroup(
                groupId,
                "User " + user.getName() + " joined the group!"
        );

        // personal notification for user
        notificationService.notifyUser(
                userId,
                "You joined the group: " + group.getName()
        );

        return saved;
    }

    public List<Membership> getUserMemberships(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return Collections.emptyList();
        return membershipRepository.findByUser(user);
    }

    public List<Membership> getGroupMemberships(Long groupId) {
        GroupEntity group = groupRepository.findById(groupId).orElse(null);
        if (group == null) return Collections.emptyList();
        return membershipRepository.findByGroupEntity(group);
    }
}




