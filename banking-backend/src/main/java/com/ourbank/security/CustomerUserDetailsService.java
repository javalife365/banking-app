package com.ourbank.security;

import com.ourbank.entity.CustomerCredential;
import com.ourbank.repository.CustomerCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerCredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomerCredential credential = credentialRepository.findByUsername(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found: "+ username));

        return User.builder()
                .username(credential.getUsername())
                .password(credential.getPasswordHash())
                .roles("CUSTOMER")
                .disabled(!credential.isEnabled())
                .build();

    }
}
