package com.ourbank.service;

import com.ourbank.dto.ActivateCustomerRequest;
import com.ourbank.entity.CustomerCredential;
import com.ourbank.entity.CustomerInvitation;
import com.ourbank.repository.CustomerCredentialRepository;
import com.ourbank.repository.CustomerInvitationRepository;
import com.ourbank.util.InvitationTokenHasher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CustomerActivationService {

    private final CustomerInvitationRepository invitationRepository;
    private final CustomerCredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void activateCustomer(ActivateCustomerRequest request){
        //Normalize username
        String username = request.username().trim().toLowerCase(Locale.ROOT);

        //Check BCrypt's 72-bytes input limit
        int passwordBytes = request.password().getBytes(StandardCharsets.UTF_8).length;

        //Hash the incoming invitation token
        String tokenHash = InvitationTokenHasher.hash(request.token());

        //Lock invitation row during activation
        CustomerInvitation invitation = invitationRepository.findByTokenHashForUpdate(tokenHash)
                .orElseThrow(() -> new IllegalArgumentException("Invalid invitation token"));

        // Check whether invitation was already used
        if (invitation.getUsedAt() != null){
            throw new IllegalArgumentException("Invitation has already been used");
        }

        // Prevent multiple credentials for the same customer
        Long customerId = invitation.getCustomer().getId();

        if (credentialRepository.existsByCustomerId(customerId)){
            throw new IllegalArgumentException("You already have online banking credentials");
        }

        // Check username availability
        if (credentialRepository.existsByUsername(username)){
            throw new IllegalArgumentException("Username is already taken");
        }

        // Hash password using BCrypt
        String passwordHash = passwordEncoder.encode(request.password());

        // Create customer credentials
        CustomerCredential credential = new CustomerCredential(invitation.getCustomer(), username, passwordHash);

        credentialRepository.save(credential);

        // Mark invitation as used
        invitation.markAsUsed();

        invitationRepository.save(invitation);

    }
}
