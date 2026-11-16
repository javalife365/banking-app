package com.ourbank.service;

import com.ourbank.dto.LoginRequest;
import com.ourbank.dto.LoginResponse;
import com.ourbank.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Value("${app.jwt.expiration}")
    private  long jwtExpiration;

    public LoginResponse login(LoginRequest request){
        log.info("login request: {}",request);

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));
        log.info("Authentication: {}", authentication);
        String username = authentication.getName();
        String accessToken = jwtService.generateToken(username);

        log.info("authentication successfully");
        return new LoginResponse(accessToken,"Bearer", jwtExpiration);
    }




}
