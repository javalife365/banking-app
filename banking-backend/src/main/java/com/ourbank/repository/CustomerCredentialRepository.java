package com.ourbank.repository;

import com.ourbank.entity.CustomerCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerCredentialRepository extends JpaRepository<CustomerCredential, Long> {

    Optional<CustomerCredential> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByCustomerId(Long customerId);
}
