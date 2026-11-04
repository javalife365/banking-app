package com.ourbank.controller;

import com.ourbank.dto.ActivateCustomerRequest;
import com.ourbank.service.CustomerActivationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/customers")
@RequiredArgsConstructor
public class CustomerActivationController {

    private final CustomerActivationService customerActivationService;

    @PostMapping("/activate")
    public ResponseEntity<String> activateCustomer(@Valid @RequestBody ActivateCustomerRequest request){

        customerActivationService.activateCustomer(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Customer online banking account activated successfully.");
    }
}
