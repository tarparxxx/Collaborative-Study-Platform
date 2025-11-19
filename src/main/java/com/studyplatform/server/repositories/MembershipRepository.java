package com.studyplatform.server.repositories;

import com.studyplatform.server.entities.Membership;
import com.studyplatform.server.entities.User;
import com.studyplatform.server.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    List<Membership> findByUser(User user);

    List<Membership> findByGroupEntity(GroupEntity groupEntity);
}


