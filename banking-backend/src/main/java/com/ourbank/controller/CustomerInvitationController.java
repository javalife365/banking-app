package com.ourbank.controller;

import com.ourbank.dto.InvitationTokenResponse;
import com.ourbank.entity.Customer;
import com.ourbank.repository.CustomerRepository;
import com.ourbank.service.CustomerInvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees/customers")
@RequiredArgsConstructor
public class CustomerInvitationController {

    private final CustomerRepository customerRepository;
    private final CustomerInvitationService customerInvitationService;


    @PostMapping("/{customerId}/invitation")
    public ResponseEntity<InvitationTokenResponse> createInvitation(@PathVariable Long customerId){

        InvitationTokenResponse response = customerInvitationService.createInvitation(customerId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerInvitationService.createInvitation(customerId));

    }

}