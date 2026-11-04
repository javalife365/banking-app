package com.ourbank.service;

import com.ourbank.dto.InvitationTokenResponse;
import com.ourbank.entity.Customer;
import com.ourbank.entity.CustomerInvitation;
import com.ourbank.repository.CustomerInvitationRepository;
import com.ourbank.repository.CustomerRepository;
import com.ourbank.util.InvitationTokenHasher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@RequiredArgsConstructor
public class CustomerInvitationService {

    private static final int TOKEN_BYTE_LENGTH = 32;
    private static final int EXPIRATION_HOURS = 24;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final CustomerInvitationRepository invitationRepository;
    private final CustomerRepository customerRepository;


    @Transactional
    public InvitationTokenResponse createInvitation(Long customerId){

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Generate secure random token
        byte[] randomBytes = new byte[TOKEN_BYTE_LENGTH];
        SECURE_RANDOM.nextBytes(randomBytes);

        String originalToken = Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);

        //Hash the original token
        String tokenHash = InvitationTokenHasher.hash(originalToken);

        //set expiration time
        LocalDateTime expiresAt = LocalDateTime.now().plusHours(EXPIRATION_HOURS);

        //create invitation entity
        CustomerInvitation invitation = new CustomerInvitation(customer,tokenHash,expiresAt);

        //save only hash token in db
        invitationRepository.save(invitation);

        return new InvitationTokenResponse(originalToken, expiresAt);
    }


}
