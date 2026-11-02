package com.ourbank.repository;

import com.ourbank.entity.CustomerInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerInvitationRepository extends JpaRepository<CustomerInvitation, Long> {

    Optional<CustomerInvitation> findByTokenHash(String tokenHash);
}
