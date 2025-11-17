package com.studyplatform.server.repositories;

import com.studyplatform.server.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {}

