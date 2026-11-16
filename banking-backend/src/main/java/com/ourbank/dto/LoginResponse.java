package com.ourbank.dto;

public record LoginResponse (
        String accessToken,
        String tokenType,
        long expiresAt
){
}
