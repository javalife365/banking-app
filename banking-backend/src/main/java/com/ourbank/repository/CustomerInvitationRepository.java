package com.ourbank.repository;

import com.ourbank.entity.CustomerInvitation;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerInvitationRepository extends JpaRepository<CustomerInvitation, Long> {

    Optional<CustomerInvitation> findByTokenHash(String tokenHash);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT invitation
            FROM CustomerInvitation invitation
            WHERE invitation.tokenHash = :tokenHash
            """)
    Optional<CustomerInvitation> findByTokenHashForUpdate(@Param("tokenHash") String tokenHash);

}
